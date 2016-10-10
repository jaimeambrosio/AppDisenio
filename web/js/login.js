$().ready(function () {
    NProgress.configure({showSpinner: false});
    NProgress.start();
    $("#idFormLogin").validate();
    TrimToInput();
    $('#idFormLogin').ajaxForm({
        url: "../usuarioServlet?accion=LOGIN",
        type: "post",
        beforeSend: function (jqXHR, settings) {
            NProgress.start();
        },
        success: function (data) {
            data = JSON.parse(data);
            console.log(data);
            if (data.msj.hayMensaje == true) {
                mostrarModalMensaje(data.msj.mensaje, data.msj.tipo);
                NProgress.done();
            } else
            {
                NProgress.done();
                window.location.href = data.url;
            }

        },
        error: function (e) {
            mostrarModalMensaje('No se pudo establecer la sesion, probablemente tengas un problema con tu conexion a internet.',
                    "ERROR");
            NProgress.done();
        }
    });

    NProgress.done();
});


