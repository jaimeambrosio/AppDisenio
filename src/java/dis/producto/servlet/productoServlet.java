/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dis.producto.servlet;

import dis.entity.Mensaje;
import dis.producto.dao.ProductoDao;
import dis.producto.entity.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Date;
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
@WebServlet(name = "productoServlet", urlPatterns = {"/productoServlet"})
public class productoServlet extends HttpServlet {

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
                buscarProductos(request, response);
                break;
            }
            case "GUARDAR": {
                guardarProducto(request, response);
                break;
            }
            case "GETPROD": {
                obtenerProd(request, response);
                break;
            }
        }
    }
    
    private void enviarDatos(HttpServletResponse response, String datos) throws Exception {
        PrintWriter out = null;
        out = response.getWriter();
        out.print(datos);
        out.close();
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

    private void buscarProductos(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonResult = new JSONObject();
        Mensaje mensaje = new Mensaje();
        String txtCodigo = request.getParameter("txtCodigo");
        String txtNombre = request.getParameter("txtNombre");
        String estado = request.getParameter("estado");
        String cbxTipo = request.getParameter("cbxTipo");
        try {
            ProductoDao dao = new ProductoDao();
            //List<Producto> list = dao.listar();
            List<Producto> list = dao.buscar(txtCodigo, txtNombre, estado, cbxTipo);
            JSONArray jsonFil = new JSONArray();
            JSONArray jsonCol = null;
            if (list != null) {
                for (Producto p : list) {
                    jsonCol = new JSONArray();
                    jsonCol.put(p.getCodProducto());
                    jsonCol.put(p.getNombre());
                    jsonCol.put(p.getCodTipoProducto().getNombreTipoProd());
                    jsonCol.put(p.getPrecio());
                    jsonCol.put(p.getFlgActivo() ? "Activo" : "Inactivo");
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
    
    private void guardarProducto(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonResult = new JSONObject();
        Mensaje mensaje = new Mensaje();
        try {
            ProductoDao dao = new ProductoDao();
            String txtCodigoProducto = request.getParameter("txtCodigoProducto");
            String txtNombreProducto = request.getParameter("txtNombreProducto");
            String txtPrecio = request.getParameter("txtPrecio");
            String cbxTipoProducto = request.getParameter("cbxTipoProducto");
            String cbxEstado = request.getParameter("cbxEstado");
            Producto producto = new Producto();
            if (!txtCodigoProducto.isEmpty()) {
                producto = dao.Obtener(txtCodigoProducto);
            }
            txtNombreProducto = txtNombreProducto.replaceAll("  ", "");
            producto.setCodProducto(txtCodigoProducto);
            producto.setNombre(txtNombreProducto);
            producto.setPrecio(Double.valueOf(txtPrecio));
            producto.setCodTipoProducto(dao.ObtenerTipo(cbxTipoProducto));
            producto.setFlgActivo(Boolean.valueOf(cbxEstado));
            
            if (txtCodigoProducto.isEmpty()) {
                producto.setFechaRegistro(new Date());
                dao.Insertar(producto);
                mensaje.setMensaje(Mensaje.INFORMACION, "Transacción exitosa. El producto con codigo: " + producto.getCodProducto() + " fue registrado");
            } else {
                dao.Actualizar(producto);
                mensaje.setMensaje(Mensaje.INFORMACION, "Transacción exitosa. El producto con codigo: " + producto.getCodProducto() + " fue actualizado");
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
    
    private void obtenerProd(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonResult = new JSONObject();
        Mensaje mensaje = new Mensaje();
        try {
            String codigo = request.getParameter("codigo");
            ProductoDao dao = new ProductoDao();
            Producto p = dao.Obtener(codigo);
            JSONObject j = new JSONObject();
            j.put("codigo", p.getCodProducto());
            j.put("nombre", p.getNombre());
            j.put("precio", p.getPrecio());
            j.put("codTipo", p.getCodTipoProducto().getCodTipoProducto());
            j.put("estado", p.getFlgActivo());
            jsonResult.put("producto", j);
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
