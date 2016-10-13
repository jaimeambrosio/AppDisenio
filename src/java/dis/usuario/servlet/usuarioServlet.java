/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dis.usuario.servlet;

import dis.curso.dao.CursoDao;
import dis.curso.entity.Carrera;
import dis.entity.Mensaje;
import dis.sede.dao.SedeDao;
import dis.sede.entity.Sede;
import dis.usuario.dao.UsuarioDao;
import dis.usuario.entity.Alumno;
import dis.usuario.entity.Docente;
import dis.usuario.entity.Tipousuario;
import dis.usuario.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Jaime Ambrosio
 */
@WebServlet(name = "usuarioServlet", urlPatterns = {"/usuarioServlet"})
public class usuarioServlet extends HttpServlet {

    static int tiempoBloq = 1000 * 60;
    static int cantTiempoBloq = 30;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion").trim();
        switch (accion) {
            case "LOGIN": {
                validarUsuario(request, response);
                break;
            }
            case "CERRARSESION": {
                cerrarSesion(request, response);
                break;
            }
            case "BUSQ": {
                busquedaUsuario(request, response);
                break;
            }
            case "GETUSUARIO": {
                obtenerUsuario(request, response);
                break;
            }
            case "GETUSUSESION": {
                obtenerUsuarioSesion(request, response);
                break;
            }
            case "GUARDAR": {
                guardarUsuario(request, response);
                break;
            }
            case "GUARUSUSES": {
                guardarUsuarioDeSesion(request, response);
                break;
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void validarUsuario(HttpServletRequest request, HttpServletResponse response) {

        JSONObject jsonResult = new JSONObject();
        Mensaje mensaje = new Mensaje();
        String codigo = request.getParameter("txtCodUsuario").trim();
        String pass = request.getParameter("txtContrasenia").trim();
        //   boolean recordar = request.getParameter("txtRecordarP") == null;
        try {
            UsuarioDao usuarioDao = new UsuarioDao();

            Usuario u = usuarioDao.Obtener(codigo);

            if (u != null) {
                if (!u.getFlgActivo()) {
                    mensaje.setMensaje(Mensaje.INFORMACION, "Lo sentimos, actualmente te encuentras inhabilitado para ingresar al sistema. Por favor, contacta al administrador.");
                } else {
                    Date date = u.getFechaHoraBloqueo();
                    if (date != null) {
                        Date hoy = new Date();
                        long minutosPasaron = (hoy.getTime() - date.getTime()) / tiempoBloq;
                        if (minutosPasaron > cantTiempoBloq) {
                            u.setCantErrorIngreso(0);
                            u.setFechaHoraBloqueo(null);
                            if (u.getContrasenia().equals(pass)) {
                                HttpSession session = request.getSession(true);
                                session.setMaxInactiveInterval(60 * 60 * 2);
                                session.setAttribute("usuarioLogeado", u);
                                mensaje.setHayMensaje(false);
                            } else {
                                Integer cant = u.getCantErrorIngreso() == null ? 0 : u.getCantErrorIngreso();
                                u.setCantErrorIngreso(cant + 1);
                                if (u.getCantErrorIngreso() >= 3) {
                                    mensaje.setMensaje(Mensaje.ERROR, "Su cuenta ha sido bloqueada por 30 minutos porque ingreso tres veces su contraseña de manera incorrecta.");
                                    u.setFechaHoraBloqueo(new Date());
                                } else {
                                    mensaje.setMensaje(Mensaje.INFORMACION, "El usuario o contraseña no coinciden. Recuerde que en el tercer intento erroneo se bloqueara su cuenta por 30 minutos.");
                                }
                            }
                            usuarioDao.Actualizar(u);

                        } else {
                            //  mensaje.setMensaje(Mensaje.ERROR, "Su cuenta ha sido bloqueada por 30 minutos. Espere "
                            //         + (cantTiempoBloq - minutosPasaron)
                            //         + " minutos para volver a ingresar.");
                            mensaje.setMensaje(Mensaje.ERROR, "Su cuenta ha sido bloqueada por 30 minutos porque ingreso tres veces su contraseña de manera incorrecta.");
                        }
                    } else if (u.getContrasenia().equals(pass)) {
                        HttpSession session = request.getSession(true);
                        session.setMaxInactiveInterval(60 * 60 * 2);
                        session.setAttribute("usuarioLogeado", u);
                        mensaje.setHayMensaje(false);
                    } else {
                        Integer cant = u.getCantErrorIngreso() == null ? 0 : u.getCantErrorIngreso();
                        u.setCantErrorIngreso(cant + 1);
                        if (u.getCantErrorIngreso() >= 3) {
                            mensaje.setMensaje(Mensaje.ERROR, "Su cuenta ha sido bloqueada por 30 minutos porque ingreso tres veces su contraseña de manera incorrecta.");
                            u.setFechaHoraBloqueo(new Date());
                        } else {
                            mensaje.setMensaje(Mensaje.INFORMACION, "El usuario o contraseña no coinciden. Recuerde que en el tercer intento erroneo se bloqueara su cuenta por 30 minutos.");
                        }
                        usuarioDao.Actualizar(u);
                    }
                }
            } else {
                mensaje.setMensaje(Mensaje.INFORMACION, "El usuario o contraseña no coinciden. Intentelo de nuevo.");
            }

        } catch (Exception e) {
            mensaje.establecerError(e);
        }

        JSONObject jsonMensaje = new JSONObject(mensaje);
        try {
            jsonResult.put("msj", jsonMensaje);
            jsonResult.put("url", "inicio.jsp");
            Cookie c = new Cookie("nombre", "valor1111");
            response.addCookie(c);
            enviarDatos(response, jsonResult.toString());

        } catch (Exception e) {
        }

    }

    private void enviarDatos(HttpServletResponse response, String datos) throws Exception {
        PrintWriter out = null;
        out = response.getWriter();
        out.print(datos);
        out.close();
    }

    private void cerrarSesion(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        session.invalidate();
        try {
            response.sendRedirect("index.jsp");
        } catch (Exception e) {
        }
    }

    private void busquedaUsuario(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonResult = new JSONObject();
        Mensaje mensaje = new Mensaje();
        String codigo = request.getParameter("txtCodUsuario");
        String apellidos = request.getParameter("txtApellidos");
        String estado = request.getParameter("estado");
        try {
            UsuarioDao usuarioDao = new UsuarioDao();
            List<Usuario> listUsuario = usuarioDao.busquedaPorCampos(codigo, apellidos, estado);
            JSONArray jsonFil = new JSONArray();
            JSONArray jsonCol = null;
            if (listUsuario != null) {
                for (Usuario usuario : listUsuario) {
                    jsonCol = new JSONArray();
                    jsonCol.put(usuario.getCodUsuario());
                    jsonCol.put(usuario.getNombre());
                    jsonCol.put(usuario.getApellido());
                    jsonCol.put(usuario.getCodTipoUsuario().getNombreTipoUsuario());
                    jsonCol.put(usuario.getFlgActivo() ? "Activo" : "Inactivo");
                    jsonFil.put(jsonCol);
                }
            }
            jsonResult.put("list", jsonFil);

        } catch (Exception e) {
            mensaje.establecerError(e);
        }
        JSONObject jsonMensaje = new JSONObject(mensaje);
        try {
            jsonResult.put("msj", jsonMensaje);
            //  jsonResult.put("", "");
            enviarDatos(response, jsonResult.toString());

        } catch (Exception e) {
        }
    }

    private void obtenerUsuario(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonResult = new JSONObject();
        Mensaje mensaje = new Mensaje();
        String codUsuario = request.getParameter("codUsuario");
        try {
            UsuarioDao usuarioDao = new UsuarioDao();
            Usuario usuario = usuarioDao.Obtener(codUsuario);
            Alumno alumno = null;
            Docente docente = null;
            if (usuario.getCodTipoUsuario().getCodTipoUsuario() == 2) { //docente
                docente = usuarioDao.ObtenerDocente(codUsuario);
            }
            if (usuario.getCodTipoUsuario().getCodTipoUsuario() == 3) { //al
                alumno = usuarioDao.ObtenerAlumno(codUsuario);
            }
            if (usuario != null) {
                JSONObject j = new JSONObject();
                j.put("codUsuario", usuario.getCodUsuario());
                j.put("nombre", usuario.getNombre());
                j.put("apellido", usuario.getApellido());
                j.put("dni", usuario.getDni());
                j.put("fechaNacimiento", dateFormat.format(usuario.getFechaNacimiento()));
                j.put("sexo", usuario.getSexo());
                j.put("numCelular", usuario.getNumCelular());
                j.put("correo", usuario.getCorreo());
                j.put("contrasenia", usuario.getContrasenia());
                j.put("flgActivo", usuario.getFlgActivo());
                j.put("codTipoUsuario", usuario.getCodTipoUsuario().getCodTipoUsuario());
                if (docente != null) {
                    j.put("isTiempoCompleto", docente.getIsTiempoCompleto());
                }
                if (alumno != null) {
                    j.put("colegioProcedencia", alumno.getColegioProcedencia());
                    j.put("codCarrera", alumno.getCodCarrera().getCodCarrera());
                    j.put("codSede", alumno.getCodSede().getCodSede());
                }
                jsonResult.put("usuario", j);
            } else {
                mensaje.setMensaje(Mensaje.ERROR, "El usuario seleccionado no se encontro en la base de datos.");
            }

        } catch (Exception e) {
            mensaje.establecerError(e);
        }
        JSONObject jsonMensaje = new JSONObject(mensaje);
        try {
            jsonResult.put("msj", jsonMensaje);
            enviarDatos(response, jsonResult.toString());

        } catch (Exception e) {
        }

    }

    private void guardarUsuario(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonResult = new JSONObject();
        Mensaje mensaje = new Mensaje();
        Map<String, String[]> map = request.getParameterMap();
        try {
            UsuarioDao usuarioDao = new UsuarioDao();
            String txtCodigo = request.getParameter("txtCodigo");
            String txtNombre = request.getParameter("txtNombre");
            String txtApellido = request.getParameter("txtApellido");
            String txtDNI = request.getParameter("txtDNI");
            String txtNacimiento = request.getParameter("txtNacimiento");
            String cbxSexo = request.getParameter("cbxSexo");
            String txtCelular = request.getParameter("txtCelular");
            String txtCorreo = request.getParameter("txtCorreo");
            String txtContrasenia = request.getParameter("txtContrasenia");
            String cbxEstado = request.getParameter("cbxEstado");
            String txtTipoUsuario = request.getParameter("txtTipoUsuario");
            Usuario u = new Usuario();
            if (!txtCodigo.isEmpty()) {
                u = usuarioDao.Obtener(txtCodigo);
            }
            u.setNombre(txtNombre.trim());
            u.setApellido(txtApellido.trim());
            u.setDni(txtDNI);
            u.setFechaNacimiento(dateFormat.parse(txtNacimiento));
            u.setSexo(Boolean.parseBoolean(cbxSexo));
            u.setNumCelular(txtCelular);
            u.setCorreo(txtCorreo);
            u.setContrasenia(txtContrasenia);
            u.setFlgActivo(Boolean.parseBoolean(cbxEstado));
            Tipousuario t = usuarioDao.ObtenerTipo(txtTipoUsuario);
            u.setCodTipoUsuario(t);
            if (!usuarioDao.existeDni(u)) {
                if (txtCodigo.isEmpty()) //nuevo
                {
                    usuarioDao.Insertar(u);
                    mensaje.setMensaje(Mensaje.INFORMACION, "Se registro correctamente el usuario con codigo: " + u.getCodUsuario());
                } else {
                    usuarioDao.Actualizar(u);
                    mensaje.setMensaje(Mensaje.INFORMACION, "Se actualizo correctamente el usuario con codigo: " + u.getCodUsuario());
                }
                CursoDao cursoDao = new CursoDao();
                SedeDao sedeDao = new SedeDao();
                if ("3".equals(txtTipoUsuario)) {
                    String txtColegioProc = request.getParameter("txtColegioProc");
                    String cbxCarrera = request.getParameter("cbxCarrera");
                    String cbxSede = request.getParameter("cbxSede");
                    Carrera c = cursoDao.ObtenerCarrera(Integer.valueOf(cbxCarrera));
                    Sede s = sedeDao.Obtener(cbxSede);
                    Alumno a = new Alumno();
                    if (!txtCodigo.isEmpty()) {
                        a = usuarioDao.ObtenerAlumno(txtCodigo);
                    }
                    a.setCodAlumno(u.getCodUsuario());
                    a.setColegioProcedencia(txtColegioProc);
                    a.setCodCarrera(c);
                    a.setCodSede(s);
                    if (txtCodigo.isEmpty()) {
                        usuarioDao.InsertarALumno(a);
                        mensaje.setMensaje(Mensaje.INFORMACION, "Se registro correctamente el usuario con codigo: " + u.getCodUsuario());
                    } else {
                        usuarioDao.ActualizarAlumno(a);
                        mensaje.setMensaje(Mensaje.INFORMACION, "Se actualizo correctamente el usuario con codigo: " + u.getCodUsuario());
                    }
                }
                if ("2".equals(txtTipoUsuario)) {
                    String cbTiempoCom = request.getParameter("cbTiempoCom");
                    Docente d = new Docente();
                    if (!txtCodigo.isEmpty()) {
                        d = usuarioDao.ObtenerDocente(txtCodigo);
                    }
                    d.setCodDocente(u.getCodUsuario());
                    d.setIsTiempoCompleto(cbTiempoCom != null);
                    if (txtCodigo.isEmpty()) {
                        usuarioDao.InsertarDocente(d);
                        mensaje.setMensaje(Mensaje.INFORMACION, "Se registro correctamente el usuario con codigo: " + u.getCodUsuario());
                    } else {
                        usuarioDao.ActualizarDocente(d);
                        mensaje.setMensaje(Mensaje.INFORMACION, "Se actualizo correctamente el usuario con codigo: " + u.getCodUsuario());
                    }
                }
            } else {
                mensaje.setMensaje(Mensaje.ERROR, "El número de DNI se encuentra registrado, ingresar un número distinto.");
            }

        } catch (Exception e) {
            mensaje.establecerError(e);
        }
        JSONObject jsonMensaje = new JSONObject(mensaje);
        try {
            jsonResult.put("msj", jsonMensaje);
            enviarDatos(response, jsonResult.toString());

        } catch (Exception e) {
        }
    }

    private void obtenerUsuarioSesion(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonResult = new JSONObject();
        Mensaje mensaje = new Mensaje();
        HttpSession session = request.getSession();
        //   Usuario usuarioLogeado = (Usuario)session.getAttribute("usuarioLogeado");
        //String codUsuario = request.getParameter("codUsuario");
        try {
            UsuarioDao usuarioDao = new UsuarioDao();
            Usuario usuarioLogeado = (Usuario) session.getAttribute("usuarioLogeado");
            Alumno alumno = null;
            Docente docente = null;
            if (usuarioLogeado.getCodTipoUsuario().getCodTipoUsuario() == 2) { //docente
                docente = usuarioDao.ObtenerDocente(usuarioLogeado.getCodUsuario());
            }
            if (usuarioLogeado.getCodTipoUsuario().getCodTipoUsuario() == 3) { //al
                alumno = usuarioDao.ObtenerAlumno(usuarioLogeado.getCodUsuario());
            }
            if (usuarioLogeado != null) {
                JSONObject j = new JSONObject();
                j.put("codUsuario", usuarioLogeado.getCodUsuario());
                j.put("nombre", usuarioLogeado.getNombre());
                j.put("apellido", usuarioLogeado.getApellido());
                j.put("dni", usuarioLogeado.getDni());
                j.put("fechaNacimiento", dateFormat.format(usuarioLogeado.getFechaNacimiento()));
                j.put("sexo", usuarioLogeado.getSexo());
                j.put("numCelular", usuarioLogeado.getNumCelular());
                j.put("correo", usuarioLogeado.getCorreo());
                j.put("contrasenia", usuarioLogeado.getContrasenia());
                j.put("flgActivo", usuarioLogeado.getFlgActivo());
                j.put("codTipoUsuario", usuarioLogeado.getCodTipoUsuario().getCodTipoUsuario());
                if (docente != null) {
                    j.put("isTiempoCompleto", docente.getIsTiempoCompleto());
                }
                if (alumno != null) {
                    j.put("colegioProcedencia", alumno.getColegioProcedencia());
                    j.put("codCarrera", alumno.getCodCarrera().getCodCarrera());
                    j.put("codSede", alumno.getCodSede().getCodSede());
                }
                jsonResult.put("usuario", j);
            } else {
                mensaje.setMensaje(Mensaje.ERROR, "No hay usuario logueado. Redireccionando ...");
                response.sendRedirect("login.jsp");
            }

        } catch (Exception e) {
            mensaje.establecerError(e);
        }
        JSONObject jsonMensaje = new JSONObject(mensaje);
        try {
            jsonResult.put("msj", jsonMensaje);
            enviarDatos(response, jsonResult.toString());

        } catch (Exception e) {
        }

    }

    private void guardarUsuarioDeSesion(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonResult = new JSONObject();
        Mensaje mensaje = new Mensaje();

        try {
            HttpSession session = request.getSession();
            UsuarioDao usuarioDao = new UsuarioDao();
            Usuario usuarioLogeado = (Usuario) session.getAttribute("usuarioLogeado");
            String txtGlbCelular = request.getParameter("txtGlbCelular");
            String txtGlbCorreo = request.getParameter("txtGlbCorreo");
            String txtGlbContrasenia = request.getParameter("txtGlbContrasenia");

            usuarioLogeado.setNumCelular(txtGlbCelular);
            usuarioLogeado.setCorreo(txtGlbCorreo);
            usuarioLogeado.setContrasenia(txtGlbContrasenia);

            usuarioDao.Actualizar(usuarioLogeado);

            mensaje.setMensaje(Mensaje.INFORMACION, "Se actualizo correctamente el usuario con codigo: " + usuarioLogeado.getCodUsuario());
        } catch (Exception e) {
            mensaje.establecerError(e);
        }
        JSONObject jsonMensaje = new JSONObject(mensaje);
        try {
            jsonResult.put("msj", jsonMensaje);
            enviarDatos(response, jsonResult.toString());

        } catch (Exception e) {
        }
    }

}
