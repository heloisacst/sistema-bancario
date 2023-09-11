package dao;

import model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ClienteDao {
    Scanner sc = new Scanner(System.in);
    ConexaoDao conexao = new ConexaoDao();
    Cliente cliente = new Cliente();

    public void administrarCliente(){
        System.out.println("O que deseja fazer? (Digite o número da opção desejada)");
        System.out.println("(1) Cadastrar um Cliente");
        System.out.print("---> ");
        int op = sc.nextInt();

        switch (op) {
            case 1: cadastrarCliente();
                break;
            default:
                System.out.println("Opção inválida!");
        }

    }

    private void cadastrarCliente(){
        System.out.println("----CADASTRO DE CLIENTE----");
        System.out.print("Número do CPF: ");
        Integer cpf = sc.nextInt();
        System.out.print("Nome completo: ");
        sc.nextLine();
        String nome = sc.nextLine();
        System.out.print("Telefone: ");
        Integer telefone = sc.nextInt();
        System.out.print("Email: ");
        sc.nextLine();
        String email = sc.nextLine();

        cliente.cadastrarCliente(cpf, nome, telefone, email);

        try (Connection connection = conexao.getConnection()) {
            String sql = "INSERT INTO cliente (CPF, nome, telefone, email) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, cliente.getCpf());
            preparedStatement.setString(2, cliente.getNome());
            preparedStatement.setInt(3, cliente.getTelefone());
            preparedStatement.setString(4, cliente.getEmail());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Cliente cadastrado com sucesso!");
            } else {
                System.out.println("Falha ao cadastrar o cliente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(cliente);
    }
}
