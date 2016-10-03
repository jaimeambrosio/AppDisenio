
function configureMenu() {

    // $("#divMenu ul").removeClass("in");
    $("#divMenu a").each(function (i) {
        var enlace = $(this);
        var link = enlace.attr("data-link");
        if (link !== undefined && link != "") {
            var seccion = enlace.html();
            var nombreFuncion = enlace.attr("data-constructor");

            $(this).click(function () {
                NProgress.start();
                $("#contenedor-main").hide('blind', {}, 200, function () {
                    $("#contenedor-main").load(link, {}, function (response, status, xhr) {
                        if (status != "error") {
                            if (nombreFuncion !== undefined)
                                eval(nombreFuncion);
                            $("#divMenu a").removeClass("active");
                            enlace.addClass("active");
                            $("#tituloPanel").html(seccion);
                            $("#contenedor-main").show('blind', {}, 200, function () {});
                        } else {
                            mostrarModalMensaje('No se pudo encontrar la seccion "' + seccion + '". Asegurate de tener una conexion activa a internet.',
                                    "ERROR");
                        }
                        NProgress.done();
                    });
                });
            });
        }
    });

}
//cuando se carga la pagina
$().ready(function () {

    NProgress.configure({showSpinner: false});
    configureMenu();
    $("#pUsuarios").click();
});


