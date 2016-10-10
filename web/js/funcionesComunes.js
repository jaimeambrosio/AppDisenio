function mostrarModalMensaje(mensaje, tipo)
{
    if (mensaje === undefined || mensaje === null)
        mensaje = "Sin mensaje.";

    $("#modalMensajesIconInformation").hide();
    $("#modalMensajesIconWarning").hide();
    $("#modalMensajesIconError").hide();

    if (tipo == "INFORMACION") {
        var titulo = '<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> Información';
        $("#modalMensajesIconInformation").show();
        $("#modalMensajeTitle").html(titulo);
    } else if (tipo == "ERROR") {
        var titulo = '<span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span> Ha ocurrido algo inesperado';
        $("#modalMensajesIconError").show();
        $("#modalMensajeTitle").html(titulo);
    } else if (tipo == "ADVERTENCIA") {
        var titulo = '<span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span> Ten en cuenta lo siguiente';
        $("#modalMensajesIconWarning").show();
        $("#modalMensajeTitle").html(titulo);
    }
    $("#modalMensajesCuerpo").html(mensaje);
    $('#modalMensajes').modal('show');
}

function prueba() {
    mostrarModalMensaje('The following example makes a button toggle the expanding and collapsing content of another element', "ADVERTENCIA");
}


function cargarCombo(idCombo, url)
{

}
function TrimToInput()
{
    $("input").each(function () {
        if ($(this).attr("type") !== "password") {
            $(this).blur(function () {
                console.log($(this));
                var input = $(this);
                if (input.val() != undefined)
                    input.val(input.val().trim());
            });
        }
    });
}