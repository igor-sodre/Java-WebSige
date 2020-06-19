/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sige.modelo.sessionbean;

import sige.modelo.entidade.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class UsuarioSBean {

    @PersistenceContext(unitName = "sigePU")
    EntityManager em;
    
    
        public void salvar(Usuario usuario) {
         em.merge(usuario);
    }
    
    public void excluir(Usuario usuario) {
        em.remove(em.find(Usuario.class, usuario.getId()));        
    }
    
    public Usuario pesquisar(Long id) {
        return em.find(Usuario.class, id);
    }
    
    public List<Usuario> pesquisar(String nome) {
        List<Usuario> listaUsuarios;
        Query consulta = em.createNamedQuery("Usuario.findByNome");
        consulta.setParameter("nome", nome.toUpperCase() + "%");
        listaUsuarios = consulta.getResultList();
        return listaUsuarios;
    }
    public Usuario logar(String userName, String senha) throws Exception {
        try {
            Query consulta = em.createNamedQuery("Usuario.findByUserNameSenha");
            consulta.setParameter("userName", userName);
            consulta.setParameter("senha", senha);
            return (Usuario) consulta.getSingleResult();
        } catch (Exception ex) {
            throw new Exception("Usuario ou Senha invalidos.");
        }
    }
}