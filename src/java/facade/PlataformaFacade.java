/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Plataforma;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.Plataforma_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Juego;
import java.util.List;

/**
 *
 * @author Patricio
 */
@Stateless
public class PlataformaFacade extends AbstractFacade<Plataforma> {

    @PersistenceContext(unitName = "RocoStoreV2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlataformaFacade() {
        super(Plataforma.class);
    }

    public boolean isJuegoListEmpty(Plataforma entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Plataforma> plataforma = cq.from(Plataforma.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(plataforma, entity), cb.isNotEmpty(plataforma.get(Plataforma_.juegoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Juego> findJuegoList(Plataforma entity) {
        Plataforma mergedEntity = this.getMergedEntity(entity);
        List<Juego> juegoList = mergedEntity.getJuegoList();
        juegoList.size();
        return juegoList;
    }
    
}
