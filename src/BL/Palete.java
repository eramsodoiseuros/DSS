package BL;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Palete {
    private boolean refrigerado;
    private ArrayList<String> conteudo;
    private boolean armazenado;
    private String codID;
    private Point localizacao;

    protected boolean isRefrigerado() {
        return refrigerado;
    }
    protected void setRefrigerado(boolean refrigerado) {
        this.refrigerado = refrigerado;
    }
    protected ArrayList<String> getConteudo() {
        return conteudo;
    }
    protected void setConteudo(ArrayList<String> conteudo) {
        this.conteudo = conteudo;
    }
    protected boolean isArmazenado() {
        return armazenado;
    }
    protected void setArmazenado(boolean armazenado) {
        this.armazenado = armazenado;
    }
    protected String getCodID() {
        return codID;
    }
    protected void setCodID(String codID) {
        this.codID = codID;
    }
    protected void setLocalizacao(Point l) {this.localizacao = l;}

    protected Point getLocalizacao() {return this.localizacao;}

    protected Palete(boolean refrigerado, ArrayList<String> conteudo, String codID, Point localizacao) {
        this.refrigerado = refrigerado;
        this.conteudo = conteudo;
        this.codID = codID;
        this.localizacao = localizacao;
    }

    protected Palete(String codID) {
        this.codID = codID;
    }

    @Override
    public String toString() {
        return "Palete{" +
                "refrigerado=" + refrigerado +
                ", conteudo=" + conteudo +
                ", armazenado=" + armazenado +
                ", codID='" + codID + '\'' +
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
                Objects.equals(codID, palete.codID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(refrigerado, conteudo, armazenado, codID, localizacao);
    }
}
