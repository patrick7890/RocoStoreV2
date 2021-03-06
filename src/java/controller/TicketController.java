package controller;

import entity.Ticket;
import facade.TicketFacade;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "ticketController")
@ViewScoped
public class TicketController extends AbstractController<Ticket> {

    @EJB
    private TicketFacade ticketFacade;
    private Collection<Ticket> ticketList;

    @Inject
    private TipoticketController tipoTicketidTipoTicketController;
    @Inject
    private UsuarioController usuarioidUsuarioController;

    public TicketController() {
        // Inform the Abstract parent controller of the concrete Ticket Entity
        super(Ticket.class);
    }

    public Collection<Ticket> getTicketList() {
        ticketList = ticketFacade.findTickets(5);
        return ticketList;
    }

    public void setTicketList(Collection<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        tipoTicketidTipoTicketController.setSelected(null);
        usuarioidUsuarioController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Tipoticket controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTipoTicketidTipoTicket(ActionEvent event) {
        Ticket selected = this.getSelected();
        if (selected != null && tipoTicketidTipoTicketController.getSelected() == null) {
            tipoTicketidTipoTicketController.setSelected(selected.getTipoTicketidTipoTicket());
        }
    }

    /**
     * Sets the "selected" attribute of the Usuario controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareUsuarioidUsuario(ActionEvent event) {
        Ticket selected = this.getSelected();
        if (selected != null && usuarioidUsuarioController.getSelected() == null) {
            usuarioidUsuarioController.setSelected(selected.getUsuarioidUsuario());
        }
    }

}
