<%-- 
    Document   : p_usuarios
    Created on : 03/10/2016, 03:10:57 AM
    Author     : Jaime Ambrosio
--%>

<%@page import="dis.curso.entity.Carrera"%>
<%@page import="dis.sede.entity.Sede"%>
<%@page import="dis.sede.dao.SedeDao"%>
<%@page import="dis.curso.dao.CursoDao"%>
<%@page import="java.util.List"%>
<%@page import="dis.usuario.entity.Tipousuario"%>
<%@page import="dis.usuario.dao.UsuarioDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    UsuarioDao usuarioDao = new UsuarioDao();
    CursoDao cursoDao = new CursoDao();
    SedeDao sedeDao = new SedeDao();
    List<Tipousuario> listTipoUsu = usuarioDao.listarTipos();
    List<Carrera> listCarrer = cursoDao.listarCarrera();
    List<Sede> listSede = sedeDao.listar();
%>
<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title" id="tituloPanel"></h3>
    </div>
    <div class="panel-body">
        <div class="row">
            <div class="col-md-4">
                <button onclick="openModalUsuario(1, true);" type="button" class="btn btn-default btn-block">
                    <span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
                    Nuevo Administrador
                </button>
            </div>
            <div class="col-md-4">

                <button onclick="openModalUsuario(2, true);" type="button" class="btn btn-default btn-block">
                    <span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
                    Nuevo Docente
                </button>
            </div>
            <div class="col-md-4">
                <button onclick="openModalUsuario(3, true);" type="button" class="btn btn-default btn-block">
                    <span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
                    Nuevo Almuno
                </button>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-sm-12">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <form id="idFormBusquedaUsuarios"  method="POST">
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="txtCodUsuario">Codigo Usuario</label>
                                        <input type="text" class="form-control" id="txtCodUsuario"  name="txtCodUsuario" placeholder="Codigo Usuario">
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="txtCodUsuario">Apellidos</label>
                                        <input type="text" class="form-control" id="txtApellidos" name="txtApellidos" placeholder="Apellidos">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="estado">Estado</label>
                                        <select  class="form-control" name="estado" id="estado">
                                            <option value="1" >Activo</option>
                                            <option value="0" >Inactivo</option>
                                        </select>

                                    </div>
                                </div>
                                <div class="col-sm-6">
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

        <div class="modal fade" data-backdrop="false" id="modalEdicionAdmin" tabindex="-1" role="dialog" >
            <div class="modal-dialog">
                <div class="modal-content"> 
                    <form id="idFormUsuarioAdmin" method="POST">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title"   >
                                <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                                Usuario
                            </h4>
                        </div>
                        <div class="modal-body">

                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label >Codigo </label>
                                        <input  class="form-control" id="txtCodigo" name="txtCodigo"  value="" readonly="readonly" >
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label class="required" >Nombres </label>
                                        <input  class="form-control" id="txtNombre" name="txtNombre"  required="" >
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label class="required" >Apellidos </label>
                                        <input  class="form-control" id="txtApellido" name="txtApellido"  required="" >
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label class="required" >DNI </label>
                                        <input  class="form-control" id="txtDNI" name="txtDNI"  required="" minlength="8" maxlength="8" >
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label class="required" >Fecha nacimiento </label>
                                        <input type="date"  class="form-control" id="txtNacimiento" name="txtNacimiento"  required="" >
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label class="required" >Sexo </label>
                                        <select class="form-control" id="cbxSexo" name="cbxSexo"  required="" >
                                            <option value="">Seleccione</option>
                                            <option value="true">Masculino</option>
                                            <option value="false">Femenino</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label class="required" >Celular </label>
                                        <input type="number" max="1000000000"    class="form-control" id="txtCelular" name="txtCelular"  required="" >
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label class="required" >Correo </label>
                                        <input type="email" class="form-control" id="txtCorreo" name="txtCorreo"  required="" >
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label class="required" >Contraseña </label>
                                        <input type="password" class="form-control" id="txtContrasenia" name="txtContrasenia"  required="" >
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label class="required" >Estado </label>
                                        <select class="form-control" id="cbxEstado" name="cbxEstado"  required="" >
                                            <option value="true">Activo</option>
                                            <option value="false">Inactivo</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label class="required" >Tipo usuario </label>
                                        <select disabled="" class="form-control" id="cbxTipoUsuario" name="cbxTipoUsuario"  required="" >
                                            <% if (listTipoUsu != null) {
                                                    for (Tipousuario t : listTipoUsu) {
                                            %>
                                            <option value="<%=t.getCodTipoUsuario()%>" ><%=t.getNombreTipoUsuario()%></option>
                                            <%
                                                    }
                                                }%>
                                        </select>
                                        <input type="hidden" value="" name="txtTipoUsuario" id="txtTipoUsuario" >

                                    </div>
                                </div>
                                <div class="col-sm-6">
                                </div>
                            </div>
                            <div id="idSectionAlumno">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label class="required" >Colegio Procedencia </label>
                                            <input type="text" class="form-control" id="txtColegioProc" name="txtColegioProc"  required="" >
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label class="required" >Carrera </label>
                                            <select  class="form-control" id="cbxCarrera" name="cbxCarrera"  required="" >
                                                <option value="" >Seleccione</option>
                                                <% if (listCarrer != null) {
                                                        for (Carrera t : listCarrer) {
                                                %>
                                                <option value="<%=t.getCodCarrera()%>" ><%=t.getNombreCarrera()%></option>
                                                <%
                                                        }
                                                    }%>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label class="required" >Sede </label>
                                            <select  class="form-control" id="cbxSede" name="cbxSede"  required="" >
                                                <option value="" >Seleccione</option>
                                                <% if (listSede != null) {
                                                        for (Sede t : listSede) {
                                                %>
                                                <option value="<%=t.getCodSede()%>" ><%=t.getNombreSede()%></option>
                                                <%
                                                        }
                                                    }%>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">

                                    </div>
                                </div>

                            </div>
                            <div id="idSectionDocente">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label class="form-control" >
                                                Es tiempo completo 
                                                <input type="checkbox" id="cbTiempoCom" name="cbTiempoCom" > 
                                            </label>

                                        </div>
                                    </div>
                                </div>

                            </div>

                            <br>
                            <label  >(<label class="required" ></label>)Campos obligatorios</label>

                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary" >Guardar</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                        </div>
                    </form>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->

    </div>
</div>