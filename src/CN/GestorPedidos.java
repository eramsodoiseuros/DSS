package CN;

import java.util.ArrayList;
import java.util.HashMap;

public class GestorPedidos {
    private HashMap<String, Requisicao> requisicoes_FEITAS;
    private HashMap<String, Entrega> entrega_FEITAS;

    private HashMap<String, Requisicao> requisicoes_ATIVAS;
    private HashMap<String, Entrega> entrega_ATIVAS;

    public ArrayList<Requisicao> listaRequisicoes_FEITAS(){
        return new ArrayList<>(requisicoes_FEITAS.values());
    }

    public ArrayList<Requisicao> listaRequisicoes_ATIVAS(){
        return new ArrayList<>(requisicoes_ATIVAS.values());
    }

    public ArrayList<Entrega> listaEntrega_FEITAS(){
        return new ArrayList<>(entrega_FEITAS.values());
    }

    public ArrayList<Entrega> listaEntrega_ATIVAS(){
        return new ArrayList<>(entrega_ATIVAS.values());
    }

    public void removeRF(String codID){
        this.requisicoes_FEITAS.remove(codID);
    }

    public void removeRA(String codID){
        this.requisicoes_ATIVAS.remove(codID);
    }

    public void removeEF(String codID){
        this.entrega_FEITAS.remove(codID);
    }

    public void removeEA(String codID){
        this.entrega_ATIVAS.remove(codID);
    }

    public void addRF(Requisicao r){
        this.requisicoes_FEITAS.putIfAbsent(r.getCodID(), r);
    }

    public void addRA(Requisicao r){
        this.requisicoes_ATIVAS.putIfAbsent(r.getCodID(), r);
    }

    public void addEF(Entrega e){
        this.entrega_FEITAS.putIfAbsent(e.getCodID(), e);
    }

    public void addEA(Entrega e){
        this.entrega_ATIVAS.putIfAbsent(e.getCodID(), e);
    }

}
