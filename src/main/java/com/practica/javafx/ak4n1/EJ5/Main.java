package com.practica.javafx.ak4n1.EJ5;

import com.practica.javafx.ak4n1.EJ5.DAO.*;
import com.practica.javafx.ak4n1.EJ5.entity.Direccion;
import com.practica.javafx.ak4n1.EJ5.entity.Persona;
import com.practica.javafx.ak4n1.EJ5.entity.Trabajo;

public class Main {
    public static void main(String[] args) {

        DireccionDAO ddao = new DireccionDAOMariaDB();
        PersonaDAO pdao = new PersonaDAOMariaDB();
        TrabajoDAO tdao = new TrabajoDAOMariaDB();

        Trabajo t1 = tdao.buscarPorId(12);
        Trabajo t2 = tdao.buscarPorId(13);
        Trabajo t3 = tdao.buscarPorId(14);

        Direccion d2 = ddao.buscarPorId(2);
        Direccion d3 = ddao.buscarPorId(3);
        Direccion d4 = ddao.buscarPorId(4);
        Direccion d5 = ddao.buscarPorId(5);

        Persona p1 = new Persona(d2, 25, "Ana");
        p1.setTrabajo(t1);

        Persona p2 = new Persona(d3, 32, "Juan");
        p2.setTrabajo(t2);

        Persona p3 = new Persona(d4, 28, "Eduardo");
        p3.setTrabajo(t3);

        Persona p4 = new Persona(d5, 40, "María");
        p4.setTrabajo(t1);

        pdao.agregarPersona(p1);
        pdao.agregarPersona(p2);
        pdao.agregarPersona(p3);
        pdao.agregarPersona(p4);

        ddao.close();
        pdao.close();
        tdao.close();

        System.out.println("✔ Personas agregadas correctamente.");
    }
}
