package com.practica.javafx.ak4n1.EJ5.DAO;

import com.practica.javafx.ak4n1.EJ5.entity.Trabajo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TrabajoDAOMariaDB implements TrabajoDAO {

    EntityManagerFactory emf;

    EntityManager em;

    public TrabajoDAOMariaDB() {
        this.emf = Persistence.createEntityManagerFactory("EJ5");
        this.em = emf.createEntityManager();
    }


    @Override
    public void agregarTrabajo(Trabajo trabajo) {
        em.getTransaction().begin();
        em.persist(trabajo);
        em.getTransaction().commit();
    }

    @Override
    public Trabajo buscarPorId(int id) {
        Trabajo trabajo = null;
        em.getTransaction().begin();
        Trabajo d = em.find(Trabajo.class, id);
        if (d != null) {
            trabajo = d;
        }
        em.getTransaction().commit();

        return trabajo;
    }

    @Override
    public void close() {
        em.close();
        emf.close();

    }
}
