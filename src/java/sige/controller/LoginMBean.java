/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sige.controller;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sige.controller.uteis.UteisJsf;
import sige.modelo.entidade.Usuario;
import sige.modelo.sessionbean.UsuarioSBean;


@Named(value = "loginMBean")
@RequestScoped
public class LoginMBean {
    private String userName = "";
    private String senha = "";
    private Usuario usuario = new Usuario();
    @EJB
    private UsuarioSBean usuarioSBean;
    
    public LoginMBean() {

    }
    
    public String logar() {
        try {
            this.usuario = usuarioSBean.logar(this.userName, this.senha);
            if (this.usuario != null) {
                UteisJsf.setObjectSession("usuarioLogado", this.usuario);
                return "home";
            }
            UteisJsf.addMensagemInfo("Usuario ou senha invalidos.", "");
        } catch (Exception ex) {
            UteisJsf.addMensagemInfo(ex.getMessage(), "");
        }
        this.usuario = new Usuario();
        this.userName = "";
        this.senha = "";
        return null;
    }
    
    public String sair() {
        UteisJsf.removeObjectSession("usuarioLogado");        
        return "login";
    }
    

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
