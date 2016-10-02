/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dis.usuario.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "docente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Docente.findAll", query = "SELECT d FROM Docente d"),
    @NamedQuery(name = "Docente.findByIsTiempoCompleto", query = "SELECT d FROM Docente d WHERE d.isTiempoCompleto = :isTiempoCompleto"),
    @NamedQuery(name = "Docente.findByCodDocente", query = "SELECT d FROM Docente d WHERE d.codDocente = :codDocente")})
public class Docente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "isTiempoCompleto")
    private Boolean isTiempoCompleto;
    @Id
    @Basic(optional = false)
    @Column(name = "codDocente")
    private String codDocente;
    @JoinColumn(name = "codDocente", referencedColumnName = "codUsuario", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario;

    public Docente() {
    }

    public Docente(String codDocente) {
        this.codDocente = codDocente;
    }

    public Boolean getIsTiempoCompleto() {
        return isTiempoCompleto;
    }

    public void setIsTiempoCompleto(Boolean isTiempoCompleto) {
        this.isTiempoCompleto = isTiempoCompleto;
    }

    public String getCodDocente() {
        return codDocente;
    }

    public void setCodDocente(String codDocente) {
        this.codDocente = codDocente;
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
        hash += (codDocente != null ? codDocente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Docente)) {
            return false;
        }
        Docente other = (Docente) object;
        if ((this.codDocente == null && other.codDocente != null) || (this.codDocente != null && !this.codDocente.equals(other.codDocente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dis.curso.entity.Docente[ codDocente=" + codDocente + " ]";
    }
    
}
