package DL;

import BL.Palete;
import BL.Robot;

import java.util.*;

public class InventarioDAO extends DataAcessObject<String, Palete>{

    private static InventarioDAO singleton = new InventarioDAO();

    public InventarioDAO() {
        super(new Palete(), "Palete", Arrays.asList("codID", "refrigerado", "conteudo"));
    }

    public static InventarioDAO getInstance(){
        return InventarioDAO.singleton;
    }

    public ArrayList<Palete> values(){
        return (ArrayList<Palete>) super.values();
    }

    public Palete get(final String key) {
        return super.get(key);
    }

    public Palete put(final Palete value) {
        return super.put(value, value.getCodID());
    }

    public Palete remove(final String key) {
        return super.remove(key);
    }

    public Set<Palete> search(final String value) {
        return super.search(value, 0);
    }
}
