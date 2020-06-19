package sige.controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import sige.controller.converter.CidadeConverter;
import sige.controller.uteis.UteisJsf;
import sige.modelo.entidade.Cidade;
import sige.modelo.entidade.Fornecedor;
import sige.modelo.sessionbean.CidadeSBean;

import sige.modelo.sessionbean.FornecedorSBean;

/**
 *
 * @author AlanCosta
 */
@Named(value = "fornecedorMBean")
@SessionScoped
public class FornecedorMBean implements Serializable {

    @EJB
    private FornecedorSBean fornecedorSBean;
    @EJB
    private CidadeSBean cidadeSBean;
    
    
    private Fornecedor fornecedor;

    public List<Cidade> getLisaCidade() {
        return lisaCidade;
    }

    public void setLisaCidade(List<Cidade> lisaCidade) {
        this.lisaCidade = lisaCidade;
    }
    
    private List<Fornecedor> listaFornecedor;
    
    private List<Cidade>lisaCidade;
    
    private CidadeConverter cidadeConverter;

    public CidadeConverter getCidadeConverter() {
        return cidadeConverter;
    }

    public void setCidadeConverter(CidadeConverter cidadeConverter) {
        this.cidadeConverter = cidadeConverter;
    }
    
    
    private String valorPesquisar;
    
    public FornecedorMBean() {
        
    }
    
    @PostConstruct
    public void init() {
        this.fornecedor = new Fornecedor();
        this.listaFornecedor = new ArrayList<>();
    }
    
    
    private void carregarSelects(){
        this.cidadeConverter = new  CidadeConverter();
        this.cidadeConverter.setCidadeSBean(cidadeSBean);
        this.lisaCidade=this.cidadeSBean.pesquisar("");
    }
    
    public String botaoNovo() {
        this.fornecedor = new Fornecedor();
        this.carregarSelects();
        return "cadFornecedor?faces-redirect=true";
    }
    
    public void botaoSalvar(){
        try{
       fornecedorSBean.salvar(fornecedor);
        fornecedor = new Fornecedor();
//        return "consFornecedor?faces-redirect=true";
    }catch(Exception ex){
            UteisJsf.addMensagemErro("Erro ao Salvar fornecedor");
    }
       
    }
    
    public void botaoPesquisar() {
        
        try {
            listaFornecedor = fornecedorSBean.pesquisar(valorPesquisar);
        } catch (Exception ex) {
            UteisJsf.addMensagemErro("Erro ao Pesquisar");
        }
    }
    public String botaoPesquisarNav(){
        return "consFornecedor?faces-redirect=true";
    }
    
    public void botaoExcluir() {
        try {
            fornecedorSBean.excluir(fornecedor);
            listaFornecedor.remove(fornecedor);
        } catch (Exception ex) {
            UteisJsf.addMensagemErro("Erro ao Excluir");
        }
    }
    
    public String botaoEditar() {
        this.carregarSelects();
        return "cadFornecedor?faces-redirect=true";
    }
        
    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<Fornecedor> getListaFornecedor() {
        return listaFornecedor;
    }   

    public String getValorPesquisar() {
        return valorPesquisar;
    }

    public void setValorPesquisar(String valorPesquisar) {
        this.valorPesquisar = valorPesquisar;
    }

  
    
}
