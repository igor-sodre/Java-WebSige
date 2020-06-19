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
import sige.modelo.entidade.UnidadedeMedida;

/**
 *
 * @author wender
 */
@Stateless
public class UnidadeMedidaSBean {

    @PersistenceContext(unitName = "sigePU")
    EntityManager em;

    public void salvar(UnidadedeMedida unidadedeMedida) {
        em.merge(unidadedeMedida);
    }

    public void excluir(UnidadedeMedida unidadedeMedida) {
        em.remove(em.find(UnidadedeMedida.class, unidadedeMedida.getId()));
    }

    public UnidadedeMedida pesquisar(Long id) {
        return em.find(UnidadedeMedida.class, id);
    }

    public List<UnidadedeMedida> pesquisar(String nome) {
        List<UnidadedeMedida> listaUnidadedeMedida;
        Query consulta = em.createNamedQuery("UnidadedeMedida.findByNome");
        consulta.setParameter("nome", nome.toUpperCase() + "%");
        listaUnidadedeMedida = consulta.getResultList();
        return listaUnidadedeMedida;
    }

    public UnidadedeMedida pesquisarPorSimbolo(String simbolo) throws Exception {
        try {
            Query consulta = em.createNamedQuery("UnidadeMedida.findBySimbolo");
            consulta.setParameter("simbolo", simbolo);
            return (UnidadedeMedida) consulta.getSingleResult();
        } catch (Exception ex) {
            throw new Exception("Erro ao Pesquisar por Simbolo");
        }

    }
}
