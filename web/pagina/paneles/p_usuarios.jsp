<%-- 
    Document   : p_usuarios
    Created on : 03/10/2016, 03:10:57 AM
    Author     : Jaime Ambrosio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title" id="tituloPanel"></h3>
    </div>
    <div class="panel-body">
        <div class="row">
            <div class="col-md-4">
                <button type="button" class="btn btn-default btn-block">
                    <span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
                    Nuevo Administrador
                </button>
            </div>
            <div class="col-md-4">
                <button type="button" class="btn btn-default btn-block">
                    <span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
                    Nuevo Almuno
                </button>
            </div>
            <div class="col-md-4">
                <button type="button" class="btn btn-default btn-block">
                    <span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
                    Nuevo Docente
                </button>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <form id="idFormBusquedaUsuarios"  method="POST">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="txtCodUsuario">Codigo Usuario</label>
                                        <input type="text" class="form-control" id="txtCodUsuario"  name="txtCodUsuario" placeholder="Codigo Usuario">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="txtCodUsuario">Apellidos</label>
                                        <input type="text" class="form-control" id="txtApellidos" name="txtApellidos" placeholder="Apellidos">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                     <div class="form-group">
                                        <label for="estado">Estado</label>
                                        <select  class="form-control" name="estado" id="estado">
                                            <option value="1" >Activo</option>
                                            <option value="0" >Inactivo</option>
                                        </select>
                                        
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <br>
                                    <button id="btnBuscarUsuarios" type="submit" class="btn btn-primary btn-block">
                                        Buscar
                                    </button>
                                </div>
                            </div>

                        </form>
                    </div>
                </div>
                <table  id="tblUsuarios" class="display nowrap" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th>Codigo</th>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Tipo</th>
                            <th>Estado</th>
                            <th>...</th>
                        </tr>
                    </thead>
                    <tbody>
                        
                    </tbody>
                </table>

                

            </div>
        </div>

    </div>
</div>