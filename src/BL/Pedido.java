package BL;

import java.util.ArrayList;

public abstract class Pedido {
    String codID;
    ArrayList<Palete> conteudo;
    boolean estado;

    abstract boolean estado();
}
