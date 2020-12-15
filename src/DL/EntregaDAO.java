package DL;

import BL.Entrega;

import java.util.*;

public class EntregaDAO extends DataAcessObject<String, Entrega>{
    private static EntregaDAO singleton = new EntregaDAO();

    public EntregaDAO() {
        super(new Entrega(), "Entrega", Arrays.asList("codID", "conteudo"));
    }

    public EntregaDAO getInstance(){
        return EntregaDAO.singleton;
    }

    public Entrega get(final String key) {
        return super.get(key);
    }

    public Entrega put(final Entrega value) {
        return super.put(value, value.getCodID());
    }

    public Entrega remove(final String key) {
        return super.remove(key);
    }

    public Set<Entrega> search(final String value) {
        return super.search(value, 0);
    }
}
