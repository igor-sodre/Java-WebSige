
package sige.modelo.sessionbean;

import sige.modelo.entidade.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class UsuarioCDSBean {

    @PersistenceContext(unitName = "sigePU")
    EntityManager em;
    
    
    public void salvar(Usuario usuario) {
         em.merge(usuario);
    }
    

    

    

   
}
