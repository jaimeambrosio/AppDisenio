$().ready(function () {
    NProgress.configure({showSpinner: false});
    NProgress.start();
    $("#idFormLogin").validate();
    
    $('#idFormLogin').ajaxForm({
        url: "usuarioServlet?accion=LOGIN",
        type: "post",
        beforeSend: function (jqXHR,settings ) {
            console.log(jqXHR);
            console.log(settings);
            NProgress.start();
        },
        success: function (data) {
            console.log(data);
            data = JSON.parse(data);
            if (data.msj.hayMensaje == true) {
                mostrarModalMensaje(data.msj.mensaje, data.msj.detalle, data.msj.tipo);
                NProgress.done();
            } else
            {
                NProgress.done();
                window.location.href = data.url;
            }

        },
        error: function (e) {
            mostrarModalMensaje('No se pudo establecer la sesion, probablemente tengas un problema con tu conexion a internet.',
                    e.statusText,
                    "ERROR");
        }
    });

    NProgress.done();
});

function redireccionaTest(){
      window.location.href = "inicio.jsp";
}

