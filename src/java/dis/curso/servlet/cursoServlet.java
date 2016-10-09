/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dis.curso.servlet;

import dis.curso.dao.CursoDao;
import dis.curso.entity.Curso;
import dis.entity.Mensaje;
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
@WebServlet(name = "cursoServlet", urlPatterns = {"/cursoServlet"})
public class cursoServlet extends HttpServlet {

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
                buscarCursos(request, response);
                break;
            }
            case "GUARDAR": {
                guardarCurso(request, response);
                break;
            }
            case "GETCURSO": {
                obtenerCurso(request, response);
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

    private void buscarCursos(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonResult = new JSONObject();
        Mensaje mensaje = new Mensaje();
        String txtCodigo = request.getParameter("txtCodigo");
        String txtNombre = request.getParameter("txtNombre");
        String estado = request.getParameter("estado");
        try {
            CursoDao cursoDao = new CursoDao();
           // List<Curso> list = cursoDao.listar();
            List<Curso> list = cursoDao.buscar(txtCodigo,txtNombre,estado);
            JSONArray jsonFil = new JSONArray();
            JSONArray jsonCol = null;
            if (list != null) {
                for (Curso curso : list) {
                    jsonCol = new JSONArray();
                    jsonCol.put(curso.getCodCurso());
                    jsonCol.put(curso.getNombreCurso());
                    jsonCol.put(curso.getNivel());
                    jsonCol.put(curso.getCreditos());
                    jsonCol.put(curso.getCodCarrera().getNombreCarrera());
                    jsonCol.put(curso.getFlgActivo() ? "Activo" : "Inactivo");
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

    private void guardarCurso(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonResult = new JSONObject();
        Mensaje mensaje = new Mensaje();
        try {
            CursoDao cursoDao = new CursoDao();
            String txtCodigoCurso = request.getParameter("txtCodigoCurso");
            String txtNombreCurso = request.getParameter("txtNombreCurso");
            String txtNivel = request.getParameter("txtNivel");
            String txtCreditos = request.getParameter("txtCreditos");
            String cbxCarrera = request.getParameter("cbxCarrera");
            String cbxEstado = request.getParameter("cbxEstado");
            Curso curso = new Curso();
            if (!txtCodigoCurso.isEmpty()) {
                curso = cursoDao.Obtener(txtCodigoCurso);
            }
            txtNombreCurso = txtNombreCurso.replaceAll("  ", "");
            curso.setNombreCurso(txtNombreCurso);
            curso.setNivel(Integer.valueOf(txtNivel));
            curso.setCreditos(Integer.valueOf(txtCreditos));
            curso.setCodCarrera(cursoDao.ObtenerCarrera(Integer.valueOf(cbxCarrera)));
            curso.setFlgActivo(Boolean.parseBoolean(cbxEstado));

            if (txtCodigoCurso.isEmpty()) {
                cursoDao.Insertar(curso);
            } else {
                cursoDao.Actualizar(curso);
            }
            mensaje.setMensaje(Mensaje.INFORMACION, "Transacción exitosa. El curso con codigo: " + curso.getCodCurso() + " fue registrado");

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

    private void obtenerCurso(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonResult = new JSONObject();
        Mensaje mensaje = new Mensaje();
        try {
            String codigo = request.getParameter("codigo");
            CursoDao  cursoDao = new CursoDao();
            Curso curso= cursoDao.Obtener(codigo);
            JSONObject j = new JSONObject();
            j.put("cod", curso.getCodCurso());
            j.put("nombre", curso.getNombreCurso());
            j.put("nivel", curso.getNivel());
            j.put("creditos", curso.getCreditos());
            j.put("codCarr", curso.getCodCarrera().getCodCarrera());
            j.put("estado", curso.getFlgActivo().toString());
            
            jsonResult.put("curso", j);
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
