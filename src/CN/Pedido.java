package CN;

import java.util.ArrayList;

abstract class Pedido {
    public String codID;
    public ArrayList<Palete> conteudo;
    public boolean estado;

    abstract boolean estado();
}
