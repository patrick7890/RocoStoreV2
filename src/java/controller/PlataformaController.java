package controller;

import entity.Plataforma;
import entity.Juego;
import java.util.List;
import facade.PlataformaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "plataformaController")
@ViewScoped
public class PlataformaController extends AbstractController<Plataforma> {

    // Flags to indicate if child collections are empty
    private boolean isJuegoListEmpty;

    public PlataformaController() {
        // Inform the Abstract parent controller of the concrete Plataforma Entity
        super(Plataforma.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsJuegoListEmpty();
    }

    public boolean getIsJuegoListEmpty() {
        return this.isJuegoListEmpty;
    }

    private void setIsJuegoListEmpty() {
        Plataforma selected = this.getSelected();
        if (selected != null) {
            PlataformaFacade ejbFacade = (PlataformaFacade) this.getFacade();
            this.isJuegoListEmpty = ejbFacade.isJuegoListEmpty(selected);
        } else {
            this.isJuegoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Juego entities that are
     * retrieved from Plataforma and returns the navigation outcome.
     *
     * @return navigation outcome for Juego page
     */
    public String navigateJuegoList() {
        Plataforma selected = this.getSelected();
        if (selected != null) {
            PlataformaFacade ejbFacade = (PlataformaFacade) this.getFacade();
            List<Juego> selectedJuegoList = ejbFacade.findJuegoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Juego_items", selectedJuegoList);
        }
        return "/app/juego/index";
    }

}
