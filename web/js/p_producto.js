/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var tblProducto;
function p_producto()
{
    TrimToInput();
    /*
     $.validator.addMethod("validNumber", function (value, element) {
     return value.indexOf(",") < 0;
     
     }, "La separacion decimal debe ser con punto(.)");
     */
    $.validator.addMethod("validNumber", function (value, element) {

        return !isNaN(value) && value.indexOf(",") < 0;
    }, "Por favor, escriba un número válido.");
    $.validator.addMethod("validNumberMinMax", function (value, element) {

        return  eval(value) >= 1 && eval(value) <= 5000
    }, "El valor debe estar entre 1 y 5000");


    tblProducto = $('#tblProducto').DataTable(glbOptionsDataTable);
    $('#idFormBusquedaProducto').ajaxForm({
        url: "../productoServlet?accion=BUSQ",
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
                tblProducto.destroy();
                for (var i = 0; i < fil.length; ++i) {
                    tbody += "<tr>";
                    tbody += "<td>" + fil[i][0] + "</td>";
                    tbody += "<td>" + fil[i][1] + "</td>";
                    tbody += "<td>" + fil[i][2] + "</td>";
                    tbody += "<td>" + fil[i][3] + "</td>";
                    tbody += "<td>" + fil[i][4] + "</td>";
                    tbody += "<td><a href='#' onclick='openEditarProducto(\"" + fil[i][0] + "\");' title='Editar'><span class='glyphicon glyphicon-edit' aria-hidden='true'></span></a></td>";

                    tbody += "</tr>";
                }
                $('#tblProducto tbody').html(tbody);
                tblProducto = $('#tblProducto').DataTable(glbOptionsDataTable);
                NProgress.done();
            }
        },
        error: function (e) {
            mostrarModalMensaje('No se pudo establecer la sesion, probablemente tengas un problema con tu conexion a internet.',
                    "ERROR");
        }
    });

    $("#idFormProducto").validate({rules: {
            txtPrecio: {
                validNumber: true,
                validNumberMinMax: true
            }
        }}).resetForm();
    $("#idFormProducto .error").removeClass("error");
    $('#idFormProducto').ajaxForm({
        url: "../productoServlet?accion=GUARDAR",
        type: "post",
        beforeSend: function (jqXHR, settings) {
            NProgress.start();
        },
        success: function (data) {
            data = JSON.parse(data);
            if (data.msj.hayMensaje == true) {
                mostrarModalMensaje(data.msj.mensaje, data.msj.tipo);
                $("#btnBuscarProducto").click();
                if (data.msj.tipo == "INFORMACION") {
                    $("#modalEdicionProducto").modal('hide');
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
function openEditarProducto(cod)
{
    $.ajax({
        url: "../productoServlet?accion=GETPROD",
        type: 'POST',
        data: {
            codigo: cod
        },
        beforeSend: function (xhr) {
            NProgress.start();
        },
        success: function (data) {
            data = JSON.parse(data);
            if (data.msj.hayMensaje != true) {
                $("#idFormProducto").validate({rules: {
                        txtPrecio: {
                            validNumber: true,
                            validNumberMinMax: true
                        }
                    }}).resetForm();
                $("#idFormProducto .error").removeClass("error");

                $("#txtCodigoProducto").val(data.producto.codigo);
                $("#txtNombreProducto").val(data.producto.nombre);
                $("#txtPrecio").val(data.producto.precio);
                $("#cbxTipoProducto").val(data.producto.codTipo);
                $("#cbxEstado").val("" + data.producto.estado);
                openModalProducto(false);
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
function openModalProducto(reestablecer)
{
    if (reestablecer) {
        $("#idFormProducto").validate({rules: {
                txtDNI: {
                    validNumber: true
                }
            }}).resetForm();
        $("#idFormProducto .error").removeClass("error");
    }
    $('#modalEdicionProducto').modal('show');
}
