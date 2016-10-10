var tblUsuarios;
function p_usuarios()
{
    TrimToInput();
    $.validator.addMethod("validFecha", function (value, element) {

        var fecha = new Date(value);
        var anios = (new Date().getTime() - fecha.getTime()) / (1000 * 60 * 60 * 24 * 365);
        return anios >= 18;

    }, "EL usuario tiene que ser mayor de edad");
    tblUsuarios = $('#tblUsuarios').DataTable(glbOptionsDataTable);
    $("#idFormUsuarioAdmin").validate({rules: {
            txtNacimiento: {
                validFecha: true
            }
        }}).resetForm();
    $('#idFormBusquedaUsuarios').ajaxForm({
        url: "../usuarioServlet?accion=BUSQ",
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
                tblUsuarios.destroy();
                for (var i = 0; i < fil.length; ++i) {
                    tbody += "<tr>";
                    tbody += "<td>" + fil[i][0] + "</td>";
                    tbody += "<td>" + fil[i][1] + "</td>";
                    tbody += "<td>" + fil[i][2] + "</td>";
                    tbody += "<td>" + fil[i][3] + "</td>";
                    tbody += "<td>" + fil[i][4] + "</td>";
                    tbody += "<td><a href='#' onclick='openEditarUsuarioById(\"" + fil[i][0] + "\");' title='Editar'><span class='glyphicon glyphicon-edit' aria-hidden='true'></span></a></td>";

                    tbody += "</tr>";
                }
                $('#tblUsuarios tbody').html(tbody);
                tblUsuarios = $('#tblUsuarios').DataTable(glbOptionsDataTable);
                NProgress.done();
            }

        },
        error: function (e) {
            mostrarModalMensaje('No se pudo establecer la sesion, probablemente tengas un problema con tu conexion a internet.',
                    "ERROR");
                    NProgress.done();
        }
    });

    $('#idFormUsuarioAdmin').ajaxForm({
        url: "../usuarioServlet?accion=GUARDAR",
        type: "post",
        beforeSend: function (jqXHR, settings) {
            NProgress.start();
        },
        success: function (data) {
            data = JSON.parse(data);
            if (data.msj.hayMensaje == true) {
                mostrarModalMensaje(data.msj.mensaje, data.msj.tipo);
                $("#btnBuscarUsuarios").click();
                if (data.msj.tipo == "INFORMACION") {
                    $("#modalEdicionAdmin").modal('hide');
                }

            }
            NProgress.done();

        },
        error: function (e) {
            mostrarModalMensaje('No se pudo establecer la sesion, probablemente tengas un problema con tu conexion a internet.',
                    "ERROR");
            NProgress.done();
        }
    });


    //$("#btnBuscarUsuarios").click();
}

function openEditarUsuarioById(codUsuario)
{
    $.ajax({
        url: "../usuarioServlet?accion=GETUSUARIO",
        type: 'POST',
        data: {
            codUsuario: codUsuario
        },
        beforeSend: function (xhr) {
            NProgress.start();
        },
        success: function (data) {
            data = JSON.parse(data);
            if (data.msj.hayMensaje != true) {
                reestablecerFormUsuario();
                $("#txtCodigo").val(data.usuario.codUsuario);
                $("#txtNombre").val(data.usuario.nombre);
                $("#txtApellido").val(data.usuario.apellido);
                $("#txtDNI").val(data.usuario.dni);
                $("#txtNacimiento").val(data.usuario.fechaNacimiento);
                $("#cbxSexo").val("" + data.usuario.sexo);
                $("#txtCelular").val(data.usuario.numCelular);
                $("#txtCorreo").val(data.usuario.correo);
                $("#txtContrasenia").val(data.usuario.contrasenia);
                $("#cbxEstado").val("" + data.usuario.flgActivo);
                if (data.usuario.codTipoUsuario == 2) {
                    $("#cbTiempoCom").attr("checked", data.usuario.isTiempoCompleto);
                }
                if (data.usuario.codTipoUsuario == 3) {
                    $("#cbxCarrera").val("" + data.usuario.codCarrera);
                    $("#cbxSede").val("" + data.usuario.codSede);
                    $("#txtColegioProc").val(data.usuario.colegioProcedencia);
                }
                openModalUsuario(data.usuario.codTipoUsuario, false);
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

function reestablecerFormUsuario()
{
    $("#idFormUsuarioAdmin").validate({rules: {
            txtNacimiento: {
                validFecha: true
            }
        }}).resetForm();
    $("#idFormUsuarioAdmin .error").removeClass("error");
}

function openModalUsuario(codTipoUsuario, reestablecer)
{
    if (reestablecer)
        reestablecerFormUsuario();
    $("#idSectionAlumno").hide();
    $("#idSectionDocente").hide();

    if (codTipoUsuario == 1) {

    }
    if (codTipoUsuario == 2) {
        $("#idSectionDocente").show();
    }
    if (codTipoUsuario == 3) {
        $("#idSectionAlumno").show();
    }

    $("#cbxTipoUsuario").val(codTipoUsuario);
    $("#txtTipoUsuario").val(codTipoUsuario);
    $("#modalEdicionAdmin").modal('show');
}