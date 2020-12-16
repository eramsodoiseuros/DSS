package BL;

import DL.Dados;
import DL.InventarioDAO;

import java.util.ArrayList;
import java.util.List;

public class Entrega extends Pedido implements Dados<Entrega>{

    public String getCodID(){
        return codeID;
    }

    protected Entrega(Palete p, String codID) {
        this.codeID = codID;
        this.conteudo = p;
        this.estado = true;
    }

    public Entrega(){
    }

    public Entrega(List<String> l){
        this.codeID = l.get(0);
        this.conteudo = new Palete(l.get(1), l.get(2));
        this.estado = false;
    }

    @Override
    boolean estado() {
        this.estado = !this.estado;
        return this.estado;
    }

    public Dados<Entrega> fromRow(final List<String> l) {
        return new Entrega(l);
    }

    public List<String> toRow() {
        List<String> r = new ArrayList<>();
        r.add(this.codeID);
        r.add(this.conteudo.toString());
        return r;
    }
}
