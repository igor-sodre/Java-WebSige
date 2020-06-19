/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sige.modelo.sessionbean;

import sige.modelo.entidade.Cidade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author wender
 */
@Stateless
public class CidadeSBean {

    @PersistenceContext(unitName = "sigePU")
    EntityManager em;
    
    
    public void salvar(Cidade cidade) {
         em.merge(cidade);
    }
    
    public void excluir(Cidade cidade) {
        em.remove(em.find(Cidade.class, cidade.getId()));
    }
    
    public Cidade pesquisar(Long id) {
        return em.find(Cidade.class, id);
    }
    
    public List<Cidade> pesquisar(String nome) {
        List<Cidade> listaCidades;
        Query consulta = em.createNamedQuery("Cidade.findByNome");
        consulta.setParameter("nome", nome.toUpperCase() + "%");
        listaCidades = consulta.getResultList();
        return listaCidades;
    }
}
