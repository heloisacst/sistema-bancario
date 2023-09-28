package dao;

import enums.TipoUsuario;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UsuarioDao {
    Scanner sc = new Scanner(System.in);
    ConexaoDao conexao = new ConexaoDao();
    Usuario usuario = new Usuario();
    public ClienteDao clienteDao = new ClienteDao();
    ResultSet retorno = null;

    public void administrarUsuario() {
        System.out.println("O que deseja fazer? (Digite o número da opção desejada)");
        System.out.println("(1) Cadastrar um usuário");
        System.out.print("---> ");
        int op = sc.nextInt();

        switch (op) {
            case 1: cadastrarUsuario();
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    public void cadastrarUsuario() {

        String tipoUsuarioStr;
        String cpf = null;
        System.out.println("----CADASTRO DE USUÁRIO----");
        System.out.println("Informe o CPF: ");
        cpf = sc.nextLine();
        tipoUsuarioStr = clienteDao.retornaTipoUsuario(cpf);

        if(tipoUsuarioStr == null){
            System.out.println("Usuário não é cliente");
            System.exit(0);
        }

        System.out.print("Informe o login desejado: ");
        String login = sc.nextLine();
        System.out.print("Informe a senha: ");
        String senha = sc.nextLine();
        TipoUsuario tipoUsuario = TipoUsuario.valueOf(tipoUsuarioStr);

        usuario.cadastrarUsuario(login, senha, tipoUsuario);

        try (Connection connection = conexao.getConnection()) {
            String sql = "INSERT INTO usuario (login, senha, cpf_usuario, tipo_usuario) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, usuario.getLogin());
            preparedStatement.setString(2, usuario.getSenha());
            preparedStatement.setString(3, cpf);
            preparedStatement.setString(4, tipoUsuarioStr);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Usuário cadastrado com sucesso!");
            } else {
                System.out.println("Falha ao cadastrar o usuário.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (retorno != null) {
                try {
                    retorno.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String validaUsuario(String user, String senha) {
        String tipoUsuario = null;

        try {
            Connection connection = conexao.getConnection();
            String sql = "SELECT tipo_usuario FROM usuario WHERE login = ? AND senha = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, senha);
            retorno = preparedStatement.executeQuery();

            if (retorno.next()) {
                tipoUsuario = retorno.getString("tipo_usuario");
                System.out.println(retorno.getString("tipo_usuario"));
            } else {
                System.out.println("Usuário ou senha incorreta.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (retorno != null) {
                try {
                    retorno.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return tipoUsuario;
    }

    public String retornaCpfUser(String login){
        String cpf = null;
        try {
            Connection connection = conexao.getConnection();
            String sql = "SELECT cpf_usuario FROM usuario WHERE login = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            retorno = preparedStatement.executeQuery();

            if (retorno.next()) {
                cpf = retorno.getString("cpf_usuario");
            } else {
                System.out.println("Usuário ou senha incorreta.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cpf;
    }

}
