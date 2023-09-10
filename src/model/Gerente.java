package model;

public class Gerente {
    private Integer matricula;
    private String nome_gerente;

    public Gerente() {

    }

    public Gerente(Integer matricula, String nome_gerente) {
        this.matricula = matricula;
        this.nome_gerente = nome_gerente;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public String getNome_gerente() {
        return nome_gerente;
    }

    public void cadastrarGerente(Integer matricula, String nome_gerente) {
        this.matricula = matricula;
        this.nome_gerente = nome_gerente;
    }

    public String toString() {
        return "Gerente{" +
                "matricula=" + matricula +
                ", nome_gerente='" + nome_gerente + '\'' +
                '}';
    }
}
