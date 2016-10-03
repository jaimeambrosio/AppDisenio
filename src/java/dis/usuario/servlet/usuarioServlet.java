/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dis.usuario.servlet;

import dis.entity.Mensaje;
import dis.usuario.dao.UsuarioDao;
import dis.usuario.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
    static int cantTiempoBloq = 1;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        boolean recordar = request.getParameter("txtRecordarP") == null;
        try {
            UsuarioDao usuarioDao = new UsuarioDao();

            Usuario u = usuarioDao.Obtener(codigo);
            if (u != null) {
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
                        mensaje.setMensaje(Mensaje.ERROR, "Su cuenta ha sido bloqueada por 30 minutos. Espere "
                                + (cantTiempoBloq - minutosPasaron)
                                + " minutos para volver a ingresar.");
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
            JSONArray jsonCol = new JSONArray();
            if (listUsuario != null) {
                for (Usuario usuario : listUsuario) {
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

}
