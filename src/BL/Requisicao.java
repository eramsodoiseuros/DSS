package BL;

import java.util.ArrayList;

public class Requisicao extends Pedido {

    public String getCodID(){
        return codID;
    }

    protected Requisicao(ArrayList<Palete> p, String codID) {
        this.codID = codID;
        this.conteudo = new ArrayList<Palete>(p);
        this.estado = true;
    }

    public Requisicao(){
    }

    @Override
    boolean estado() {
        this.estado = !this.estado;
        return this.estado;
    }
}
