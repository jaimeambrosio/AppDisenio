/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dis.sede.dao;

import dis.dao.BaseDao;
import dis.dao.ConexionJPA;
import dis.sede.entity.Sede;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Jaime Ambrosio
 */
public class SedeDao implements BaseDao<Sede, String>{
    EntityManager em = null;
    public SedeDao(){
        em = ConexionJPA.getEntityManager();
    }
    

    @Override
    public void Insertar(Sede entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Actualizar(Sede entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Eliminar(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sede Obtener(String id) throws Exception {
        return em.find(Sede.class, id);
    }

    @Override
    public List<Sede> listar() throws Exception {
            try {
            return em.createQuery("SELECT s FROM Sede s").getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    
    
}
