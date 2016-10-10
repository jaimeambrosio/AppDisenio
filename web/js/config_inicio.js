
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

var glbOptionsDataTable = {
    responsive: true,
    "language": {
        "lengthMenu": "Mostrar _MENU_ registros por pagina",
        "zeroRecords": "No se encontraron registros",
        "info": "Mostrando _PAGE_ de _PAGES_",
        "infoEmpty": "No hay registros disponibles",
        "infoFiltered": "(filtrado de _MAX_ registros)",
        "search": "Buscar",
        "emptyTable": "Tabla sin registros.",
        "paginate": {
            "first": "Primero",
            "last": "Ultimo",
            "next": "Siguiente",
            "previous": "Anterior"
        }
    }
};

function openModalGlbUsuario()
{
    $.ajax({
        url: "../usuarioServlet?accion=GETUSUSESION",
        type: 'POST',
        data: {
        },
        beforeSend: function (xhr) {
            NProgress.start();
        },
        success: function (data) {

            data = JSON.parse(data);
            $("#idGlbSectionDocente").hide();
            $("#idGlbSectionAlumno").hide();
            if (data.msj.hayMensaje != true) {
                $("#idGlbFormUsuario").validate().resetForm();
                $("#idGlbFormUsuario .error").removeClass("error");
                $("#idGlbFormUsuario *").attr("disabled", true);

                $("#txtGlbCodigo").val(data.usuario.codUsuario);
                $("#txtGlbNombre").val(data.usuario.nombre);
                $("#txtGlbApellido").val(data.usuario.apellido);
                $("#txtGlbDNI").val(data.usuario.dni);
                $("#txtGlbNacimiento").val(data.usuario.fechaNacimiento);
                $("#cbxGlbSexo").val("" + data.usuario.sexo);
                $("#txtGlbCelular").val(data.usuario.numCelular);
                $("#txtGlbCorreo").val(data.usuario.correo);
                $("#txtGlbContrasenia").val(data.usuario.contrasenia);
                $("#cbxGlbEstado").val("" + data.usuario.flgActivo);
                if (data.usuario.codTipoUsuario == 2) {
                    $("#cbGlbTiempoCom").attr("checked", data.usuario.isTiempoCompleto);
                    $("#idGlbSectionDocente").show();
                }
                if (data.usuario.codTipoUsuario == 3) {
                    $("#cbxGlbCarrera").val("" + data.usuario.codCarrera);
                    $("#cbxGlbSede").val("" + data.usuario.codSede);
                    $("#txtGlbColegioProc").val(data.usuario.colegioProcedencia);
                    $("#idGlbSectionAlumno").show();
                }
                $("#txtGlbCelular").attr("disabled", false);
                $("#txtGlbCorreo").attr("disabled", false);
                $("#txtGlbContrasenia").attr("disabled", false);
                $("#idGlbFormUsuario button").attr("disabled", false);

                //  $("#idGlbFormUsuario txtGlbCelular").attr("disabled",false);
                $("#modalGlbEdicion").modal('show');
            } else
            {
                mostrarModalMensaje(data.msj.mensaje, data.msj.tipo);
            }
            NProgress.done();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            mostrarModalMensaje('No se pudo invocar al servidor, probablemente tengas un problema con tu conexion a internet.',
                    jqXHR.responseText,
                    "ERROR");
            NProgress.done();
        }
    });
}

//cuando se carga la pagina
$().ready(function () {


    NProgress.configure({showSpinner: false});
    $("#idGlbFormUsuario").validate().resetForm();
    $('#idGlbFormUsuario').ajaxForm({
        url: "../usuarioServlet?accion=GUARUSUSES",
        type: "post",
        beforeSend: function (jqXHR, settings) {
            NProgress.start();
        },
        success: function (data) {
            data = JSON.parse(data);
            if (data.msj.hayMensaje == true) {
                mostrarModalMensaje(data.msj.mensaje, data.msj.tipo);
                if (data.msj.tipo == "INFORMACION") {
                    $("#modalGlbEdicion").modal('hide');
                }

            }
            NProgress.done();

        },
        error: function (e) {
            NProgress.done();
            mostrarModalMensaje('No se pudo establecer la sesion, probablemente tengas un problema con tu conexion a internet.',
                    "ERROR");
        }
    });
    configureMenu();
    TrimToInput();
    $("#pCursos").click();
});


