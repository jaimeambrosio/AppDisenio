<%-- 
    Document   : login
    Created on : 27/09/2016, 10:25:37 AM
    Author     : Jaime Ambrosio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"> 
        <link rel="icon" type="image/ico" href="/AppDisenio/img/icon.ico" />
        <title>Ingresar al sistema</title>
        <%@include file="../plantilla/contenedor-css.jsp" %>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="jumbotron">
                    <h1>Bienvenido al sistema</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Ingresar al sistema</h3>
                        </div>
                        <div class="panel-body">
                            <form id="idFormLogin"  method="POST">
                                <div class="form-group">
                                    <label class="required">Codigo</label>
                                    <input type="text" maxlength="10" class="form-control" name="txtCodUsuario" value="" id="txtCodUsuario" placeholder="Codigo" required="true">
                                </div>
                                <div class="form-group">
                                    <label class="required">Contraseña</label>
                                    <input type="password" name="txtContrasenia" id="txtContrasenia" value="" class="form-control"  placeholder="Contraseña" required>
                                </div>
                                <br>
                                <div class="form-group" style="display: none">
                                    <div class="checkbox">
                                        <label>
                                            <input name="txtRecordarP" id="txtRecordarP" type="checkbox" checked=""> Recordar contraseña
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group" align="center" >
                                    <button  type="submit" class="btn btn-primary">Ingresar al sistema</button>

                                </div>

                            </form>    
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="../plantilla/modalMensajes.jsp" %>
        <%@include file="../plantilla/contenedor-js.jsp" %>
        <script src="../js/login.js" type="text/javascript"></script>
    </body>
</html>
