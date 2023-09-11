package model;

public class Cliente {
    private Integer cpf;
    private String nome;
    private Integer telefone;
    private String email;

    public Cliente(){

    }

    public Cliente(Integer cpf, String nome, Integer telefone, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public Integer getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public Integer getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public void cadastrarCliente(Integer cpf, String nome, Integer telefone, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public String toString() {
        return "Cliente{" +
                "cpf=" + cpf +
                ", nome='" + nome + '\'' +
                ", telefone=" + telefone +
                ", email='" + email + '\'' +
                '}';
    }
}
