package CN;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

public class Servidor {
    private Map<String, Gestor> listaGestores;
    private Queue<Robot> robotsDisponiveis;

    private Inventario inventario;
    private GestorPedidos gestor_Pedidos;

    private Integer parking;

    public Map<String, Gestor> getListaGestores() {
        Map<String, Gestor> listaGestoresReturn = new TreeMap<String, Gestor>();
        for(Gestor g : this.listaGestores.values()){
            listaGestoresReturn.putIfAbsent(g.getCodeId(), g.clone());
        }
        return listaGestoresReturn;
    }

    public Queue<Robot> getRobotsDisponiveis() {
        Queue robosDisponiveisReturn = new PriorityQueue<Robot>();
        for(Robot r : robotsDisponiveis){
            robosDisponiveisReturn.add(r.clone());
        }
        return robosDisponiveisReturn;
    }

    public Inventario getInventario() {
        return inventario.clone();
    }

    public GestorPedidos getGestor_Pedidos() {
        return gestor_Pedidos.clone();
    }

    public Integer getParking() {
        return parking;
    }
}
