package BL;

import DL.Dados;

import java.util.ArrayList;
import java.util.List;

public class Requisicao extends Pedido implements Dados<Requisicao> {

    public String getCodID(){
        return codeID;
    }

    protected Requisicao(Palete p, String codID) {
        this.codeID = codID;
        this.conteudo = p;
        this.estado = true;
    }

    public Requisicao(){
    }

    public Requisicao(List<String> l){
        this.codeID = l.get(0);
        this.conteudo = new Palete(l.get(1));
        this.estado = false;
    }

    @Override
    boolean estado() {
        this.estado = !this.estado;
        return this.estado;
    }

    public Dados<Requisicao> fromRow(final List<String> l) {
        return new Requisicao(l);
    }

    public List<String> toRow() {
        List<String> r = new ArrayList<>();
        r.add(this.codeID);
        r.add(this.conteudo.toString());
        return r;
    }
}
