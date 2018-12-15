package converter;

import entity.Tarjeta;
import facade.TarjetaFacade;
import controller.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.convert.FacesConverter;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@FacesConverter(value = "tarjetaConverter")
public class TarjetaConverter implements Converter {

    private TarjetaFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.getEjbFacade().find(getKey(value));
    }

    entity.TarjetaPK getKey(String value) {
        entity.TarjetaPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new entity.TarjetaPK();
        key.setIdTarjeta(Integer.parseInt(values[0]));
        key.setUsuarioidUsuario(Integer.parseInt(values[1]));
        return key;
    }

    String getStringKey(entity.TarjetaPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getIdTarjeta());
        sb.append(SEPARATOR);
        sb.append(value.getUsuarioidUsuario());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof Tarjeta) {
            Tarjeta o = (Tarjeta) object;
            return getStringKey(o.getTarjetaPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Tarjeta.class.getName()});
            return null;
        }
    }

    private TarjetaFacade getEjbFacade() {
        this.ejbFacade = CDI.current().select(TarjetaFacade.class).get();
        return this.ejbFacade;
    }
}
