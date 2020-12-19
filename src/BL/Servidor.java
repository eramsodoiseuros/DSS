package BL;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

import DL.*;
import UI.UI;

public class Servidor {
    private Map<String, Gestor> listaGestores;
    private Map<String, Robot> robots_r; // par
    private Map<String, Robot> robots_e; // impar
    private Inventario inventario;
    private GestorPedidos gestor_Pedidos;
    private Integer parking;
    private Integer[][] mapa; // -1 nao pode, 0 onde pode, 1 onde esta, prateleiras vazias 2, prateleiras cheias 3, 4-10 quantidade de stuff la
    private int tamanho_lateral;
    private int tamanho_altura;
    private ExecutorService threadpool;
    private ArrayList<Future<Robot>> robosEmProgresso;

    public Servidor(){
        this.listaGestores = new HashMap<>();
        this.robots_r = new HashMap<>();
        this.robots_e = new HashMap<>();
        this.inventario = new Inventario();
        this.gestor_Pedidos = new GestorPedidos();
        this.parking = 2;
        this.robosEmProgresso = new ArrayList<>();
        this.threadpool = Executors.newCachedThreadPool();

        tamanho_lateral = 8;
        tamanho_altura = 6;
        this.mapa = new Integer[tamanho_altura][tamanho_lateral];
        for(int i = 0; i < tamanho_altura; i++)
            for(int j = 0; j < tamanho_lateral; j++)
                mapa[i][j] = 0;

        for(int a = 2; a <=6;a++ ) {
            mapa[0][a] = 2;
            mapa[5][a] = 2;
        }

        for (Gestor g : GestorDAO.getInstance().values()) {
            listaGestores.put(g.getCodeID(), g);
        }

        for (Robot r : RobotsDAO.getInstance().values()) {
            if( Integer.parseInt(r.getCodeID().substring(1,r.getCodeID().length())) % 2 != 0 ){
                robots_e.put(r.getCodeID(), r);
            }

            if( Integer.parseInt(r.getCodeID().substring(1,r.getCodeID().length())) % 2 == 0 ){
                robots_r.put(r.getCodeID(), r);
            }
        }

        for (Palete p : InventarioDAO.getInstance().values()) {
            inventario.add(p);
            addToMap(p);
        }

        for (Entrega e : EntregaDAO.getInstance().values()) {
            gestor_Pedidos.addEF(e);
        }

        for (Requisicao r : RequisicaoDAO.getInstance().values()) {
            gestor_Pedidos.addRF(r);
        }

    }

    private void addToMap(Palete p) {
        if(p.isArmazenado()){
            mapa[p.getLocalizacao().x][p.getLocalizacao().y]++;
        }
    }

    private Robot getAvailable(Map<String, Robot> robots){
        boolean b = true;
        while (b){
            for (Robot r1 : robots.values()) {
                if (!r1.getAtivo()) {
                    return r1;
                }
            }
        }

        return new Robot();
    }
    public List<Robot> RobotsDisponiveis(Map<String, Robot> robots) {
        ArrayList<Robot> disponiveis = new ArrayList<>();
        for(Robot r : robots.values()){
            disponiveis.add(r.clone());
        }
        return disponiveis;
    }
    public Point getEspacoLivre(){
        int i = 2;
        Point pointReturn = new Point();

        for(; i < 7; i++){
            if (mapa[0][i] < 10 && mapa[0][i] >= 2) {
                pointReturn.setLocation(0,i);
                return pointReturn;
            }
        }

        for(i = 2; i < 7; i++){
            if (mapa[5][i] < 10 && mapa[5][i] >= 2) {
                pointReturn.setLocation(5,i);
                return pointReturn;
            }
        }

        return pointReturn;
    }
    public Robot entregaPalete(Palete p, Robot robot) {

        Point destino = getEspacoLivre();

        boolean iniciado = false;
        boolean entregou = false;

        while (!iniciado) iniciado = robot.startWork(this.mapa);
        while (!entregou) entregou = robot.andaParaPalete(mapa, destino.x, destino.y);

        UI.notifica("O robot: " + robot.getCodeID() + " acabou de movimentar a Palete " + p.getCodID() + " e vai cessar atividade.");

        p.setArmazenado(true);
        p.setLocalizacao(destino);
        if(mapa[destino.x][destino.y] > 2) mapa[destino.x][destino.y]++;
        recolherRobo(robot);

        return robot;
    }
    public Robot requisicaoPalete (Palete p, Robot r){
        Point destino = p.getLocalizacao();
        boolean temPalete = false;
        boolean iniciado = false;
        boolean entregouPalete = false;

        while (!iniciado) iniciado = r.startWork(this.mapa);
        while(!temPalete) temPalete = r.andaParaPalete(mapa, destino.x, destino.y);

        if(mapa[destino.x][destino.y] > 2)mapa[destino.x][destino.y]--;
        inventario.remove(p.getCodID());

        while(!entregouPalete) entregouPalete = r.entregaPalete(mapa);
        UI.notifica("O robot: " + r.getCodeID() + " acabou de movimentar a Palete " + p.getCodID() + " e vai cessar atividade.");
        recolherRobo(r);

        return r;
    }

    public void recolherRobo(Robot robot){
        boolean voltou = false;
        while (!voltou) voltou = robot.takeBreak(mapa);
    }

