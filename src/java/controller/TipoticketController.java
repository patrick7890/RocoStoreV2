package controller;

import entity.Tipoticket;
import entity.Ticket;
import java.util.List;
import facade.TipoticketFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "tipoticketController")
@ViewScoped
public class TipoticketController extends AbstractController<Tipoticket> {

    // Flags to indicate if child collections are empty
    private boolean isTicketListEmpty;

    public TipoticketController() {
        // Inform the Abstract parent controller of the concrete Tipoticket Entity
        super(Tipoticket.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsTicketListEmpty();
    }

    public boolean getIsTicketListEmpty() {
        return this.isTicketListEmpty;
    }

    private void setIsTicketListEmpty() {
        Tipoticket selected = this.getSelected();
        if (selected != null) {
            TipoticketFacade ejbFacade = (TipoticketFacade) this.getFacade();
            this.isTicketListEmpty = ejbFacade.isTicketListEmpty(selected);
        } else {
            this.isTicketListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Ticket entities that are
     * retrieved from Tipoticket and returns the navigation outcome.
     *
     * @return navigation outcome for Ticket page
     */
    public String navigateTicketList() {
        Tipoticket selected = this.getSelected();
        if (selected != null) {
            TipoticketFacade ejbFacade = (TipoticketFacade) this.getFacade();
            List<Ticket> selectedTicketList = ejbFacade.findTicketList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Ticket_items", selectedTicketList);
        }
        return "/app/ticket/index";
    }

}
