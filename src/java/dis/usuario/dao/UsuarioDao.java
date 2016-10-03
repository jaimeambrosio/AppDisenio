/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dis.usuario.dao;

import dis.dao.BaseDao;
import dis.dao.ConexionJPA;
import dis.usuario.entity.Usuario;
import dis.util.Val;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Actualizar(Usuario entity) throws Exception {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();

    }

    @Override
    public void Eliminar(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario Obtener(String id) throws Exception {
        return em.find(Usuario.class, id);
    }

    @Override
    public List<Usuario> listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
}
