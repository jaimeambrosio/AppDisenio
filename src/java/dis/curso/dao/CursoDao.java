/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dis.curso.dao;

import dis.curso.entity.Carrera;
import dis.curso.entity.Curso;
import dis.dao.BaseDao;
import dis.dao.ConexionJPA;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Jaime Ambrosio
 */
public class CursoDao implements BaseDao<Curso, String> {

    EntityManager em;

    public CursoDao() {
        em = ConexionJPA.getEntityManager();
    }

    @Override
    public void Insertar(Curso entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Actualizar(Curso entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Eliminar(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Curso Obtener(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Curso> listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Carrera> listarCarrera() {
        try {
            return em.createQuery("SELECT c FROM Carrera c").getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public Carrera ObtenerCarrera(Integer valueOf) {
        return em.find(Carrera.class, valueOf);
    }
}
