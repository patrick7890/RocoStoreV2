package controller;

import entity.Codigo;
import entity.Compra;
import java.util.List;
import facade.CodigoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "codigoController")
@ViewScoped
public class CodigoController extends AbstractController<Codigo> {

    @Inject
    private JuegoController juegoidJuegoController;

    // Flags to indicate if child collections are empty
    private boolean isCompraListEmpty;

    public CodigoController() {
        // Inform the Abstract parent controller of the concrete Codigo Entity
        super(Codigo.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        juegoidJuegoController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsCompraListEmpty();
    }

    public boolean getIsCompraListEmpty() {
        return this.isCompraListEmpty;
    }

    private void setIsCompraListEmpty() {
        Codigo selected = this.getSelected();
        if (selected != null) {
            CodigoFacade ejbFacade = (CodigoFacade) this.getFacade();
            this.isCompraListEmpty = ejbFacade.isCompraListEmpty(selected);
        } else {
            this.isCompraListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Compra entities that are
     * retrieved from Codigo and returns the navigation outcome.
     *
     * @return navigation outcome for Compra page
     */
    public String navigateCompraList() {
        Codigo selected = this.getSelected();
        if (selected != null) {
            CodigoFacade ejbFacade = (CodigoFacade) this.getFacade();
            List<Compra> selectedCompraList = ejbFacade.findCompraList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Compra_items", selectedCompraList);
        }
        return "/app/compra/index";
    }

    /**
     * Sets the "selected" attribute of the Juego controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareJuegoidJuego(ActionEvent event) {
        Codigo selected = this.getSelected();
        if (selected != null && juegoidJuegoController.getSelected() == null) {
            juegoidJuegoController.setSelected(selected.getJuegoidJuego());
        }
    }

}
