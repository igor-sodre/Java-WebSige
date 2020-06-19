/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sige.modelo.sessionbean;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sige.modelo.entidade.Cidade;
import sige.modelo.entidade.Cliente;

/**
 *
 * @author wender
 */
@Stateless
public class ClienteSBean {

    @PersistenceContext(unitName = "sigePU")
    private EntityManager em;

    public void salvar(Cliente cliente) throws Exception {
        try {
            em.merge(cliente);
        } catch (Exception ex) {
            throw new Exception("Erro ao Salvar o cliente. " + ex.getMessage());
        }
    }

    public void excluir(Cliente cliente) throws Exception {
        try {
            em.remove(em.find(Cliente.class, cliente.getId()));
        } catch (Exception ex) {
            throw new Exception("Erro ao Excluir o cliente. " + ex.getMessage());
        }
    }

    public Cliente pesquisar(Long id) throws Exception {
        try {
            return em.find(Cliente.class, id);
        } catch (Exception ex) {
            throw new Exception("Erro ao pesquisar o cliente por Id. " + ex.getMessage());
        }
    }

    public List<Cliente> pesquisar(String nome) throws Exception {
        try {
            Query consulta = em.createNamedQuery("Cliente.findByNome");
            consulta.setParameter("nome", nome.toUpperCase() + "%");
            return consulta.getResultList();
        } catch (Exception ex) {
            throw new Exception("Erro ao pesquisar o cliente por nome. " + ex.getMessage());
        }
    }

    public List<Cliente> pesquisar(Cidade cidade, String nome) throws Exception {
        try {
            Query consulta = em.createNamedQuery("Cliente.findByCidadeNome");
            consulta.setParameter("cidade", cidade);
            consulta.setParameter("nome", nome.toUpperCase() + "%");
            return consulta.getResultList();
        } catch (Exception ex) {
            throw new Exception("Erro ao pesquisar o cliente por Cidade e Nome. " + ex.getMessage());
        }
    }

    public Cliente pesquisarPorCpfCnpj(String cpfCnpj) throws Exception {
        try {
            Query consulta = em.createNamedQuery("Cliente.findByCpfCnpj");
            consulta.setParameter("cpfCnpj", cpfCnpj);
            return (Cliente) consulta.getSingleResult();
        }catch(NoResultException ex){
            return null;
                
        } catch (Exception ex) {
            throw new Exception("Erro ao pesquisar o cliente por CPF/CNPJ. " + ex.getMessage());
        }
    }

}
