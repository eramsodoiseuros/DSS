package PL;

import javafx.event.ActionEvent;

public interface Controlador {

    /* *
     * Guarda o estado do Programa na Base de Dados
     * */
    void save();

    /* *
     * Fecha uma Scene do JavaFX
     * */
    void end_scene(ActionEvent e);

    /**
     * Verifica todos os codID de Gestores e ve se e possivel registar este novo
     * @param nome email do utilizador a ser validado
     * @param codID nome do utilizador a ser validado
     * @param pwd palavra-passe do utilizador a ser validado
     * */
    void validaRegisto (String nome, String codID, String pwd);

    /**
     * Verifica as informações de Login de um Gestor
     * @param codID Email do user
     * @param pwd Pwd do user
     * */
    void validaLogInGestor (String codID, String pwd);
}
