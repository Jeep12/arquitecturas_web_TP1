package com.practica.javafx.ak4n1.EJ5.entity;


import javax.persistence.*;

@Entity
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String nombre;

    @Column
    private int edad;


    @ManyToOne
    private Direccion domicilio;

    @ManyToOne
    private Trabajo trabajo;

    public Persona() {
    }

    public Persona(Direccion domicilio, int edad, String nombre) {
        super();
        this.domicilio = domicilio;
        this.edad = edad;
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Direccion getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Direccion domicilio) {
        this.domicilio = domicilio;
    }

    public int getId() {
        return id;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Trabajo getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(Trabajo trabajo) {
        this.trabajo = trabajo;
    }

    @Override
    public String toString() {
        return "\n [ " + id + " , " + nombre + " , " + edad + " ] \n";
    }

}
