var tblUsuarios;
function p_usuarios()
{
    tblUsuarios = $('#tblUsuarios').DataTable({responsive: true});
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
                for (i = 0; i < fil.length; ++i) {
                    tbody += "<tr>";
                    tbody += "<td>" + fil[i][0] + "</td>";
                    tbody += "<td>" + fil[i][1] + "</td>";
                    tbody += "<td>" + fil[i][2] + "</td>";
                    tbody += "<td>" + fil[i][3] + "</td>";
                    tbody += "<td>" + fil[i][4] + "</td>";
                    tbody += "<td><a href='#' onclick='openEditarUsuarioById(\"" +fil[i][0] + "\");' title='Editar'><span class='glyphicon glyphicon-edit' aria-hidden='true'></span></a></td>";
                    

                    tbody += "</tr>";
                }
                $('#tblUsuarios tbody').html(tbody);
                tblUsuarios = $('#tblUsuarios').DataTable({responsive: true});
                NProgress.done();
            }

        },
        error: function (e) {
            mostrarModalMensaje('No se pudo establecer la sesion, probablemente tengas un problema con tu conexion a internet.',
                    "ERROR");
        }
    });
    //$("#btnBuscarUsuarios").click();
}

function openEditarUsuarioById(codUsuario)
{
    
}