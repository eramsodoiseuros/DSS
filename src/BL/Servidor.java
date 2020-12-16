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

    private void run() throws ExecutionException, InterruptedException {
        boolean novaRecolha = true;
        while (true) {
            //ver se algum dos robôs terminou o que está em progresso
            for (Future<Robot> r : robosEmProgresso) {
                if (r.isDone()) {
                    //Processa o R, seja como for
                    Robot res = r.get();
                    if (res.getAtivo()) recolherRobo(res);
                    else robosEmProgresso.remove(r);
                    //terminaPalete
                }
            }

            if (novaRecolha && robosEmProgresso.size()<3){
                Future<Robot> futureTask = threadpool.submit(() -> recolherPalete(new Palete()));
                robosEmProgresso.add(futureTask);
            }
            //maybe fazer um sleepzito aqui so we don't check robot constantly
            //menu
        }
        //threadpool.shutdown();
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

        tamanho_lateral = 40;
        tamanho_altura = 20;
        int[][] mapa = new int[tamanho_altura][tamanho_lateral];
        for(int i = 0; i < tamanho_altura; i++)
            for(int j = 0; j < tamanho_lateral; j++)
                mapa[i][j] = 0;


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
    }

    public void addGestor (String codID, String nome, String pwd){
        Gestor g = new Gestor(nome, codID, pwd, false);
        listaGestores.put(codID, g);
        GestorDAO.getInstance().put(g);
    }

    public Integer manageRobo(Robot robot){
        Integer caso = 1;
        if (caso == 1){
            return caso++;
        }
        return caso;
    }

    //so pode ser chamado se ouver espaço
    //corre do momento em que o robot é ligado até guardar a palete
    public Robot recolherPalete(Palete p) {

        Point destino = getEspacoLivre();
        Robot robot = robotsDisponiveis.element();
        boolean iniciado = false;
        boolean entregou = false;

        while (iniciado) iniciado = robot.startWork(mapa);

        robotsDisponiveis.remove(robot);

        while (entregou) entregou = robot.andaParaPalete(mapa, destino.x, destino.y);

        p.setArmazenado(true);
        p.setLocalizacao(destino);
        inventario.add(p);
        mapa[destino.x][destino.y]++;
        if (mapa[destino.x][destino.y] == 10) mapa[destino.x][destino.y] = 3;

        return robot;
    }

    //robo retorna a posição defaul e é desligado
    public void recolherRobo(Robot robot){
        boolean voltou = false;
        while (voltou) voltou = robot.takeBreak(mapa);
        robotsDisponiveis.add(robot);
    }

    public Point getEspacoLivre(){
        int i = 0;
        Point pointReturn = new Point();

        for(; mapa[0][i] >= 4 || mapa[0][i] <= 9; i++);
        if (i < 7) {
            pointReturn.setLocation(0,i);
            return pointReturn;
        }

        for(i = 0; mapa[0][i] >= 4 || mapa[0][i] <= 9; i++);
        if (i < 7) {
            pointReturn.setLocation(0,i);
            return pointReturn;
        }
        return pointReturn;
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
}
