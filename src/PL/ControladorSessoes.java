package PL;

import BL.*;

import GUI.View;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class ControladorSessoes implements Controlador{

    private Servidor servidor;

    public ControladorSessoes(){
        servidor = new Servidor();
    }

    @Override
    public void logOutGestor(String codID){
        if(servidor.getGO(codID))
            servidor.offline(codID);
    }

    @Override
    public boolean iniciaSessao(String codID, String password){
        boolean b = false;
        if(servidor.containsGestor(codID))
            if (b = !(servidor.getGP(codID).equals(password) && servidor.getGO(codID)))
                servidor.online(codID);
        return b;
    }

    public void add(String c, String n, String p) {
        servidor.addGestor(c,n,p);
    }

    public void delete(String c) {
        if(servidor.containsGestor(c)) {
            Gestor g = servidor.getGestor(c);
            View.alert("Aviso", "Gestor " + g.getNome() + " foi permanentemente apagado do sistema." );
            servidor.removeGestor(c);
        } else View.alert("ERRO", "Não existe nenhum Gestor com o código " + c + ".");
    }

    public  void addE (String s){
        addE(servidor.criaPalete(s));
    }

    private void addE (Palete p){
        if(servidor.isParkingAvailable()){
            int t = 1;
            String s = "E1";
            while(servidor.searchEA(s) || servidor.searchEF(s)){
                s = "E" + t;
                t++;
            }
            Entrega e = new Entrega(p,s);
            servidor.addEntrega(e);
            aceitou();
        }
    }

    public void addR(String s){
        Palete p = servidor.search(s);
        if(p != null && servidor.isParkingAvailable()){
            int t = 1;
            String s1 = "P1";
            while(servidor.searchRA(s1) || servidor.searchRF(s1)){
                s = "P" + t;
                t++;
            }
            Requisicao r = new Requisicao(p,s);
            servidor.addRequisicao(r);
            aceitou();
        }
        else View.alert("ERRO", "Tentou requisitar algo não existente no armazem.");
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

        if (servidor.containsGestor(codID)) {
            View.alert("Erro.", "O Código ID introduzido já pertence a outro Gestor. Tente novamente com um novo ID.");
        } else add(codID, nome, pwd);
    }

    @Override
    public List<String> inventario (){
    return servidor.inventario();
    }

    @Override
    public List<String> listagem (){
        return servidor.listagem();
    }

    @Override
    public List<String> robots() {
        return null;
    }

    @Override
    public List<String> lista_requisicoes() {
        return servidor.listar_requisicoes();
    }

    @Override
    public List<String> lista_entregas() {
        return servidor.listar_entregas();
    }

    @Override
    public int parking() {
        return servidor.getParking();
    }

    @Override
    public void aceitou() {
        servidor.minusSpot();
        servidor.plusSpot();
    }

    @Override
    public void end_scene(ActionEvent e) {
        final Node source = (Node) e.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @Override
    public List<String> getReqFeitas() {
        return servidor.getReqFeitas();
    }

    @Override
    public List<String> getReqAtivas() {
        return servidor.getReqAtivas();
    }

    @Override
    public List<String> getEntFeitas() {
        return servidor.getEntFeitas();
    }

    @Override
    public List<String> getEntAtivas() {
        return servidor.getEntAtivas();
    }
}
