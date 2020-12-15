package BL;

import java.util.ArrayList;

public class Entrega extends Pedido {

    public String getCodID(){
        return codID;
    }

    protected Entrega(ArrayList<Palete> p, String codID) {
        this.codID = codID;
        this.conteudo = new ArrayList<Palete>(p);
        this.estado = true;
    }

    public Entrega(){
    }

    @Override
    boolean estado() {
        this.estado = !this.estado;
        return this.estado;
    }
}
