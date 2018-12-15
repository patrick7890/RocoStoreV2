/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Patricio
 */
@Entity
@Table(name = "ticket")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ticket.findAll", query = "SELECT t FROM Ticket t")
    , @NamedQuery(name = "Ticket.findByIdTicket", query = "SELECT t FROM Ticket t WHERE t.idTicket = :idTicket")
    , @NamedQuery(name = "Ticket.findByDescripcionTicket", query = "SELECT t FROM Ticket t WHERE t.descripcionTicket = :descripcionTicket")
    , @NamedQuery(name = "Ticket.findByEstado", query = "SELECT t FROM Ticket t WHERE t.estado = :estado")
    , @NamedQuery(name = "Ticket.findByRespuestaTicket", query = "SELECT t FROM Ticket t WHERE t.respuestaTicket = :respuestaTicket")
    , @NamedQuery(name = "Ticket.findByNombreAdmin", query = "SELECT t FROM Ticket t WHERE t.nombreAdmin = :nombreAdmin")
    , @NamedQuery(name = "Ticket.findByImagenTicket", query = "SELECT t FROM Ticket t WHERE t.imagenTicket = :imagenTicket")})
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTicket")
    private Integer idTicket;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "descripcionTicket")
    private String descripcionTicket;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private short estado;
    @Size(max = 255)
    @Column(name = "respuestaTicket")
    private String respuestaTicket;
    @Size(max = 45)
    @Column(name = "nombreAdmin")
    private String nombreAdmin;
    @Size(max = 45)
    @Column(name = "imagenTicket")
    private String imagenTicket;
    @JoinColumn(name = "TipoTicket_idTipoTicket", referencedColumnName = "idTipoTicket")
    @ManyToOne(optional = false)
    private Tipoticket tipoTicketidTipoTicket;
    @JoinColumn(name = "Usuario_idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false)
    private Usuario usuarioidUsuario;

    public Ticket() {
    }

    public Ticket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public Ticket(Integer idTicket, String descripcionTicket, short estado) {
        this.idTicket = idTicket;
        this.descripcionTicket = descripcionTicket;
        this.estado = estado;
    }

    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public String getDescripcionTicket() {
        return descripcionTicket;
    }

    public void setDescripcionTicket(String descripcionTicket) {
        this.descripcionTicket = descripcionTicket;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public String getRespuestaTicket() {
        return respuestaTicket;
    }

    public void setRespuestaTicket(String respuestaTicket) {
        this.respuestaTicket = respuestaTicket;
    }

    public String getNombreAdmin() {
        return nombreAdmin;
    }

    public void setNombreAdmin(String nombreAdmin) {
        this.nombreAdmin = nombreAdmin;
    }

    public String getImagenTicket() {
        return imagenTicket;
    }

    public void setImagenTicket(String imagenTicket) {
        this.imagenTicket = imagenTicket;
    }

    public Tipoticket getTipoTicketidTipoTicket() {
        return tipoTicketidTipoTicket;
    }

    public void setTipoTicketidTipoTicket(Tipoticket tipoTicketidTipoTicket) {
        this.tipoTicketidTipoTicket = tipoTicketidTipoTicket;
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
        hash += (idTicket != null ? idTicket.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ticket)) {
            return false;
        }
        Ticket other = (Ticket) object;
        if ((this.idTicket == null && other.idTicket != null) || (this.idTicket != null && !this.idTicket.equals(other.idTicket))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Ticket[ idTicket=" + idTicket + " ]";
    }
    
}
