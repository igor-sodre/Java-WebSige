package sige.modelo.entidade;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author AlanCosta
 */
@Entity
@Table(name = "unidade_medida", schema = "projeto001")
@NamedQueries(
        @NamedQuery(
                name = "UnidadedeMedida.findByNome",
                query = "SELECT u FROM UnidadedeMedida u WHERE u.nome LIKE :nome"
        )

        
)
public class UnidadedeMedida implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(name = "nome", length = 20, nullable = false)
    private String nome;
    @Column(name = "simbolo", length = 3, nullable = false, unique = true)
    private String simbolo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnidadedeMedida)) {
            return false;
        }
        UnidadedeMedida other = (UnidadedeMedida) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sige.modelo.entidade.UnidadedeMedida[ id=" + id + " ]";
    }

}
