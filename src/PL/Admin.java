package PL;


import BL.Palete;
import BL.Servidor;

import java.util.Map;

public class Admin {

    protected Servidor servidor;

    protected Admin(){
        boolean estado = false;
        if(estado){

            //load last
        } else servidor = new Servidor();

        // falta verificar estado
        // load last
    }

    private void deleteUser(String codID){
        servidor.removeGestor(codID);
    }
    private void addUser (String codID, String nome) {
        servidor.addGestor(codID,nome);
    }

    private Map<String, Palete> consultarListaPaletes (){
        return servidor.getInventario();
    } // falta tabela

    // TEM METODOS QUE FALAM COM O SERVIDOR
}
