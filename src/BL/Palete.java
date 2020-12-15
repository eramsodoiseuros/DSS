package BL;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Palete {
    private boolean refrigerado;
    private String conteudo;
    private boolean armazenado;
    private String codeID;
    private Point localizacao;

    protected boolean isRefrigerado() {
        return refrigerado;
    }
    protected void setRefrigerado(boolean refrigerado) {
        this.refrigerado = refrigerado;
    }
    protected String getConteudo() {
        return conteudo;
    }
    protected void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
    protected boolean isArmazenado() {
        return armazenado;
    }
    protected void setArmazenado(boolean armazenado) {
        this.armazenado = armazenado;
    }
    public String getCodID() {
        return codeID;
    }
    protected void setCodID(String codID) {
        this.codeID = codID;
    }
    protected void setLocalizacao(Point l) {this.localizacao = l;}

    protected Point getLocalizacao(){return this.localizacao;}

    protected Palete(boolean refrigerado, String conteudo, String codID) {
        this.refrigerado = refrigerado;
        this.conteudo = conteudo;
        this.codeID = codID;
    }

    public Palete Palete(String conteudo){
        Palete p = new Palete();
        p.codeID = "pedido";
        p.conteudo = conteudo;
        p.armazenado = true;
        p.refrigerado = false;
        return p;
    }

    public Palete(){
    }

    protected Palete(String codID) {
        this.codeID = codID;
    }

    @Override
    public String toString() {
        return "Palete{" +
                "refrigerado=" + refrigerado +
                ", conteudo=" + conteudo +
                ", armazenado=" + armazenado +
                ", codID='" + codeID + '\'' +
                ", localizacao='" + localizacao.x +' '+localizacao.y+ '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Palete palete = (Palete) o;
        return refrigerado == palete.refrigerado &&
                armazenado == palete.armazenado &&
                localizacao.equals(palete.getLocalizacao()) &&
                Objects.equals(conteudo, palete.conteudo) &&
                Objects.equals(codeID, palete.codeID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(refrigerado, conteudo, armazenado, codeID, localizacao);
    }

}
