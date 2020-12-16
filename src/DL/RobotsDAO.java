package DL;

import BL.Gestor;
import BL.Robot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Set;

public class RobotsDAO extends DataAcessObject<String, Robot>{
    private static RobotsDAO singleton = new RobotsDAO();

    public RobotsDAO() {
        super(new Robot(), "Robots", Arrays.asList("codID", "ordens_feitas"));
    }

    public static RobotsDAO getInstance(){
        return RobotsDAO.singleton;
    }

    public Robot get(final String key) {
        return super.get(key);
    }

   /* public PriorityQueue<Robot> values(){
        return (PriorityQueue<Robot>) super.values();
    }*/

    public ArrayList<Robot> values2(){
        return (ArrayList<Robot>) super.values();
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
