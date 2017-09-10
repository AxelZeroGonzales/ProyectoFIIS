package pe.edu.unac.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FactoryDAO {

    public static Connection connection() throws ClassNotFoundException, SQLException {
        String database = "sakila";
        String username = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/" + database;

        return DriverManager.getConnection(url, username, password);
    }

}
