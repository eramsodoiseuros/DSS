package PL;

import CN.Palete;
import CN.Servidor;

import java.util.Map;

public class ControladorGestor {
    private Servidor servidor;

    private Map<String, Palete> consultarListaPaletes (){
        return servidor.getInventario().getInventario();
    }
}
