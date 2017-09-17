package pe.edu.unac.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FactoryDAO {

    public static Connection connection() throws ClassNotFoundException, SQLException {
        String database = "sakila";
        String username = "homestead";
        String password = "secret";
        String url = "jdbc:mysql://192.168.10.10:3306/" + database + "?useSSL=false";

        return DriverManager.getConnection(url, username, password);
    }

}
