/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dis.usuario.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jaime Ambrosio
 */
@Entity
@Table(name = "tipousuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipousuario.findAll", query = "SELECT t FROM Tipousuario t"),
    @NamedQuery(name = "Tipousuario.findByCodTipoUsuario", query = "SELECT t FROM Tipousuario t WHERE t.codTipoUsuario = :codTipoUsuario"),
    @NamedQuery(name = "Tipousuario.findByNombreTipoUsuario", query = "SELECT t FROM Tipousuario t WHERE t.nombreTipoUsuario = :nombreTipoUsuario")})
public class Tipousuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codTipoUsuario")
    private Integer codTipoUsuario;
    @Column(name = "nombreTipoUsuario")
    private String nombreTipoUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codTipoUsuario")
    private List<Usuario> usuarioList;

    public Tipousuario() {
    }

    public Tipousuario(Integer codTipoUsuario) {
        this.codTipoUsuario = codTipoUsuario;
    }

    public Integer getCodTipoUsuario() {
        return codTipoUsuario;
    }

    public void setCodTipoUsuario(Integer codTipoUsuario) {
        this.codTipoUsuario = codTipoUsuario;
    }

    public String getNombreTipoUsuario() {
        return nombreTipoUsuario;
    }

    public void setNombreTipoUsuario(String nombreTipoUsuario) {
        this.nombreTipoUsuario = nombreTipoUsuario;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codTipoUsuario != null ? codTipoUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipousuario)) {
            return false;
        }
        Tipousuario other = (Tipousuario) object;
        if ((this.codTipoUsuario == null && other.codTipoUsuario != null) || (this.codTipoUsuario != null && !this.codTipoUsuario.equals(other.codTipoUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dis.curso.entity.Tipousuario[ codTipoUsuario=" + codTipoUsuario + " ]";
    }
    
}
