/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sige.controller.converter;



import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sige.modelo.entidade.Fornecedor;
import sige.modelo.sessionbean.FornecedorSBean;



@FacesConverter("fornecedorConverter")
public class FornecedorConverter implements Converter {

        
    private FornecedorSBean fornecedorEJB;
    
   
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
             try{  
        if (value != null && value.trim().length() > 0) {
            Long id = Long.parseLong(value);
            return fornecedorEJB.pesquisar(id);
        }
        }catch (Exception ex){
                 
             }
             return null;
    }


    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Fornecedor fornecedor = (Fornecedor)value;
            return fornecedor.getId().toString();            
        }        
        return null;
    }

  
    public void setCidadeSBean(FornecedorSBean fornecedorSBean) {
        this.fornecedorEJB = fornecedorSBean;
    }

    public void setFornecedorEJB(FornecedorSBean fornecedorEJB) {
        this.fornecedorEJB = fornecedorEJB;
    }

    
    
}
