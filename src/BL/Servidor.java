package BL;

import java.util.*;

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
        this.listaGestores = new HashMap<>();
        this.robotsDisponiveis = new PriorityQueue<>();
        this.listaRobots = new HashMap<>();
        this.inventario = new Inventario();
        this.gestor_Pedidos = new GestorPedidos();
        this.parking = 1;

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
