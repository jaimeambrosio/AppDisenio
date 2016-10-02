/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dis.usuario.entity;

import dis.curso.entity.Carrera;
import dis.sede.entity.Sede;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jaime Ambrosio
 */
@Entity
@Table(name = "alumno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alumno.findAll", query = "SELECT a FROM Alumno a"),
    @NamedQuery(name = "Alumno.findByColegioProcedencia", query = "SELECT a FROM Alumno a WHERE a.colegioProcedencia = :colegioProcedencia"),
    @NamedQuery(name = "Alumno.findByCodAlumno", query = "SELECT a FROM Alumno a WHERE a.codAlumno = :codAlumno")})
public class Alumno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "colegioProcedencia")
    private String colegioProcedencia;
    @Id
    @Basic(optional = false)
    @Column(name = "codAlumno")
    private String codAlumno;
    @JoinColumn(name = "codCarrera", referencedColumnName = "codCarrera")
    @ManyToOne(optional = false)
    private Carrera codCarrera;
    @JoinColumn(name = "codSede", referencedColumnName = "codSede")
    @ManyToOne(optional = false)
    private Sede codSede;
    @JoinColumn(name = "codAlumno", referencedColumnName = "codUsuario", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario;

    public Alumno() {
    }

    public Alumno(String codAlumno) {
        this.codAlumno = codAlumno;
    }

    public String getColegioProcedencia() {
        return colegioProcedencia;
    }

    public void setColegioProcedencia(String colegioProcedencia) {
        this.colegioProcedencia = colegioProcedencia;
    }

    public String getCodAlumno() {
        return codAlumno;
    }

    public void setCodAlumno(String codAlumno) {
        this.codAlumno = codAlumno;
    }

    public Carrera getCodCarrera() {
        return codCarrera;
    }

    public void setCodCarrera(Carrera codCarrera) {
        this.codCarrera = codCarrera;
    }

    public Sede getCodSede() {
        return codSede;
    }

    public void setCodSede(Sede codSede) {
        this.codSede = codSede;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codAlumno != null ? codAlumno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alumno)) {
            return false;
        }
        Alumno other = (Alumno) object;
        if ((this.codAlumno == null && other.codAlumno != null) || (this.codAlumno != null && !this.codAlumno.equals(other.codAlumno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dis.curso.entity.Alumno[ codAlumno=" + codAlumno + " ]";
    }
    
}
