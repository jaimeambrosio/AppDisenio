/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dis.producto.dao;

import dis.dao.BaseDao;
import dis.dao.ConexionJPA;
import dis.producto.entity.Producto;
import dis.producto.entity.Tipoproducto;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Jaime Ambrosio
 */
public class ProductoDao implements BaseDao<Producto, String> {

    EntityManager em = null;

    public ProductoDao() {
        em = ConexionJPA.getEntityManager();
    }

    @Override
    public void Insertar(Producto entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Actualizar(Producto entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Eliminar(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Producto Obtener(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Producto> listar() throws Exception {
        try {
            return em.createNamedQuery("Producto.findAll").getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    public List<Tipoproducto> listarTiposProd()
    {
        try {
            return em.createNamedQuery("Tipoproducto.findAll").getResultList();
        } catch (Exception e) {
            return null;
        }
    }

}
