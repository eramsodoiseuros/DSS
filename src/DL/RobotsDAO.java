package DL;

import BL.Robot;

import java.util.Arrays;
import java.util.Set;

public class RobotsDAO extends DataAcessObject<String, Robot>{
    private static RobotsDAO singleton = new RobotsDAO();

    public RobotsDAO() {
        super(new Robot(), "Robot", Arrays.asList("codID", "ordens_feitas"));
    }

    public RobotsDAO getInstance(){
        return RobotsDAO.singleton;
    }

    public Robot get(final String key) {
        return super.get(key);
    }

    public Robot put(final Robot value) {
        return super.put(value, value.getCodeID());
    }

    public Robot remove(final String key) {
        return super.remove(key);
    }

    public Set<Robot> search(final String value) {
        return super.search(value, 0);
    }
}
