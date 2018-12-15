/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Tarjeta;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.Tarjeta_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Usuario;

/**
 *
 * @author Patricio
 */
@Stateless
public class TarjetaFacade extends AbstractFacade<Tarjeta> {

    @PersistenceContext(unitName = "RocoStoreV2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TarjetaFacade() {
        super(Tarjeta.class);
    }

    public boolean isUsuarioEmpty(Tarjeta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Tarjeta> tarjeta = cq.from(Tarjeta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tarjeta, entity), cb.isNotNull(tarjeta.get(Tarjeta_.usuario)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Usuario findUsuario(Tarjeta entity) {
        return this.getMergedEntity(entity).getUsuario();
    }
    
}
