package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDao {
    ConexaoDao conexao = new ConexaoDao();
    ResultSet retorno = null;
    public String validaUsuario(String user, String senha){
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

}
