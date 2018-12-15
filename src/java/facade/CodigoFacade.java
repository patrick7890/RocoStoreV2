/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Codigo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.Codigo_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Compra;
import entity.Juego;
import java.util.List;

/**
 *
 * @author Patricio
 */
@Stateless
public class CodigoFacade extends AbstractFacade<Codigo> {

    @PersistenceContext(unitName = "RocoStoreV2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CodigoFacade() {
        super(Codigo.class);
    }

    public boolean isCompraListEmpty(Codigo entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Codigo> codigo = cq.from(Codigo.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(codigo, entity), cb.isNotEmpty(codigo.get(Codigo_.compraList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Compra> findCompraList(Codigo entity) {
        Codigo mergedEntity = this.getMergedEntity(entity);
        List<Compra> compraList = mergedEntity.getCompraList();
        compraList.size();
        return compraList;
    }

    public boolean isJuegoidJuegoEmpty(Codigo entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Codigo> codigo = cq.from(Codigo.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(codigo, entity), cb.isNotNull(codigo.get(Codigo_.juegoidJuego)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Juego findJuegoidJuego(Codigo entity) {
        return this.getMergedEntity(entity).getJuegoidJuego();
    }
    
}
