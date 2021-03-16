package src.com.MoviesInfo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    final String DRIVER_CLASS = "org.mariadb.jdbc.Driver"; // found in the referenced libraries at the given path
    final String URL = "jdbc:mariadb://localhost:3306/";
    private String username;
    private String password;
    private String database;
    private Connection conn = null;

    public DbConnection(String username, String password, String database) {
        this.username = username;
        this.password = password;
        this.database = database;
    }

    public Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName(DRIVER_CLASS);
                conn = DriverManager.getConnection(URL + this.database, this.username, this.password);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error while creating connection.");
            e.printStackTrace();
        }
    }

}
