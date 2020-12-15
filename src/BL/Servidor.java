package BL;

import java.awt.*;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
                Future<Robot> futureTask = threadpool.submit(() -> recolherPalete(new Palete("yep")));
                robosEmProgresso.add(futureTask);
            }
            //maybe fazer um sleepzito aqui so we don't check robot constantly
            //menu
        }
        //threadpool.shutdown();
    }

    public Servidor(){//falta iniciar  robosEmProgresso threadpool
        this.listaGestores = new HashMap<>();
        this.robotsDisponiveis = new PriorityQueue<>();
        this.listaRobots = new HashMap<>();
        this.inventario = new Inventario();
        this.gestor_Pedidos = new GestorPedidos();
        this.parking = 1;
        this.robosEmProgresso = new ArrayList<>();
        this.threadpool = Executors.newCachedThreadPool();

        tamanho_lateral = 40;
        tamanho_altura = 20;
        for(int i = 0; i < tamanho_lateral; i++)
            for(int j = 0; j < tamanho_altura; j++)
                mapa[i][j] = 0;
    }

    public Servidor(String namefile){
        // cenas
    }

    public Map<String, Gestor> getListaGestores() {
        Map<String, Gestor> listaGestoresReturn = new TreeMap<>();
        for(Gestor g : this.listaGestores.values()){
            listaGestoresReturn.putIfAbsent(g.getCodeID(), g.clone());
        }
        return listaGestoresReturn;
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

    public Integer getParking() {
        return parking;
    }

    public void removeGestor (String codID){
        listaGestores.remove(codID);
    }

    public void addGestor (String codID, String nome){
        listaGestores.put(codID, new Gestor(nome, codID, codID+"12345", true));
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
