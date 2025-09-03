package com.practica.javafx.ak4n1.EJ2;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try (Connection conn = Conexion.getInstance()) {

            Select.mostrarPersonas(conn);
            Select.mostrarPersonasConTrabajos(conn);
        } catch (SQLException e) {
            System.err.println("error en la base de datos");
        }

        // conn.close(); La conexion se cierra automaticamente al terminar el try/catch
    }
}
