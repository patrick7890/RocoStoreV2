/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Patricio
 */
@Entity
@Table(name = "tipoticket")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipoticket.findAll", query = "SELECT t FROM Tipoticket t")
    , @NamedQuery(name = "Tipoticket.findByIdTipoTicket", query = "SELECT t FROM Tipoticket t WHERE t.idTipoTicket = :idTipoTicket")
    , @NamedQuery(name = "Tipoticket.findByDescripcionTicket", query = "SELECT t FROM Tipoticket t WHERE t.descripcionTicket = :descripcionTicket")})
public class Tipoticket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipoTicket")
    private Integer idTipoTicket;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "descripcionTicket")
    private String descripcionTicket;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoTicketidTipoTicket")
    private List<Ticket> ticketList;

    public Tipoticket() {
    }

    public Tipoticket(Integer idTipoTicket) {
        this.idTipoTicket = idTipoTicket;
    }

    public Tipoticket(Integer idTipoTicket, String descripcionTicket) {
        this.idTipoTicket = idTipoTicket;
        this.descripcionTicket = descripcionTicket;
    }

    public Integer getIdTipoTicket() {
        return idTipoTicket;
    }

    public void setIdTipoTicket(Integer idTipoTicket) {
        this.idTipoTicket = idTipoTicket;
    }

    public String getDescripcionTicket() {
        return descripcionTicket;
    }

    public void setDescripcionTicket(String descripcionTicket) {
        this.descripcionTicket = descripcionTicket;
    }

    @XmlTransient
    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoTicket != null ? idTipoTicket.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoticket)) {
            return false;
        }
        Tipoticket other = (Tipoticket) object;
        if ((this.idTipoTicket == null && other.idTipoTicket != null) || (this.idTipoTicket != null && !this.idTipoTicket.equals(other.idTipoTicket))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Tipoticket[ idTipoTicket=" + idTipoTicket + " ]";
    }
    
}
