package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RelatoriosDao {
    Scanner sc = new Scanner(System.in);
    ConexaoDao conexao = new ConexaoDao();
    ResultSet retorno = null;

    public void gerarRelatorios() {
        System.out.println("Escolha o relatório que deseja gerar");
        System.out.println("(1) Quantidade de Transferências por Cliente");
        int op = sc.nextInt();

        switch (op){
            case 1: consultaTransacoes();
                break;
            default:
                System.out.println("Operação inválida!");
        }

    }

    private void consultaTransacoes() {

        try (Connection connection = conexao.getConnection()) {
            String sql = "SELECT cc.nome, c.nro_conta, c.tipo_conta, COUNT(t.cod_transacao) quantidade_transacoes FROM transacao t, conta c, cliente cc WHERE c.nro_conta = t.nro_conta_origem AND c.CPF_cliente = cc.CPF GROUP BY cc.nome, c.nro_conta, c.tipo_conta ORDER BY COUNT(t.cod_transacao) DESC;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            retorno = preparedStatement.executeQuery();

            System.out.println("Relatório de Transações:");
            System.out.printf("%-20s %-15s %-15s %-20s\n", "Nome", "Nro Conta", "Tipo Conta", "Quantidade Transações");
            System.out.println("--------------------------------------------------------------");

            while (retorno.next()) {
                String nome = retorno.getString("nome");
                int nroConta = retorno.getInt("nro_conta");
                String tipoConta = retorno.getString("tipo_conta");
                int quantidadeTransacoes = retorno.getInt("quantidade_transacoes");

                System.out.printf("%-20s %-15d %-15s %-20d\n", nome, nroConta, tipoConta, quantidadeTransacoes);
            }

           /* if (retorno.next()) {


            } else {
                System.out.println("Falha ao gerar relatório!");
            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
