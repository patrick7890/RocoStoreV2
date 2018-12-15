package controller;

import entity.Tarjeta;
import facade.TarjetaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "tarjetaController")
@ViewScoped
public class TarjetaController extends AbstractController<Tarjeta> {

    @Inject
    private UsuarioController usuarioController;

    public TarjetaController() {
        // Inform the Abstract parent controller of the concrete Tarjeta Entity
        super(Tarjeta.class);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getTarjetaPK().setUsuarioidUsuario(this.getSelected().getUsuario().getIdUsuario());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setTarjetaPK(new entity.TarjetaPK());
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        usuarioController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Usuario controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareUsuario(ActionEvent event) {
        Tarjeta selected = this.getSelected();
        if (selected != null && usuarioController.getSelected() == null) {
            usuarioController.setSelected(selected.getUsuario());
        }
    }

}
