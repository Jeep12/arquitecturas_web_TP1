package com.practica.javafx.ak4n1.EJ3;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:mariadb://localhost:3306/arquitecturas_ej2";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection conn = null;

    private Conexion() {}

    public static Connection getInstance() {
        if (conn == null) {
            try {
                // Cargar driver
                Class.forName("org.mariadb.jdbc.Driver");

                // Crear conexión
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("conectado ej3");
            } catch (ClassNotFoundException e) {
                System.err.println("️ no se encontró driver");
            } catch (SQLException e) {
                System.err.println(" error al conectar");
            }
        } else {
            System.out.println("usando singleton");
        }
        return conn;
    }

    public static void close() {
        if (conn != null) {
            try {
                conn.close();
                conn = null; // Reset para permitir nueva conexión
                System.out.println("conexión cortada");
            } catch (SQLException e) {
                System.err.println("error al cerrar conexión: ");
            }
        }
    }


}
