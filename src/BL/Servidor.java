package BL;

import java.util.*;

import DL.*;

public class Servidor {
    private Map<String, Gestor> listaGestores;
    private Queue<Robot> robotsDisponiveis;
    private Map<String, Robot> listaRobots;
    private Inventario inventario;
    private GestorPedidos gestor_Pedidos;
    private Integer parking;
    private Integer[][] mapa; // -1 nao pode, 0 onde pode, 1 onde esta, prateleiras vazias 2, prateleiras cheias 3
    private int tamanho_lateral;
    private int tamanho_altura;


    public Servidor(){
        this.listaGestores = new HashMap<String, Gestor>();
        this.robotsDisponiveis = new PriorityQueue<Robot>();
        this.listaRobots = new HashMap<String, Robot>();
        this.inventario = new Inventario();
        this.gestor_Pedidos = new GestorPedidos();
        this.parking = 1;

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

        this.robotsDisponiveis = RobotsDAO.getInstance().values();

        for (Palete p : InventarioDAO.getInstance().values()) {
            inventario.add(p);
        }

        for (Entrega e : EntregaDAO.getInstance().values()) {
            gestor_Pedidos.addEA(e);
        }

        for (Requisicao r : RequisicaoDAO.getInstance().values()) {
            gestor_Pedidos.addRA(r);
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

    public Integer getParking() {
        return parking;
    }

    public void removeGestor (String codID){
        listaGestores.remove(codID);
    }

    public void addGestor (String codID, String nome){
        listaGestores.put(codID, new Gestor(nome, codID, codID+"123", false));
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
