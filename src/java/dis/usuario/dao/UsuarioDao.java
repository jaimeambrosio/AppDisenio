/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dis.usuario.dao;

import dis.dao.BaseDao;
import dis.dao.ConexionJPA;
import dis.usuario.entity.Alumno;
import dis.usuario.entity.Docente;
import dis.usuario.entity.Tipousuario;
import dis.usuario.entity.Usuario;
import dis.util.Val;
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
public class UsuarioDao implements BaseDao<Usuario, String> {

    private EntityManager em = null;

    public UsuarioDao() {
        em = ConexionJPA.getEntityManager();
    }

    @Override
    public void Insertar(Usuario entity) throws Exception {
        entity.setNombre(entity.getNombre().toUpperCase());
        entity.setApellido(entity.getApellido().toUpperCase());
        String cod = null;
        if (null != entity.getCodTipoUsuario().getCodTipoUsuario()) {
            switch (entity.getCodTipoUsuario().getCodTipoUsuario()) {
                case 3: //alumno
                    Calendar c = new GregorianCalendar();
                    c.setTime(new Date());
                    cod = "A" + c.get(Calendar.YEAR);
                    if (c.get(Calendar.MONTH) < 7) {
                        cod += "1";
                    } else {
                        cod += "2";
                    }
                    cod += String.format("%04d", listarSoloAlumnos().size() + 1);
                    break;
                case 2: //docente
                    cod = "PC";
                    cod += entity.getNombre().charAt(0);
                    cod += entity.getApellido().substring(0, 3);
                    cod = cod.toUpperCase();
                    break;
                case 1:
                    cod = "AD";
                    cod += entity.getNombre().charAt(0);
                    cod += entity.getApellido().substring(0, 3);
                    cod = cod.toUpperCase();
                    break;
                default:
                    break;
            }
        }
        entity.setCodUsuario(cod);

        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    public void InsertarALumno(Alumno entity) throws Exception {

        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    public void InsertarDocente(Docente entity) throws Exception {

        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    @Override
    public void Actualizar(Usuario entity) throws Exception {
        entity.setNombre(entity.getNombre().toUpperCase());
        entity.setApellido(entity.getApellido().toUpperCase());
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }

    @Override
    public void Eliminar(String id) throws Exception {

    }

    @Override
    public Usuario Obtener(String id) throws Exception {
        try {
            return em.find(Usuario.class, id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Usuario> listar() throws Exception {

        return em.createQuery("SELECT u FROM Usuario u ").getResultList();
    }

    public List<Usuario> listarSoloAlumnos() throws Exception {

        return em.createQuery("SELECT u FROM Usuario u WHERE u.codTipoUsuario.codTipoUsuario=3").getResultList();
    }

    public List<Usuario> busquedaPorCampos(String codigo, String apellidos, String estado) throws Exception {
        //SELECT u FROM Usuario u WHERE u.username = :username AND u.contrasenia = :contrasenia"

        String s = "SELECT u FROM Usuario u WHERE u.flgActivo = :estado ";
        if (Val.hayText(codigo)) {
            s += " AND u.codUsuario = :codigo ";
        }
        if (Val.hayText(apellidos)) {
            s += " AND u.apellido LIKE :apellidos";
        }
        Query query = em.createQuery(s);
        query.setParameter("estado", "1".equals(estado));
        if (Val.hayText(codigo)) {
            query.setParameter("codigo", codigo);
        }
        if (Val.hayText(apellidos)) {
            query.setParameter("apellidos", "%" + apellidos + "%");
        }
        return query.getResultList();
    }

    public List<Tipousuario> listarTipos() {
        try {
            return em.createQuery("SELECT t FROM Tipousuario t").getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public Alumno ObtenerAlumno(String codigoAl) {
        try {
            return em.find(Alumno.class, codigoAl);
        } catch (Exception e) {
            return null;
        }
    }

    public Docente ObtenerDocente(String codigoDocente) {
        try {
            return em.find(Docente.class, codigoDocente);
        } catch (Exception e) {
            return null;
        }
    }

    public Tipousuario ObtenerTipo(String txtTipoUsuario) {
        return em.find(Tipousuario.class, Integer.valueOf(txtTipoUsuario));
    }

    public void ActualizarAlumno(Alumno a) {
        em.getTransaction().begin();
        em.merge(a);
        em.getTransaction().commit();
    }

    public void ActualizarDocente(Docente d) {
        em.getTransaction().begin();
        em.merge(d);
        em.getTransaction().commit();
    }

    public boolean existeDni(Usuario u) {
        String s = "SELECT u FROM Usuario u WHERE NOT(u.codUsuario = :cod) AND u.dni = :dni";
        Query q = em.createQuery(s);
        q.setParameter("cod", u.getCodUsuario() == null ? "" : u.getCodUsuario());
        q.setParameter("dni", u.getDni());
        List<Usuario> list = q.getResultList();
        return q.getResultList().size() > 0;
    }
}
