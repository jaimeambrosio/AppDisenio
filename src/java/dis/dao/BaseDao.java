/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dis.dao;

import java.util.List;

/**
 *
 * @author Jaime Ambrosio
 */
public interface BaseDao<E,ID> {
    
    public void Insertar(E entity)throws Exception;
    public void Actualizar(E entity)throws Exception;
    public void Eliminar(ID id)throws Exception;
    public E Obtener(ID id)throws Exception;
    public List<E> listar()throws Exception;
    
}
