package controller;

import entity.Genero;
import entity.Juego;
import java.util.List;
import facade.GeneroFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "generoController")
@ViewScoped
public class GeneroController extends AbstractController<Genero> {

    // Flags to indicate if child collections are empty
    private boolean isJuegoListEmpty;

    public GeneroController() {
        // Inform the Abstract parent controller of the concrete Genero Entity
        super(Genero.class);
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
        Genero selected = this.getSelected();
        if (selected != null) {
            GeneroFacade ejbFacade = (GeneroFacade) this.getFacade();
            this.isJuegoListEmpty = ejbFacade.isJuegoListEmpty(selected);
        } else {
            this.isJuegoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Juego entities that are
     * retrieved from Genero and returns the navigation outcome.
     *
     * @return navigation outcome for Juego page
     */
    public String navigateJuegoList() {
        Genero selected = this.getSelected();
        if (selected != null) {
            GeneroFacade ejbFacade = (GeneroFacade) this.getFacade();
            List<Juego> selectedJuegoList = ejbFacade.findJuegoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Juego_items", selectedJuegoList);
        }
        return "/app/juego/index";
    }

}
