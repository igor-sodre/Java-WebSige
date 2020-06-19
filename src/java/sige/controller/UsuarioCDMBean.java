/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sige.controller;

import sige.modelo.entidade.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import sige.modelo.sessionbean.UsuarioCDSBean;

/**
 *
 * @author wender
 */
@Named(value = "usuarioCDMBean")
@SessionScoped
public class UsuarioCDMBean implements Serializable {

    @EJB
    private UsuarioCDSBean usuarioCDSBean;
    
    private Usuario usuario;
    
  
    
    
    
    public UsuarioCDMBean() {
        
    }
    
    
    
    public String botaoNovo() {
        this.usuario = new Usuario();
        return "cadUsuario_CD?faces-redirect=true";
    }
    
    public String botaoSalvar() {
        usuarioCDSBean.salvar(usuario);
        usuario = new Usuario();
        return "cadUsuario_CD?faces-redirect=true";
    }
    
  
    

    

        
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

   

   
    
}
