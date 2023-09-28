package dao;

import model.Gerente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GerenteDao {
    Scanner sc = new Scanner(System.in);
    ConexaoDao conexao = new ConexaoDao();
    Gerente gerente = new Gerente();
    ResultSet retorno = null;

    public void administrarGerente(){
        System.out.println("***************************************************************");
        System.out.println("\nO que deseja fazer? (Digite o número da opção desejada)");
        System.out.println("(1) Consultar gerente");
        System.out.println("(2) Cadastrar um gerente");
        System.out.println("(3) Atualizar dados do gerente");
        System.out.println("(4) Excluir gerente");
        System.out.print("---> ");
        int op = sc.nextInt();

        switch (op) {
            case 1: consultarGerente();
                break;
            case 2: cadastrarGerente();
                break;
            case 3: atualizarGerente();
                break;
            case 4: excluirGerente();
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    private void cadastrarGerente() {
        System.out.println("----CADASTRO DE GERENTE----");
        System.out.print("Informe o número da matrícula do gerente: ");
        int matricula = sc.nextInt();
        System.out.print("Informe o nome do gerente: ");
        sc.nextLine();
        String nome_gerente = sc.nextLine();
        System.out.print("Informe o CPF do gerente: ");
        String cpf = sc.nextLine();

        gerente.cadastrarGerente(matricula, nome_gerente, cpf);

        try (Connection connection = conexao.getConnection()) {
            String sql = "INSERT INTO gerente (matricula, nome_gerente, cpf_gerente) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, gerente.getMatricula());
            preparedStatement.setString(2, gerente.getNome_gerente());
            preparedStatement.setString(3, gerente.getCpf());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Gerente cadastrado com sucesso!");
            } else {
                System.out.println("Falha ao cadastrar gerente!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void atualizarGerente() {
        System.out.println("Informe a matrícula do gerente que deseja atualizar");
        int matricula = sc.nextInt();

        try (Connection connection = conexao.getConnection()) {
            String sql = "SELECT * FROM gerente WHERE matricula = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, matricula);
            retorno = preparedStatement.executeQuery();

            if (retorno.next()) {
                System.out.print("Nova Matrícula: ");
                int newMatricula = sc.nextInt();
                System.out.print("Novo nome: ");
                sc.nextLine();
                String nome = sc.nextLine();
                System.out.print("Novo CPF: ");
                String cpf = sc.nextLine();

                gerente.cadastrarGerente(newMatricula, nome, cpf);

                String update = "UPDATE gerente SET matricula = ?, nome_gerente = ?, cpf_gerente = ? WHERE matricula = ?";
                preparedStatement = connection.prepareStatement(update);
                preparedStatement.setInt(1, gerente.getMatricula());
                preparedStatement.setString(2, gerente.getNome_gerente());
                preparedStatement.setString(3, gerente.getCpf());
                preparedStatement.setInt(4, matricula);

                int rowsAffected = preparedStatement.executeUpdate();

                if(rowsAffected > 0){
                    System.out.println("Gerente atualizado com sucesso!");
                } else {
                    System.out.println("Falha ao atualizar gerente!");
                }
            } else {
                System.out.println("Gerente não localizado!");
                excluirGerente();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void consultarGerente() {
        String nomeGerente = null;
        String cpfGerente = null;

        System.out.print("Informe a matrícula do gerente que deseja consultar: ");
        int matricula = sc.nextInt();

        try (Connection connection = conexao.getConnection()) {
            String sql = "SELECT matricula, nome_gerente, cpf_gerente FROM gerente WHERE matricula = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, matricula);
            retorno = preparedStatement.executeQuery();

            if (retorno.next()) {
                nomeGerente = retorno.getString("nome_gerente");
                cpfGerente = retorno.getString("cpf_gerente");

                System.out.println("| " + matricula + " | " + nomeGerente + " | " + cpfGerente + " | ");
            } else {
                System.out.println("Gerente não localizado!");
                consultarGerente();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void excluirGerente(){
        System.out.print("Informe a matrícula do gerente que deseja excluir: ");
        int matricula = sc.nextInt();

        try (Connection connection = conexao.getConnection()) {
            String sql = "SELECT * FROM gerente WHERE matricula = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, matricula);
            retorno = preparedStatement.executeQuery();

            if (retorno.next()) {
                String delete = "DELETE FROM gerente WHERE matricula = ?";
                preparedStatement = connection.prepareStatement(delete);
                preparedStatement.setInt(1, matricula);
                int rowsAffected = preparedStatement.executeUpdate();

                if(rowsAffected > 0){
                    System.out.println("Gerente excluído com sucesso!");
                } else {
                    System.out.println("Falha ao excluir gerente!");
                }
            } else {
                System.out.println("Gerente não localizado!");
                excluirGerente();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
