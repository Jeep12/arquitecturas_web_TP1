package com.practica.javafx.ak4n1.EJ5.DAO;

import com.practica.javafx.ak4n1.EJ5.entity.Persona;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersonaDAOMariaDB implements PersonaDAO {

    EntityManagerFactory emf;

    EntityManager em;

    public PersonaDAOMariaDB() {
        this.emf = Persistence.createEntityManagerFactory("EJ5");
        this.em = emf.createEntityManager();
    }

    @Override
    public void agregarPersona(Persona persona) {
        em.getTransaction().begin();
        em.persist(persona);
        em.getTransaction().commit();
    }

    @Override
    public void close() {
        em.close();
        emf.close();

    }
}
