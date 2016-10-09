var tblCursos;
function p_cursos()
{
    tblCursos = $('#tblCursos').DataTable({responsive: true});
    $('#idFormBusqCurso').ajaxForm({
        url: "../cursoServlet?accion=BUSQ",
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
                tblCursos.destroy();
                for (var i = 0; i < fil.length; ++i) {
                    tbody += "<tr>";
                    tbody += "<td>" + fil[i][0] + "</td>";
                    tbody += "<td>" + fil[i][1] + "</td>";
                    tbody += "<td>" + fil[i][2] + "</td>";
                    tbody += "<td>" + fil[i][3] + "</td>";
                    tbody += "<td>" + fil[i][4] + "</td>";
                    tbody += "<td>" + fil[i][5] + "</td>";
                    tbody += "<td><a href='#' onclick='openEditarCurso(\"" + fil[i][0] + "\");' title='Editar'><span class='glyphicon glyphicon-edit' aria-hidden='true'></span></a></td>";

                    tbody += "</tr>";
                }
                $('#tblCursos tbody').html(tbody);
                tblCursos = $('#tblCursos').DataTable({responsive: true});
                NProgress.done();
            }
        },
        error: function (e) {
            mostrarModalMensaje('No se pudo establecer la sesion, probablemente tengas un problema con tu conexion a internet.',
                    "ERROR");
        }
    });

    $("#idFormCurso").validate().resetForm();
    $("#idFormCurso .error").removeClass("error");
    $('#idFormCurso').ajaxForm({
        url: "../cursoServlet?accion=GUARDAR",
        type: "post",
        beforeSend: function (jqXHR, settings) {
            NProgress.start();
        },
        success: function (data) {
            data = JSON.parse(data);
            if (data.msj.hayMensaje == true) {
                mostrarModalMensaje(data.msj.mensaje, data.msj.tipo);
                $("#btnBuscarCurso").click();
                if (data.msj.tipo == "INFORMACION") {
                    $("#modalEdicionCurso").modal('hide');
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
function openModalCurso(reestablecer)
{
    if (reestablecer) {
        $("#idFormCurso").validate().resetForm();
        $("#idFormCurso .error").removeClass("error");
    }
    $("#modalEdicionCurso").modal('show');
}
function openEditarCurso(cod) {
    $.ajax({
        url: "../cursoServlet?accion=GETCURSO",
        type: 'POST',
        data: {
            codigo: cod
        },
        beforeSend: function (xhr) {
            NProgress.start();
        },
        success: function (data) {
            data = JSON.parse(data);
            console.log(data);
            if (data.msj.hayMensaje != true) {
                $("#idFormCurso").validate().resetForm();
                $("#idFormCurso .error").removeClass("error");

                $("#txtCodigoCurso").val(data.curso.cod);
                $("#txtNombreCurso").val(data.curso.nombre  );
                $("#txtNivel").val(data.curso.nivel);
                $("#txtCreditos").val(data.curso.creditos);
                $("#cbxCarrera").val(data.curso.codCarr);
                $("#cbxEstado").val(data.curso.estado);
                
                openModalCurso(false);
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