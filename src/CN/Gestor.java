package CN;

import java.util.Map;
import java.util.Objects;

public class Gestor {

private String nome;
private String password;
private String codID;
private boolean online;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gestor gestor = (Gestor) o;
        return online == gestor.online &&
                Objects.equals(nome, gestor.nome) &&
                Objects.equals(password, gestor.password) &&
                Objects.equals(codID, gestor.codID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, password, codID, online);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCodID() {
        return codID;
    }

    public void setCodID(String codID) {
        this.codID = codID;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    @Override
    public String toString() {
        return "Gestor{" +
                "nome='" + nome + '\'' +
                ", password='" + password + '\'' +
                ", codID='" + codID + '\'' +
                ", online=" + online +
                '}';
    }

    private boolean checkPassID (String password, String codID){
        boolean r = false;
        if (password.equals(this.password) && codID.equals(this.codID)) r = true;

        return r;
}

/* FALTA FAZER
    private Map<String, Pair> consultaLista (boolean online){

    }*/
}
