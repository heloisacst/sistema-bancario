package view;

import dao.ClienteDao;
import dao.ContaDao;
import dao.GerenteDao;
import dao.TransacaoDao;
import dao.*;
import enums.TipoUsuario;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UsuarioDao usuarioDao = new UsuarioDao();
        String retorno = null;

        System.out.println("---------SISTEMA BANCÁRIO----------");
        System.out.println("(1) Fazer login");
        System.out.println("(2) Cadastrar novo usuário");
        System.out.print("--> ");
        int op = sc.nextInt();
        sc.nextLine();

        switch (op) {
            case 1:
                do {
                    System.out.print("Usuário: ");
                    String user = sc.nextLine();
                    System.out.print("Senha: ");
                    String senha = sc.nextLine();

                    retorno = usuarioDao.validaUsuario(user, senha);

                } while (retorno == null);

                TipoUsuario tipoUsuario = TipoUsuario.GERENTE;
                String tipoUsuarioStr = tipoUsuario.name();

                if (retorno.equals(tipoUsuarioStr)) {

                    int op1;

                    do {
                        System.out.println("O que deseja fazer? (Digite o número da opção desejada)");
                        System.out.println("(1)- Administrar Gerentes");
                        System.out.println("(2)- Administrar Clientes");
                        System.out.println("(3)- Administrar Contas");
                        System.out.println("(4)- Administrar Usuários");
                        System.out.println("(5)- Gerar Relatórios");
                        System.out.println("(0)- Sair sistema");

                        System.out.print("--> ");
                        op1 = sc.nextInt();


                            switch (op1) {
                                case 1:
                                    GerenteDao administrarGerente = new GerenteDao();
                                    administrarGerente.administrarGerente();
                                    break;
                                case 2:
                                    ClienteDao administrarCliente = new ClienteDao();
                                    administrarCliente.administrarCliente();
                                    break;
                                case 3:
                                    ContaDao administrarConta = new ContaDao();
                                    administrarConta.administrarConta();
                                    break;
                                case 4:
                                    usuarioDao.administrarUsuario();
                                    break;
                                case 5: RelatoriosDao relatoriosDao = new RelatoriosDao();
                                        relatoriosDao.gerarRelatorios();
                                case 0: System.out.println("Saindo do Sistema...");
                                        break;
                                default:
                                    System.out.println("Opção inválida!");
                                    break;
                            }
                    }while( op1 != 0);

                } else {
                    System.out.println("O que deseja fazer? (Digite o número da opção desejada)");
                    System.out.println("(1)- Fazer uma transação");
                    System.out.println("(2)- Consultar Saldo");
                    System.out.print("--> ");
                    int op1 = sc.nextInt();

                    switch (op1) {
                        case 1:

                            TransacaoDao transacaoDao = new TransacaoDao();
                            transacaoDao.efetuarTransacao();
                            break;
                        case 2:
                            sc.nextLine();
                            System.out.println("Digite seu cpf: ");
                            System.out.print("--> ");
                            String cpf = sc.nextLine();
                            Double saldo = new ContaDao().retornaSaldo(cpf);
                            System.out.print("Saldo: " + saldo);
                            break;
                        default:
                            System.out.println("Opção inválida!");
                            break;
                    }

                    sc.close();

                }
                break;

            case 2: usuarioDao.cadastrarUsuario();
                break;

            default:
                System.out.println("Opção inválida");
                break;
        }

    }
}
