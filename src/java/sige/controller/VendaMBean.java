package sige.controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import sige.controller.uteis.UteisJsf;
import sige.modelo.entidade.ItemVenda;
import sige.modelo.entidade.Venda;
import sige.modelo.sessionbean.ClienteSBean;
import sige.modelo.sessionbean.ProdutoSBean;
import sige.modelo.sessionbean.VendaSBean;

/**
 *
 * @author AlanCosta
 */
@Named(value = "vendaMBean")
@SessionScoped
public class VendaMBean implements Serializable {

    @EJB
    private VendaSBean vendaSBean;

    @EJB
    private ProdutoSBean produtoSBean;
    
    @EJB
    private ClienteSBean clienteSBean;
    
   
    private Venda venda;
    
  private String nomeClientePesquisar;
    private Date dataVendaInicio;
    private Date dataVendaFim;
    private ItemVenda itemVendaSelecionado;
    private List<Venda> listaVenda;

    private String valorPesquisar;
    private String cpfCnpjPesquisar;
    private Long idProdutoPesquisar;

    public String getValorPesquisar() {
        return valorPesquisar;
    }

    public void setValorPesquisar(String valorPesquisar) {
        this.valorPesquisar = valorPesquisar;
    }

    public String getCpfCnpjPesquisar() {
        return cpfCnpjPesquisar;
    }

    public void setCpfCnpjPesquisar(String cpfCnpjPesquisar) {
        this.cpfCnpjPesquisar = cpfCnpjPesquisar;
    }

    public Long getIdProdutoPesquisar() {
        return idProdutoPesquisar;
    }

    public void setIdProdutoPesquisar(Long idProdutoPesquisar) {
        this.idProdutoPesquisar = idProdutoPesquisar;
    }

    public String getNomeClientePesquisar() {
        return nomeClientePesquisar;
    }

    public void setNomeClientePesquisar(String nomeClientePesquisar) {
        this.nomeClientePesquisar = nomeClientePesquisar;
    }


    
    




   
   



    public VendaMBean() {

    }

    @PostConstruct
    public void init() {

    }

    public String botaoNovo() {
        this.venda = new Venda();
        this.venda.setDataVenda(new Date());
        cpfCnpjPesquisar="";

        return "cadVenda?faces-redirect=true";
    }
    
    public String botaoEditarItem(){
        idProdutoPesquisar=itemVendaSelecionado.getProduto().getId();
         return "addItemVenda?faces-redirect=true";
    }
     public void botaoExcluirItem(){
   this.venda.removerItemVenda(this.itemVendaSelecionado);
   this.itemVendaSelecionado= new ItemVenda();
         
        
    }

    public ItemVenda getItemVendaSelecionado() {
        return itemVendaSelecionado;
    }

    public void setItemVendaSelecionado(ItemVenda itemVendaSelecionado) {
        this.itemVendaSelecionado = itemVendaSelecionado;
    }
    
    

    public String botaoSalvar() {
        try {
            this.vendaSBean.salvar(this.venda);
            UteisJsf.addMensagemInfo("Salvo com Sucesso", "");
            this.venda = new Venda();
            this.venda.setDataVenda(new Date());
            cpfCnpjPesquisar="";
            return "consVenda?faces-redirect=true";
        } catch (Exception ex) {
            UteisJsf.addMensagemErro("Erro ao Salvar", ex.getMessage());
        }
        return null;
    }
     public String botaoAddItemVendaNav(){
         itemVendaSelecionado=new ItemVenda();
         itemVendaSelecionado.setQuantidade(1.0);
         return "addItemVenda?faces-redirect=true";
     }
    public void botaoPesquisar() {
        try{
        this.listaVenda=this.vendaSBean.pesquisar(nomeClientePesquisar, dataVendaInicio, dataVendaFim);
        

    }catch(Exception ex){
         UteisJsf.addMensagemErro("Erro ao Pesquisar", ex.getMessage());
    }
    }

    public Date getDataVendaInicio() {
        return dataVendaInicio;
    }

    public void setDataVendaInicio(Date dataVendaInicio) {
        this.dataVendaInicio = dataVendaInicio;
    }

    public Date getDataVendaFim() {
        return dataVendaFim;
    }

    public void setDataVendaFim(Date dataVendaFim) {
        this.dataVendaFim = dataVendaFim;
    }
    
    

    
    
    
    public void pesquisarCliente(){
        try{
            if(this.venda==null){
                this.venda=new Venda();
            }
            
            this.venda.setCliente(this.clienteSBean.pesquisarPorCpfCnpj(cpfCnpjPesquisar));
            
        }catch (Exception ex){
//            UteisJsf.addMensagemErro("Erro ao pesquisar -"+ ex.getMessage());
        }
    }
    public void pesquisarProduto(){
       try{
            this.itemVendaSelecionado = new ItemVenda();
        
       this.itemVendaSelecionado.setProduto(this.produtoSBean.pesquisar(idProdutoPesquisar));
    }catch(Exception ex){
//        UteisJsf.addMensagemErro("Erro ao Pesquisar Produto"+ex.getMessage());
    }
    }
    public String addItemVenda(){
        try{
            if(this.venda==null){
                this.venda=new Venda();
                this.venda.setDataVenda(new Date());
            }
        this.venda.addItemVenda(this.itemVendaSelecionado);
        this.itemVendaSelecionado = new ItemVenda();
        this.idProdutoPesquisar=null;
        return "cadVenda?faces-redirect=true";
    }catch (Exception ex){
         UteisJsf.addMensagemErro("Erro ao adicionar item venda"+ex.getMessage());
    }
        return null;
    }
    public void calcularValorTotalItem(){
        this.itemVendaSelecionado.calcularValorTotalItem();
    }
    
    

    public void botaoExcluir() {
        vendaSBean.excluir(venda);
    }

    public String botaoEditar() {
        cpfCnpjPesquisar=venda.getCliente().getCpfCnpj();

        return "cadVenda?faces-redirect=true";
    }
    
     
    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public List<Venda> getListaVenda() {
        return listaVenda;
    }

    public void setListaVenda(List<Venda> listaVenda) {
        this.listaVenda = listaVenda;
    }

}
