/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Ticket;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.Ticket_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Tipoticket;
import entity.Usuario;

/**
 *
 * @author Patricio
 */
@Stateless
public class TicketFacade extends AbstractFacade<Ticket> {

    @PersistenceContext(unitName = "RocoStoreV2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TicketFacade() {
        super(Ticket.class);
    }

    public boolean isTipoTicketidTipoTicketEmpty(Ticket entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Ticket> ticket = cq.from(Ticket.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(ticket, entity), cb.isNotNull(ticket.get(Ticket_.tipoTicketidTipoTicket)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Tipoticket findTipoTicketidTipoTicket(Ticket entity) {
        return this.getMergedEntity(entity).getTipoTicketidTipoTicket();
    }

    public boolean isUsuarioidUsuarioEmpty(Ticket entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Ticket> ticket = cq.from(Ticket.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(ticket, entity), cb.isNotNull(ticket.get(Ticket_.usuarioidUsuario)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Usuario findUsuarioidUsuario(Ticket entity) {
        return this.getMergedEntity(entity).getUsuarioidUsuario();
    }
    
}
