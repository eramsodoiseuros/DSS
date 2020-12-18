package BL;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import DL.*;

public class Servidor {
    private Map<String, Gestor> listaGestores;
    private Queue<Robot> robotsDisponiveis;
    private Map<String, Robot> listaRobots;
    private Inventario inventario;
    private GestorPedidos gestor_Pedidos;
    private Integer parking;
    private Integer[][] mapa; // -1 nao pode, 0 onde pode, 1 onde esta, prateleiras vazias 2, prateleiras cheias 3, 4-10 quantidade de stuff la
    private int tamanho_lateral;
    private int tamanho_altura;
    private ExecutorService threadpool;
    private ArrayList<Future<Robot>> robosEmProgresso;

    public void run() throws ExecutionException, InterruptedException {
        int i = 2;
        boolean needToWork = true;
        while (needToWork) {
            //ver se algum dos robôs terminou o que está em progresso
            robosEmProgresso.removeIf(Future::isDone);

            if (i > 0){
                while (inventario.size() > 0 && robotsDisponiveis.size() > 0){
                    for (Palete p: inventario.values()){
                        if (!p.isArmazenado() && mapa[1][0] == 0) {
                            Future<Robot> futureTask = threadpool.submit(() -> recolherPalete(p,this.robotsDisponiveis.poll() ));
                            inventario.remove(p.getCodID());
                            robosEmProgresso.add(futureTask);
                            i--;
                            break;
                        }
                    }
                }
            }
            if (robosEmProgresso.size() == 0) needToWork = false;
            //maybe fazer um sleepzito aqui so we don't check robot constantly
            //menu
        }
        threadpool.shutdown();
    }


    public Servidor(){
        this.listaGestores = new HashMap<String, Gestor>();
        this.robotsDisponiveis = new PriorityQueue<Robot>();
        this.listaRobots = new HashMap<String, Robot>();
        this.inventario = new Inventario();
        this.gestor_Pedidos = new GestorPedidos();
        this.parking = 1;
        this.robosEmProgresso = new ArrayList<>();
        this.threadpool = Executors.newCachedThreadPool();

        tamanho_lateral = 8;
        tamanho_altura = 6;
        this.mapa = new Integer[tamanho_altura][tamanho_lateral];
        for(int i = 0; i < tamanho_altura; i++)
            for(int j = 0; j < tamanho_lateral; j++)
                mapa[i][j] = 0;

        for(int a = 2; a <=6;a++ ) {
            mapa[0][a] = 4;
            mapa[5][a] = 4;
        }

        for (Gestor g : GestorDAO.getInstance().values()) {
            listaGestores.put(g.getCodeID(), g);
        }

        for (Robot r : RobotsDAO.getInstance().values2()) {
            listaRobots.put(r.getCodeID(), r);
        }

        /*
        this.robotsDisponiveis = RobotsDAO.getInstance().values();
        */

        for (Palete p : InventarioDAO.getInstance().values()) {
            inventario.add(p);
        }

        for (Entrega e : EntregaDAO.getInstance().values()) {
            gestor_Pedidos.addEF(e);
        }

        for (Requisicao r : RequisicaoDAO.getInstance().values()) {
            gestor_Pedidos.addRF(r);
        }

    }


    public Map<String, Gestor> getListaGestores() {
        return new HashMap<String, Gestor>(listaGestores);
    }

    public Queue getRobotsDisponiveis() {
        Queue robosDisponiveisReturn = new PriorityQueue<Robot>();
        for(Robot r : robotsDisponiveis){
            robosDisponiveisReturn.add(r.clone());
        }
        return robosDisponiveisReturn;
    }

    public HashMap<String, Palete> getInventario() {
        return inventario.getInventario();
    }

    public GestorPedidos getGestor_Pedidos() {
        return gestor_Pedidos.Clone();
    }

    public List<String> getEntAtivas(){

         ArrayList<Entrega> el = getGestor_Pedidos().listaEntrega_ATIVAS();
         List<String> s = new ArrayList<>();
         for (Entrega e : el){
            s.add(getGestor_Pedidos().EntToStringAtivas(e));
         }
         return s;
    }

    public List<String> getEntFeitas(){

        ArrayList<Entrega> el = getGestor_Pedidos().listaEntrega_FEITAS();
        List<String> s = new ArrayList<>();
        for (Entrega e : el){
            s.add(getGestor_Pedidos().EntToStringFeitas(e));
        }
        return s;
    }

    public List<String> getReqFeitas(){

        ArrayList<Requisicao> rl = getGestor_Pedidos().listaRequisicoes_FEITAS();
        List<String> s = new ArrayList<>();
        for (Requisicao r : rl){
            s.add(getGestor_Pedidos().ReqToStringFeitas(r));
        }
        return s;
    }

