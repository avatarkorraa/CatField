package it.unisa.progetto.Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connector {

    public static Connection getConnection() {

        Connection conn = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection
                    ("SQL JDBC STRING QUI");

        } catch (Exception e) {

            throw new RuntimeException(e);

        }

        return conn;

    }

}
