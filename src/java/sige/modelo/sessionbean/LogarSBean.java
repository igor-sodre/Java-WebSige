/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sige.modelo.sessionbean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sige.modelo.entidade.Usuario;


@Stateless
public class LogarSBean {

    @PersistenceContext(unitName = "sigePU")
    EntityManager em;
    
    public Usuario logar(String userName, String senha) throws Exception {
        try {
            Query consulta = em.createNamedQuery("Usuario.logar");
            consulta.setParameter("userName", userName);
            consulta.setParameter("senha", senha);
            return (Usuario) consulta.getSingleResult();
        }catch(NoResultException ex)  {
            throw new Exception("Usuario ou senha invalidos. ");
        } catch (Exception ex) {
            throw new Exception("Erro ao fazer login. - " + ex.getMessage());
        }
    }
}
