/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dis.dao;

import dis.usuario.entity.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jaime Ambrosio
 */
public class test {

    public static void main(String[] args) {

        EntityManager em = ConexionJPA.getEntityManager();
        System.out.println(em.find(Usuario.class, "sd"));
        /*
        TypedQuery<Usuario> qFindByCorreo = em.createNamedQuery("Usuario.findByCodUsuario", Usuario.class);
        qFindByCorreo.setParameter("codUsuario", "D201620001");
        Usuario u = qFindByCorreo.getSingleResult();
        System.out.println(u);*/

    }

}
