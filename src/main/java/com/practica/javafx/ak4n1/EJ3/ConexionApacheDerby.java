package com.practica.javafx.ak4n1.EJ3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionApacheDerby {
    private static final String URL = "jdbc:derby:MDerbyDb;create=true";

    private static Connection conn = null;

    private ConexionApacheDerby() {}

    public static Connection getInstance() {
        if (conn == null) {
            try {
                // Cargar driver
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

                // Crear conexión
                conn = DriverManager.getConnection(URL);
                System.out.println("Conectado a Apache Derby (singleton)");
            } catch (ClassNotFoundException e) {
                System.err.println("No se encontró driver de Derby");
            } catch (SQLException e) {
                System.err.println("Error al conectar a Derby");
            }
        } else {
            System.out.println("Usando conexión Derby singleton");
        }
        return conn;
    }

    public static void close() {
        if (conn != null) {
            try {
                conn.close();
                conn = null; // Permite nueva conexión si se llama otra vez
                System.out.println("Conexión Derby cerrada");
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión Derby");
            }
        }
    }
}
