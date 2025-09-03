package com.practica.javafx.ak4n1.EJ3.dao;

import com.practica.javafx.ak4n1.EJ3.entity.Persona;

import java.util.List;

public interface PersonaDAO {

    void agregarPersona(Persona persona);

    void eliminarPersona(int id);

    void actualizarPersona(Persona persona);

    List<Persona> listarPersonas();

    Persona buscarPorId(int id);

}
