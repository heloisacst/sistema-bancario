package model;

import enums.TipoTransacao;

import java.time.LocalDateTime;

public class Transacao {
    private LocalDateTime data_hora;
    private Double valor_transacao;
    private TipoTransacao tipoTransacao;

    public Transacao(){}

    public Transacao(LocalDateTime data_hora, Double valor_transacao, TipoTransacao tipoTransacao) {
        this.data_hora = data_hora;
        this.valor_transacao = valor_transacao;
        this.tipoTransacao = tipoTransacao;
    }

    public LocalDateTime getData_hora() {
        return data_hora;
    }

    public Double getValor_transacao() {
        return valor_transacao;
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public void cadastrarTransacao(LocalDateTime data_hora, Double valor_transacao, TipoTransacao tipoTransacao){
        this.data_hora = data_hora;
        this.valor_transacao = valor_transacao;
        this.tipoTransacao = tipoTransacao;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "data_hora=" + data_hora +
                ", valor_transacao=" + valor_transacao +
                ", tipoTransacao=" + tipoTransacao +
                '}';
    }
}
