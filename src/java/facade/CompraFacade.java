/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Compra;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.Compra_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import entity.Codigo;
import entity.Usuario;
import java.util.List;

/**
 *
 * @author Patricio
 */
@Stateless
public class CompraFacade extends AbstractFacade<Compra> {

    @PersistenceContext(unitName = "RocoStoreV2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompraFacade() {
        super(Compra.class);
    }

    public boolean isCodigoListEmpty(Compra entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Compra> compra = cq.from(Compra.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(compra, entity), cb.isNotEmpty(compra.get(Compra_.codigoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Codigo> findCodigoList(Compra entity) {
        Compra mergedEntity = this.getMergedEntity(entity);
        List<Codigo> codigoList = mergedEntity.getCodigoList();
        codigoList.size();
        return codigoList;
    }

    public boolean isUsuarioidUsuarioEmpty(Compra entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Compra> compra = cq.from(Compra.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(compra, entity), cb.isNotNull(compra.get(Compra_.usuarioidUsuario)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Usuario findUsuarioidUsuario(Compra entity) {
        return this.getMergedEntity(entity).getUsuarioidUsuario();
    }

    @Override
    public Compra findWithParents(Compra entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Compra> cq = cb.createQuery(Compra.class);
        Root<Compra> compra = cq.from(Compra.class);
        compra.fetch(Compra_.codigoList, JoinType.LEFT);
        cq.select(compra).where(cb.equal(compra, entity));
        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            return entity;
        }
    }
    
}
