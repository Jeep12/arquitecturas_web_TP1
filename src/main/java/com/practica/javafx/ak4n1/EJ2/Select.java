package com.practica.javafx.ak4n1.EJ2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Select {

    // Mostrar todas las personas
    public static void mostrarPersonas(Connection conn) {
        String sql = "SELECT * FROM persona";

        try (PreparedStatement ps = conn.prepareStatement(sql);
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

    // Mostrar personas con sus trabajos
    public static void mostrarPersonasConTrabajos(Connection conn) {
        String sql = """
            SELECT p.id, p.nombre, p.edad, t.titulo, t.salario
            FROM persona p
            LEFT JOIN trabajo t ON p.id = t.persona_id
            """;

        try (PreparedStatement ps = conn.prepareStatement(sql);
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
