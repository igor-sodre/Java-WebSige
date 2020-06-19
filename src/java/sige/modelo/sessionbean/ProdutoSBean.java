package sige.modelo.sessionbean;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sige.modelo.entidade.Grupo;
import sige.modelo.entidade.Produto;


@Stateless
public class ProdutoSBean {

    @PersistenceContext(unitName = "sigePU")
    EntityManager em;
    
    
    public void salvar(Produto produto) throws Exception {
         em.merge(produto);
    }
    
    public void excluir(Produto produto) {
        em.remove(em.find(Produto.class, produto.getId()));        
    }
    
    public Produto pesquisar(Long id)throws Exception{
        return em.find(Produto.class, id);
    }
    
    public List<Produto> pesquisar(String nome) throws Exception{
        List<Produto> listaProduto;
        Query consulta = em.createNamedQuery("Produto.findByNomeProduto");
        consulta.setParameter("nomeProduto", nome.toUpperCase() + "%");
        listaProduto = consulta.getResultList();
        return listaProduto;
    }
    
    
    public List<Produto>pesquisar(Grupo grupo) throws Exception{
        try{
            Query consulta=em.createNamedQuery("Produto.findByGrupo");
            consulta.setParameter("grupo",grupo);
            return consulta.getResultList();
        }catch (Exception ex){
            throw new Exception("Erro ao pesquisar por grupo");
        }
    }
    
    
    
    
}
