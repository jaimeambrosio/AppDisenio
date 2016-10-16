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
import dis.entity.Mensaje;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

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
    public Mensaje Insertar(Curso entity) throws Exception {
        Mensaje m = new Mensaje();
        String[] nombres = entity.getNombreCurso().split(" ");
        String cod
                = nombres.length > 1
                        ? String.valueOf(nombres[0].charAt(0)) + nombres[1].charAt(0)
                        : nombres[0].substring(0, 2);
        cod += entity.getNivel() + String.format("%02d", listar().size() + 1);

        if (Obtener(cod) == null) {
            entity.setCodCurso(cod.toUpperCase());
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
    public void Actualizar(Curso entity) throws Exception {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }

    @Override
    public void Eliminar(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Curso Obtener(String id) throws Exception {
        return em.find(Curso.class, id);

    }

    @Override
    public List<Curso> listar() throws Exception {
        try {
            return em.createQuery("SELECT c FROM Curso c").getResultList();
        } catch (Exception e) {
            return null;
        }

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

    public List<Curso> buscar(String txtCodigo, String txtNombre, String estado) {
        String s = "SELECT c FROM Curso c WHERE c.flgActivo = :estado ";
        if (!txtCodigo.isEmpty()) {
            s += " AND c.codCurso = :cod ";
        }
        if (!txtNombre.isEmpty()) {
            s += " AND c.nombreCurso LIKE :nom ";
        }
        Query q = em.createQuery(s);
        q.setParameter("estado", "true".equals(estado));
        if (!txtNombre.isEmpty()) {
            q.setParameter("nom", "%" + txtNombre + "%");
        }
        if (!txtCodigo.isEmpty()) {
            q.setParameter("cod", txtCodigo);
        }
        return q.getResultList();
    }
}
