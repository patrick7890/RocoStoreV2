/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Genero;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.Genero_;
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
public class GeneroFacade extends AbstractFacade<Genero> {

    @PersistenceContext(unitName = "RocoStoreV2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GeneroFacade() {
        super(Genero.class);
    }

    public boolean isJuegoListEmpty(Genero entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Genero> genero = cq.from(Genero.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(genero, entity), cb.isNotEmpty(genero.get(Genero_.juegoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Juego> findJuegoList(Genero entity) {
        Genero mergedEntity = this.getMergedEntity(entity);
        List<Juego> juegoList = mergedEntity.getJuegoList();
        juegoList.size();
        return juegoList;
    }
    
}
