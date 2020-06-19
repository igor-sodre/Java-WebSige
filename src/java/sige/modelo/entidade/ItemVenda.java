/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sige.modelo.entidade;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author AlanCosta
 */
@Entity
@Table(name = "itens_venda", schema = "projeto001")
public class ItemVenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @ManyToOne
    @JoinColumn(name="Venda_id")
    private Venda venda;
    @Id
    @ManyToOne
    @JoinColumn(name="Produto_id")
    private Produto produto;
    @Column(name="quantidade")
    private Double quantidade;
   @Column(name="valor")
    private Double valor;
   @Column(name="valorTotal")
    private Double valorTotal;
   
   public ItemVenda(){
      this.quantidade=1.0; 
   }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
   


    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
       // if(this.Venda.getStatus().equals(StatusVenda.Emitida)){
        this.valor = this.produto.getValor();
    //}
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValor() {
        return valor;
    }

    public Double getValorTotal() {
        this.calcularValorTotalItem();

        return valorTotal;
    }
    public void calcularValorTotalItem(){
         if((this.valor!=null)&&(this.valor>0)&&(this.quantidade !=null)&&(this.quantidade>0)){
             
             this.valorTotal=this.quantidade*this.valor;
                
            }
    }

//    public void setValorTotal(Double valorTotal) {
//        this.valorTotal = valorTotal;
//    }

//    public void setValor(Double valor) { 
//        this.valor = valor;
//    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.venda);
        hash = 41 * hash + Objects.hashCode(this.produto);
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
        final ItemVenda other = (ItemVenda) obj;
        if (!Objects.equals(this.venda, other.venda)) {
            return false;
        }
        return true;
    }
   
   
   
   
   
   
    
}
  