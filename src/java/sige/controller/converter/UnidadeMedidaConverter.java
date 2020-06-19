package sige.controller.converter;



import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sige.modelo.entidade.UnidadedeMedida;
import sige.modelo.sessionbean.UnidadeMedidaSBean;



@FacesConverter("cidadeConverter")
public class UnidadeMedidaConverter implements Converter {

        
    private UnidadeMedidaSBean unidadeMedidaEJB;
    
   
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
               
        if (value != null && value.trim().length() > 0) {
            Long id = Long.parseLong(value);
            return unidadeMedidaEJB.pesquisar(id);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            UnidadedeMedida unidadedeMedida = (UnidadedeMedida)value;
            return unidadedeMedida.getId().toString();            
        }        
        return null;
    }

  
    public void setUnidadeMedidaSBean(UnidadeMedidaSBean unidadeMedidaSBean) {
        this.unidadeMedidaEJB = unidadeMedidaSBean;
    }

    
    
}
