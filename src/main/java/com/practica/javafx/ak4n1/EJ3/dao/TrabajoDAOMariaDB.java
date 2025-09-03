package com.practica.javafx.ak4n1.EJ3.dao;

import com.practica.javafx.ak4n1.EJ3.entity.Trabajo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrabajoDAOMariaDB implements TrabajoDAO {

    private Connection conn;

    public TrabajoDAOMariaDB(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void agregarTrabajo(Trabajo trabajo) {
        String sql = "INSERT INTO trabajo (id, persona_id, titulo, salario) VALUES (?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, trabajo.getId());
            ps.setInt(2, trabajo.getPersonaId());
            ps.setString(3, trabajo.getTitulo());
            ps.setDouble(4, trabajo.getSalario());
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            System.out.println("error al agregar trabajo");
        }
    }

    @Override
    public void eliminarTrabajo(int id) {
        String sql = "DELETE FROM trabajo WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            conn.commit();
            if (filas > 0) {
                System.out.println("trabajo eliminado");
            } else {
                System.out.println("no se encontro trabajo");
            }
        } catch (SQLException e) {
            System.out.println("error al eliminar trabajo");
        }
    }

    @Override
    public void actualizarTrabajo(Trabajo trabajo) {
        String sql = "UPDATE trabajo SET titulo = ?, salario = ?, persona_id = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, trabajo.getTitulo());
            ps.setDouble(2, trabajo.getSalario());
            ps.setInt(3, trabajo.getPersonaId());
            ps.setInt(4, trabajo.getId());
            int filas = ps.executeUpdate();
            conn.commit();
            if (filas > 0) {
                System.out.println("trabajo actualizado");
            } else {
                System.out.println("no se encontro trabajo");
            }
        } catch (SQLException e) {
            System.out.println("error al actualizar trabajo");
        }
    }

    @Override
    public List<Trabajo> listarTrabajos() {
        List<Trabajo> trabajos = new ArrayList<>();
        String sql = "SELECT * FROM trabajo";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Trabajo t = new Trabajo(
                        rs.getInt("id"),
                        rs.getInt("persona_id"),
                        rs.getString("titulo"),
                        rs.getDouble("salario")
                );
                trabajos.add(t);
            }

        } catch (SQLException e) {
            System.out.println("error buscando trabajos");
        }
        return trabajos;
    }

    @Override
    public Trabajo buscarPorId(int id) {
        String sql = "SELECT * FROM trabajo WHERE id = ?";
        Trabajo trabajo = null;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int trabajoId = rs.getInt("id");
                    int personaId = rs.getInt("persona_id");
                    String titulo = rs.getString("titulo");
                    double salario = rs.getDouble("salario");
                    trabajo = new Trabajo(trabajoId, personaId, titulo, salario);
                }
            }
        } catch (SQLException e) {
            System.out.println("error al buscar trabajo");
        }

        return trabajo;
    }
}
