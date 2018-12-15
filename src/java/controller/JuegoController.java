package controller;

import entity.Juego;
import entity.Genero;
import entity.Plataforma;
import entity.Codigo;
import java.util.List;
import facade.JuegoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "juegoController")
@ViewScoped
public class JuegoController extends AbstractController<Juego> {

    // Flags to indicate if child collections are empty
    private boolean isGeneroListEmpty;
    private boolean isPlataformaListEmpty;
    private boolean isCodigoListEmpty;

    public JuegoController() {
        // Inform the Abstract parent controller of the concrete Juego Entity
        super(Juego.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsGeneroListEmpty();
        this.setIsPlataformaListEmpty();
        this.setIsCodigoListEmpty();
    }

    public boolean getIsGeneroListEmpty() {
        return this.isGeneroListEmpty;
    }

    private void setIsGeneroListEmpty() {
        Juego selected = this.getSelected();
        if (selected != null) {
            JuegoFacade ejbFacade = (JuegoFacade) this.getFacade();
            this.isGeneroListEmpty = (selected.getGeneroList() == null || selected.getGeneroList().isEmpty());
        } else {
            this.isGeneroListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Genero entities that are
     * retrieved from Juego and returns the navigation outcome.
     *
     * @return navigation outcome for Genero page
     */
    public String navigateGeneroList() {
        Juego selected = this.getSelected();
        if (selected != null) {
            JuegoFacade ejbFacade = (JuegoFacade) this.getFacade();
            // Note: GeneroList has already been read as is initialized
            List<Genero> selectedGeneroList = selected.getGeneroList();
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Genero_items", selectedGeneroList);
        }
        return "/app/genero/index";
    }

    public boolean getIsPlataformaListEmpty() {
        return this.isPlataformaListEmpty;
    }

    private void setIsPlataformaListEmpty() {
        Juego selected = this.getSelected();
        if (selected != null) {
            JuegoFacade ejbFacade = (JuegoFacade) this.getFacade();
            this.isPlataformaListEmpty = (selected.getPlataformaList() == null || selected.getPlataformaList().isEmpty());
        } else {
            this.isPlataformaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Plataforma entities that
     * are retrieved from Juego and returns the navigation outcome.
     *
     * @return navigation outcome for Plataforma page
     */
    public String navigatePlataformaList() {
        Juego selected = this.getSelected();
        if (selected != null) {
            JuegoFacade ejbFacade = (JuegoFacade) this.getFacade();
            // Note: PlataformaList has already been read as is initialized
            List<Plataforma> selectedPlataformaList = selected.getPlataformaList();
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Plataforma_items", selectedPlataformaList);
        }
        return "/app/plataforma/index";
    }

    public boolean getIsCodigoListEmpty() {
        return this.isCodigoListEmpty;
    }

    private void setIsCodigoListEmpty() {
        Juego selected = this.getSelected();
        if (selected != null) {
            JuegoFacade ejbFacade = (JuegoFacade) this.getFacade();
            this.isCodigoListEmpty = ejbFacade.isCodigoListEmpty(selected);
        } else {
            this.isCodigoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Codigo entities that are
     * retrieved from Juego and returns the navigation outcome.
     *
     * @return navigation outcome for Codigo page
     */
    public String navigateCodigoList() {
        Juego selected = this.getSelected();
        if (selected != null) {
            JuegoFacade ejbFacade = (JuegoFacade) this.getFacade();
            List<Codigo> selectedCodigoList = ejbFacade.findCodigoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Codigo_items", selectedCodigoList);
        }
        return "/app/codigo/index";
    }

}
