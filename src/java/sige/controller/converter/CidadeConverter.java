package sige.controller.converter;



import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sige.modelo.entidade.Cidade;
import sige.modelo.sessionbean.CidadeSBean;



@FacesConverter("cidadeConverter")
public class CidadeConverter implements Converter {

        
    private CidadeSBean cidadeEJB;
    
   
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
               
        if (value != null && value.trim().length() > 0) {
            Long id = Long.parseLong(value);
            return cidadeEJB.pesquisar(id);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Cidade cidade = (Cidade)value;
            return cidade.getId().toString();            
        }        
        return null;
    }

  
    public void setCidadeSBean(CidadeSBean cidadeSBean) {
        this.cidadeEJB = cidadeSBean;
    }

    
    
}
