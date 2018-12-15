package controller;

import entity.Usuario;
import entity.Compra;
import entity.Ticket;
import entity.Tarjeta;
import java.util.List;
import facade.UsuarioFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "usuarioController")
@ViewScoped
public class UsuarioController extends AbstractController<Usuario> {

    @Inject
    private TipousuarioController tipoUsuarioidTipoUsuarioController;

    // Flags to indicate if child collections are empty
    private boolean isCompraListEmpty;
    private boolean isTicketListEmpty;
    private boolean isTarjetaListEmpty;

    public UsuarioController() {
        // Inform the Abstract parent controller of the concrete Usuario Entity
        super(Usuario.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        tipoUsuarioidTipoUsuarioController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsCompraListEmpty();
        this.setIsTicketListEmpty();
        this.setIsTarjetaListEmpty();
    }

    public boolean getIsCompraListEmpty() {
        return this.isCompraListEmpty;
    }

    private void setIsCompraListEmpty() {
        Usuario selected = this.getSelected();
        if (selected != null) {
            UsuarioFacade ejbFacade = (UsuarioFacade) this.getFacade();
            this.isCompraListEmpty = ejbFacade.isCompraListEmpty(selected);
        } else {
            this.isCompraListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Compra entities that are
     * retrieved from Usuario and returns the navigation outcome.
     *
     * @return navigation outcome for Compra page
     */
    public String navigateCompraList() {
        Usuario selected = this.getSelected();
        if (selected != null) {
            UsuarioFacade ejbFacade = (UsuarioFacade) this.getFacade();
            List<Compra> selectedCompraList = ejbFacade.findCompraList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Compra_items", selectedCompraList);
        }
        return "/app/compra/index";
    }

    public boolean getIsTicketListEmpty() {
        return this.isTicketListEmpty;
    }

    private void setIsTicketListEmpty() {
        Usuario selected = this.getSelected();
        if (selected != null) {
            UsuarioFacade ejbFacade = (UsuarioFacade) this.getFacade();
            this.isTicketListEmpty = ejbFacade.isTicketListEmpty(selected);
        } else {
            this.isTicketListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Ticket entities that are
     * retrieved from Usuario and returns the navigation outcome.
     *
     * @return navigation outcome for Ticket page
     */
    public String navigateTicketList() {
        Usuario selected = this.getSelected();
        if (selected != null) {
            UsuarioFacade ejbFacade = (UsuarioFacade) this.getFacade();
            List<Ticket> selectedTicketList = ejbFacade.findTicketList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Ticket_items", selectedTicketList);
        }
        return "/app/ticket/index";
    }

    /**
     * Sets the "selected" attribute of the Tipousuario controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTipoUsuarioidTipoUsuario(ActionEvent event) {
        Usuario selected = this.getSelected();
        if (selected != null && tipoUsuarioidTipoUsuarioController.getSelected() == null) {
            tipoUsuarioidTipoUsuarioController.setSelected(selected.getTipoUsuarioidTipoUsuario());
        }
    }

    public boolean getIsTarjetaListEmpty() {
        return this.isTarjetaListEmpty;
    }

    private void setIsTarjetaListEmpty() {
        Usuario selected = this.getSelected();
        if (selected != null) {
            UsuarioFacade ejbFacade = (UsuarioFacade) this.getFacade();
            this.isTarjetaListEmpty = ejbFacade.isTarjetaListEmpty(selected);
        } else {
            this.isTarjetaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Tarjeta entities that are
     * retrieved from Usuario and returns the navigation outcome.
     *
     * @return navigation outcome for Tarjeta page
     */
    public String navigateTarjetaList() {
        Usuario selected = this.getSelected();
        if (selected != null) {
            UsuarioFacade ejbFacade = (UsuarioFacade) this.getFacade();
            List<Tarjeta> selectedTarjetaList = ejbFacade.findTarjetaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Tarjeta_items", selectedTarjetaList);
        }
        return "/app/tarjeta/index";
    }

}
