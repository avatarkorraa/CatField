package it.unisa.progetto.Database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * La classe Connector fornisce un metodo statico per stabilire una connessione a un database MySQL.
 */

public class Connector {

    /**
     * Restituisce una connessione a un database MySQL.
     *
     * @return un oggetto Connection che rappresenta la connessione al database.
     * @throws RuntimeException se si verifica un errore durante il tentativo di stabilire la connessione.
     */

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
