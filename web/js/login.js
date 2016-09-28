$().ready(function () {
    NProgress.configure({showSpinner: false});
    NProgress.start();
    $("#idFormLogin").validate();

    NProgress.done();
});

function redireccionaTest(){
      window.location.href = "inicio.jsp";
}