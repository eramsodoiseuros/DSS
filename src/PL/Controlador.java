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

    /**
     * Função que
     * */
    public void painel_RG();

    /**
     * Função que
     * */
    public void painel_LogInG();

    /**
     * Função que
     * */
    public void painel_EA();

    /**
     * Função que
     * */
    public void painel_EF();

    /**
     * Função que
     * */
    public void painel_RA();

    /**
     * Função que
     * */
    public void painel_RF();

    /**
     * Função que
     * */
    public void painel_Robots();
}
