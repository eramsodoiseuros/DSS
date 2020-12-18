package BL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GestorPedidos {

    private HashMap<String, Requisicao> requisicoes;
    private HashMap<String, Entrega> entrega;

    private HashMap<String, Requisicao> requisicoes_ATIVAS;
    private HashMap<String, Entrega> entrega_ATIVAS;

    private HashMap<String, Requisicao> requisicoes_FEITAS;
    private HashMap<String, Entrega> entrega_FEITAS;

    protected GestorPedidos(){
        this.requisicoes = new HashMap<>();
        this.entrega = new HashMap<>();
        this.requisicoes_FEITAS = new HashMap<>();
        this.entrega_FEITAS = new HashMap<>();
        this.requisicoes_ATIVAS = new HashMap<>();
        this.entrega_ATIVAS = new HashMap<>();
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

    protected HashMap<String,Requisicao> listaRequisicoes_FEITAS_MAP(){
        return new HashMap<>(requisicoes_FEITAS);
    }

    protected HashMap<String,Requisicao> listaRequisicoes_Ativas_MAP(){
        return new HashMap<>(requisicoes_ATIVAS);
    }

    protected HashMap<String,Entrega> listaEntrga_FEITAS_MAP(){
        return new HashMap<>(entrega_FEITAS);
    }

    protected HashMap<String,Entrega> listaEntrga_Ativas_MAP(){
        return new HashMap<>(entrega_ATIVAS);
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

    @Override
    public String toString() {
        return "GestorPedidos{" +
                "requisicoes_FEITAS=" + requisicoes_FEITAS +
                ", entrega_FEITAS=" + entrega_FEITAS +
                ", requisicoes_ATIVAS=" + requisicoes_ATIVAS +
                ", entrega_ATIVAS=" + entrega_ATIVAS +
                '}';
    }

    public String EntToStringAtivas(Entrega e){
        return "Entrega: " + e.getCodID() + " - " + e.toStingConteudoAtivas();
    }

    public String EntToStringFeitas(Entrega e){
        return "Entrega: " + e.getCodID() + " - " + e.toStringConteudoFeitas();
    }

    public String ReqToStringAtivas(Requisicao r){
        return "Requisição: " + r.getCodID() + " - " + r.toStingConteudoAtivas();
    }

    public String ReqToStringFeitas(Requisicao r){
        return "Requisição: " + r.getCodID() + " - " + r.toStringConteudoFeitas();
    }

    public void addEntrega(Entrega e) {
        entrega.putIfAbsent(e.codeID,e);
    }

    public void addRequisicao(Requisicao r) {
        requisicoes.putIfAbsent(r.codeID, r);
    }

    public void removeEntrega(Entrega e) {
        entrega.remove(e);
    }

    public void removeRequisicao(Requisicao r) {
        requisicoes.remove(r);
    }

    public ArrayList<Requisicao> listaRequisicoes() {
        return new ArrayList<>(requisicoes.values());
    }

    public ArrayList<Entrega> listaEntregas() {
        return new ArrayList<>(entrega.values());
    }

    public boolean searchEA(String codID) {
        return entrega_ATIVAS.containsKey(codID);
    }

    public boolean searchEF(String codID) {
        return entrega_FEITAS.containsKey(codID);
    }
}
