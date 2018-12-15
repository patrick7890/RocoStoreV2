/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Patricio
 */
@Entity
@Table(name = "compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compra.findAll", query = "SELECT c FROM Compra c")
    , @NamedQuery(name = "Compra.findByIdCompra", query = "SELECT c FROM Compra c WHERE c.idCompra = :idCompra")
    , @NamedQuery(name = "Compra.findByFechaCompra", query = "SELECT c FROM Compra c WHERE c.fechaCompra = :fechaCompra")
    , @NamedQuery(name = "Compra.findByTotalCompra", query = "SELECT c FROM Compra c WHERE c.totalCompra = :totalCompra")
    , @NamedQuery(name = "Compra.findByFacturada", query = "SELECT c FROM Compra c WHERE c.facturada = :facturada")})
public class Compra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCompra")
    private Integer idCompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaCompra")
    @Temporal(TemporalType.DATE)
    private Date fechaCompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "totalCompra")
    private int totalCompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "facturada")
    private boolean facturada;
    @JoinTable(name = "codigo_compra", joinColumns = {
        @JoinColumn(name = "compra_idCompra", referencedColumnName = "idCompra")}, inverseJoinColumns = {
        @JoinColumn(name = "codigo_codigo", referencedColumnName = "codigo")})
    @ManyToMany
    private List<Codigo> codigoList;
    @JoinColumn(name = "Usuario_idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false)
    private Usuario usuarioidUsuario;

    public Compra() {
    }

    public Compra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Compra(Integer idCompra, Date fechaCompra, int totalCompra, boolean facturada) {
        this.idCompra = idCompra;
        this.fechaCompra = fechaCompra;
        this.totalCompra = totalCompra;
        this.facturada = facturada;
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public int getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(int totalCompra) {
        this.totalCompra = totalCompra;
    }

    public boolean getFacturada() {
        return facturada;
    }

    public void setFacturada(boolean facturada) {
        this.facturada = facturada;
    }

    @XmlTransient
    public List<Codigo> getCodigoList() {
        return codigoList;
    }

    public void setCodigoList(List<Codigo> codigoList) {
        this.codigoList = codigoList;
    }

    public Usuario getUsuarioidUsuario() {
        return usuarioidUsuario;
    }

    public void setUsuarioidUsuario(Usuario usuarioidUsuario) {
        this.usuarioidUsuario = usuarioidUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCompra != null ? idCompra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compra)) {
            return false;
        }
        Compra other = (Compra) object;
        if ((this.idCompra == null && other.idCompra != null) || (this.idCompra != null && !this.idCompra.equals(other.idCompra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Compra[ idCompra=" + idCompra + " ]";
    }
    
}
