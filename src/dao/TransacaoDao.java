package dao;

import enums.TipoTransacao;
import model.Transacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Scanner;

public class TransacaoDao {
    Scanner sc = new Scanner(System.in);
    ConexaoDao conexao = new ConexaoDao();
    Transacao transacao = new Transacao();
    ContaDao contaDao = new ContaDao();

    public void efetuarTransacao() {
        System.out.println("Informe qual operação será realizada | [DEPOSITO, SAQUE, ENVIAR]");
        System.out.print("--> ");
        TipoTransacao tipoTransacao = TipoTransacao.valueOf(sc.next());
        System.out.println("Informe o seu CPF: ");
        sc.nextLine();
        String cpf = sc.nextLine();
        int contaTransacao = contaDao.retornaNroConta(cpf);

        if (tipoTransacao == TipoTransacao.DEPOSITO) {
            System.out.print("Qual valor será depositado: R$ ");
            double valorDeposito = sc.nextDouble();
            LocalDateTime dth_transacao = LocalDateTime.now();

            transacao.cadastrarTransacao(dth_transacao, valorDeposito, tipoTransacao);
            Timestamp dth_transacaoSql = Timestamp.valueOf(dth_transacao);
            String tipoTransacaoSql = tipoTransacao.toString();


            try (Connection connection = conexao.getConnection()) {
                String sql = "INSERT INTO transacao (data_hora, valor_transacao, tipo_transacao, nro_conta_origem) VALUES (?, ?, ?, ?)";

                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setTimestamp(1, dth_transacaoSql);
                preparedStatement.setDouble(2, transacao.getValor_transacao());
                preparedStatement.setString(3, tipoTransacaoSql);
                preparedStatement.setInt(4, contaTransacao);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Transação feita com sucesso!");
                } else {
                    System.out.println("Falha ao realizar a transação.");
                }

                double saldoAtual = contaDao.retornaSaldo(cpf);
                saldoAtual += valorDeposito;
                contaDao.atualizaSaldo(contaTransacao, saldoAtual);

            } catch (SQLException e) {
                e.printStackTrace();
            }

            System.out.println(transacao);

        } else if (tipoTransacao == TipoTransacao.SAQUE) {
            System.out.println("Qual valor deseja sacar?");
            System.out.print("--> R$ ");
            double valorSaque = sc.nextDouble();

            double saldoAtual = contaDao.retornaSaldo(cpf);
            saldoAtual -= valorSaque;
            contaDao.atualizaSaldo(contaTransacao, saldoAtual);

        } else
            System.out.println("Informe qual conta receberá a operação de " + tipoTransacao);

    }
}


