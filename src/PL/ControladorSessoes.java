package PL;

import BL.*;

import GUI.View;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ControladorSessoes implements Controlador{

    private Admin a;
    private final View v;

    public ControladorSessoes(){
        a = new Admin();
        v = new View(0);
    }

    public boolean iniciaSessao(String codID, String password){
        boolean b = false;
        if(a.servidor.getListaGestores().containsKey(codID))
            if (b = !(a.servidor.getListaGestores().get(codID).getPassword().equals(password) && a.servidor.getListaGestores().get(codID).getOnline()))
                a.servidor.online(codID);
        return b;
    }

    private void terminaSessao (String codID){
        if(a.servidor.getListaGestores().get(codID).getOnline())
            a.servidor.offline(codID);
    }

    public void add(String c, String n, String p) {
        a.addUser(c,n,p);
    }

    public void delete(String c) {
        if(a.servidor.getListaGestores().containsKey(c)) {
            Gestor g = a.servidor.getListaGestores().get(c);
            View.alert("Aviso", "Gestor " + g.getNome() + " foi permanentemente apagado do sistema." );
            a.deleteUser(c);
        } else View.alert("ERRO", "Não existe nenhum Gesto com o código " + c + ".");
    }

    @Override
    public List<String> inventario() {
        return null;
    }

    @Override
    public List<String> robots() {
        return null;
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

    public  void addEA (String s){
        addEA(a.servidor.criaPalete(s));
    }

    private void addEA (Palete p){
        int t = 1;
        String s = "E1";
        while(a.servidor.getEA().containsKey(s)){
            s = "E" + t;
            t++;
        }
        Entrega e = new Entrega(p,s);
        a.servidor.addEA(e);
    }

    public void addRA(String s){
        addRA(a.servidor.criaPalete(s));
    }

    @Override
    public List<String> listagem() {
        return null;
    }

    private void addRA (Palete p){

        int t = 1;
        String s = "P1";
        while(a.servidor.getRA().containsKey(s)){
            s = "P" + t;
            t++;
        }
        Requisicao r = new Requisicao(p,s);
        a.servidor.addRA(r);
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

        if (a.servidor.getListaGestores().containsKey(codID)) {
            View.alert("Erro.", "O Código ID introduzido já pertence a outro Gestor. Tente novamente com um novo ID.");
            return;
        } else add(codID, nome, pwd);

        // a.addGestor(nome, codID, pwd);
    }

    @Override
    public void logOutGestor(String codID) {

    }

    @Override
    public void logInGestor(String codID, String pwd) {
        if(iniciaSessao(codID, pwd)){
            v.make_window("Menu do Gestor", v.painel_gestor(this, a.servidor.getListaGestores().get(codID)));
        } else View.alert("ERRO", "Falha ao iniciar a sessão, verifique os seus dados.");

    }

    public List<String> inventario (){
        List<String> s = new ArrayList<>();
        for(Palete p : a.servidor.getInventario().values()){
            s.add(p.toStringInventario());
        }
    return s;
    }

    

    // TEM O MENU
    // PERGUNTA INFOS AO ADMIN
}
