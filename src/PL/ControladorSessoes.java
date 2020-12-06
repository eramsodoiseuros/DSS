package PL;

import CN.Servidor;

public class ControladorSessoes {

    private Servidor servidor;

    private boolean iniciaSessao (String password, String codID){
       if ((servidor.getListaGestores().get(codID).getPassword().equals(password) && servidor.getListaGestores().get(codID).getOnline()) == false) return true;
       
    }

}
