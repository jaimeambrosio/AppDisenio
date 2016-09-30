/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dis.producto.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "tipoproducto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipoproducto.findAll", query = "SELECT t FROM Tipoproducto t"),
    @NamedQuery(name = "Tipoproducto.findByCodTipoProducto", query = "SELECT t FROM Tipoproducto t WHERE t.codTipoProducto = :codTipoProducto"),
    @NamedQuery(name = "Tipoproducto.findByNombreTipoProd", query = "SELECT t FROM Tipoproducto t WHERE t.nombreTipoProd = :nombreTipoProd")})
public class Tipoproducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codTipoProducto")
    private Integer codTipoProducto;
    @Column(name = "nombreTipoProd")
    private String nombreTipoProd;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codTipoProducto")
    private List<Producto> productoList;

    public Tipoproducto() {
    }

    public Tipoproducto(Integer codTipoProducto) {
        this.codTipoProducto = codTipoProducto;
    }

    public Integer getCodTipoProducto() {
        return codTipoProducto;
    }

    public void setCodTipoProducto(Integer codTipoProducto) {
        this.codTipoProducto = codTipoProducto;
    }

    public String getNombreTipoProd() {
        return nombreTipoProd;
    }

    public void setNombreTipoProd(String nombreTipoProd) {
        this.nombreTipoProd = nombreTipoProd;
    }

    @XmlTransient
    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codTipoProducto != null ? codTipoProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoproducto)) {
            return false;
        }
        Tipoproducto other = (Tipoproducto) object;
        if ((this.codTipoProducto == null && other.codTipoProducto != null) || (this.codTipoProducto != null && !this.codTipoProducto.equals(other.codTipoProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dis.curso.entity.Tipoproducto[ codTipoProducto=" + codTipoProducto + " ]";
    }
    
}
