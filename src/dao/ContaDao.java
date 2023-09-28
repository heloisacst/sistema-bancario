package dao;

import enums.TipoConta;
import model.Conta;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ContaDao {
    Scanner sc = new Scanner(System.in);
    ConexaoDao conexao = new ConexaoDao();
    Conta conta = new Conta();
    ResultSet retorno = null;

    public void administrarConta(){
        System.out.println("***************************************************************");
        System.out.println("\nO que deseja fazer? (Digite o número da opção desejada)");
        System.out.println("(1) Consultar conta");
        System.out.println("(2) Cadastrar conta");
        System.out.println("(3) Atualizar conta");
        System.out.println("(4) Excluir conta");
        System.out.print("---> ");
        int op = sc.nextInt();

        switch (op) {
            case 1: consultarConta();
                break;
            case 2: cadastrarConta();
                break;
            case 3: atualizarConta();
                break;
            case 4: excluirConta();
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    private void cadastrarConta(){
        System.out.println("----CADASTRO DE CONTA----");
        System.out.print("Número da conta: ");
        Integer nro_conta = sc.nextInt();
        System.out.print("Tipo da Conta: ");
        sc.nextLine();
        TipoConta tipoConta = TipoConta.valueOf(sc.nextLine().replaceAll("\\s+", "_").toUpperCase());
        LocalDateTime dataAbertura = LocalDateTime.now();

        conta.cadastrarConta(nro_conta, 0001, tipoConta, dataAbertura, 0.0);
        String tipoContaSql = tipoConta.toString();
        Timestamp dataAberturaSql = Timestamp.valueOf(dataAbertura);

        System.out.print("Informar o CPF que essa conta será vinculada: ");
        String cpf_cliente = sc.nextLine();

        try (Connection connection = conexao.getConnection()) {
            String sql = "INSERT INTO conta (nro_conta, agencia, tipo_conta, data_abertura, saldo, CPF_cliente) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, conta.getNro_conta());
            preparedStatement.setInt(2, conta.getAgencia());
            preparedStatement.setString(3, tipoContaSql);
            preparedStatement.setTimestamp(4, dataAberturaSql);
            preparedStatement.setDouble(5, conta.getSaldo());
            preparedStatement.setString(6, cpf_cliente);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Conta cadastrada com sucesso!");
            } else {
                System.out.println("Falha ao cadastrar a conta.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(conta);
    }

    private void atualizarConta() {
        System.out.println("Informe o nº da conta que deseja atualizar");
        int conta = sc.nextInt();

        try (Connection connection = conexao.getConnection()) {
            String sql = "SELECT * FROM conta WHERE nro_conta = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, conta);
            retorno = preparedStatement.executeQuery();

            if (retorno.next()) {
                System.out.print("Novo nº de conta: ");
                int newConta = sc.nextInt();
                System.out.println("Novo tipo de conta [POUPANÇA, CONTA_CORRENTE CONTA_SALARIO]");
                TipoConta tipoConta = TipoConta.valueOf(sc.next().replaceAll("\\s+", "_").toUpperCase());
                String newTipoConta = tipoConta.toString();

                String update = "UPDATE conta SET nro_conta = ?, tipo_conta = ? WHERE nro_conta = ?";
                preparedStatement = connection.prepareStatement(update);
                preparedStatement.setInt(1, newConta);
                preparedStatement.setString(2, newTipoConta);
                preparedStatement.setInt(3, conta);
                int rowsAffected = preparedStatement.executeUpdate();

                if(rowsAffected > 0){
                    System.out.println("Conta atualizada com sucesso!");
                } else {
                    System.out.println("Falha ao atualizar conta!");
                }
            } else {
                System.out.println("Falha ao localizar a conta.");
                atualizarConta();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void consultarConta() {
        System.out.println("Informe o nº da conta que deseja consultar");
        int conta = sc.nextInt();

        try (Connection connection = conexao.getConnection()) {
            String sql = "SELECT nro_conta, agencia, tipo_conta, data_abertura, saldo, CPF_cliente FROM conta WHERE nro_conta = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, conta);
            retorno = preparedStatement.executeQuery();

            if (retorno.next()) {
                int agencia = retorno.getInt("agencia");
                String tipoConta = retorno.getString("tipo_conta");
                String dataAbertura = retorno.getString("data_abertura");
                double saldo  = retorno.getDouble("saldo");
                String cpfCliente = retorno.getString("CPF_cliente");

                System.out.println("| " + conta + " | " + agencia + " | " + tipoConta + " | " + dataAbertura + " | " + saldo + " | " + cpfCliente + " |");

            } else {
                System.out.println("Falha ao localizar a conta.");
                consultarConta();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void excluirConta(){
        System.out.println("Informe o nº da conta que deseja excluir");
        int conta = sc.nextInt();

        try (Connection connection = conexao.getConnection()) {
            String sql = "SELECT * FROM conta WHERE nro_conta = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, conta);
            retorno = preparedStatement.executeQuery();

            if (retorno.next()) {
                String delete = "DELETE FROM conta WHERE nro_conta = ?";
                preparedStatement = connection.prepareStatement(delete);
                preparedStatement.setInt(1, conta);
                int rowsAffected = preparedStatement.executeUpdate();

                if(rowsAffected > 0){
                    System.out.println("Conta excluída com sucesso!");
                } else {
                    System.out.println("Falha ao excluir conta!");
                }
            } else {
                System.out.println("Falha ao localizar a conta.");
                excluirConta();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void extrato(String login) {
        UsuarioDao usuarioDao = new UsuarioDao();
        String cpfConta = usuarioDao.retornaCpfUser(login);

        try {
            Connection connection = conexao.getConnection();
            String sql = "SELECT cc.nome, c.nro_conta conta_origem, c.agencia, t.tipo_transacao, t.data_hora, t.valor_transacao, t.nro_conta_destino destinatario FROM transacao t, conta c, cliente cc where c.nro_conta = t.nro_conta_origem and c.CPF_cliente = cc.CPF and c.CPF_cliente = ? order by t.data_hora desc;\n";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cpfConta);
            retorno = preparedStatement.executeQuery();

            System.out.println("Extrato de Transações:");
            System.out.printf("%-20s %-15s %-10s %-17s %-22s %-17s %-15s\n", "Nome", "Nro Conta", "Agência", "Tipo Transação", "Data e Hora", "Valor Transação", "Destinatário");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------");

            while (retorno.next()) {
                String nome = retorno.getString("nome");
                int nroConta = retorno.getInt("conta_origem");
                String agencia = retorno.getString("agencia");
                String tipoTransacao = retorno.getString("tipo_transacao");
                String dataHora = retorno.getString("data_hora");
                double valorTransacao = retorno.getDouble("valor_transacao");
                int nroContaDestino = retorno.getInt("destinatario");

                System.out.printf("%-20s %-15d %-10s %-17s %-22s %-17.2f %-15d\n", nome, nroConta, agencia, tipoTransacao, dataHora, valorTransacao, nroContaDestino);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double retornaSaldo(String CPF_cliente) {
        ConexaoDao conexaoDao = new ConexaoDao();
        ResultSet retorno = null;
        double saldo = 0;

        try {
            Connection conexao = conexaoDao.getConnection();
            String consulta = "SELECT saldo FROM conta WHERE CPF_cliente = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(consulta);
            preparedStatement.setString(1, CPF_cliente);
            retorno = preparedStatement.executeQuery();

            if (retorno.next()) {
                saldo =  retorno.getDouble("saldo");
            } else {
                System.out.println("Nº de conta não encontrado, informe um existe no banco de dados!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return saldo;
    }

    public int retornaNroConta(String CPF_cliente) {
        ConexaoDao conexaoDao = new ConexaoDao();
        ResultSet retorno = null;
        int nro_conta = 0;

        try {
            Connection conexao = conexaoDao.getConnection();
            String consulta = "SELECT nro_conta FROM conta WHERE CPF_cliente = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(consulta);
            preparedStatement.setString(1, CPF_cliente);
            retorno = preparedStatement.executeQuery();

            if (retorno.next()) {
                nro_conta =  retorno.getInt("nro_conta");
            } else {
                System.out.println("Nº de conta não encontrado, informe um existe no banco de dados!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nro_conta;
    }

    public void atualizaSaldo(int nro_conta, double valor) {
        ConexaoDao conexaoDao = new ConexaoDao();

        try {
            Connection conexao = conexaoDao.getConnection();
            String sql = "UPDATE conta SET saldo = ? WHERE nro_conta = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            preparedStatement.setDouble(1, valor);
            preparedStatement.setInt(2, nro_conta);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Saldo atualizado com sucesso!");
            } else {
                System.out.println("Nº de conta não encontrado, informe um existe no banco de dados!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
