package entities;

import entities.enums.TiposProduto;

public class Produtos {
    private Integer cod_produto;
    private String nome_produto;
    private TiposProduto tiposProduto;

    public Produtos(){
    }

    public Produtos(Integer cod_produto, String nome_produto, TiposProduto tiposProduto) {
        this.cod_produto = cod_produto;
        this.nome_produto = nome_produto;
        this.tiposProduto = tiposProduto;
    }

    public Integer getCod_produto() {
        return cod_produto;
    }

    public void setCod_produto(Integer cod_produto) {
        this.cod_produto = cod_produto;
    }

    public String getNome_produto() {
        return nome_produto;
    }

    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }

    public TiposProduto getTiposProduto() {
        return tiposProduto;
    }

    public void setTiposProduto(TiposProduto tiposProduto) {
        this.tiposProduto = tiposProduto;
    }
}
