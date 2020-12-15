package DL;

import BL.Requisicao;

import java.util.*;

public class RequisicaoDAO extends DataAcessObject<String, Requisicao>{
    private static RequisicaoDAO singleton = new RequisicaoDAO();

    public RequisicaoDAO() {
        super(new Requisicao(), "Requisicao", Arrays.asList("codID", "conteudo"));
    }

    public RequisicaoDAO getInstance(){
        return RequisicaoDAO.singleton;
    }

    public Requisicao get(final String key) {
        return super.get(key);
    }

    public Requisicao put(final Requisicao value) {
        return super.put(value, value.getCodID());
    }

    public Requisicao remove(final String key) {
        return super.remove(key);
    }

    public Set<Requisicao> search(final String value) {
        return super.search(value, 0);
    }
}
