package controller;

import entity.Compra;
import entity.Codigo;
import java.util.List;
import facade.CompraFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "compraController")
@ViewScoped
public class CompraController extends AbstractController<Compra> {

    @Inject
    private UsuarioController usuarioidUsuarioController;

    // Flags to indicate if child collections are empty
    private boolean isCodigoListEmpty;

    public CompraController() {
        // Inform the Abstract parent controller of the concrete Compra Entity
        super(Compra.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        usuarioidUsuarioController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsCodigoListEmpty();
    }

    public boolean getIsCodigoListEmpty() {
        return this.isCodigoListEmpty;
    }

    private void setIsCodigoListEmpty() {
        Compra selected = this.getSelected();
        if (selected != null) {
            CompraFacade ejbFacade = (CompraFacade) this.getFacade();
            this.isCodigoListEmpty = (selected.getCodigoList() == null || selected.getCodigoList().isEmpty());
        } else {
            this.isCodigoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Codigo entities that are
     * retrieved from Compra and returns the navigation outcome.
     *
     * @return navigation outcome for Codigo page
     */
    public String navigateCodigoList() {
        Compra selected = this.getSelected();
        if (selected != null) {
            CompraFacade ejbFacade = (CompraFacade) this.getFacade();
            // Note: CodigoList has already been read as is initialized
            List<Codigo> selectedCodigoList = selected.getCodigoList();
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Codigo_items", selectedCodigoList);
        }
        return "/app/codigo/index";
    }

    /**
     * Sets the "selected" attribute of the Usuario controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareUsuarioidUsuario(ActionEvent event) {
        Compra selected = this.getSelected();
        if (selected != null && usuarioidUsuarioController.getSelected() == null) {
            usuarioidUsuarioController.setSelected(selected.getUsuarioidUsuario());
        }
    }

}
