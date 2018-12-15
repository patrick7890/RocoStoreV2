/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Tipousuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.Tipousuario_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Usuario;
import java.util.List;

/**
 *
 * @author Patricio
 */
@Stateless
public class TipousuarioFacade extends AbstractFacade<Tipousuario> {

    @PersistenceContext(unitName = "RocoStoreV2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipousuarioFacade() {
        super(Tipousuario.class);
    }

    public boolean isUsuarioListEmpty(Tipousuario entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Tipousuario> tipousuario = cq.from(Tipousuario.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipousuario, entity), cb.isNotEmpty(tipousuario.get(Tipousuario_.usuarioList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Usuario> findUsuarioList(Tipousuario entity) {
        Tipousuario mergedEntity = this.getMergedEntity(entity);
        List<Usuario> usuarioList = mergedEntity.getUsuarioList();
        usuarioList.size();
        return usuarioList;
    }
    
}
