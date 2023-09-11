package dao;

import enums.TipoConta;
import model.Conta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
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
        }
    }

    private void cadastrarConta(){
        System.out.println("----CADASTRO DE CONTA----");
        System.out.print("Número da conta: ");
        Integer nro_conta = sc.nextInt();
        System.out.print("Tipo da Conta: ");
        sc.nextLine();
        TipoConta tipoConta = TipoConta.valueOf(sc.next());

        conta.cadastrarConta(nro_conta, 0001, tipoConta, new Date(), 0.0);

        try (Connection connection = conexao.getConnection()) {
            String sql = "INSERT INTO conta (nro_conta, agencia, tipo_conta, data_abertura, saldo) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, conta.getNro_conta());
            preparedStatement.setInt(2, conta.getAgencia());
            preparedStatement.setObject(3, conta.getTipo_conta());
            preparedStatement.setDate(4, conta.getData_abertura());
            preparedStatement.setDouble(5, conta.getSaldo());


            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Cliente cadastrado com sucesso!");
            } else {
                System.out.println("Falha ao cadastrar o cliente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(conta);
    }

}
