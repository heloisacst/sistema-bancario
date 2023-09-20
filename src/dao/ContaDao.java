package dao;

import enums.TipoConta;
import model.Conta;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ContaDao {

    Scanner sc = new Scanner(System.in);
    ConexaoDao conexao = new ConexaoDao();
    Conta conta = new Conta();
    public void administrarConta(){
        System.out.println("O que deseja fazer? (Digite o número da opção desejada)");
        System.out.println("(1) Cadastrar uma Conta");
        System.out.print("---> ");
        int op = sc.nextInt();

        switch (op) {
            case 1: cadastrarConta();
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    private void cadastrarConta(){
        System.out.println("----CADASTRO DE CONTA----");
        System.out.print("Número da conta: ");
        Integer nro_conta = sc.nextInt();
        System.out.print("Tipo da Conta: ");
        sc.nextLine();
        TipoConta tipoConta = TipoConta.valueOf(sc.next());
        LocalDateTime dataAbertura = LocalDateTime.now();

        conta.cadastrarConta(nro_conta, 0001, tipoConta, dataAbertura, 0.0);
        String tipoContaSql = tipoConta.toString();
        Timestamp dataAberturaSql = Timestamp.valueOf(dataAbertura);

        System.out.print("Informar o CPF que essa conta será vinculada: ");
        sc.nextLine();
        String cpf_cliente = sc.nextLine();

        try (Connection connection = conexao.getConnection()) {
            String sql = "INSERT INTO conta (nro_conta, agencia, tipo_conta, data_abertura, saldo, CPF_cliente) VALUES (?, ?, ?, ?, ?, ?)";

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

        System.out.println(conta);
    }

    public double retornaSaldo(String CPF_cliente) {
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
                saldo =  retorno.getDouble("saldo");
            } else {
                System.out.println("Nº de conta não encontrado, informe um existe no banco de dados!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return saldo;
    }

    public int retornaNroConta(String CPF_cliente) {
        ConexaoDao conexaoDao = new ConexaoDao();
        ResultSet retorno = null;
        int nro_conta = 0;

        try {
            Connection conexao = conexaoDao.getConnection();
            String consulta = "SELECT nro_conta FROM conta WHERE CPF_cliente = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(consulta);
            preparedStatement.setString(1, CPF_cliente);
            retorno = preparedStatement.executeQuery();

            if (retorno.next()) {
                nro_conta =  retorno.getInt("nro_conta");
            } else {
                System.out.println("Nº de conta não encontrado, informe um existe no banco de dados!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nro_conta;
    }

    public void atualizaSaldo(int nro_conta, double valor) {
        ConexaoDao conexaoDao = new ConexaoDao();

        try {
            Connection conexao = conexaoDao.getConnection();
            String sql = "UPDATE conta SET saldo = ? WHERE nro_conta = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            preparedStatement.setDouble(1, valor);
            preparedStatement.setInt(2, nro_conta);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Saldo atualizado com sucesso!");
            } else {
                System.out.println("Nº de conta não encontrado, informe um existe no banco de dados!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
