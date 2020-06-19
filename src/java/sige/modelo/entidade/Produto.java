package sige.modelo.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "produto", schema = "projeto001")
@NamedQueries({
    @NamedQuery(
            name = "Produto.findByNomeProduto",
            query = "SELECT t FROM Produto t WHERE t.nomeProduto LIKE :nomeProduto order by  t.nomeProduto "
    )

})
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome_produto", length = 50, nullable = false)
    private String nomeProduto;
   @Column(name = "valor", length = 10, nullable = false)
    private Double valor;
    @ManyToOne
    @JoinColumn(name = "Unidade_Medida_id")
    private UnidadedeMedida unidadedeMedida;
    @ManyToOne
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;
    @Column(name = "estoque")
    private double estoque;

    @ManyToMany(cascade = (CascadeType.ALL), fetch = FetchType.EAGER)
    @JoinTable(schema = "projeto001", name = "Produto_fornecedor",
            joinColumns = {
                @JoinColumn(name = "produto_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "fornecedor_id")}
    )
    private List<Fornecedor> listafornecedors;

    public Produto() {
        this.listafornecedors = new ArrayList<>();
    }

    public void addFornecedor(Fornecedor fornecedor) {
        this.listafornecedors.add(fornecedor);
    }

    public void removerFornecedor(Fornecedor fornecedor) {
        this.listafornecedors.remove(fornecedor);
    }

    public UnidadedeMedida getUnidadedeMedida() {
        return unidadedeMedida;
    }

    public void setUnidadedeMedida(UnidadedeMedida unidadedeMedida) {
        this.unidadedeMedida = unidadedeMedida;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public double getEstoque() {
        return estoque;
    }

    public void setEstoque(double estoque) {
        this.estoque = estoque;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

   

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public List<Fornecedor> getListafornecedors() {
        return listafornecedors;
    }

    public void setListafornecedors(List<Fornecedor> listafornecedors) {
        this.listafornecedors = listafornecedors;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final Produto other = (Produto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
