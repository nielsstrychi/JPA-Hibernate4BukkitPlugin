package com.nielsstrychi;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.UUID;

public class Database {

    protected static void write() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-unit");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Entity data = new Entity();
        data.setName("Lara");

        em.persist(data);

        tx.commit();
        em.close();
        emf.close();

    }

    protected static void read() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-unit");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Entity data = em.find(Entity.class, 1);

        System.out.println("entity ="+ data);

        tx.commit();
        em.close();
        emf.close();
    }
}
