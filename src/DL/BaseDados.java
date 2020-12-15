package DL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class BaseDados {
    private static Connection connection;
    private static String driver = System.getenv("SYM_DATABASE_DRIVER");
    private static String url = System.getenv("SYM_DATABASE_URL");
    private static String user = System.getenv("SYM_DATABASE_USER");
    private static String password = System.getenv("SYM_DATABASE_PASSWORD");

    static {
        BaseDados.startConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private BaseDados() {
    }

    public static void startConnection() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url + "?user=" + user + "&password=" + password);
            connection.setAutoCommit(false);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            if (connection.isClosed()) {
                BaseDados.startConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void stopConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

