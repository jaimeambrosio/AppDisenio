package dis.sede.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import dis.entity.Mensaje;
import dis.sede.dao.SedeDao;
import dis.sede.entity.Sede;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Jaime Ambrosio
 */
@WebServlet(urlPatterns = {"/sedeServlet"})
public class sedeServlet extends HttpServlet {

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
            case "BUSQ": {
                buscarSedes(request, response);
                break;
            }
            case "GETSEDE": {
                obtenerSede(request, response);
                break;
            }
            case "GUARDAR": {
                guardarEdicion(request, response);
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

    private void buscarSedes(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonResult = new JSONObject();
        Mensaje mensaje = new Mensaje();
        String txtCodigo = request.getParameter("txtCodigo");
        String txtNombreSede = request.getParameter("txtNombreSede");
        String estado = request.getParameter("estado");
        try {
            SedeDao sedeDao = new SedeDao();
            //List<Sede> list = sedeDao.listar();
            List<Sede> list = sedeDao.buscar(txtCodigo, txtNombreSede, estado);
            JSONArray jsonFil = new JSONArray();
            JSONArray jsonCol = null;
            if (list != null) {
                for (Sede sede : list) {
                    jsonCol = new JSONArray();
                    jsonCol.put(sede.getCodSede());
                    jsonCol.put(sede.getNombreSede());
                    jsonCol.put(sede.getCodDistrito().getNombreDistrito());
                    jsonCol.put(sede.getFlgActivo() ? "Activo" : "Inactivo");
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

    private void enviarDatos(HttpServletResponse response, String datos) throws Exception {
        PrintWriter out = null;
        out = response.getWriter();
        out.print(datos);
        out.close();
    }

    private void obtenerSede(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonResult = new JSONObject();
        Mensaje mensaje = new Mensaje();
        try {
            String codSede = request.getParameter("codSede");
            SedeDao dao = new SedeDao();
            Sede sede = dao.Obtener(codSede);
            JSONObject j = new JSONObject();
            j.put("codSede", sede.getCodSede());
            j.put("nombreSede", sede.getNombreSede());
            j.put("direccion", sede.getDireccion());
            j.put("descripcion", sede.getDescripcion());
            j.put("estado", sede.getFlgActivo());
            j.put("codDistrito", sede.getCodDistrito().getCodDistrito());
            jsonResult.put("sede", j);
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

    private void guardarEdicion(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonResult = new JSONObject();
        Mensaje mensaje = new Mensaje();
        try {
            SedeDao sedeDao = new SedeDao();
            String txtCodigoSede = request.getParameter("txtCodigoSede");
            String txtNombre = request.getParameter("txtNombre");
            String txtDireccion = request.getParameter("txtDireccion");
            String txtDescripcion = request.getParameter("txtDescripcion");
            String cbxDistrito = request.getParameter("cbxDistrito");
            String cbxEstado = request.getParameter("cbxEstado");
            Sede sede = new Sede();
            if (!txtCodigoSede.isEmpty()) {
                sede = sedeDao.Obtener(txtCodigoSede);
            }
            txtNombre = txtNombre.replaceAll("  ", "");
            sede.setNombreSede(txtNombre);
            sede.setDireccion(txtDireccion);
            sede.setDescripcion(txtDescripcion);
            sede.setCodDistrito(sedeDao.ObtenerDistrito(Integer.valueOf(cbxDistrito)));
            sede.setFlgActivo(Boolean.parseBoolean(cbxEstado));

            if (txtCodigoSede.isEmpty()) {
                Mensaje m = sedeDao.Insertar(sede);
                if (m == null) {
                    mensaje.setMensaje(Mensaje.INFORMACION, "Transacción exitosa. La sede: " + sede.getCodSede() + " fue registrada");
                } else {
                    mensaje = m;
                }
            } else {
                sedeDao.Actualizar(sede);
                mensaje.setMensaje(Mensaje.INFORMACION, "Transacción exitosa. La sede: " + sede.getCodSede() + " fue actualizada");
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

}
