package model;

import enums.TipoConta;

import java.sql.Date;

public class Conta {
    private Integer nro_conta;
    private Integer agencia;
    private TipoConta tipo_conta;
    private Date data_abertura;
    private Double saldo;


    public Conta(){

    }

    public Conta(Integer nro_conta, Integer agencia, TipoConta tipo_conta, Date data_abertura, Double saldo) {
        this.nro_conta = nro_conta;
        this.agencia = agencia;
        this.tipo_conta = tipo_conta;
        this.data_abertura = data_abertura;
        this.saldo = saldo;
    }

    public Integer getNro_conta() {
        return nro_conta;
    }
    public Integer getAgencia() {
        return agencia;
    }
    public TipoConta getTipo_conta() {
        return tipo_conta;
    }
    public Date getData_abertura() {
        return data_abertura;
    }
    public Double getSaldo() {
        return saldo;
    }



    public void cadastrarConta(Integer nro_conta, Integer agencia, TipoConta tipo_conta, Date data_abertura, Double saldo) {
        this.nro_conta = nro_conta;
        this.agencia = agencia;
        this.tipo_conta = tipo_conta;
        this.data_abertura = data_abertura;
        this.saldo = saldo;

    }
}
