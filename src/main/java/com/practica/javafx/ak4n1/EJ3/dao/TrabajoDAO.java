package com.practica.javafx.ak4n1.EJ3.dao;

import com.practica.javafx.ak4n1.EJ3.entity.Trabajo;

import java.util.List;

public interface TrabajoDAO {


    void agregarTrabajo(Trabajo trabajo);

    void eliminarTrabajo(int id);

    void actualizarTrabajo(Trabajo trabajo);

    List<Trabajo> listarTrabajos();

    Trabajo buscarPorId(int id);
}

