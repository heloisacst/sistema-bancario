package model;

import dao.ConexaoDao;
import enums.TipoConta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Conta {
    private Integer nro_conta;
    private Integer agencia;
    private TipoConta tipo_conta;
    private LocalDateTime data_abertura;
    private Double saldo;

    public Conta(){

    }

    public Conta(Integer nro_conta, Integer agencia, TipoConta tipo_conta, LocalDateTime data_abertura, Double saldo) {
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
    public LocalDateTime getData_abertura() {
        return data_abertura;
    }
    public Double getSaldo() {
        return saldo;
    }

    public void cadastrarConta(Integer nro_conta, Integer agencia, TipoConta tipo_conta, LocalDateTime data_abertura, Double saldo) {
        this.nro_conta = nro_conta;
        this.agencia = agencia;
        this.tipo_conta = tipo_conta;
        this.data_abertura = data_abertura;
        this.saldo = saldo;
    }

    public double verificaSaldo(String CPF_cliente) {
        ConexaoDao conexaoDao = new ConexaoDao();
        ResultSet retorno = null;
        double saldo = 0;

        try {
            Connection conexao = conexaoDao.getConnection();
            String consulta = "SELECT saldo FROM conta WHERE CPF_cliente = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(consulta);
            preparedStatement.setString(1, CPF_cliente);
            retorno = preparedStatement.executeQuery();

            if (retorno.next()) {
                saldo =  retorno.getRow();
            } else {
                System.out.println("Nº de conta não encontrado, informe um existe no banco de dados!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return saldo;
    }

   /* public void depositar(Integer nro_conta, Double valor){
        ConexaoDao conexao = new ConexaoDao();

        try (Connection connection = conexao.getConnection()) {
            String sql = "UPDATE conta SET ";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, conta.getNro_conta());
            preparedStatement.setInt(2, conta.getAgencia());
            preparedStatement.setString(3, tipoContaSql);
            preparedStatement.setTimestamp(4, dataAberturaSql);
            preparedStatement.setDouble(5, conta.getSaldo());
            preparedStatement.setString(6, cpf_cliente);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Conta cadastrada com sucesso!");
            } else {
                System.out.println("Falha ao cadastrar a conta.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }*/
    
    public String toString() {
        return "Conta{" +
                "nro_conta=" + nro_conta +
                ", agencia=" + agencia +
                ", tipo_conta=" + tipo_conta +
                ", data_abertura=" + data_abertura +
                ", saldo=" + saldo +
                '}';
    }
}
