
package sige.controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import sige.controller.converter.CidadeConverter;
import sige.modelo.entidade.Cidade;
import sige.modelo.entidade.Cliente;
import sige.modelo.sessionbean.CidadeSBean;
import sige.modelo.sessionbean.ClienteSBean;


@Named(value = "clienteMBean")
@SessionScoped
public class ClienteMBean implements Serializable {

    @EJB
    private ClienteSBean clienteSBean;
    @EJB
    private CidadeSBean cidadeSBean;
    
    private Cliente cliente;
    private List<Cidade> listaCidade;
    private List<Cliente> listaCliente;
    
    private String valorPesquisar;
    
    private CidadeConverter cidadeConverter;
    
    
    
    
    
    public ClienteMBean() {
        
    }
    
        
    public String botaoNovo() {
        carregarListaCidade();
        cliente = new Cliente();
        return "cadCliente";
    }
    
    public String  botaoSalvar() {
        try {
            clienteSBean.salvar(cliente);
            FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                        "Sucesso", "Cliente salvo com sucesso"));
            return "consCliente";
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                        "Erro Salvar", "Error ao salvar cliente. " + ex.getMessage()));
        }
        return null;
    }    
    
    public String botaoEditar() {
        carregarListaCidade();
        return "cadCliente";
    }
    
    public void botaoExcluir() {
        try {
            clienteSBean.excluir(cliente);
            /*se excluir do banco já removo o objeto da lista que ele vai atualizar o dataTable*/
            this.listaCliente.remove(cliente);
        } catch (Exception ex) {
            
            FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                        "Erro Excluir", "Error ao excluir cliente. " + ex.getMessage());
            /*a mensagem não foi mostrada na tela por que não tinha passado para FacesContext.getCurrentiInstance()
            */
            FacesContext.getCurrentInstance().addMessage(null, fmsg);
        }
    }
    
    
    public void botaoPesquisar() {
        try {           
         
            this.listaCliente = clienteSBean.pesquisar(valorPesquisar);
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Pesquisar", ex.getMessage()));           
        }
    }
    
    private void carregarListaCidade() {
       this.listaCidade = cidadeSBean.pesquisar("");
       this.cidadeConverter = new CidadeConverter();
       this.cidadeConverter.setCidadeSBean(cidadeSBean);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cidade> getListaCidade() {
        return listaCidade;
    }

    public void setListaCidade(List<Cidade> listaCidade) {
        this.listaCidade = listaCidade;
    }

    public List<Cliente> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public String getValorPesquisar() {
        return valorPesquisar;
    }

    public void setValorPesquisar(String valorPesquisar) {
        this.valorPesquisar = valorPesquisar;
    }

    public CidadeConverter getCidadeConverter() {
        return cidadeConverter;
    }

    public void setCidadeConverter(CidadeConverter cidadeConverter) {
        this.cidadeConverter = cidadeConverter;
    }
    
    
    
    
}
