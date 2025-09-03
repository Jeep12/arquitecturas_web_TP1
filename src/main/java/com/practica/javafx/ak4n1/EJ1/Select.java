package com.practica.javafx.ak4n1.EJ1;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class Select {

    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String URI = "jdbc:derby:MDerbyDb;create=true";

    public static void main(String[] args) {
        cargarDriver();

        mostrarPersonas();

        mostrarPersonasConTrabajos();
    }


    private static void cargarDriver() {
        try {
            Class.forName(DRIVER).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException |
                 InvocationTargetException | NoSuchMethodException |
                 ClassNotFoundException e) {
            throw new RuntimeException("Error cargando el driver JDBC", e);
        }
    }


    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URI);
    }


    private static void mostrarPersonas() {
        String sql = "SELECT * FROM persona";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean hayDatos = false;
            System.out.println("Personas registradas:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                System.out.println(id + " , " + nombre + " , " + edad);
                hayDatos = true;
            }
            if (!hayDatos) {
                System.out.println("La tabla PERSONA está vacía");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar PERSONA", e);
        }
    }

    private static void mostrarPersonasConTrabajos() {
        String sql = """
            SELECT p.id, p.nombre, p.edad, t.titulo, t.salario
            FROM persona p
            LEFT JOIN trabajo t ON p.id = t.persona_id
            """;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean hayDatos = false;
            System.out.println("\nPersonas y sus trabajos:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                String titulo = rs.getString("titulo");
                double salario = rs.getDouble("salario");

                if (titulo == null) titulo = "(Sin trabajo asignado)";
                System.out.println(id + " , " + nombre + " , " + edad +
                        " , " + titulo + " , $" + salario);
                hayDatos = true;
            }
            if (!hayDatos) {
                System.out.println("No hay registros en PERSONA ni TRABAJO");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar PERSONA + TRABAJO", e);
        }
    }

}
