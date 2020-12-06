package CN;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventario {

    private HashMap<String,Palete> inventario;
    private int size_t1;
    private int size_t2;

    public Inventario(HashMap<String, Palete> inventario, int size_t1, int size_t2) {
        this.inventario = inventario;
        this.size_t1 = size_t1;
        this.size_t2 = size_t2;
    }

    public HashMap<String, Palete> getInventario() {
        return new HashMap<String, Palete>(inventario);
    }

    public void setInventario(HashMap<String, Palete> inventario) {
        this.inventario = inventario;
    }
    public int getSize_t1() {
        return size_t1;
    }
    public void setSize_t1(int size_t1) {
        this.size_t1 = size_t1;
    }
    public int getSize_t2() {
        return size_t2;
    }
    public void setSize_t2(int size_t2) {
        this.size_t2 = size_t2;
    }

    public HashMap<String, Pair> check_itens(ArrayList<String> lista){
        return new HashMap<String, Pair>();
    }

    public void add(Palete p){
        this.inventario.putIfAbsent(p.getCodID(),p);
    }

    public void remove(String codID){
        this.inventario.remove(codID);
    }

    public boolean existe(String codID){
        return inventario.containsKey(codID);
    }

    public Inventario Clone() {
        return new Inventario(this.getInventario(), this.size_t1, this.size_t2);
    }
}
