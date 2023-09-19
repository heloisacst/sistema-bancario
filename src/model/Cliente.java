package model;

public class Cliente {
    private String cpf;
    private String nome;
    private String telefone;
    private String email;

    public Cliente(){

    }

    public Cliente(String cpf, String nome, String telefone, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public void cadastrarCliente(String cpf, String nome, String telefone, String email) {
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
