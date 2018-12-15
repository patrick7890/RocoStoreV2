/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Patricio
 */
@Entity
@Table(name = "tarjeta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tarjeta.findAll", query = "SELECT t FROM Tarjeta t")
    , @NamedQuery(name = "Tarjeta.findByIdTarjeta", query = "SELECT t FROM Tarjeta t WHERE t.tarjetaPK.idTarjeta = :idTarjeta")
    , @NamedQuery(name = "Tarjeta.findByNumTarjeta", query = "SELECT t FROM Tarjeta t WHERE t.numTarjeta = :numTarjeta")
    , @NamedQuery(name = "Tarjeta.findByFechaExp", query = "SELECT t FROM Tarjeta t WHERE t.fechaExp = :fechaExp")
    , @NamedQuery(name = "Tarjeta.findByCvv", query = "SELECT t FROM Tarjeta t WHERE t.cvv = :cvv")
    , @NamedQuery(name = "Tarjeta.findByUsuarioidUsuario", query = "SELECT t FROM Tarjeta t WHERE t.tarjetaPK.usuarioidUsuario = :usuarioidUsuario")})
public class Tarjeta implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TarjetaPK tarjetaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numTarjeta")
    private int numTarjeta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaExp")
    @Temporal(TemporalType.DATE)
    private Date fechaExp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CVV")
    private int cvv;
    @JoinColumn(name = "Usuario_idUsuario", referencedColumnName = "idUsuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Tarjeta() {
    }

    public Tarjeta(TarjetaPK tarjetaPK) {
        this.tarjetaPK = tarjetaPK;
    }

    public Tarjeta(TarjetaPK tarjetaPK, int numTarjeta, Date fechaExp, int cvv) {
        this.tarjetaPK = tarjetaPK;
        this.numTarjeta = numTarjeta;
        this.fechaExp = fechaExp;
        this.cvv = cvv;
    }

    public Tarjeta(int idTarjeta, int usuarioidUsuario) {
        this.tarjetaPK = new TarjetaPK(idTarjeta, usuarioidUsuario);
    }

    public TarjetaPK getTarjetaPK() {
        return tarjetaPK;
    }

    public void setTarjetaPK(TarjetaPK tarjetaPK) {
        this.tarjetaPK = tarjetaPK;
    }

    public int getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(int numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public Date getFechaExp() {
        return fechaExp;
    }

    public void setFechaExp(Date fechaExp) {
        this.fechaExp = fechaExp;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
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
        hash += (tarjetaPK != null ? tarjetaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tarjeta)) {
            return false;
        }
        Tarjeta other = (Tarjeta) object;
        if ((this.tarjetaPK == null && other.tarjetaPK != null) || (this.tarjetaPK != null && !this.tarjetaPK.equals(other.tarjetaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Tarjeta[ tarjetaPK=" + tarjetaPK + " ]";
    }
    
}
