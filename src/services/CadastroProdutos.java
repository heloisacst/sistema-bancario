package services;

import connection.Conexao;
import entities.Produtos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CadastroProdutos {
    Scanner sc = new Scanner(System.in);
    Conexao conexao = new Conexao();
    Produtos produtos = new Produtos();
    public void cadastroProudtos(){

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
}
