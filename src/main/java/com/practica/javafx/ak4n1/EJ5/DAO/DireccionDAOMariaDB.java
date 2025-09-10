package com.practica.javafx.ak4n1.EJ5.DAO;

import com.practica.javafx.ak4n1.EJ5.entity.Direccion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DireccionDAOMariaDB implements DireccionDAO {


    EntityManagerFactory emf;

    EntityManager em;

    public DireccionDAOMariaDB() {
        this.emf = Persistence.createEntityManagerFactory("EJ5");
        this.em = emf.createEntityManager();
    }

    @Override
    public void agregarDireccion(Direccion direccion) {
        em.getTransaction().begin();
        em.persist(direccion);
        em.getTransaction().commit();
    }

    @Override
    public Direccion buscarPorId(int id) {
        Direccion direccion = null;
        em.getTransaction().begin();
        Direccion d = em.find(Direccion.class, id);
        if (d != null) {
            direccion = d;
        }
        em.getTransaction().commit();
        return direccion;
    }


    @Override
    public void close(){
        em.close();
        emf.close();

    }

}
