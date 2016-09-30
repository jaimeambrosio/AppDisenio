/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dis.usuario.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jaime Ambrosio
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByCodUsuario", query = "SELECT u FROM Usuario u WHERE u.codUsuario = :codUsuario"),
    @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "Usuario.findByApellido", query = "SELECT u FROM Usuario u WHERE u.apellido = :apellido"),
    @NamedQuery(name = "Usuario.findByDni", query = "SELECT u FROM Usuario u WHERE u.dni = :dni"),
    @NamedQuery(name = "Usuario.findByFechaNacimiento", query = "SELECT u FROM Usuario u WHERE u.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Usuario.findBySexo", query = "SELECT u FROM Usuario u WHERE u.sexo = :sexo"),
    @NamedQuery(name = "Usuario.findByNumCelular", query = "SELECT u FROM Usuario u WHERE u.numCelular = :numCelular"),
    @NamedQuery(name = "Usuario.findByCorreo", query = "SELECT u FROM Usuario u WHERE u.correo = :correo"),
    @NamedQuery(name = "Usuario.findByContrasenia", query = "SELECT u FROM Usuario u WHERE u.contrasenia = :contrasenia"),
    @NamedQuery(name = "Usuario.findByFlgActivo", query = "SELECT u FROM Usuario u WHERE u.flgActivo = :flgActivo"),
    @NamedQuery(name = "Usuario.findByColegioProcedencia", query = "SELECT u FROM Usuario u WHERE u.colegioProcedencia = :colegioProcedencia"),
    @NamedQuery(name = "Usuario.findByFechaHoraBloqueo", query = "SELECT u FROM Usuario u WHERE u.fechaHoraBloqueo = :fechaHoraBloqueo"),
    @NamedQuery(name = "Usuario.findByCantErrorIngreso", query = "SELECT u FROM Usuario u WHERE u.cantErrorIngreso = :cantErrorIngreso")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codUsuario")
    private String codUsuario;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "dni")
    private String dni;
    @Column(name = "fechaNacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "sexo")
    private Boolean sexo;
    @Column(name = "numCelular")
    private String numCelular;
    @Basic(optional = false)
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @Column(name = "contrasenia")
    private String contrasenia;
    @Column(name = "flgActivo")
    private Boolean flgActivo;
    @Column(name = "colegioProcedencia")
    private String colegioProcedencia;
    @Column(name = "fechaHoraBloqueo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraBloqueo;
    @Column(name = "cantErrorIngreso")
    private Integer cantErrorIngreso;
    @JoinColumn(name = "codTipoUsuario", referencedColumnName = "codTipoUsuario")
    @ManyToOne(optional = false)
    private Tipousuario codTipoUsuario;

    public Usuario() {
    }

    public Usuario(String codUsuario) {
        this.codUsuario = codUsuario;
    }

    public Usuario(String codUsuario, String correo, String contrasenia) {
        this.codUsuario = codUsuario;
        this.correo = correo;
        this.contrasenia = contrasenia;
    }

    public String getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(String codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Boolean getSexo() {
        return sexo;
    }

    public void setSexo(Boolean sexo) {
        this.sexo = sexo;
    }

    public String getNumCelular() {
        return numCelular;
    }

    public void setNumCelular(String numCelular) {
        this.numCelular = numCelular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Boolean getFlgActivo() {
        return flgActivo;
    }

    public void setFlgActivo(Boolean flgActivo) {
        this.flgActivo = flgActivo;
    }

    public String getColegioProcedencia() {
        return colegioProcedencia;
    }

    public void setColegioProcedencia(String colegioProcedencia) {
        this.colegioProcedencia = colegioProcedencia;
    }

    public Date getFechaHoraBloqueo() {
        return fechaHoraBloqueo;
    }

    public void setFechaHoraBloqueo(Date fechaHoraBloqueo) {
        this.fechaHoraBloqueo = fechaHoraBloqueo;
    }

    public Integer getCantErrorIngreso() {
        return cantErrorIngreso;
    }

    public void setCantErrorIngreso(Integer cantErrorIngreso) {
        this.cantErrorIngreso = cantErrorIngreso;
    }

    public Tipousuario getCodTipoUsuario() {
        return codTipoUsuario;
    }

    public void setCodTipoUsuario(Tipousuario codTipoUsuario) {
        this.codTipoUsuario = codTipoUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codUsuario != null ? codUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.codUsuario == null && other.codUsuario != null) || (this.codUsuario != null && !this.codUsuario.equals(other.codUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dis.usuario.entity.Usuario[ codUsuario=" + codUsuario + " ]";
    }
    
}
