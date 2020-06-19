package sige.controller.converter;



import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sige.modelo.entidade.Grupo;
import sige.modelo.sessionbean.GrupoSBean;



@FacesConverter("grupoConverter")
public class GrupoConverter implements Converter {

        
    private GrupoSBean grupoEJB;
    
   
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
               
        if (value != null && value.trim().length() > 0) {
            Long id = Long.parseLong(value);
            return grupoEJB.pesquisar(id);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Grupo grupo = (Grupo)value;
            return grupo.getId().toString();            
        }        
        return null;
    }

  
    public void setGrupoEJB(GrupoSBean grupoSBean) {
        this.grupoEJB = grupoSBean;
    }

  

    
    
}
