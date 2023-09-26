package model;

public class Gerente {
    private Integer matricula;
    private String nome_gerente;

    private String cpf;

    public Gerente() {

    }

    public Gerente(Integer matricula, String nome_gerente, String cpf) {
        this.matricula = matricula;
        this.nome_gerente = nome_gerente;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public String getNome_gerente() {
        return nome_gerente;
    }

    public String getCpf() {
        return cpf;
    }

    public void cadastrarGerente(Integer matricula, String nome_gerente, String cpf) {
        this.matricula = matricula;
        this.nome_gerente = nome_gerente;
        this.cpf = cpf;
    }

    public String toString() {
        return "Gerente{" +
                "matricula=" + matricula +
                ", nome_gerente='" + nome_gerente + '\'' +
                '}';
    }
}
