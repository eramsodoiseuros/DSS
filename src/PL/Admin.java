package PL;


import CN.Gestor;
import CN.Servidor;

public class Admin {

    private Servidor servidor;

    private void deleteUser(String codID){

        servidor.removeGestor(codID);
    }
    private void addUser (String codID, String nome) {
        servidor.addGestor(codID,nome);
    }
}
