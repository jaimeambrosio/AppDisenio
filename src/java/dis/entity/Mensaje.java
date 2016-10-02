/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dis.entity;

/**
 *
 * @author Jaime Ambrosio
 */
public class Mensaje {

    public static final String ERROR = "ERROR";
    public static final String ADVERTENCIA = "ADVERTENCIA";
    public static final String INFORMACION = "INFORMACION";

    private boolean hayMensaje;
    private String tipo;
    private String mensaje;

    public Mensaje() {
        hayMensaje = false;
        tipo = null;
        mensaje = null;
    }

    public Mensaje(boolean hayMensaje, String tipo) {
        this.hayMensaje = hayMensaje;
        this.tipo = tipo;
    }

    public void setMensaje(String tipo, String mensaje) {
        this.hayMensaje = true;
        this.tipo = tipo;
        this.mensaje = mensaje;
    }

    /**
     * @return the hayMensaje
     */
    public boolean isHayMensaje() {
        return hayMensaje;
    }

    /**
     * @param hayMensaje the hayMensaje to set
     */
    public void setHayMensaje(boolean hayMensaje) {
        this.hayMensaje = hayMensaje;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void establecerError(Exception e) {
        hayMensaje = true;
        tipo = Mensaje.ERROR;
        mensaje = "Error al procesar la solicitud en el servidor. <br>" + e.getMessage();
    }

}
