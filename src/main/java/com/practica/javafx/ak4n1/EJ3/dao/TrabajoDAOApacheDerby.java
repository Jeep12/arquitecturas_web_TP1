package com.practica.javafx.ak4n1.EJ3.dao;

import com.practica.javafx.ak4n1.EJ3.entity.Trabajo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrabajoDAOApacheDerby implements TrabajoDAO {

    private Connection conn;

    public TrabajoDAOApacheDerby(Connection conn) {
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
            System.out.println("Trabajo agregado");
        } catch (SQLException e) {
            System.out.println("Error al agregar trabajo");
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
                System.out.println("Trabajo eliminado");
            } else {
                System.out.println("No se encontró trabajo");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar trabajo");
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
                System.out.println("Trabajo actualizado");
            } else {
                System.out.println("No se encontró trabajo");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar trabajo");
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
            System.out.println("Error buscando trabajos");
        }
        return trabajos;
    }

    @Override
    public Trabajo buscarPorId(int id) {
        Trabajo trabajo = null;
        String sql = "SELECT * FROM trabajo WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    trabajo = new Trabajo(
                            rs.getInt("id"),
                            rs.getInt("persona_id"),
                            rs.getString("titulo"),
                            rs.getDouble("salario")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar trabajo");
        }
        return trabajo;
    }
}
