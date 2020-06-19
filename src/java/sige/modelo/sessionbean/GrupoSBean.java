/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sige.modelo.sessionbean;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sige.modelo.entidade.Grupo;


@Stateless
public class GrupoSBean {

    @PersistenceContext(unitName = "sigePU")
    EntityManager em;

    public void salvar(Grupo grupo) {
        em.merge(grupo);
    }

    public void excluir(Grupo grupo) {
        em.remove(em.find(Grupo.class, grupo.getId()));
    }

    public Grupo pesquisar(Long id) {
        return em.find(Grupo.class, id);
    }

    public List<Grupo> pesquisar(String nome) {
        List<Grupo> listaGrupo;
        Query consulta = em.createNamedQuery("Grupo.findByNome");
        consulta.setParameter("nome", nome.toUpperCase() + "%");
        listaGrupo = consulta.getResultList();
        return listaGrupo;
    }


}
