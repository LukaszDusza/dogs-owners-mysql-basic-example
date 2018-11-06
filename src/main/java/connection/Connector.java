package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    public static Connection getConnect(
            String url,
            String login,
            String pass) throws SQLException {

        Connection conn = DriverManager.getConnection(url, login,pass);
        return conn;
    }



}
