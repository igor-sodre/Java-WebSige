/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sige.modelo.entidade;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "cliente", schema = "projeto001")
@NamedQueries({
    @NamedQuery(
            name = "Cliente.findByNome",
            query = "SELECT c FROM Cliente c WHERE c.nome LIKE :nome ORDER BY c.nome"
    ),
    @NamedQuery(
            name = "Cliente.findByCpfCnpj",
            query = "SELECT c FROM Cliente c WHERE c.cpfCnpj = :cpfCnpj"
    ),
    @NamedQuery(
            name = "Cliente.findByCidadeNome",
            query = "SELECT c FROM Cliente c WHERE c.cidade = :cidade AND c.nome LIKE :nome ORDER BY c.nome"
    )
})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;
    @Column(name = "cpf_cnpf", length = 18, nullable = false, unique = true)
    private String cpfCnpj;
    @Column(name = "rg_insc_estadual", length = 15)
    private String rgInscEstadual;
    @Column(name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @Column(name = "email", length = 50)
    private String email;
    @Column(name = "telefone", length = 14)
    private String telefone;
    @Column(name = "celular", length = 14)
    private String celular;
    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;
    @Column(name = "bairro", length = 50)
    private String bairro;
    @Column(name = "endereco", length = 150)
    private String endereco;
    @Column(name = "cep", length = 10)
    private String cep;

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

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getRgInscEstadual() {
        return rgInscEstadual;
    }

    public void setRgInscEstadual(String rgInscEstadual) {
        this.rgInscEstadual = rgInscEstadual;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
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

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sige.modelo.entidade.Cliente[ id=" + id + " ]";
    }
    
}
