package sige.modelo.sessionbean;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sige.modelo.entidade.Cidade;
import sige.modelo.entidade.Fornecedor;


@Stateless
public class FornecedorSBean {

   

    @PersistenceContext(unitName = "sigePU")
    EntityManager em;
    
    
    public void salvar(Fornecedor fornecedor)throws Exception{
        
         em.merge(fornecedor);
    }
    
    public void excluir(Fornecedor fornecedor)throws Exception {
        em.remove(em.find(Fornecedor.class, fornecedor.getId()));        
    }
    
    public Fornecedor fornecedor(Long id)throws Exception {
        return em.find(Fornecedor.class, id);
    }
    
    public List<Fornecedor> pesquisar(String nome)throws Exception {
        
        List<Fornecedor> listaFornecedor;
        Query consulta = em.createNamedQuery("Fornecedor.findByNomeFornecedor");
        consulta.setParameter("nomeFornecedor", nome.toUpperCase() + "%");
        listaFornecedor = consulta.getResultList();
        return listaFornecedor;
    }

   public Fornecedor pesquisaCnpj(String cnpj) throws Exception{
       try{
           Query consulta =em.createNamedQuery("Fornecedor.findBycnpj");
           consulta.setParameter("cnpj",cnpj);
           return(Fornecedor)consulta.getSingleResult();
           
       }catch(NoResultException ex){
           return null;
       }catch(Exception ex){
           
           throw new Exception("Erro ao Buscar Fornecedor por CNPJ");
       
   }
}

    public Fornecedor pesquisar(Long id) {
        return em.find(Fornecedor.class, id);
    }

    
    
}
