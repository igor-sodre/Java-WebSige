package sige.controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import sige.controller.converter.UnidadeMedidaConverter;
import sige.controller.uteis.UteisJsf;
import sige.modelo.entidade.Fornecedor;
import sige.modelo.entidade.Grupo;
import sige.modelo.entidade.Produto;
import sige.modelo.entidade.UnidadedeMedida;
import sige.modelo.sessionbean.GrupoSBean;
import sige.modelo.sessionbean.ProdutoSBean;
import sige.modelo.sessionbean.UnidadeMedidaSBean;
import sige.controller.converter.GrupoConverter;
import sige.modelo.sessionbean.FornecedorSBean;

/**
 *
 * @author AlanCosta
 */
@Named(value = "produtoMBean")
@SessionScoped
public class ProdutoMBean implements Serializable {

    @EJB
    private ProdutoSBean produtoSBean;
    @EJB
    private GrupoSBean grupoSBean;
    @EJB
    private UnidadeMedidaSBean unidadeMedidaSBean;
    @EJB
    private FornecedorSBean fornecedorSBean;

    private List<Fornecedor> ListaFornecedores;

    private Produto produto;

    private List<Produto> listaProduto;

    private String nomeFornecedor;

    private List<Grupo> listaGrupo;

    private List<UnidadedeMedida> listaUnidadedeMedida;

    private String valorPesquisar;

    private Fornecedor fornecedorSelecionado;
    private GrupoConverter grupoConverter;

    private UnidadeMedidaConverter unidadeMedidaConverter;

    public ProdutoMBean() {

    }

    @PostConstruct
    public void init() {
        this.produto = new Produto();
        this.listaProduto = new ArrayList<>();

    }

    private void carregarSelects() {
        try {
            this.grupoConverter = new GrupoConverter();
            this.grupoConverter.setGrupoEJB(grupoSBean);
            this.unidadeMedidaConverter = new UnidadeMedidaConverter();
            this.unidadeMedidaConverter.setUnidadeMedidaSBean(unidadeMedidaSBean);
            this.listaGrupo = this.grupoSBean.pesquisar("");
            this.listaUnidadedeMedida = this.unidadeMedidaSBean.pesquisar("");
        } catch (Exception ex) {
            UteisJsf.addMensagemErro("Erro ao Carregar os Selects", ex.getMessage());
        }

    }

    public GrupoConverter getGrupoConverter() {
        return grupoConverter;
    }

    public void setGrupoConverter(GrupoConverter grupoConverter) {
        this.grupoConverter = grupoConverter;
    }

    public UnidadeMedidaConverter getUnidadeMedidaConverter() {
        return unidadeMedidaConverter;
    }

    public void setUnidadeMedidaConverter(UnidadeMedidaConverter unidadeMedidaConverter) {
        this.unidadeMedidaConverter = unidadeMedidaConverter;
    }

    public String botaoAbrirConsFornecedor() {
        return "consFornecedorAddProduto?faces-redirect=true";
    }

    public String botaoAddFornecedor() {
        this.produto.addFornecedor(this.fornecedorSelecionado);
        return "cadProduto?faces-redirect=true";
    }

    public void botaoRemoverFornecedor() {
        this.produto.removerFornecedor(this.fornecedorSelecionado);
    }

    public String botaoNovo() {
        this.produto = new Produto();
        this.carregarSelects();
        return "cadProduto?faces-redirect=true";
    }

    public List<Fornecedor> getListaFornecedores() {
        return ListaFornecedores;
    }

    public void setListaFornecedores(List<Fornecedor> ListaFornecedores) {
        this.ListaFornecedores = ListaFornecedores;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public Fornecedor getFornecedorSelecionado() {
        return fornecedorSelecionado;
    }

    public void setFornecedorSelecionado(Fornecedor fornecedorSelecionado) {
        this.fornecedorSelecionado = fornecedorSelecionado;
    }

    public void botaoSalvar() {
        try {
        produtoSBean.salvar(produto);
        produto = new Produto();
//        return "consProduto?faces-redirect=true";
        }catch(Exception ex) {
            UteisJsf.addMensagemErro(ex.getMessage());
        }
    }

    public void botaoPesquisar() {
        try {
            this.listaProduto = this.produtoSBean.pesquisar(this.valorPesquisar);
        } catch (Exception ex) {
            UteisJsf.addMensagemErro(ex.getMessage());
        }
    }

    public void botaoPesquisarFornecedor() {
        try {
            this.ListaFornecedores = this.fornecedorSBean.pesquisar(this.nomeFornecedor);
        } catch (Exception ex) {
            UteisJsf.addMensagemErro(ex.getMessage());
        }
    }

    public void botaoExcluir() {
        try {
            this.produtoSBean.excluir(this.produto);
        } catch (Exception ex) {
            UteisJsf.addMensagemErro("Erro ao Excluir", ex.getMessage());
        }
    }

    public String botaoEditar() {
        this.carregarSelects();
        return "cadProduto?faces-redirect=true";
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getListaProduto() {
        return listaProduto;
    }

    public String getValorPesquisar() {
        return valorPesquisar;
    }

    public void setValorPesquisar(String valorPesquisar) {
        this.valorPesquisar = valorPesquisar;
    }

    public List<Grupo> getListaGrupo() {
        return listaGrupo;
    }

    public void setListaGrupo(List<Grupo> listaGrupo) {
        this.listaGrupo = listaGrupo;
    }

    public List<UnidadedeMedida> getListaUnidadedeMedida() {
        return listaUnidadedeMedida;
    }

    public void setListaUnidadedeMedida(List<UnidadedeMedida> listaUnidadedeMedida) {
        this.listaUnidadedeMedida = listaUnidadedeMedida;
    }

}
