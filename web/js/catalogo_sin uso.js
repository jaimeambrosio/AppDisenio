 $.validator.addMethod("validFecha", function (value, element) {

        var fecha = new Date(value);
        var anios = (new Date().getTime() - fecha.getTime()) / (1000 * 60 * 60 * 24 * 365);
        return anios >= 18;

    }, "EL usuario tiene que ser mayor de edad");
mostrarModalMensaje(mensaje, detalle, tipo);
formatoMontoChange(idInput);

$('#idFormMiPerfil').ajaxForm({
    url: "../usuarioServlet?accion=MODIFICAR",
    type: "post",
    beforeSend: function (jqXHR, settings) {
        NProgress.start();
    },
    success: function (data) {
        data = JSON.parse(data);
        if (data.msj.hayMensaje == true) {
            mostrarModalMensaje(data.msj.mensaje, data.msj.detalle, data.msj.tipo);
            $("#pMiPerfil").click();
        }
        NProgress.done();
    },
    error: function (e) {
        mostrarModalMensaje('No se pudo enviar los datos, probablemente tengas un problema con tu conexion a internet.',
                e.statusText,
                "ERROR");
        NProgress.done();
    }
});

 $.ajax({
        url: "../usuarioServlet?accion=LISTARTB",
        type: 'POST',
        data: {
        },
        beforeSend: function (xhr) {
            NProgress.start();
        },
        success: function (data) {
            data = JSON.parse(data);
            if (data.msj.hayMensaje != true) {
                tblPanelUsuarios.fnClearTable();
                tblPanelUsuarios.fnDestroy();
                $("#tblPanelUsuarios tbody").html(data.tbody);
                tblPanelUsuarios = $('#tblPanelUsuarios').dataTable({});
            } else
            {
                mostrarModalMensaje(data.msj.mensaje, data.msj.detalle, data.msj.tipo);
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
    
     $.ajax({
        url: "../afpServlet?accion=TBRENTABILIDAD",
        type: 'POST',
        data: {
        },
        beforeSend: function (xhr) {
            NProgress.start();
        },
        success: function (data) {
            data = JSON.parse(data);
            if (data.msj.hayMensaje != true) {
               // tblTipoFondoXAfp.fnClearTable();
                tblTipoFondoXAfp.destroy();
                $("#tblTipoFondoXAfp").html(data.tbl);
                tblTipoFondoXAfp = $('#tblTipoFondoXAfp').DataTable({
                    
                });
               // $("#tblTipoFondoXAfp thead").empty();
            } else
            {
                mostrarModalMensaje(data.msj.mensaje, data.msj.detalle, data.msj.tipo);
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