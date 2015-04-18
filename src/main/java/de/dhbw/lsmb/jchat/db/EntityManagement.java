/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.lsmb.jchat.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Maurice Busch <busch.maurice@gmx.net>
 */
public class EntityManagement {
    private static EntityManagerFactory emf;

    public static EntityManagerFactory getEntityManagerFactoryInstance() {
        if (emf == null || !emf.isOpen()) {
            String environment = "chat";

            emf = Persistence.createEntityManagerFactory(environment);
        }

         return emf;
    }

    public static EntityManager createEntityManager() {
        return getEntityManagerFactoryInstance().createEntityManager();
    }
    
    
}
