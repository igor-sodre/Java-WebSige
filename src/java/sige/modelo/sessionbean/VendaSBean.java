/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sige.modelo.sessionbean;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import sige.modelo.entidade.Cliente;
import sige.modelo.entidade.Venda;

@Stateless
public class VendaSBean {

    @PersistenceContext(unitName = "sigePU")
    EntityManager em;

    public void salvar(Venda venda) throws Exception{
        em.merge(venda);
    }

    public void excluir(Venda venda) {
        em.remove(em.find(Venda.class, venda.getId()));
    }

    public Venda pesquisar(Long id) {
        return em.find(Venda.class, id);
    }

    public List<Venda> pesquisar(Cliente cliente) throws Exception {
        try {
            Query consulta = em.createNamedQuery("venda.findByNome");
            consulta.setParameter("cliente", cliente);
            return consulta.getResultList();
        } catch (Exception ex) {
            throw new Exception("Error ao pesquisar por cliente");

        }
    }

    public List<Venda> pesquisar(Date dataVenda) throws Exception {
        try {
            Query consulta = em.createNamedQuery("venda.findByDataVenda");
            consulta.setParameter("dataVenda", dataVenda);
            return consulta.getResultList();
        } catch (Exception ex) {
            throw new Exception("Error ao pesquisar por Data de Venda");

        }
    }
    public List<Venda> pesquisarAll( ) throws Exception {
        try {
            Query consulta = em.createNamedQuery("venda.findAll");
            
            return consulta.getResultList();
        } catch (Exception ex) {
            throw new Exception("Error ao pesquisar a Venda");

        }
    }
        public List<Venda> pesquisarPorCliente(String nomeCliente) throws Exception {
        try {
            Query consulta = em.createNamedQuery("Venda.findByCliente");
            consulta.setParameter("nomeCliente", nomeCliente +"%" );
            return consulta.getResultList();
        } catch (Exception ex) {
            throw new Exception("Error ao pesquisar a Venda");

        }
        }
        public List<Venda> pesquisar(String nomeCliente, Date dataVendaInicio, Date dataVendaFim) throws Exception {
        try {

            String consulta = "SELECT v FROM Venda v WHERE ";
     
            if (dataVendaInicio != null && dataVendaFim != null) {
            consulta += " v.dataVenda BETWEEN :dataVendaInicio AND :dataVendaFim AND ";  
            
            }
            consulta += " v.cliente.nome LIKE :nomeCliente";
            
            TypedQuery<Venda> query = em.createQuery(consulta, Venda.class);            
           
            if (dataVendaInicio != null && dataVendaFim != null) {
                query.setParameter("dataVendaInicio", dataVendaInicio,TemporalType.DATE);
                query.setParameter("dataVendaFim", dataVendaFim,TemporalType.DATE);
            } 
            query.setParameter("nomeCliente",nomeCliente + "%");
            return query.getResultList();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
        
        
        
        
        

}
