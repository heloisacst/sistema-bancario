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
        System.out.println("O que deseja fazer? (Digite o número da opção desejada)");
        System.out.println("(1) Cadastrar um gerente");
        System.out.println("(2) Excluir gerente");
        System.out.print("---> ");
        int op = sc.nextInt();

        switch (op) {
            case 1: cadastrarGerente();
                break;
            case 2: excluirGerente();
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

        gerente.cadastrarGerente(matricula, nome_gerente);

        try (Connection connection = conexao.getConnection()) {
            String sql = "INSERT INTO gerente (matricula, nome_gerente) VALUES (?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, gerente.getMatricula());
            preparedStatement.setString(2, gerente.getNome_gerente());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Gerente cadastrado com sucesso!");
            } else {
                System.out.println("Falha ao cadastrar o gerente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void excluirGerente(){
        System.out.println("Informe a matrícula do gerente que deseja excluir");
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
                System.out.println("Falha ao localizar a gerente.");
                excluirGerente();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
