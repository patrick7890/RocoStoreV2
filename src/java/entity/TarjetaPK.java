/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Patricio
 */
@Embeddable
public class TarjetaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idTarjeta")
    private int idTarjeta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Usuario_idUsuario")
    private int usuarioidUsuario;

    public TarjetaPK() {
    }

    public TarjetaPK(int idTarjeta, int usuarioidUsuario) {
        this.idTarjeta = idTarjeta;
        this.usuarioidUsuario = usuarioidUsuario;
    }

    public int getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(int idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public int getUsuarioidUsuario() {
        return usuarioidUsuario;
    }

    public void setUsuarioidUsuario(int usuarioidUsuario) {
        this.usuarioidUsuario = usuarioidUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idTarjeta;
        hash += (int) usuarioidUsuario;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TarjetaPK)) {
            return false;
        }
        TarjetaPK other = (TarjetaPK) object;
        if (this.idTarjeta != other.idTarjeta) {
            return false;
        }
        if (this.usuarioidUsuario != other.usuarioidUsuario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TarjetaPK[ idTarjeta=" + idTarjeta + ", usuarioidUsuario=" + usuarioidUsuario + " ]";
    }
    
}
