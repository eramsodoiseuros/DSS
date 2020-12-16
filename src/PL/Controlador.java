package PL;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

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
    void validaRegisto(String nome, String codID, String pwd);

    /**
     * Verifica as informações de Login de um Gestor
     * @param codID codeID do Gestor
     * */
    void logOutGestor(String codID);

    /**
     * Verifica as informações de Login de um Gestor
     * @param codID codeID do Gestor
     * @param pwd password do Gestor
     * */
    public void logInGestor(String codID, String pwd);

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
    void delete(String user);

    /**
     * Função que
     * */
    List<String> inventario();

    /**
     * Função que
     * */
    List<String> robots();

    /**
     * Função que
     * */
    void addEA(String s);

    /**
     * Função que
     * */
    void addRA(String s);

    /**
     * Função que
     * */
    List<String> listagem();
}
