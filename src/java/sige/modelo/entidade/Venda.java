/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sige.modelo.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author AlanCosta
 */
@Entity
@Table(name = "venda", schema = "projeto001")
@NamedQueries({
    @NamedQuery(
            name = "Venda.findByDataVenda",
            query = "SELECT v FROM  Venda v  WHERE v.dataVenda = :dataVenda"
    ),
        @NamedQuery(
            name = "Venda.findAll",
            query = "SELECT v FROM Venda v"
    ),
        @NamedQuery(
          name="Venda.findByCliente",
                query="SELECT v FROM Venda v WHERE v.cliente.nome LIKE :nomeCliente"
        )

})

public class Venda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "data_venda")
    @Temporal(TemporalType.DATE)
    private Date dataVenda;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    
    @Column(name = "valor_total")
    private Double valorTotal;
    
    

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "venda")
    @JoinColumn(name = "venda_id")
    private List<ItemVenda> itens;

    //private StatusVenda statusVenda;
    public void venda() {
        this.itens = new ArrayList<>();
        this.dataVenda = new Date();
    }

    public void addItemVenda(ItemVenda itemVenda) throws Exception {
        if (this.itens == null) {
            this.itens = new ArrayList<>();
        }
        if (itemVenda == null) {
            throw new Exception("Item de venda e nulo");
        }
        if (this.itens == null) {
            this.itens = new ArrayList<>();
        }

        if (!this.itens.contains(itemVenda)) {
            itemVenda.setVenda(this);
            this.itens.add(itemVenda);
        }
        calcularTotalVenda();
    }

    public void calcularTotalVenda() {
        this.valorTotal = 0.0;
//        if (this.itens != null && this.itens.size() > 0) {
//
//        }
        

        for (Iterator item = this.itens.iterator(); item.hasNext();) {
            ItemVenda itemVenda = (ItemVenda) item.next();
            if (itemVenda.getValorTotal() != null && itemVenda.getValorTotal() > 0) {
                 this.valorTotal += itemVenda.getValorTotal();

            }
           
        }

    }

    public void removerItemVenda(ItemVenda itemVenda) {
        if (itemVenda != null) {
            if (this.itens != null && this.itens.contains(itemVenda)) {
                this.itens.remove(itemVenda);
                calcularTotalVenda();
            }
        }
    }
    
    
  

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
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
        if (!(object instanceof Venda)) {
            return false;
        }
        Venda other = (Venda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sige.modelo.entidade.Venda[ id=" + id + " ]";
    }

}
