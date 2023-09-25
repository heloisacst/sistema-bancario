package view;

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 1a40567 (nova feature - login)
import dao.ClienteDao;
import dao.ContaDao;
import dao.GerenteDao;
import dao.TransacaoDao;

<<<<<<< HEAD
=======
=======
import dao.*;
import enums.TipoUsuario;
>>>>>>> cb5e729 (nova feature - login)
>>>>>>> 1a40567 (nova feature - login)
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 1a40567 (nova feature - login)

        System.out.println("-----SISTEMA BANCÁRIO-----");
        System.out.println("O que deseja fazer? (Digite o número da opção desejada)");
        System.out.println("(1)- Administrar Gerentes");
        System.out.println("(2)- Administrar Clientes");
        System.out.println("(3)- Administrar Contas");
        System.out.println("(4)- Fazer uma transação");
        System.out.print("--> ");
        int op = sc.nextInt();

        switch (op) {
            case 1: GerenteDao cadastrarGerente = new GerenteDao();
                    cadastrarGerente.cadastrarGerente();
                    break;
            case 2: ClienteDao administrarCliente = new ClienteDao();
                    administrarCliente.administrarCliente();
                    break;
            case 3: ContaDao administrarConta = new ContaDao();
                    administrarConta.administrarConta();
                    break;
            case 4: TransacaoDao transacaoDao = new TransacaoDao();
                    transacaoDao.efetuarTransacao();
                    break;
            default:
                System.out.println("Opção inválida!");
                break;
        }

        sc.close();

<<<<<<< HEAD
=======
=======
        UsuarioDao usuarioDao = new UsuarioDao();
        String retorno = null;

        System.out.println("-----SISTEMA BANCÁRIO-----");
        do{
            System.out.println("Faça o seu login:");
            System.out.print("Usuário: ");
            String user = sc.nextLine();
            System.out.print("Senha: ");
            String senha = sc.nextLine();

            retorno = usuarioDao.validaUsuario(user, senha);

        }while(retorno == null);

        TipoUsuario tipoUsuario = TipoUsuario.GERENTE;
        String tipoUsuarioStr = tipoUsuario.name();

        if (retorno.equals(tipoUsuarioStr)) {
            System.out.println("O que deseja fazer? (Digite o número da opção desejada)");
            System.out.println("(1)- Administrar Gerentes");
            System.out.println("(2)- Administrar Clientes");
            System.out.println("(3)- Administrar Contas");
            System.out.print("--> ");
            int op = sc.nextInt();

            switch (op) {
                case 1:
                    GerenteDao cadastrarGerente = new GerenteDao();
                    cadastrarGerente.cadastrarGerente();
                    break;
                case 2:
                    ClienteDao administrarCliente = new ClienteDao();
                    administrarCliente.administrarCliente();
                    break;
                case 3:
                    ContaDao administrarConta = new ContaDao();
                    administrarConta.administrarConta();
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } else {
            System.out.println("O que deseja fazer? (Digite o número da opção desejada)");
            System.out.println("(1)- Fazer uma transação");
            System.out.print("--> ");
            int op = sc.nextInt();

            switch (op) {
                case 1:
                    TransacaoDao transacaoDao = new TransacaoDao();
                    transacaoDao.efetuarTransacao();
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }

            sc.close();

        }
>>>>>>> cb5e729 (nova feature - login)
>>>>>>> 1a40567 (nova feature - login)
    }
}