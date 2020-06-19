
package sige.modelo.entidade;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "fornecedor", schema = "projeto001")
@NamedQueries({
    @NamedQuery(
            name = "Fornecedor.findByNomeFornecedor",
            query = "SELECT f FROM Fornecedor f WHERE f.nomeFornecedor LIKE :nomeFornecedor "
    )   
})
public class Fornecedor implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nome_fornecedor", length = 50, nullable=false)
    private String nomeFornecedor;
    @Column(name = "cpff", length = 14, nullable = false)
    private String cpff;
    @Column(name = "Telefone", length = 14, nullable = false)
    private String telefone;
    @Column(name = "Email", length = 155)
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    @Column(name = "Bairro", length = 1500)
    private String bairro;
     @Column(name = "Endereco", length = 1500)
    private String endereco;
    
    
    @ManyToOne
    @JoinColumn(name="cidade_id")
    private Cidade cidade;

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    
    
   @Column(name = "cep", length = 15)
    private String cep;
   @Column(name="observacao",length = 255)
   private String observacao;
   
   @ManyToMany(mappedBy = "listafornecedors")
   private List<Produto> ListaProduto;
   
   @Column(name="insc_municipal",length = 20)
   private String inscMunicipal;
    @Column(name="insc_estadual",length = 20)
   private String inscEstadual;

    public String getInscMunicipal() {
        return inscMunicipal;
    }

    public void setInscMunicipal(String inscMunicipal) {
        this.inscMunicipal = inscMunicipal;
    }

    public String getInscEstadual() {
        return inscEstadual;
    }

    public void setInscEstadual(String inscEstadual) {
        this.inscEstadual = inscEstadual;
    }
    
    
    
    

    public Fornecedor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public String getCpff() {
        return cpff;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public void setCpff(String cpff) {
        this.cpff = cpff;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Fornecedor other = (Fornecedor) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    

   

    
}

   
    
    

  
    

    

   
    

