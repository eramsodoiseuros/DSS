package CN;

import java.util.ArrayList;
import java.util.Objects;

public class Palete {
    private boolean refrigerado;
    private ArrayList<String> conteudo;
    private boolean armazenado;
    private String codID;

    public boolean isRefrigerado() {
        return refrigerado;
    }
    public void setRefrigerado(boolean refrigerado) {
        this.refrigerado = refrigerado;
    }
    public ArrayList<String> getConteudo() {
        return conteudo;
    }
    public void setConteudo(ArrayList<String> conteudo) {
        this.conteudo = conteudo;
    }
    public boolean isArmazenado() {
        return armazenado;
    }
    public void setArmazenado(boolean armazenado) {
        this.armazenado = armazenado;
    }
    public String getCodID() {
        return codID;
    }
    public void setCodID(String codID) {
        this.codID = codID;
    }

    public Palete(boolean refrigerado, ArrayList<String> conteudo, String codID) {
        this.refrigerado = refrigerado;
        this.conteudo = conteudo;
        this.codID = codID;
    }

    public Palete(String codID) {
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
