package CN;

import java.util.ArrayList;

public class Requisicao extends Pedido {

    protected String getCodID(){
        return codID;
    }

    protected Requisicao(ArrayList<Palete> p, String codID) {
        this.codID = codID;
        this.conteudo = new ArrayList<Palete>(p);
        this.estado = true;
    }

    @Override
    boolean estado() {
        this.estado = !this.estado;
        return this.estado;
    }
}
