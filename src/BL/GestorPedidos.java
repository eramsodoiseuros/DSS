package BL;

import DL.EntregaDAO;
import DL.RequisicaoDAO;
import UI.UI;

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

    protected void removeRF(String codID){
        this.requisicoes_FEITAS.remove(codID);
    }
    protected void removeRA(String codID){
        this.requisicoes_FEITAS.putIfAbsent(codID, requisicoes_ATIVAS.get(codID));
        UI.notifica("NOTIFICAÇÃO DE MUDANÇA DE ESTADO: " + "R->" + codID + "    ||      C->" + requisicoes_FEITAS.get(codID).conteudo + " de momento está FEITA.");
        RequisicaoDAO.getInstance().put(requisicoes_ATIVAS.get(codID));
        this.requisicoes_ATIVAS.remove(codID);
    }
    protected void removeEF(String codID){
        this.entrega_FEITAS.remove(codID);
    }
    protected void removeEA(String codID){
        this.entrega_FEITAS.putIfAbsent(codID, entrega_ATIVAS.get(codID));
        UI.notifica("NOTIFICAÇÃO DE MUDANÇA DE ESTADO: " + "E->" + codID + "    ||      C->" + entrega_FEITAS.get(codID).conteudo + " de momento está FEITA.");
        EntregaDAO.getInstance().put(entrega_ATIVAS.get(codID));
        this.entrega_ATIVAS.remove(codID);
    }
    protected void addEF(Entrega e) {
        entrega_FEITAS.putIfAbsent(e.codeID,e);
    }
    protected void addRF(Requisicao r) {
        requisicoes_FEITAS.putIfAbsent(r.codeID,r);
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

    protected void addEntrega(Entrega e) {
        System.out.println("NOTIFICAÇÃO DE MUDANÇA DE ESTADO: " + "E->" + e.codeID + "\\C->" + e.conteudo + " de momento está NÃO PROCESSADA.");
        entrega.putIfAbsent(e.codeID,e);
    }
    protected void addRequisicao(Requisicao r) {
        System.out.println("NOTIFICAÇÃO DE MUDANÇA DE ESTADO: " + "R->" + r.codeID + "\\C->" + r.conteudo + " de momento está NÃO PROCESSADA.");
        requisicoes.putIfAbsent(r.codeID, r);
    }
    protected void removeEntrega(String e) {
        this.entrega_ATIVAS.putIfAbsent(e, entrega.get(e));
        UI.notifica("NOTIFICAÇÃO DE MUDANÇA DE ESTADO: " + "E->" + e + "    ||      C->" + entrega.get(e).conteudo + " de momento está ATIVA.");
        this.entrega.remove(e);
    }
    protected void removeRequisicao(String r) {
        System.out.println(requisicoes_ATIVAS);
        this.requisicoes_ATIVAS.putIfAbsent(r, requisicoes.get(r));
        System.out.println(requisicoes_ATIVAS);
        UI.notifica("NOTIFICAÇÃO DE MUDANÇA DE ESTADO: " + "R->" + r + "    ||      C->" + requisicoes.get(r).conteudo + " de momento está ATIVA.");
        this.requisicoes.remove(r);
    }

    protected ArrayList<Requisicao> listaRequisicoes() {
        return new ArrayList<>(requisicoes.values());
    }
    protected ArrayList<Entrega> listaEntregas() {
        return new ArrayList<>(entrega.values());
    }

    protected boolean searchEA(String codID) {
        return entrega_ATIVAS.containsKey(codID);
    }
    protected boolean searchRA(String s) {
        return requisicoes_ATIVAS.containsKey(s);
    }
    protected boolean searchEF(String codID) {
        return entrega_FEITAS.containsKey(codID);
    }
    protected boolean searchRF(String s) {
        return requisicoes_FEITAS.containsKey(s);
    }
}
