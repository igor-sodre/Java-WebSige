
package sige.controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import sige.modelo.entidade.UnidadedeMedida;
import sige.modelo.sessionbean.UnidadeMedidaSBean;


@Named(value = "unidadeMedidaMBean")
@SessionScoped
public class UnidadeMedidaMBean implements Serializable {

    @EJB
    private UnidadeMedidaSBean unidadeMedidaSBean;
   
    private UnidadedeMedida unidadedeMedida;
    
    
    private List<UnidadedeMedida> listaUnidadedeMedida;
    
    
    private String valorPesquisar;
    
   
    
    
    
    
    
    public UnidadeMedidaMBean() {
        
        
        
    }
    @PostConstruct
    public void init(){
        this.listaUnidadedeMedida = new ArrayList<>();
    }
    
        
    public String botaoNovo() {
      this.unidadedeMedida = new UnidadedeMedida();
      return"cadUnidade?faces-redirect=true";
    }
    
    public String  botaoSalvar() {
        try {
            unidadeMedidaSBean.salvar(this.unidadedeMedida);
            FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                        "Sucesso", "Unidade De Medida foi salvo com sucesso"));
            
            return "consUnidadedeMedida";
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                        "Erro Salvar", "Error ao salvar Unidade de Medida. " + ex.getMessage()));
        }
        return null;
    }    
    
    public String botaoEditar() {
        
        return "cadUnidadedeMedida?faces-redirect=true";
    }
    
    public void botaoExcluir() {
        try {
            unidadeMedidaSBean.excluir(unidadedeMedida);
            
            this.listaUnidadedeMedida.remove(unidadedeMedida);
        } catch (Exception ex) {
            
            FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                        "Erro Excluir", "Error ao excluir cliente. " + ex.getMessage());
            
            FacesContext.getCurrentInstance().addMessage(null, fmsg);
        }
    }
    
    
    public void botaoPesquisar() {
        try {           
         
            this.listaUnidadedeMedida = unidadeMedidaSBean.pesquisar(valorPesquisar);
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Pesquisar", ex.getMessage()));           
        }
    }

    public UnidadedeMedida getUnidadedeMedida() {
        return unidadedeMedida;
    }

    public void setUnidadedeMedida(UnidadedeMedida unidadedeMedida) {
        this.unidadedeMedida = unidadedeMedida;
    }

    public List<UnidadedeMedida> getListaUnidadedeMedida() {
        return listaUnidadedeMedida;
    }

    public void setListaUnidadedeMedida(List<UnidadedeMedida> listaUnidadedeMedida) {
        this.listaUnidadedeMedida = listaUnidadedeMedida;
    }

    public String getValorPesquisar() {
        return valorPesquisar;
    }

    public void setValorPesquisar(String valorPesquisar) {
        this.valorPesquisar = valorPesquisar;
    }
    
   
    
    
   }
