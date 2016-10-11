<%-- 
    Document   : p_usuarios
    Created on : 03/10/2016, 03:10:57 AM
    Author     : Jaime Ambrosio
--%>

<%@page import="java.util.List"%>
<%@page import="dis.sede.entity.Distrito"%>
<%@page import="dis.sede.dao.SedeDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    SedeDao sedeDao = new SedeDao();
    List<Distrito> listDis = sedeDao.listarDistritos();

%>
<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title" id="tituloPanel"></h3>
    </div>
    <div class="panel-body">
        <div class="row">
            <div class="col-md-4">
                <button onclick="openModalSede(true);" type="button" class="btn btn-default btn-block">
                    <span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
                    Nueva Sede
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
                        <form id="idFormBusquedaSede"  method="POST">
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="txtCodUsuario">Codigo sede</label>
                                        <input type="text" class="form-control" id="txtCodigo"  name="txtCodigo" placeholder="Codigo">
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="txtNombreSede">Nombre sede</label>
                                        <input type="text" class="form-control" id="txtNombreSede" name="txtNombreSede" placeholder="Nombre">
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
                                    <button id="btnBuscarSede" type="submit" class="btn btn-primary btn-block">
                                        Buscar
                                    </button>
                                </div>
                            </div>

                        </form>
                    </div>

                </div>
                <table  id="tblSede" class="display nowrap" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th>Codigo</th>
                            <th>Nombre sede</th>
                            <th>Distrito</th>
                            <th>Estado</th>
                            <th>...</th>
                        </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
        </div>

        <div class="modal fade" data-backdrop="false" id="modalEdicionSede" tabindex="-1" role="dialog" >
            <div class="modal-dialog">
                <div class="modal-content"> 
                    <form id="idFormSede" method="POST">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title"   >
                                <span class="glyphicon glyphicon-equalizer" aria-hidden="true"></span>
                                Sede
                            </h4>
                        </div>
                        <div class="modal-body">

                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label >Codigo </label>
                                        <input  class="form-control" id="txtCodigoSede" name="txtCodigoSede"  value="" readonly="readonly" >
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label class="required" >Nombre sede </label>
                                        <input  minlength="3"  type="text"  class="form-control" id="txtNombre" name="txtNombre"  required="" >
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label class="required"  >Dirección </label>
                                        <input  minlength="10"  type="text"  class="form-control" id="txtDireccion" name="txtDireccion"  value="" required="" >
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label  >Descripción </label>
                                        <textarea    type="text"  class="form-control" id="txtDescripcion" name="txtDescripcion"  ></textarea>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label class="required" >Distrito </label>
                                        <select class="form-control" id="cbxDistrito" name="cbxDistrito"  required="" >
                                            <option value="">Seleccione</option>
                                            <%if (listDis != null) {
                                                    for (Distrito d : listDis) {
                                            %>
                                            <option value="<%=d.getCodDistrito()%>"><%=d.getNombreDistrito()%></option>
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