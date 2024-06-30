package escuela.de.chefs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/?user=root";
    private static final String USER = "root"; 
    private static final String PASSWORD = "0301"; 

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
