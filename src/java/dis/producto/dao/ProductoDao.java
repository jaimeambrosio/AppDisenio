/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dis.producto.dao;

import dis.dao.BaseDao;
import dis.dao.ConexionJPA;
import dis.entity.Mensaje;
import dis.producto.entity.Producto;
import dis.producto.entity.Tipoproducto;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

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
    public Mensaje Insertar(Producto entity) throws Exception {
        Mensaje m = new Mensaje();
        Calendar c = new GregorianCalendar();
        c.setTime(new Date());
        String cod = "P" + c.get(Calendar.YEAR) + String.format("%03d", listar().size() + 1);
        if (Obtener(cod) == null) {
            entity.setCodProducto(cod.toUpperCase());
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            return null;
        } else {
            m.setMensaje(Mensaje.ERROR, "El codigo generado ya existe: " + cod);
            return m;
        }
    }

    @Override
    public void Actualizar(Producto entity) throws Exception {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }

    @Override
    public void Eliminar(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Producto Obtener(String id) throws Exception {
        return em.find(Producto.class, id);
    }

    @Override
    public List<Producto> listar() throws Exception {
        try {
            return em.createNamedQuery("Producto.findAll").getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Tipoproducto> listarTiposProd() {
        try {
            return em.createNamedQuery("Tipoproducto.findAll").getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public Tipoproducto ObtenerTipo(String cbxTipoProducto) {
        try {
            return em.find(Tipoproducto.class, Integer.valueOf(cbxTipoProducto));
        } catch (Exception e) {
            return null;
        }
    }

    public List<Producto> buscar(String txtCodigo, String txtNombre, String estado, String cbxTipo) {
        String s = "SELECT p FROM Producto p WHERE p.flgActivo = :est ";
        s += txtCodigo.isEmpty() ? "" : " AND p.codProducto = :cod";
        s += txtNombre.isEmpty() ? "" : " AND p.nombre LIKE :nom";
        s += cbxTipo.isEmpty() ? "" : " AND p.codTipoProducto.codTipoProducto = :tipo";
        Query q = em.createQuery(s);
        q.setParameter("est", Boolean.valueOf(estado));
        if (!txtCodigo.isEmpty()) {
            q.setParameter("cod", txtCodigo);
        }
        if (!txtNombre.isEmpty()) {
            q.setParameter("nom", "%" + txtNombre + "%");
        }
        if (!cbxTipo.isEmpty()) {
            q.setParameter("tipo", Integer.valueOf(cbxTipo));
        }
        return q.getResultList();
    }

}
