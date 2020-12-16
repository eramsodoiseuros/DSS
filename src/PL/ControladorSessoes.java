package PL;

import BL.Entrega;
import BL.Palete;
import BL.Requisicao;
import BL.Servidor;
import GUI.View;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ControladorSessoes implements Controlador{

    private Admin a;
    private View v;

    public ControladorSessoes(){
        a = new Admin();
        v = new View(0);
    }

    public boolean iniciaSessao(String codID, String password) throws NullPointerException{
        boolean b;
        if (b = !(a.servidor.getListaGestores().get(codID).getPassword().equals(password) && a.servidor.getListaGestores().get(codID).getOnline()))
                a.servidor.online(codID);
        return b;
    }

    private void terminaSessao (String codID){
        if(a.servidor.getListaGestores().get(codID).getOnline())
            a.servidor.offline(codID);
    }

    public void addUser(String c, String n) {
        a.addUser(c,n);
    }

    public void deleteUser(String c) {
        a.deleteUser(c);
    }

    public List<String> consultarListaPaletes() {
        return new ArrayList<String>((Collection<? extends String>) a.consultarListaPaletes());
    }

    public String getGestor_Pedidos() {
        return a.getGestor_Pedidos();
    }

    public String getRobotsDisponiveis() {
        return a.getRobotsDisponiveis();
    }

    public List<String> lista_EA(){
        return a.servidor.getEntAtivas();
    }

    public List<String> lista_EF(){
        return a.servidor.getEntFeitas();
    }

    public List<String> lista_RA(){
        return a.servidor.getReqAtivas();
    }

    public List<String> lista_RF(){
        return a.servidor.getReqFeitas();
    }

    public Palete criaPalete (String c){
        int t = 1;
        String s = "p1";
        while(a.servidor.getInventario().containsKey(s)){
            s = "p" + t;
            t++;
        }

        Palete p = new Palete(s,c);
        return p;


    }

    public void addEA (Palete p){

        int t = 1;
        String s = "E1";
        while(a.servidor.getEA().containsKey(s)){
            s = "E" + t;
            t++;
        }
        Entrega e = new Entrega(p,s);
        a.servidor.addEA(e);

    }

    public void addEF (Entrega e){
        a.servidor.addEF(e);

    }
    
    public void remEA (Entrega e){
        a.servidor.removeEA(e.getCodID());
    }

    public void remRA (Requisicao r){
        a.servidor.removeRA(r.getCodID());
    }

    public void addRA (Palete p){

        int t = 1;
        String s = "P1";
        while(a.servidor.getRA().containsKey(s)){
            s = "P" + t;
            t++;
        }
        Requisicao r = new Requisicao(p,s);
        a.servidor.addRA(r);

    }


    public void addRF (Requisicao r){
        a.servidor.addRF(r);

    }


    @Override
    public void painel_RG() {
        View.make_window("Resgistar Gestor", v.registar_gestor());
    }

    @Override
    public void painel_LogInG() {
        View.make_window("Login de Gestor", v.login_gestor());
    }

    @Override
    public void painel_EA(){
        View.make_window("Painel das Entregas Ativas", v.painel_pedido(a.servidor.getEntAtivas()));
    }

    @Override
    public void painel_EF(){
        View.make_window("Painel das Entregas Feitas", v.painel_pedido(a.servidor.getEntFeitas()));
    }

    @Override
    public void painel_RA(){
        View.make_window("Painel das Requisições Ativas", v.painel_pedido(a.servidor.getReqAtivas()));
    }

    @Override
    public void painel_RF(){
        View.make_window("Painel das Requisições Feitas", v.painel_pedido(a.servidor.getReqFeitas()));
    }

    @Override
    public void painel_Robots(){
        View.make_window("Painel das Requisições Feitas", v.painel_pedido(a.servidor.getReqFeitas()));
    }

    @Override
    public void save() {

    }

    @Override
    public void end_scene(ActionEvent e) {
        final Node source = (Node) e.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @Override
    public void validaRegisto(String nome, String codID, String pwd){
        if(nome == null) {
            View.alert("ERRO","Não introduziu um Nome.");
            return;
        }
        if(codID == null) {
            View.alert("ERRO","Não introduziu um Código ID.");
            return;
        }
        if(pwd == null) {
            View.alert("ERRO","Não introduziu uma Password.");
            return;
        }

        if (false) {
            View.alert("Erro.", "O Código ID introduzido já pertence a outro Gestor. Tente novamente com um novo ID.");
            return;
        }

        // a.addGestor(nome, codID, pwd);
    }

    @Override
    public void logInGestor(String codID, String pwd) {

    }

    @Override
    public void logOutGestor(String codID) {

    }
    // TEM O MENU
    // PERGUNTA INFOS AO ADMIN
}