    public void giveWork(Entrega e){
        UI.print_mapa(mapa, 6, 8);
        Robot wallie = getAvailable(robots_e);
        Palete p = e.conteudo;
        UI.notifica("O Robot " + wallie.getCodeID()
                + " vai agora iniciar a recolha da Palete " + p.getCodID() + " na localização (0,1).");

        entregaPalete(p, wallie); // vai de (0,1) a (x,y)
        InventarioDAO.getInstance().put(p);
        gestor_Pedidos.removeEA(e.codeID);
        UI.print_mapa(mapa, 6, 8);
    }

    public void giveWork(Requisicao r){
        UI.print_mapa(mapa, 6, 8);
        Robot wallie = getAvailable(robots_r);
        Palete p = r.conteudo;
        UI.notifica("O Robot " + wallie.getCodeID() + " vai agora iniciar a recolha da Palete "
                + p.getCodID() + " na localização (" + p.getLocalizacao().x + ", " + p.getLocalizacao().y + ").");

        requisicaoPalete (p, wallie); // vai de (x,y) a (7,2)
        InventarioDAO.getInstance().remove(p.getCodID());
        gestor_Pedidos.removeRA(r.codeID);
        UI.print_mapa(mapa, 6, 8);
    }

    public List<String> getEntAtivas(){
         ArrayList<Entrega> el = gestor_Pedidos.listaEntrega_ATIVAS();
         List<String> s = new ArrayList<>();
         for (Entrega e : el)
            s.add(gestor_Pedidos.EntToStringAtivas(e));
         return s;
    }
    public List<String> getEntFeitas(){
        ArrayList<Entrega> el = gestor_Pedidos.listaEntrega_FEITAS();
        List<String> s = new ArrayList<>();
        for (Entrega e : el)
            s.add(gestor_Pedidos.EntToStringFeitas(e));
        return s;
    }
    public List<String> getReqFeitas(){
        ArrayList<Requisicao> rl = gestor_Pedidos.listaRequisicoes_FEITAS();
        List<String> s = new ArrayList<>();
        for (Requisicao r : rl)
            s.add(gestor_Pedidos.ReqToStringFeitas(r));
        return s;
    }
    public List<String> getReqAtivas(){
        ArrayList<Requisicao> rl = gestor_Pedidos.listaRequisicoes_ATIVAS();
        List<String> s = new ArrayList<>();
        for (Requisicao r : rl)
            s.add(gestor_Pedidos.ReqToStringAtivas(r));
        return s;
    }

    public void removeGestor (String codID){
        listaGestores.remove(codID);
        GestorDAO.getInstance().remove(codID);
    }
    public void addGestor (String codID, String nome, String pwd){
        Gestor g = new Gestor(nome, pwd, codID, false);
        listaGestores.put(codID, g);
        GestorDAO.getInstance().put(g);
    }

    public void removeEntrega(String e){
        this.gestor_Pedidos.removeEntrega(e);
    }
    public void removeRequisicao(String r){
        this.gestor_Pedidos.removeRequisicao(r);
    }

    public void addEntrega(Entrega e){
        this.gestor_Pedidos.addEntrega(e);
    }
    public void addRequisicao(Requisicao r){
        this.gestor_Pedidos.addRequisicao(r);
    }

    public boolean isParkingAvailable(){
        return parking != 0;
    }
    public void minusSpot(){
        parking--;
    }
    public void plusSpot(){
        parking++;
    }
    public Integer getParking() {
        return parking;
    }

    public void offline(String codID) {
        listaGestores.get(codID).setOnline(false);
    }
    public void online(String codID) {
        listaGestores.get(codID).setOnline(true);
    }

    public Palete criaPalete (String c){
        int t = 1;
        String s = "p1";
        while(inventario.contains(s)){
            s = "p" + t;
            t++;
        }
        Palete p = new Palete(s,c);
        inventario.add(p);
        return p;
    }

    public List<String> inventario (){
        List<String> s = new ArrayList<>();
        for(Palete p : inventario.values()){
            s.add(p.toStringFeitas());
        }
        return s;
    }
    public List<String> listagem (){
        List<String> s = new ArrayList<>();
        for(Palete p : inventario.values()){
            s.add(p.toStringListagem());
        }
        return s;
    }
    public List<String> listar_entregas() {
        ArrayList<Entrega> rl = gestor_Pedidos.listaEntregas();
        List<String> s = new ArrayList<>();
        for (Entrega r : rl){
            s.add(gestor_Pedidos.EntToStringAtivas(r));
        }
        return s;
    }
    public List<String> listar_requisicoes() {
        ArrayList<Requisicao> rl = gestor_Pedidos.listaRequisicoes();
        List<String> s = new ArrayList<>();
        for (Requisicao r : rl){
            s.add(gestor_Pedidos.ReqToStringAtivas(r));
        }
        return s;
    }

    public Palete search(String s) {
        return inventario.search(s);
    }

    public boolean searchEA(String codID) {
        return gestor_Pedidos.searchEA(codID);
    }
    public boolean searchEF(String codID) {
        return gestor_Pedidos.searchEF(codID);
    }
    public boolean searchRA(String s) {
        return gestor_Pedidos.searchRA(s);
    }
    public boolean searchRF(String s) {
        return gestor_Pedidos.searchRF(s);
    }

    public boolean containsGestor(String codID) {
        return listaGestores.containsKey(codID);
    }
    public String getGP(String s) {
        return listaGestores.get(s).getPassword();
    }
    public boolean getGO(String s) {
        return listaGestores.get(s).getOnline();
    }
    public Gestor getGestor(String c) {
        return listaGestores.get(c);
    }

    public Requisicao getRA(String s) {
        return gestor_Pedidos.getRA(s);
    }
    public Entrega getEA(String s) {
        return gestor_Pedidos.getEA(s);
    }
}
