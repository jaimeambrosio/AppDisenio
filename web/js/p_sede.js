var tblSede;
function p_sede()
{
    tblSede = $('#tblSede').DataTable(glbOptionsDataTable);
    $('#idFormBusquedaSede').ajaxForm({
        url: "../sedeServlet?accion=BUSQ",
        type: "post",
        beforeSend: function (jqXHR, settings) {
            NProgress.start();
        },
        success: function (data) {
            data = JSON.parse(data);
            if (data.msj.hayMensaje == true) {
                mostrarModalMensaje(data.msj.mensaje, data.msj.tipo);
                NProgress.done();
            } else
            {
                var fil = data.list;
                var tbody = "";
                tblSede.destroy();
                for (var i = 0; i < fil.length; ++i) {
                    tbody += "<tr>";
                    tbody += "<td>" + fil[i][0] + "</td>";
                    tbody += "<td>" + fil[i][1] + "</td>";
                    tbody += "<td>" + fil[i][2] + "</td>";
                    tbody += "<td>" + fil[i][3] + "</td>";
                    tbody += "<td><a href='#' onclick='openEditarSede(\"" + fil[i][0] + "\");' title='Editar'><span class='glyphicon glyphicon-edit' aria-hidden='true'></span></a></td>";

                    tbody += "</tr>";
                }
                $('#tblSede tbody').html(tbody);
                tblSede = $('#tblSede').DataTable(glbOptionsDataTable);
                NProgress.done();
            }
        },
        error: function (e) {
            mostrarModalMensaje('No se pudo establecer la sesion, probablemente tengas un problema con tu conexion a internet.',
                    "ERROR");
        }
    });
    $("#idFormSede").validate().resetForm();
    $("#idFormSede .error").removeClass("error");
    $('#idFormSede').ajaxForm({
        url: "../sedeServlet?accion=GUARDAR",
        type: "post",
        beforeSend: function (jqXHR, settings) {
            NProgress.start();
        },
        success: function (data) {
            data = JSON.parse(data);
            if (data.msj.hayMensaje == true) {
                mostrarModalMensaje(data.msj.mensaje, data.msj.tipo);
                $("#btnBuscarSede").click();
                if (data.msj.tipo == "INFORMACION") {
                    $("#modalEdicionSede").modal('hide');
                }
            }
            NProgress.done();

        },
        error: function (e) {
            mostrarModalMensaje('No se pudo establecer la sesion, probablemente tengas un problema con tu conexion a internet.',
                    "ERROR");
        }
    });

}

function openEditarSede(cod)
{
    $.ajax({
        url: "../sedeServlet?accion=GETSEDE",
        type: 'POST',
        data: {
            codSede: cod
        },
        beforeSend: function (xhr) {
            NProgress.start();
        },
        success: function (data) {
            data = JSON.parse(data);
            console.log(data);
            if (data.msj.hayMensaje != true) {
                $("#idFormSede").validate().resetForm();
                $("#idFormSede .error").removeClass("error");

                $("#cbxDistrito").val("" + data.sede.codDistrito);
                $("#txtNombre").val(data.sede.nombreSede);
                $("#txtCodigoSede").val(data.sede.codSede);
                $("#txtDescripcion").val(data.sede.descripcion);
                $("#txtDireccion").val(data.sede.direccion);
                $("#cbxEstado").val("" + data.sede.estado);
                openModalSede(false);
            } else
            {
                mostrarModalMensaje(data.msj.mensaje, data.msj.tipo);
            }
            NProgress.done();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            mostrarModalMensaje('No se pudo invocar al servidor, probablemente tengas un problema con tu conexion a internet.',
                    "ERROR");
            NProgress.done();
        }
    });
}
function openModalSede(reestablecer)
{
    if (reestablecer) {
        $("#idFormSede").validate().resetForm();
        $("#idFormSede .error").removeClass("error");
    }

    $("#modalEdicionSede").modal('show');
}
