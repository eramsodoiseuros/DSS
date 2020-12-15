package PL;

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

    public ControladorSessoes(){
        a = new Admin();
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
        if(nome == null) {
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
    public void validaLogInGestor(String codID, String pwd) {

    }
    // TEM O MENU
    // PERGUNTA INFOS AO ADMIN
}
