package entities;

public class Produtos {
    private Integer cod_produto;
    private String nome_produto;

    public Produtos() {
    }

    public Produtos(Integer cod_produto, String nome_produto) {
        this.cod_produto = cod_produto;
        this.nome_produto = nome_produto;
    }

    public Integer getCod_produto() {
        return cod_produto;
    }

    public String getNome_produto() {
        return nome_produto;
    }

    public void cadastrarProduto(int cod_produto, String nome_produto) {
        this.cod_produto = cod_produto;
        this.nome_produto = nome_produto;
    }

    public void atualizarProduto(int cod_produto, String nome_produto) {
        this.cod_produto = cod_produto;
        this.nome_produto = nome_produto;
    }

    public void removerProduto(int cod_produto, String nome_produto) {
        this.cod_produto = cod_produto;
        this.nome_produto = nome_produto;
    }

    public String toString() {
        return "Produtos{" +
                "cod_produto=" + cod_produto +
                ", nome_produto='" + nome_produto + '\'' +
                '}';
    }
}
