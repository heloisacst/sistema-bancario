package dao;

import model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ClienteDao {
    Scanner sc = new Scanner(System.in);
    ConexaoDao conexao = new ConexaoDao();
    public Cliente cliente = new Cliente();
    ResultSet retorno = null;

    public void administrarCliente(){
        System.out.println("O que deseja fazer? (Digite o número da opção desejada)");
        System.out.println("(1) Consultar cliente");
        System.out.println("(2) Cadastrar um cliente");
        System.out.println("(3) Atualizar dados de cliente");
        System.out.println("(4) Excluir um cliente");
        System.out.print("---> ");
        int op = sc.nextInt();

        switch (op) {
            case 1: consultarCliente();
                break;
            case 2: cadastrarCliente();
                break;
            case 3: atualizarCliente();
                break;
            case 4: excluirCliente();
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }

    }

    private void cadastrarCliente(){
        System.out.println("----CADASTRO DE CLIENTE----");
        sc.nextLine();
        System.out.print("Número do CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Nome completo: ");
        String nome = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();
        System.out.print("E-mail: ");
        String email = sc.nextLine();
        cpf = cpf.replaceAll("[^0-9]", "");

        cliente.cadastrarCliente(cpf, nome, telefone, email);

        try (Connection connection = conexao.getConnection()) {
            String sql = "INSERT INTO cliente (CPF, nome, telefone, email) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, cliente.getCpf());
            preparedStatement.setString(2, cliente.getNome());
            preparedStatement.setString(3, cliente.getTelefone());
            preparedStatement.setString(4, cliente.getEmail());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Cliente cadastrado com sucesso!");
            } else {
                System.out.println("Falha ao cadastrar o cliente.");
                cadastrarCliente();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void atualizarCliente(){
        System.out.print("Informe o CPF do cliente que deseja atualizar: ");
        sc.nextLine();
        String cpf = sc.nextLine();

        try (Connection connection = conexao.getConnection()) {
            String sql = "SELECT * FROM cliente WHERE CPF = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cpf);
            retorno = preparedStatement.executeQuery();

            if (retorno.next()) {
                System.out.print("Novo CPF: ");
                String newCpf = sc.nextLine();
                System.out.print("Novo nome completo: ");
                String newNome = sc.nextLine();
                System.out.print("Novo telefone: ");
                String newTelefone = sc.nextLine();
                System.out.print("Novo e-mail: ");
                String newEmail = sc.nextLine();
                cpf = cpf.replaceAll("[^0-9]", "");

                cliente.cadastrarCliente(newCpf, newNome, newTelefone, newEmail);

                String update = "UPDATE cliente SET CPF = ?, nome = ?, telefone = ?, email = ? WHERE CPF = ?";
                preparedStatement = connection.prepareStatement(update);
                preparedStatement.setString(1, cliente.getCpf());
                preparedStatement.setString(2, cliente.getNome());
                preparedStatement.setString(3, cliente.getTelefone());
                preparedStatement.setString(4, cliente.getEmail());
                preparedStatement.setString(5, cpf);

                int rowsAffected = preparedStatement.executeUpdate();

                if(rowsAffected > 0){
                    System.out.println("Cliente atualizado com sucesso!");
                } else {
                    System.out.println("Falha ao atualizar cliente!");
                }
            } else {
                System.out.println("Cliente não encontrado!");
                atualizarCliente();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void consultarCliente() {
        String nome = null;
        String telefone = null;
        String email = null;

        System.out.println("Informe o CPF do cliente que deseja consultar");
        String cpf = sc.nextLine();

        try (Connection connection = conexao.getConnection()) {
            String sql = "SELECT CPF, nome, telefone, email FROM cliente WHERE CPF = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cpf);
            retorno = preparedStatement.executeQuery();

            if (retorno.next()) {
                nome = retorno.getString("nome");
                telefone = retorno.getString("telefone");
                email = retorno.getString("email");

                System.out.println("| " + nome + " | " + telefone + " | " + email + " |");

            } else {
                System.out.println("Cliente não encontrado!");
                consultarCliente();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void excluirCliente(){
        System.out.println("Informe o CPF do cliente que deseja excluir");
        sc.nextLine();
        String cpf = sc.nextLine();

        try (Connection connection = conexao.getConnection()) {
            String sql = "SELECT * FROM cliente WHERE CPF = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cpf);
            retorno = preparedStatement.executeQuery();

            if (retorno.next()) {
                String delete = "DELETE FROM cliente WHERE CPF = ?";
                preparedStatement = connection.prepareStatement(delete);
                preparedStatement.setString(1, cpf);
                int rowsAffected = preparedStatement.executeUpdate();

                if(rowsAffected > 0){
                    System.out.println("Cliente excluído com sucesso!");
                } else {
                    System.out.println("Falha ao excluir cliente!");
                }
            } else {
                System.out.println("Cliente não encontrado!");
                excluirCliente();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String retornaTipoUsuario(String cpf) {
        String tipoUsuario = null;
        ResultSet retornoCliente = null;
        ResultSet retornoGerente = null;

        try (Connection connection = conexao.getConnection()) {
            String consultaCliente = "SELECT * FROM cliente WHERE CPF = ?";
            PreparedStatement queryCliente = connection.prepareStatement(consultaCliente);
            queryCliente.setString(1, cpf);
            retornoCliente = queryCliente.executeQuery();

            String consultaGerente = "SELECT * FROM gerente WHERE cpf_gerente = ?";
            PreparedStatement queryGerente = connection.prepareStatement(consultaGerente);
            queryGerente.setString(1, cpf);
            retornoGerente = queryGerente.executeQuery();

            if (retornoCliente.next()) {
                tipoUsuario = "CLIENTE";
            } else if (retornoGerente.next()) {
                tipoUsuario = "GERENTE";
            } else {
                System.out.println("CPF não encontrado na base de dados!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tipoUsuario;
    }
}
