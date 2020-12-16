package GUI;

import BL.Gestor;
import PL.Controlador;
import javafx.scene.Scene;

import java.util.List;

public interface GUI {

    /**
     * Função que gera a Scene JavaFX representativa do Menu Principal
     * @return Scene repesentativa do Menu Principal
     * */
    Scene menu();

    /**
     * Função que gera a Scene JavaFX representativa do
     * @return Scene repesentativa do
     * */
    Scene painel_pedido(List<String> lista);

    /**
     * Função que gera a Scene JavaFX representativa do
     * @return Scene repesentativa do
     * */
    Scene painel_robot();

    Scene painel_gestor(Controlador c1, Gestor g);
}
