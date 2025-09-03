package com.practica.javafx.ak4n1.EJ2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrearTablaEInsertar {


    public static void crearTablaPersona(Connection conn) {
        String table = " CREATE TABLE persona(" +
                "id INT," +
                "nombre VARCHAR(500)," +
                "edad INT," +
                "PRIMARY KEY (id))";
        try {
            conn.prepareStatement(table).execute();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void agregarPersona(Connection conn, int id, String nombre, int edad) throws SQLException {
        String insert = "INSERT INTO persona (id,nombre,edad) VALUES (?,?,?)";
        PreparedStatement ps = conn.prepareStatement(insert);
        ps.setInt(1, id);
        ps.setString(2, nombre);
        ps.setInt(3, edad);
        ps.executeUpdate();
        ps.close();
        conn.commit();

    }

    public static void crearTablaTrabajo(Connection conn) {
        String table = """
                CREATE TABLE trabajo(
                    id INT PRIMARY KEY,
                    titulo VARCHAR(100),
                    salario DECIMAL(10,2),
                    persona_id INT,
                    FOREIGN KEY (persona_id) REFERENCES persona(id)
                )
                """;

        try (PreparedStatement ps = conn.prepareStatement(table)) {
            ps.execute();
            conn.commit();
            System.out.println("Tabla creada");
        } catch (SQLException e) {
            System.err.println(" Error al crear la tabla");
            throw new RuntimeException(e);
        }
    }


    public static void agregarTrabajo(Connection conn, int id, int personaId, String titulo, double salario) throws SQLException {
        String insert = "INSERT INTO trabajo (id, persona_id, titulo, salario) VALUES (?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(insert)) {
            ps.setInt(1, id);          // id del trabajo
            ps.setInt(2, personaId);   // id de la persona (foreign key)
            ps.setString(3, titulo);   // nombre del trabajo
            ps.setDouble(4, salario);  // sueldo
            ps.executeUpdate();
            conn.commit();
            System.out.println("Trabajor agregado");
        }
    }

}
