<%-- 
    Document   : p_usuarios
    Created on : 03/10/2016, 03:10:57 AM
    Author     : Jaime Ambrosio
--%>

<%@page import="java.util.List"%>
<%@page import="dis.curso.entity.Carrera"%>
<%@page import="dis.curso.dao.CursoDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    CursoDao cursoDao = new CursoDao();
    List<Carrera> listCarr = cursoDao.listarCarrera();
%>
<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title" id="tituloPanel"></h3>
    </div>
    <div class="panel-body">
        <div class="row">
            <div class="col-md-4">
                <button onclick="openModalCurso(true);" type="button" class="btn btn-default btn-block">
                    <span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
                    Nuevo curso
                </button>
            </div>
            <div class="col-md-4"></div>
            <div class="col-md-4"></div>
        </div>
        <br>
        <div class="row" >
            <div class="col-sm-12">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <form id="idFormBusqCurso"  method="POST">
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="txtCodigo">Codigo curso</label>
                                        <input type="text" class="form-control" id="txtCodigo"  name="txtCodigo" placeholder="Codigo">
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="txtNombre">Nombre curso</label>
                                        <input type="text" class="form-control" id="txtNombre" name="txtNombre" placeholder="Nombre">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="estado">Estado</label>
                                        <select  class="form-control" name="estado" id="estado">
                                            <option value="true" >Activo</option>
                                            <option value="false" >Inactivo</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <br>
                                    <button id="btnBuscarCurso" type="submit" class="btn btn-primary btn-block">
                                        Buscar
                                    </button>
                                </div>
                            </div>



                        </form>
                    </div>
                </div>
                <table  id="tblCursos" class="display nowrap" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th>Codigo</th>
                            <th>Nombre curso</th>
                            <th>Nivel</th>
                            <th>Cant. creditos</th>
                            <th>Carrera</th>
                            <th>Estado</th>
                            <th>...</th>
                        </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
        </div>
        <div class="modal fade" data-backdrop="false" id="modalEdicionCurso" tabindex="-1" role="dialog" >
            <div class="modal-dialog">
                <div class="modal-content"> 
                    <form id="idFormCurso" method="POST">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title"   >
                                <span class="glyphicon glyphicon-book" aria-hidden="true"></span>
                                Curso
                            </h4>
                        </div>
                        <div class="modal-body">

                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label >Codigo </label>
                                        <input  class="form-control" id="txtCodigoCurso" name="txtCodigoCurso"  value="" readonly="readonly" >
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label class="required" >Nombre curso </label>
                                        <input  class="form-control" id="txtNombreCurso" name="txtNombreCurso"  required="" >
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label class="required"  >Nivel </label>
                                        <input  class="form-control" id="txtNivel" name="txtNivel"  value="" required="" type="number" min="1" max="10">
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label class="required" >Creditos </label>
                                        <input  class="form-control" id="txtCreditos" name="txtCreditos"  required="" type="number" min="1" max="6" >
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label class="required" >Carrera </label>
                                        <select class="form-control" id="cbxCarrera" name="cbxCarrera"  required="" >
                                            <option value="">Seleccione</option>
                                            <%if (listCarr != null) {
                                                    for (Carrera d : listCarr) {
                                            %>
                                            <option value="<%=d.getCodCarrera()%>"><%=d.getNombreCarrera()%></option>
                                            <%
                                                    }
                                                }%>
                                        </select>   
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