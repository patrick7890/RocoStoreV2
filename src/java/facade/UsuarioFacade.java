/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.Usuario_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Compra;
import entity.Ticket;
import entity.Tipousuario;
import entity.Tarjeta;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Patricio
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "RocoStoreV2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public boolean isCompraListEmpty(Usuario entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Usuario> usuario = cq.from(Usuario.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(usuario, entity), cb.isNotEmpty(usuario.get(Usuario_.compraList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Compra> findCompraList(Usuario entity) {
        Usuario mergedEntity = this.getMergedEntity(entity);
        List<Compra> compraList = mergedEntity.getCompraList();
        compraList.size();
        return compraList;
    }

    public boolean isTicketListEmpty(Usuario entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Usuario> usuario = cq.from(Usuario.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(usuario, entity), cb.isNotEmpty(usuario.get(Usuario_.ticketList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Ticket> findTicketList(Usuario entity) {
        Usuario mergedEntity = this.getMergedEntity(entity);
        List<Ticket> ticketList = mergedEntity.getTicketList();
        ticketList.size();
        return ticketList;
    }

    public boolean isTipoUsuarioidTipoUsuarioEmpty(Usuario entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Usuario> usuario = cq.from(Usuario.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(usuario, entity), cb.isNotNull(usuario.get(Usuario_.tipoUsuarioidTipoUsuario)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Tipousuario findTipoUsuarioidTipoUsuario(Usuario entity) {
        return this.getMergedEntity(entity).getTipoUsuarioidTipoUsuario();
    }

    public boolean isTarjetaListEmpty(Usuario entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Usuario> usuario = cq.from(Usuario.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(usuario, entity), cb.isNotEmpty(usuario.get(Usuario_.tarjetaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Tarjeta> findTarjetaList(Usuario entity) {
        Usuario mergedEntity = this.getMergedEntity(entity);
        List<Tarjeta> tarjetaList = mergedEntity.getTarjetaList();
        tarjetaList.size();
        return tarjetaList;
    }

    public Usuario validate(String user, String pwd) {
        try {
            Query q = em.createNamedQuery("Usuario.login");
            q.setParameter("nombreUsuario", user);
            q.setParameter("passUsuario", pwd);
            return (Usuario) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
