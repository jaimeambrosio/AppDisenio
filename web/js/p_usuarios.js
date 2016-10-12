var tblUsuarios;
function p_usuarios()
{
    TrimToInput();
    $.validator.addMethod("validDNI", function (value, element) {

        return value.length === 8 && !isNaN(value);
    }, "Este campo solo debe tener 8 digitos.");
    $.validator.addMethod("vSoloText", function (value, element) {
        for (var i = 0; i < value.length; i++) {
            var code = value.charCodeAt(i);
            if (!(code > 64 && code < 91)) {
                if (!(code > 96 && code < 123)) {
                    if (code !== 32)
                        return false;
                }
            }
        }
        return true;
    }, "No se permiten numeros o caracteres  especiales.");
    $.validator.addMethod("vCel", function (value, element) {
        return value.length === 9 && !isNaN(value);
    }, "Este campo solo debe tener 9 digitos.");

    tblUsuarios = $('#tblUsuarios').DataTable(glbOptionsDataTable);
    $("#idFormUsuarioAdmin").validate({rules: {
            txtDNI: {
                validDNI: true
            },
            txtNombre: {
                vSoloText: true
            },
            txtApellido: {
                vSoloText: true
            },
            txtCelular: {
                vCel: true
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
            txtDNI: {
                validDNI: true
            },
            txtNombre: {
                vSoloText: true
            },
            txtApellido: {
                vSoloText: true
            },
            txtCelular: {
                vCel: true
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