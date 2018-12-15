package controller;

import entity.Tipousuario;
import entity.Usuario;
import java.util.List;
import facade.TipousuarioFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "tipousuarioController")
@ViewScoped
public class TipousuarioController extends AbstractController<Tipousuario> {

    // Flags to indicate if child collections are empty
    private boolean isUsuarioListEmpty;

    public TipousuarioController() {
        // Inform the Abstract parent controller of the concrete Tipousuario Entity
        super(Tipousuario.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsUsuarioListEmpty();
    }

    public boolean getIsUsuarioListEmpty() {
        return this.isUsuarioListEmpty;
    }

    private void setIsUsuarioListEmpty() {
        Tipousuario selected = this.getSelected();
        if (selected != null) {
            TipousuarioFacade ejbFacade = (TipousuarioFacade) this.getFacade();
            this.isUsuarioListEmpty = ejbFacade.isUsuarioListEmpty(selected);
        } else {
            this.isUsuarioListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Usuario entities that are
     * retrieved from Tipousuario and returns the navigation outcome.
     *
     * @return navigation outcome for Usuario page
     */
    public String navigateUsuarioList() {
        Tipousuario selected = this.getSelected();
        if (selected != null) {
            TipousuarioFacade ejbFacade = (TipousuarioFacade) this.getFacade();
            List<Usuario> selectedUsuarioList = ejbFacade.findUsuarioList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Usuario_items", selectedUsuarioList);
        }
        return "/app/usuario/index";
    }

}
