<%-- 
    Document   : p_usuarios
    Created on : 03/10/2016, 03:10:57 AM
    Author     : Jaime Ambrosio
--%>


<%@page import="java.util.List"%>
<%@page import="dis.producto.dao.ProductoDao"%>
<%@page import="dis.producto.entity.Tipoproducto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ProductoDao productoDao = new ProductoDao();
    List<Tipoproducto> listTipo = productoDao.listarTiposProd();
%>
<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title" id="tituloPanel"></h3>
    </div>
    <div class="panel-body">
        <div class="row">
            <div class="col-md-4">
                <button onclick="openModalProducto(true);" type="button" class="btn btn-default btn-block">
                    <span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
                    Nuevo Producto
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
                        <form id="idFormBusquedaProducto"  method="POST">
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="txtCodigo">Codigo produto</label>
                                        <input type="text" class="form-control" id="txtCodigo"  name="txtCodigo" placeholder="Codigo">
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="txtNombre">Nombre producto</label>
                                        <input type="text" class="form-control" id="txtNombre" name="txtNombre" placeholder="Nombre">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="cbxTipo">Tipo producto</label>
                                        <select  class="form-control" name="cbxTipo" id="cbxTipo">
                                            <option value="" >Seleccione</option>
                                            <%if (listTipo != null) {
                                                    for (Tipoproducto tp : listTipo) {
                                            %>
                                            <option value="<%=tp.getCodTipoProducto()%>" ><%=tp.getNombreTipoProd()%></option>
                                            <%
                                                    }
                                                }%>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="estado">Estado</label>
                                        <select  class="form-control" name="estado" id="estado">
                                            <option value="true" >Activo</option>
                                            <option value="false" >Inactivo</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                </div>
                                <div class="col-sm-6">
                                    <br>
                                    <button id="btnBuscarProducto" type="submit" class="btn btn-primary btn-block">
                                        Buscar
                                    </button>
                                </div>
                            </div>

                        </form>
                    </div>

                </div>
                <table  id="tblProducto" class="display nowrap" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th>Codigo</th>
                            <th>Nombre</th>
                            <th>Tipo</th>
                            <th>Precio</th>
                            <th>Estado</th>
                            <th>...</th>
                        </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
        </div>

        <div class="modal fade" data-backdrop="false" id="modalEdicionProducto" tabindex="-1" role="dialog" >
            <div class="modal-dialog">
                <div class="modal-content"> 
                    <form id="idFormProducto" method="POST">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title"   >
                                <span class="glyphicon glyphicon-file" aria-hidden="true"></span>
                                Producto
                            </h4>
                        </div>
                        <div class="modal-body">

                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label >Codigo </label>
                                        <input  type="text"   class="form-control" id="txtCodigoProducto" name="txtCodigoProducto"  value="" readonly="readonly" >
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label class="required" >Nombre producto </label>
                                        <input  type="text"  class="form-control" id="txtNombreProducto" name="txtNombreProducto"  required="" >
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label class="required" >Precio </label>
                                        <input  class="form-control" id="txtPrecio" name="txtPrecio"  min="1" max="5000" type="number" required="" >
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label class="required" >Tipo producto </label>
                                        <select class="form-control" id="cbxTipoProducto" name="cbxTipoProducto"  required="" >
                                            <option value="">Seleccione</option>
                                            <%if (listTipo != null) {
                                                    for (Tipoproducto tp : listTipo) {
                                            %>
                                            <option value="<%=tp.getCodTipoProducto()%>" ><%=tp.getNombreTipoProd()%></option>
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
                                        <label class="required" >Estado </label>
                                        <select class="form-control" id="cbxEstado" name="cbxEstado"  required="" >
                                            <option value="true">Activo</option>
                                            <option value="false">Inactivo</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-sm-6">

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