package PL;

import BL.Servidor;

public class ControladorSessoes {

    private Admin a;

    public boolean iniciaSessao(String password, String codID){
        boolean b;
        if(b = !(a.servidor.getListaGestores().get(codID).getPassword().equals(password) && a.servidor.getListaGestores().get(codID).getOnline()))
            a.servidor.online(codID);
        return b;
    }

    private void terminaSessao (String codID){
        if(a.servidor.getListaGestores().get(codID).getOnline())
            a.servidor.offline(codID);
    }

    // TEM O MENU
    // PERGUNTA INFOS AO ADMIN
}
