package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDao {

        private Connection con;

        public ConexaoDao() {

            String user = "root"; //"postgres";
            String senha = "root"; //"udesc";
            String url = "jdbc:mysql://localhost:3306/banking"; //jdbc:postgresql://localhost:5432/clinica";

            try {
                con = DriverManager.getConnection(url, user, senha);
            } catch (SQLException e) {
                System.err.println("Erro na conexão: " + e.getMessage());
                throw new RuntimeException("Erro na conexão com o banco de dados", e);
            }

        }

        public Connection getConnection() {
            return this.con;
        }

        public void closeConnection() {
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                    System.out.println("Conexão fechada com sucesso!");
                }
            } catch (SQLException e) {
                System.err.println("Erro no fechamento da conexão: " + e.getMessage());
            }

        }
    }
