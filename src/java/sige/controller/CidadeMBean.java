/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sige.controller;

import sige.modelo.entidade.Cidade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import sige.modelo.sessionbean.CidadeSBean;

/**
 *
 * @author wender
 */
@Named(value = "cidadeMBean")
@SessionScoped
public class CidadeMBean implements Serializable {

    @EJB
    private CidadeSBean cidadeSBean;
    
    private Cidade cidade;
    
    private List<Cidade> listaCidade;
    
    private String valorPesquisar;
    
    public CidadeMBean() {
        
    }
    
    @PostConstruct
    public void init() {
        this.cidade = new Cidade();
        this.listaCidade = new ArrayList<>();
    }
    
    public String botaoNovo() {
        this.cidade = new Cidade();
        return "cadCidade?faces-redirect=true";
    }
    
    public String botaoSalvar() {
        cidadeSBean.salvar(cidade);
        cidade = new Cidade();
        return "consCidade?faces-redirect=true";
    }
    
    public void botaoPesquisar() {
        listaCidade = cidadeSBean.pesquisar(valorPesquisar);
    }
    
    public void botaoExcluir() {
        cidadeSBean.excluir(cidade);
    }
    
    public String botaoEditar() {
        return "cadCidade?faces-redirect=true";
    }
        
    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public List<Cidade> getListaCidade() {
        return listaCidade;
    }   

    public String getValorPesquisar() {
        return valorPesquisar;
    }

    public void setValorPesquisar(String valorPesquisar) {
        this.valorPesquisar = valorPesquisar;
    }
    
    
}
