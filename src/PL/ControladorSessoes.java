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
    private View v;

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
        a.servidor.addGestor(c,n,p);
    }

    public void delete(String c) {
        if(a.servidor.getListaGestores().containsKey(c)) {
            Gestor g = a.servidor.getListaGestores().get(c);
            View.alert("Aviso", "Gestor " + g.getNome() + " foi permanentemente apagado do sistema." );
            a.servidor.removeGestor(c);
        } else View.alert("ERRO", "Não existe nenhum Gestor com o código " + c + ".");
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
        } else add(codID, nome, pwd);
    }

    @Override
    public void logOutGestor(String codID) {

    }

    @Override
    public void logInGestor(String codID, String pwd) {
        if(iniciaSessao(codID, pwd)){
            View.make_window("Menu do Gestor", v.painel_gestor(this, a.servidor.getListaGestores().get(codID)));
        } else View.alert("ERRO", "Falha ao iniciar a sessão, verifique os seus dados.");

    }

    @Override
    public List<String> inventario (){
        List<String> s = new ArrayList<>();
        for(Palete p : a.servidor.getInventario().values()){
            s.add(p.toStringFeitas());
        }
    return s;
    }

    @Override
    public List<String> listagem (){
        List<String> s = new ArrayList<>();
        for(Palete p : a.servidor.getInventario().values()){
            s.add(p.toStringListagem());
        }
        return s;
    }

    @Override
    public List<String> robots() {
        return null;
    }

    @Override
    public void end_scene(ActionEvent e) {
        final Node source = (Node) e.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
