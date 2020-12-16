package PL;

import javafx.event.ActionEvent;

import java.util.List;

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
     * @param codID codeID do Gestor
     * @param pwd Pwd do Gestor
     * */
    void logInGestor (String codID, String pwd);

    /**
     * Verifica as informações de Login de um Gestor
     * @param codID codeID do Gestor
     * */
    void logOutGestor (String codID);

    public List<String> lista_EA();

    public List<String> lista_EF();

    public List<String> lista_RA();

    public List<String> lista_RF();
}
