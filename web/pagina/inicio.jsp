
<%@page import="dis.usuario.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Usuario usuarioLogeado = (Usuario) request.getSession().getAttribute("usuarioLogeado");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <title>Inicio de la aplicación</title>
        <%@include file="../plantilla/contenedor-css.jsp" %>
    </head>
    <body>

        <div class="container" >
            <div id="wrapper">
                <nav class="navbar navbar-inverse navbar-static-top" role="navigation" style="margin-bottom: 0">

                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>

                        <a class="navbar-brand" href="inicio.jsp">
                            Sistema universitario 
                        </a>

                    </div>
                    <!-- /.navbar-header -->

                    <ul class="nav navbar-top-links navbar-right">

                        <li class="dropdown"></li>

                    </ul>
                    <!-- /.navbar-top-links -->

                    <div class="navbar-default sidebar" id="divMenu" role="navigation">
                        <div class="sidebar-nav navbar-collapse">
                            <ul class="nav" id="side-menu">
                                <li class="sidebar-search" align="center" >
                                    <img src="http://www.repuestosparamotos.co/page/images/user.png" alt="" class="img-thumbnail" /><br>
                                    <span class="label label-default"><%=usuarioLogeado.getNombre()%></span>

                                </li>
                                <li>
                                    <a href="#" data-link="paneles/p_usuarios.jsp" data-constructor="p_usuarios();" id="pUsuarios" ><span class="glyphicon glyphicon-user"  ></span>  Usuarios</a>
                                    <ul class="nav nav-second-level"></ul>
                                </li>
                                <li>
                                    <a href="#"  data-link="paneles/p_cursos.jsp" data-constructor="p_cursos();" ><span class="glyphicon glyphicon-book" ></span> Cursos</a>
                                    <ul class="nav nav-second-level"></ul>

                                </li>
                                <li>
                                    <a href="#"><span class="glyphicon glyphicon-file" ></span> Producto</a>
                                    <ul class="nav nav-second-level"></ul>

                                </li>
                                <li>
                                    <a href="#"  data-link="paneles/p_sede.jsp" data-constructor="p_sede();" ><span class="glyphicon glyphicon-equalizer" ></span> Sede</a>
                                    <ul class="nav nav-second-level"></ul>

                                </li>

                                <!--MI CUENTA-->
                                <li> 
                                    <a href="#" id="miCuenta"><span class="glyphicon glyphicon-cog"  ></span> Mi cuenta<span class="fa arrow"></span></a>
                                    <ul class="nav nav-second-level">
                                        <li>
                                            <a href="#" data-link="paneles/p-miperfil.jsp" data-constructor="p_miperfil();" id="pMiPerfil" ><span class="glyphicon glyphicon-user" ></span> Mi perfil</a>
                                        </li>

                                        <li>
                                            <a href="../usuarioServlet?accion=CERRARSESION"><span class="glyphicon glyphicon-off" ></span> Cerrar sesion</a>
                                        </li>
                                    </ul>
                                    <!-- /.nav-second-level -->
                                </li>
                            </ul>
                        </div>
                        <!-- /.sidebar-collapse -->
                    </div>
                    <!-- /.navbar-static-side -->
                </nav>
            </div>

            <div id="page-wrapper">
                <br>
                <div id="contenedor-main" >
                    
                </div>
            </div>

        </div>

        <%@include file="../plantilla/modalMensajes.jsp" %>
        <%@include file="../plantilla/contenedor-js.jsp" %>
        
        <script src="../js/config_inicio.js" type="text/javascript"></script>
        <script src="../js/p_usuarios.js" type="text/javascript"></script>
        <script src="../js/p_cursos.js" type="text/javascript"></script>
        <script src="../js/p_sede.js" type="text/javascript"></script>
    </body>
</html>
