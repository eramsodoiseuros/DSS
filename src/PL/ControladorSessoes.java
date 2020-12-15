package PL;

import BL.Servidor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ControladorSessoes {

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
        return new ArrayList<>((Collection<? extends String>) a.consultarListaPaletes());
    }

    public String getGestor_Pedidos() {
        return a.getGestor_Pedidos();
    }

    public String getRobotsDisponiveis() {
        return a.getRobotsDisponiveis();
    }

    // TEM O MENU
    // PERGUNTA INFOS AO ADMIN
}
