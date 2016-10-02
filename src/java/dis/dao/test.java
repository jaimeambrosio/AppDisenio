/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dis.dao;

import dis.usuario.entity.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Jaime Ambrosio
 */
public class test {

    public static void main(String[] args) {
         EntityManager em = ConexionJPA.getEntityManager();
         Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.correo = :correo AND u.contrasenia = :contrasenia");
        query.setParameter("correo", "correo@correo.com");
        query.setParameter("contrasenia", "12345");
        Usuario u =  (Usuario)query.getSingleResult();
        System.out.println(u);
         /*
        EntityManager em = ConexionJPA.getEntityManager();
        Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.username = :username AND u.contrasenia = :contrasenia");
        query.setParameter("username", "admin");
        query.setParameter("contrasenia", "admin");
        */
    }

}
