
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                                    <span class="label label-default">Jaime Ambrosio</span>

                                </li>
                                <li>
                                    <a href="#" data-link="paneles/p-inicio.jsp" id="pInicio"><span class="glyphicon glyphicon-home"  ></span>  Inicio</a>
                                    <ul class="nav nav-second-level"></ul>
                                </li>
                                <li>
                                    <a href="#"><span class="glyphicon glyphicon-hourglass" id="pSimuladorAfp" ></span> Simulador AFP<span class="fa arrow"></span></a>
                                    <ul class="nav nav-second-level">
                                        <li>
                                            <a href="#" data-link="paneles/p-simular.jsp" data-constructor="p_simular();"><span class="glyphicon glyphicon-tint" ></span> Simular</a>
                                        </li>
                                    </ul>
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
                                        <li style="display: none" >
                                            <a href="#"  onclick="prueba();"  ><span class="glyphicon glyphicon-off" ></span> Prueba modal</a>
                                        </li>
                                        <li style="display: none" >
                                            <a href="#" data-link="paneles/p-prueba.jsp" data-constructor="p_prueba();" data-pass="1" ><span class="glyphicon glyphicon-off" ></span> Prueba</a>
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
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title" id="tituloPanel">sdasasdasd</h3>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-sm-6 col-md-3">
                                    <div class="thumbnail">
                                        <img src="../img/integra.png" alt="...">
                                        <div class="caption">
                                            <h3>Integra</h3>
                                            <p>Somos una Administradora de Fondos de Pensiones (AFP) fundada el 19 de mayo de 1993 con la misión de liderar la industria, establecer el estándar en la administración de pensiones y otorgar el mejor servicio en el mercado. </p>
                                            <p><a target="_blank" href="http://www.integra.com.pe/wps/portal/integra/"  class="btn btn-primary" role="button">Leer más</a> </p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6 col-md-3">
                                    <div class="thumbnail">
                                        <img src="../img/prima.png" alt="...">
                                        <div class="caption">
                                            <h3>Prima</h3>
                                            <p>Somos una administradora privada de fondos de pensiones, nacimos en el año 2005 para brindar a nuestros afiliados la mejor pensión de jubilación. Contamos con el respaldo del Grupo Crédito, el grupo financiero líder del país con más de 125 años de experiencia.</p>
                                            <p><a target="_blank" href="http://www.prima.com.pe/wcm/portal/PrimaAFP/inicio" class="btn btn-primary" role="button">Leer más</a> </p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6 col-md-3">
                                    <div class="thumbnail">
                                        <img src="../img/habitat.png" alt="...">
                                        <div class="caption">
                                            <h3>Habitat</h3>
                                            <p>Tras la resolución de la Superintendencia de Banca Seguros y AFP (SBS) N° 8512-2012 y 9356-2012, del día 17 de diciembre del 2012, se ha autorizado a AFP Habitat a operar como Administradora Privada de Fondo de Pensiones en Perú.</p>
                                            <p><a target="_blank" href="https://www.afphabitat.com.pe/" class="btn btn-primary" role="button">Leer más</a></p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6 col-md-3">
                                    <div class="thumbnail">
                                        <img src="../img/profuturo.png" alt="...">
                                        <div class="caption">
                                            <h3>Profuturo</h3>
                                            <p>Profuturo AFP se fundó en 1993 para administrar fondos de inversión y su correspondiente cartera, con el fin de ofrecer a los peruanos beneficiosas alternativas de retiro a través de un servicio de calidad total, brindando información permanente y asesoría de primer nivel.</p>
                                            <p><a target="_blank" href="https://www.profuturo.com.pe/" class="btn btn-primary" role="button">Leer más</a> </p>
                                        </div>
                                    </div>
                                </div>

                            </div>


                        </div>
                    </div>
                </div>
            </div>

        </div>
        
        <%@include file="../plantilla/modalMensajes.jsp" %>
        <%@include file="../plantilla/contenedor-js.jsp" %>
    </body>
</html>
