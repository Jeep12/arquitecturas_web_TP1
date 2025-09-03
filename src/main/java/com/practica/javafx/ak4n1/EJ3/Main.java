package com.practica.javafx.ak4n1.EJ3;

import com.practica.javafx.ak4n1.EJ2.Select;
import com.practica.javafx.ak4n1.EJ3.dao.PersonaDAO;
import com.practica.javafx.ak4n1.EJ3.dao.PersonaDAOMariaDB;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try (Connection conn = Conexion.getInstance()) {

            PersonaDAO pdao = new PersonaDAOMariaDB(conn);

            System.out.println(pdao.buscarPorId(1));

        } catch (SQLException e) {
            System.err.println("error en la base de datos");
        }

        // conn.close(); La conexion se cierra automaticamente al terminar el try/catch
    }
}
