/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dis.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Jaime Ambrosio
 */
public class ConexionJPA {

    private static EntityManagerFactory EMF;
    private static EntityManager em;

    public static EntityManager getEntityManager() {
        if (em == null) {
            EMF = Persistence.createEntityManagerFactory("AppDisenioPU");
            em = EMF.createEntityManager();
        }
        return em;
    }
}
