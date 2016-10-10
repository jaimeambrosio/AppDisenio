<%-- 
    Document   : modalGlbUsuario
    Created on : 09/10/2016, 03:53:25 PM
    Author     : Jaime Ambrosio
--%>

<%@page import="dis.sede.entity.Sede"%>
<%@page import="dis.sede.dao.SedeDao"%>
<%@page import="dis.curso.dao.CursoDao"%>
<%@page import="dis.usuario.dao.UsuarioDao"%>
<%@page import="java.util.List"%>
<%@page import="dis.curso.entity.Carrera"%>
<%@page import="dis.usuario.entity.Tipousuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    UsuarioDao usuarioDao = new UsuarioDao();
    CursoDao cursoDao = new CursoDao();
    SedeDao sedeDao = new SedeDao();
    List<Tipousuario> listTipoUsu = usuarioDao.listarTipos();
    List<Carrera> listCarrer = cursoDao.listarCarrera();
    List<Sede> listSede = sedeDao.listar();
%>
<div class="modal fade" data-backdrop="false" id="modalGlbEdicion" tabindex="-1" role="dialog" >
    <div class="modal-dialog">
        <div class="modal-content"> 
            <form id="idGlbFormUsuario" method="POST">
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
                                <input  type="text"   class="form-control" id="txtGlbCodigo" name="txtGlbCodigo"  value="" readonly="readonly" >
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label class="required" >Nombres </label>
                                <input  type="text"  class="form-control" id="txtGlbNombre" name="txtGlbNombre"  required="" >
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label class="required" >Apellidos </label>
                                <input  type="text"  class="form-control" id="txtGlbApellido" name="txtGlbApellido"  required="" >
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label class="required" >DNI </label>
                                <input  type="text"  class="form-control" id="txtGlbDNI" name="txtGlbDNI"  required="" minlength="8" maxlength="8" >
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label class="required" >Fecha nacimiento </label>
                                <input type="date"  class="form-control" id="txtGlbNacimiento" name="txtGlbNacimiento"  required="" >
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label class="required" >Sexo </label>
                                <select class="form-control" id="cbxGlbSexo" name="cbxGlbSexo"  required="" >
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
                                <input type="number" max="1000000000"    class="form-control" id="txtGlbCelular" name="txtGlbCelular"  required="" >
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label class="required" >Correo </label>
                                <input type="email" class="form-control" id="txtGlbCorreo" name="txtGlbCorreo"  required="" >
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label class="required" >Contraseña </label>
                                <input type="password" class="form-control" id="txtGlbContrasenia" name="txtGlbContrasenia"  required="" >
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label class="required" >Estado </label>
                                <select class="form-control" id="cbxGlbEstado" name="cbxGlbEstado"  required="" >
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
                                <select disabled="" class="form-control" id="cbxGlbTipoUsuario" name="cbxGlbTipoUsuario"  required="" >
                                    <% if (listTipoUsu != null) {
                                            for (Tipousuario t : listTipoUsu) {
                                    %>
                                    <option value="<%=t.getCodTipoUsuario()%>" ><%=t.getNombreTipoUsuario()%></option>
                                    <%
                                            }
                                        }%>
                                </select>
                            </div>
                        </div>
                        <div class="col-sm-6">
                        </div>
                    </div>
                    <div id="idGlbSectionAlumno">
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label class="required" >Colegio Procedencia </label>
                                    <input type="text" class="form-control" id="txtGlbColegioProc" name="txtGlbColegioProc"  required="" >
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label class="required" >Carrera </label>
                                    <select  class="form-control" id="cbxGlbCarrera" name="cbxGlbCarrera"  required="" >
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
                                    <select  class="form-control" id="cbxGlbSede" name="cbxGlbSede"  required="" >
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
                    <div id="idGlbSectionDocente">
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label class="form-control" >
                                        Es tiempo completo 
                                        <input type="checkbox" id="cbGlbTiempoCom" name="cbGlbTiempoCom" > 
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