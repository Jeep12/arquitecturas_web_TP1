package com.practica.javafx.ak4n1.EJ3;

import com.practica.javafx.ak4n1.EJ3.dao.PersonaDAO;
import com.practica.javafx.ak4n1.EJ3.dao.PersonaDAOApacheDerby;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try (Connection conn = ConexionApacheDerby.getInstance()) {

            PersonaDAO pdao = new PersonaDAOApacheDerby(conn);

            System.out.println(pdao.listarPersonas());

        } catch (SQLException e) {
            System.err.println("error en la base de datos");
        }

        // conn.close(); La conexion se cierra automaticamente al terminar el try/catch
    }
}
