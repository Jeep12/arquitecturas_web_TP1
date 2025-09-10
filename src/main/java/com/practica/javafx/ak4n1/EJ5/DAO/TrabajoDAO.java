package com.practica.javafx.ak4n1.EJ5.DAO;

import com.practica.javafx.ak4n1.EJ5.entity.Trabajo;

public interface TrabajoDAO {

    void agregarTrabajo(Trabajo trabajo);

    Trabajo buscarPorId(int id);

    void close();
}
