package com.practica.javafx.ak4n1.EJ3.entity;

public class Trabajo {

    private int id;
    private int personaId;
    private String titulo;
    private double salario;

    public Trabajo(int id, int personaId, String titulo, double salario) {
        this.id = id;
        this.personaId = personaId;
        this.titulo = titulo;
        this.salario = salario;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
