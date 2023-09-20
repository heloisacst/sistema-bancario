package dao;

import enums.TipoTransacao;
import model.Transacao;

import java.sql.*;
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
        LocalDateTime dth_transacao = LocalDateTime.now();
        Timestamp dth_transacaoSql = Timestamp.valueOf(dth_transacao);
        String tipoTransacaoSql = tipoTransacao.toString();
        double saldoAtual;

        if (tipoTransacao == TipoTransacao.DEPOSITO) {
            System.out.print("Qual valor será depositado: R$ ");
            double valorDeposito = sc.nextDouble();
            transacao.cadastrarTransacao(dth_transacao, valorDeposito, tipoTransacao);

            efetivaTransacao(dth_transacaoSql, tipoTransacaoSql, contaTransacao, 0);

            saldoAtual = contaDao.retornaSaldo(cpf);
            saldoAtual += valorDeposito;
            contaDao.atualizaSaldo(contaTransacao, saldoAtual);

        } else if (tipoTransacao == TipoTransacao.SAQUE) {
            double valorSaque = 0;

            do {
                saldoAtual = contaDao.retornaSaldo(cpf);
                System.out.println("Qual valor deseja sacar?");
                System.out.print("--> R$ ");
                valorSaque = sc.nextDouble();

                saldoAtual -= valorSaque;

                if (saldoAtual < 0) {
                    System.out.println("Seu saldo é R$" + contaDao.retornaSaldo(cpf) + " .Você não pode sacar R$" + valorSaque);
                }

            } while(saldoAtual < 0);

            transacao.cadastrarTransacao(dth_transacao, valorSaque, tipoTransacao);
            efetivaTransacao(dth_transacaoSql, tipoTransacaoSql, contaTransacao, 0);
            contaDao.atualizaSaldo(contaTransacao, saldoAtual);

        } else{
            System.out.println("Informe qual conta receberá a operação de " + tipoTransacao);
            int conta_recebimento = sc.nextInt();
            double valorEnvio = 0;

            do{
                saldoAtual = contaDao.retornaSaldo(cpf);
                System.out.println("Qual valor deseja ENVIAR?");
                System.out.print("--> R$ ");
                valorEnvio = sc.nextDouble();

                saldoAtual -= valorEnvio;

                if (saldoAtual < 0) {
                    System.out.println("Seu saldo é R$" + contaDao.retornaSaldo(cpf) + " .Você não pode enviar R$" + valorEnvio);
                }
            } while (saldoAtual < 0);

            transacao.cadastrarTransacao(dth_transacao, valorEnvio, tipoTransacao);
            efetivaTransacao(dth_transacaoSql, tipoTransacaoSql, contaTransacao, conta_recebimento);
            contaDao.atualizaSaldo(contaTransacao, saldoAtual);

        }

        sc.close();
    }
    private void efetivaTransacao(Timestamp dth_transacaoSql, String tipoTransacaoSql, int contaTransacao, int contaDestino){
        try (Connection connection = conexao.getConnection()) {
            String sql = "INSERT INTO transacao (data_hora, valor_transacao, tipo_transacao, nro_conta_origem, nro_conta_destino) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setTimestamp(1, dth_transacaoSql);
            preparedStatement.setDouble(2, transacao.getValor_transacao());
            preparedStatement.setString(3, tipoTransacaoSql);
            preparedStatement.setInt(4, contaTransacao);
            if(contaDestino == 0){
                preparedStatement.setString(5, null);
            }else{
                preparedStatement.setInt(5, contaDestino);
            }

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Transação feita com sucesso!");
            } else {
                System.out.println("Falha ao realizar a transação.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


