/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Tipoticket;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.Tipoticket_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Ticket;
import java.util.List;

/**
 *
 * @author Patricio
 */
@Stateless
public class TipoticketFacade extends AbstractFacade<Tipoticket> {

    @PersistenceContext(unitName = "RocoStoreV2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoticketFacade() {
        super(Tipoticket.class);
    }

    public boolean isTicketListEmpty(Tipoticket entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Tipoticket> tipoticket = cq.from(Tipoticket.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoticket, entity), cb.isNotEmpty(tipoticket.get(Tipoticket_.ticketList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Ticket> findTicketList(Tipoticket entity) {
        Tipoticket mergedEntity = this.getMergedEntity(entity);
        List<Ticket> ticketList = mergedEntity.getTicketList();
        ticketList.size();
        return ticketList;
    }
    
}
