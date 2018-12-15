/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Juego;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.Juego_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import entity.Genero;
import entity.Plataforma;
import entity.Codigo;
import java.util.List;

/**
 *
 * @author Patricio
 */
@Stateless
public class JuegoFacade extends AbstractFacade<Juego> {

    @PersistenceContext(unitName = "RocoStoreV2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public JuegoFacade() {
        super(Juego.class);
    }

    public boolean isGeneroListEmpty(Juego entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Juego> juego = cq.from(Juego.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(juego, entity), cb.isNotEmpty(juego.get(Juego_.generoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Genero> findGeneroList(Juego entity) {
        Juego mergedEntity = this.getMergedEntity(entity);
        List<Genero> generoList = mergedEntity.getGeneroList();
        generoList.size();
        return generoList;
    }

    public boolean isPlataformaListEmpty(Juego entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Juego> juego = cq.from(Juego.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(juego, entity), cb.isNotEmpty(juego.get(Juego_.plataformaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Plataforma> findPlataformaList(Juego entity) {
        Juego mergedEntity = this.getMergedEntity(entity);
        List<Plataforma> plataformaList = mergedEntity.getPlataformaList();
        plataformaList.size();
        return plataformaList;
    }

    public boolean isCodigoListEmpty(Juego entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Juego> juego = cq.from(Juego.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(juego, entity), cb.isNotEmpty(juego.get(Juego_.codigoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Codigo> findCodigoList(Juego entity) {
        Juego mergedEntity = this.getMergedEntity(entity);
        List<Codigo> codigoList = mergedEntity.getCodigoList();
        codigoList.size();
        return codigoList;
    }

    @Override
    public Juego findWithParents(Juego entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Juego> cq = cb.createQuery(Juego.class);
        Root<Juego> juego = cq.from(Juego.class);
        juego.fetch(Juego_.generoList, JoinType.LEFT);
        juego.fetch(Juego_.plataformaList, JoinType.LEFT);
        cq.select(juego).where(cb.equal(juego, entity));
        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            return entity;
        }
    }
    
}
