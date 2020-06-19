
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
import sige.modelo.entidade.Grupo;
import sige.modelo.sessionbean.GrupoSBean;


@Named(value = "grupoMBean")
@SessionScoped
public class GrupoMBean implements Serializable {

    @EJB
    private GrupoSBean grupoSBean;
   
    private Grupo grupo;
    
    
    private List<Grupo> listaGrupo;
    
    
    private String valorPesquisar;
    
   
    
    
    
    
    
    public GrupoMBean() {
        
        
        
    }
    @PostConstruct
    public void init(){
        this.listaGrupo = new ArrayList<>();
    }
    
        
    public String botaoNovo() {
      this.grupo = new Grupo();
      return"cadGrupo?faces-redirect=true";
    }
    
    public String  botaoSalvar() {
        try {
            grupoSBean.salvar(this.grupo);
            FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                        "Sucesso", "Grupo foi salvo com sucesso"));
            
            return "consGrupo";
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                        "Erro Salvar", "Error ao salvar Grupo. " + ex.getMessage()));
        }
        return null;
    }    
    
    public String botaoEditar() {
        
        return "cadGrupo?faces-redirect=true";
    }
    
    public void botaoExcluir() {
        try {
            grupoSBean.excluir(grupo);
            
            this.listaGrupo.remove(grupo);
        } catch (Exception ex) {
            
            FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                        "Erro Excluir", "Error ao excluir cliente. " + ex.getMessage());
            
            FacesContext.getCurrentInstance().addMessage(null, fmsg);
        }
    }
    
    
    public void botaoPesquisar() {
        try {           
         
            this.listaGrupo = grupoSBean.pesquisar(valorPesquisar);
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Pesquisar", ex.getMessage()));           
        }
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public List<Grupo> getListaGrupo() {
        return listaGrupo;
    }

    public void setListaGrupo(List<Grupo> listaGrupo) {
        this.listaGrupo = listaGrupo;
    }

    public String getValorPesquisar() {
        return valorPesquisar;
    }

    public void setValorPesquisar(String valorPesquisar) {
        this.valorPesquisar = valorPesquisar;
    }

   
   
    
    
   }
