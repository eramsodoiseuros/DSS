package BL;

import java.util.ArrayList;
import java.util.HashMap;

public class GestorPedidos {
    private HashMap<String, Requisicao> requisicoes_FEITAS;
    private HashMap<String, Entrega> entrega_FEITAS;

    private HashMap<String, Requisicao> requisicoes_ATIVAS;
    private HashMap<String, Entrega> entrega_ATIVAS;

    protected GestorPedidos(){
        this.requisicoes_FEITAS = new HashMap<>();
        this.entrega_FEITAS = new HashMap<>();
        this.requisicoes_ATIVAS = new HashMap<>();
        this.entrega_ATIVAS = new HashMap<>();
    }

    protected GestorPedidos(HashMap<String, Requisicao> requisicoes_FEITAS, HashMap<String, Entrega> entrega_FEITAS, HashMap<String, Requisicao> requisicoes_ATIVAS, HashMap<String, Entrega> entrega_ATIVAS) {
        this.requisicoes_FEITAS = requisicoes_FEITAS;
        this.entrega_FEITAS = entrega_FEITAS;
        this.requisicoes_ATIVAS = requisicoes_ATIVAS;
        this.entrega_ATIVAS = entrega_ATIVAS;
    }

    protected ArrayList<Requisicao> listaRequisicoes_FEITAS(){
        return new ArrayList<>(requisicoes_FEITAS.values());
    }

    protected ArrayList<Requisicao> listaRequisicoes_ATIVAS(){
        return new ArrayList<>(requisicoes_ATIVAS.values());
    }

    protected ArrayList<Entrega> listaEntrega_FEITAS(){
        return new ArrayList<>(entrega_FEITAS.values());
    }

    protected ArrayList<Entrega> listaEntrega_ATIVAS(){
        return new ArrayList<>(entrega_ATIVAS.values());
    }

    protected void removeRF(String codID){
        this.requisicoes_FEITAS.remove(codID);
    }

    protected void removeRA(String codID){
        this.requisicoes_ATIVAS.remove(codID);
    }

    protected void removeEF(String codID){
        this.entrega_FEITAS.remove(codID);
    }

    protected void removeEA(String codID){
        this.entrega_ATIVAS.remove(codID);
    }

    protected void addRF(Requisicao r){
        this.requisicoes_FEITAS.putIfAbsent(r.getCodID(), r);
    }

    protected void addRA(Requisicao r){
        this.requisicoes_ATIVAS.putIfAbsent(r.getCodID(), r);
    }

    protected void addEF(Entrega e){
        this.entrega_FEITAS.putIfAbsent(e.getCodID(), e);
    }

    protected void addEA(Entrega e){
        this.entrega_ATIVAS.putIfAbsent(e.getCodID(), e);
    }

    // NOT WELL DONE BTW
    protected GestorPedidos Clone() {
        return new GestorPedidos(this.requisicoes_FEITAS, this.entrega_FEITAS, this.requisicoes_ATIVAS, this.entrega_ATIVAS);
    }

    @Override
    public String toString() {
        return "GestorPedidos{" +
                "requisicoes_FEITAS=" + requisicoes_FEITAS +
                ", entrega_FEITAS=" + entrega_FEITAS +
                ", requisicoes_ATIVAS=" + requisicoes_ATIVAS +
                ", entrega_ATIVAS=" + entrega_ATIVAS +
                '}';
    }
}
