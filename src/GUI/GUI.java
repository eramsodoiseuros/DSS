package GUI;

import javafx.scene.Scene;

public interface GUI {

    /**
     * Função que gera a Scene JavaFX representativa do Menu Principal
     * @return Scene repesentativa do Menu Principal
     * */
    Scene menu();

    /**
     * Função que cria uma nova Janela
     * @param title Titulo da nova Janela
     * @param s Scene do JavaFX
     * */
    void make_window(String title, Scene s);

    /**
     * Função que gera a Scene JavaFX representativa do Registo de Gestor
     * @return Scene repesentativa do Registo de Gestor
     * */
    Scene registar_gestor();

    /**
     * Função que gera a Scene JavaFX representativa do Login de Gestor
     * @return Scene repesentativa do Login de Gestor
     * */
    Scene login_gestor();

}
