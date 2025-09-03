package com.practica.javafx.ak4n1.EJ1;


import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String uri = "jdbc:derby:MDerbyDb;create=true";
        try {
            Connection conn = DriverManager.getConnection(uri);
            agregarTrabajo(conn, 1, 1, "Programador", 1500.00);;
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addPerson(Connection conn, int id, String nombre, int edad) throws SQLException {
        String insert = "INSERT INTO persona (id,nombre,edad) VALUES (?,?,?)";
        PreparedStatement ps = conn.prepareStatement(insert);
        ps.setInt(1, id);
        ps.setString(2, nombre);
        ps.setInt(3, edad);
        ps.executeUpdate();
        ps.close();
        conn.commit();

    }

    private static void createTables(Connection conn) {
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
