package com.practica.javafx.ak4n1.EJ5.entity;


import javax.persistence.*;
import java.util.List;

@Entity
public class Trabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String titulo;

    @Column
    private double salario;


    @OneToMany(mappedBy = "trabajo")
    private List<Persona> empleados;

    public Trabajo(){

    }
    public Trabajo(double salario, String titulo) {
        this.salario = salario;
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }


    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "Trabajo{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", salario=" + salario +
                '}';
    }
}
