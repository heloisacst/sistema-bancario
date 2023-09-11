package dao;

import model.Gerente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class GerenteDao {
    Scanner sc = new Scanner(System.in);
    ConexaoDao conexao = new ConexaoDao();
    Gerente gerente = new Gerente();

    public void cadastrarGerente() {
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

        System.out.println(gerente);

    }

}
