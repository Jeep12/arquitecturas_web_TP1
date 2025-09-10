package com.practica.javafx.ak4n1.EJ5.DAO;

import com.practica.javafx.ak4n1.EJ5.entity.Direccion;

public interface DireccionDAO {

    void agregarDireccion(Direccion direccion);

    Direccion buscarPorId(int id);

    void close();
}
