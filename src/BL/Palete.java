package BL;

import java.util.ArrayList;
import java.util.Objects;

public class Palete {
    private boolean refrigerado;
    private ArrayList<String> conteudo;
    private boolean armazenado;
    private String codID;

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
    public String getCodID() {
        return codID;
    }
    protected void setCodID(String codID) {
        this.codID = codID;
    }

    protected Palete(boolean refrigerado, ArrayList<String> conteudo, String codID) {
        this.refrigerado = refrigerado;
        this.conteudo = conteudo;
        this.codID = codID;
    }

    public Palete(){
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
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Palete palete = (Palete) o;
        return refrigerado == palete.refrigerado &&
                armazenado == palete.armazenado &&
                Objects.equals(conteudo, palete.conteudo) &&
                Objects.equals(codID, palete.codID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(refrigerado, conteudo, armazenado, codID);
    }

}
