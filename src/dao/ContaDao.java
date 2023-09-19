package dao;

import enums.TipoConta;
import model.Conta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
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

}
