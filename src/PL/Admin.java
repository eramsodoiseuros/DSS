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

    public void deleteUser(String codID){
        servidor.removeGestor(codID);
    }
    public void addUser (String codID, String nome) {
        servidor.addGestor(codID,nome);
    }

    public Map<String, Palete> consultarListaPaletes (){
        return servidor.getInventario();
    } // falta tabela

    public String getGestor_Pedidos() {
        return servidor.getGestor_Pedidos().toString();
    }

    public String getRobotsDisponiveis() {
        return servidor.getRobotsDisponiveis().toString();
    }

    // TEM METODOS QUE FALAM COM O SERVIDOR
}