    public List<String> getReqAtivas(){

        ArrayList<Requisicao> rl = getGestor_Pedidos().listaRequisicoes_ATIVAS();
        List<String> s = new ArrayList<>();
        for (Requisicao r : rl){
            s.add(getGestor_Pedidos().ReqToStringAtivas(r));
        }
        return s;
    }

    public Integer getParking() {
        return parking;
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

    //so pode ser chamado se ouver espaço
    public Robot recolherPalete(Palete p, Robot robot) {

        Point destino = getEspacoLivre();
        System.out.println("destino = "+ destino);
        System.out.println(robot.getCodeID());
        System.out.println(p.getCodID());
        boolean iniciado = false;
        boolean entregou = false;

        while (!iniciado) iniciado = robot.startWork(this.mapa);

        robotsDisponiveis.remove(robot);

        while (!entregou) entregou = robot.andaParaPalete(mapa, destino.x, destino.y);
        System.out.println(robot.getCodeID()+" acabou a entrega e vai voltar pa casa");
        p.setArmazenado(true);
        p.setLocalizacao(destino);
        inventario.add(p);
        mapa[destino.x][destino.y]++;
        if (mapa[destino.x][destino.y] == 10) mapa[destino.x][destino.y] = 3;

        recolherRobo(robot);

        return robot;
    }

    public Robot entregarPalete (Palete p, Robot r){
        Point destino = p.getLocalizacao();
        boolean temPalete = false;
        boolean iniciado = false;
        boolean entregouPalete = false;

        while (!iniciado) iniciado = r.startWork(this.mapa);
        robotsDisponiveis.remove(r);

        while(!temPalete) temPalete = r.andaParaPalete(mapa, destino.x, destino.y);
        inventario.remove(p.getCodID());

        while(!entregouPalete) entregouPalete = r.entregaPalete(mapa);

        recolherRobo(r);

        return r;
    }

    //robo retorna a posição defaul e é desligado
    public void recolherRobo(Robot robot){
        boolean voltou = false;
        while (!voltou) voltou = robot.takeBreak(mapa);
        robotsDisponiveis.add(robot);
    }

    public Point getEspacoLivre(){
        int i = 2;
        Point pointReturn = new Point();

        for(; i<7; i++){
            if ((mapa[0][i] > 3 && mapa[0][i] <10) || mapa[0][i] == 2) {
                pointReturn.setLocation(0,i);
                return pointReturn;
            }
        }

        for(i = 2; i<7; i++){
            if ((mapa[0][i] > 3 && mapa[0][i] <10) || mapa[0][i] == 2) {
                pointReturn.setLocation(5,i);
                return pointReturn;
            }
        }

        return pointReturn;
    }


    public void removeRF(String codID){
        this.gestor_Pedidos.removeRF(codID);
        RequisicaoDAO.getInstance().remove(codID);
    }

    public void removeRA(String codID){
        this.gestor_Pedidos.removeRA(codID);
        RequisicaoDAO.getInstance().remove(codID);

    }

    public void removeEF(String codID){
        this.gestor_Pedidos.removeEF(codID);
        EntregaDAO.getInstance().remove(codID);
    }

    public void removeEA(String codID){
        this.gestor_Pedidos.removeEA(codID);
        EntregaDAO.getInstance().remove(codID);
    }

    public void addRF(Requisicao r){
        this.gestor_Pedidos.addRF(r);
        RequisicaoDAO.getInstance().put(r);
    }

    public void addRA(Requisicao r){
        this.gestor_Pedidos.addRA(r);
        RequisicaoDAO.getInstance().put(r);
    }

    public void addEF(Entrega e){
        this.gestor_Pedidos.addEF(e);
        EntregaDAO.getInstance().put(e);
    }

    public void addEA(Entrega e){
        this.gestor_Pedidos.addEA(e);
        EntregaDAO.getInstance().put(e);
    }

    public HashMap<String,Requisicao> getRF(){
        return getGestor_Pedidos().listaRequisicoes_FEITAS_MAP();
    }

    public HashMap<String,Requisicao> getRA(){
        return getGestor_Pedidos().listaRequisicoes_Ativas_MAP();
    }

    public HashMap<String,Entrega> getEF(){
        return getGestor_Pedidos().listaEntrga_FEITAS_MAP();
    }

    public HashMap<String,Entrega> getEA(){
        return getGestor_Pedidos().listaEntrga_Ativas_MAP();
    }

    public boolean isParkingAvailable(){
        return parking == 0;
    }

    public void minusSpot(){
        parking--;
    }

    public void plusSpot(){
        parking++;
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
        while(inventario.getInventario().containsKey(s)){
            s = "p" + t;
            t++;
        }

        return new Palete(s,c);
    }
}
