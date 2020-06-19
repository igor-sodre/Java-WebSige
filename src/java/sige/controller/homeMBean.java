/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sige.controller;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sige.controller.uteis.UteisJsf;
import sige.modelo.entidade.Usuario;

/**
 *
 * @author Alan
 */
@Named(value = "homeMBean")
@RequestScoped
public class homeMBean {
 
    private Usuario usuarioLogado;
       

    public homeMBean() {
        
    }
    
    @PostConstruct
    public void init() {
         usuarioLogado = (Usuario)UteisJsf.getObjectSession("usuarioLogado");
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
    
    
}
