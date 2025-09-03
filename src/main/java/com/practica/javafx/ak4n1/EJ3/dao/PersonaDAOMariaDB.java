package com.practica.javafx.ak4n1.EJ3.dao;

import com.practica.javafx.ak4n1.EJ3.entity.Persona;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAOMariaDB implements PersonaDAO {


    private Connection conn;

    public PersonaDAOMariaDB(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void agregarPersona(Persona persona) {
        String sql = "INSERT INTO persona (id,nombre,edad) VALUES (?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, persona.getId());
            ps.setString(2, persona.getNombre());
            ps.setInt(3, persona.getEdad());
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            System.out.println("error al agregar persona");
        }
    }

    @Override
    public void eliminarPersona(int id) {
        String sql = "DELETE FROM persona WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);     //Asigna el parametro 1 al id
            int filas = ps.executeUpdate();
            conn.commit();
            if (filas > 0) {
                System.out.println("persona eliminada");
            } else {
                System.out.println("no se encontro persona");
            }
        } catch (SQLException e) {
            System.out.println("error al eliminar persona");
        }
    }


    @Override
    public void actualizarPersona(Persona persona) {
        String sql = "UPDATE persona SET nombre = ?, edad = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, persona.getNombre());
            ps.setInt(2, persona.getEdad());
            ps.setInt(3, persona.getId());
            int filas = ps.executeUpdate();
            conn.commit();
            if (filas > 0) {
                System.out.println("persona actualizada");
            } else {
                System.out.println("no se encontro persona");
            }
        } catch (SQLException e) {
            System.out.println("error al actualizar persona");

        }
    }


    @Override
    public List<Persona> listarPersonas() {
        List<Persona> personas = new ArrayList<>();
        String sql = "SELECT * FROM persona";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Persona p = new Persona(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("edad")
                );
                personas.add(p);
            }
        } catch (SQLException e) {
            System.out.println("error buscando personas");
        }
        return personas;
    }

    @Override
    public Persona buscarPorId(int id) {
        String sql = "SELECT * FROM persona WHERE id = ?";
        Persona persona = null;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int personaId = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    int edad = rs.getInt("edad");
                    persona = new Persona(personaId, nombre, edad);
                }
            }
        } catch (SQLException e) {
            System.out.println("error al buscar persona");
        }

        return persona;
    }
}
