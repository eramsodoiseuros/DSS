package BL;

import DL.Dados;
import DL.InventarioDAO;
import GUI.View;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Requisicao extends Pedido implements Dados<Requisicao> {

    public String getCodID(){
        return codeID;
    }

    public Requisicao(Palete p, String codID) {
        this.codeID = codID;
        this.conteudo = p;
        this.estado = true;
    }

    public Requisicao(){
    }

    public Requisicao(List<String> l){
        this.codeID = l.get(0);
        boolean preencheu = false;
        for(Palete p : InventarioDAO.getInstance().values()) {
            if (p.getConteudo().equals(l.get(1))) {
                this.conteudo = new Palete(p);
                preencheu = true;
                break;
            }
        }
        if(!preencheu) View.alert("ERRO", "Requisição inválida");
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
        r.add(this.conteudo.toStringFeitas());
        return r;
    }

    public String toStingConteudoAtivas() { return conteudo.toStringAtivas();
    }

    public String toStringConteudoFeitas() { return conteudo.toStringFeitas();
    }
}
