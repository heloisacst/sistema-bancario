package dao;

import model.Produtos;

import java.sql.*;
import java.util.Scanner;

public class ProdutosDao {
    Scanner sc = new Scanner(System.in);
    ConexaoDao conexao = new ConexaoDao();
    Produtos produtos = new Produtos();

    public void administrarProdutos(){
        System.out.println("O que deseja fazer? (Digite o número da opção desejada)");
        System.out.println("(1) Cadastrar um Produto");
        System.out.println("(2) Excluir um Produto");
        System.out.print("---> ");
        int op = sc.nextInt();

        switch (op) {
            case 1: cadastrarProdutos();
                    break;
            case 2: removerProduto();
        }

    }

    private void cadastrarProdutos(){

        System.out.print("Informe o código do produto: ");
        int cod_produto = sc.nextInt();
        System.out.print("Informe o nome do produto: ");
        sc.nextLine();
        String nome_produto = sc.nextLine();

        produtos.cadastrarProduto(cod_produto, nome_produto);

        try (Connection connection = conexao.getConnection()) {
            String sql = "INSERT INTO produtos (cod_produto, nome_produto) VALUES (?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, produtos.getCod_produto());
            preparedStatement.setString(2, produtos.getNome_produto());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Produto cadastrado com sucesso!");
            } else {
                System.out.println("Falha ao cadastrar o produto.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(produtos);

    }

    private void removerProduto(){
        System.out.print("Informe o código do produto que será excluído: ");
        int cod_produto = sc.nextInt();
        ResultSet retorno = null;

        try (Connection connection = conexao.getConnection()){

            String consulta = "SELECT * FROM produtos WHERE cod_produto = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(consulta);
            preparedStatement.setInt(1, cod_produto);
            retorno = preparedStatement.executeQuery();

            if (retorno.next()) {
                String remove = "DELETE FROM produtos WHERE cod_produto = ?";
                preparedStatement = connection.prepareStatement(remove);
                preparedStatement.setInt(1, cod_produto);
                preparedStatement.executeUpdate();
                System.out.println("Produto " + cod_produto + " excluído!");
            } else {
                System.out.println("Produto não encontrado, informe um existe no banco de dados!");
                administrarProdutos();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
