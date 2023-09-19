package view;

import dao.ClienteDao;
import dao.ContaDao;
import dao.GerenteDao;
import dao.TransacaoDao;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

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
            case 3: ContaDao administrarConta = new ContaDao();
                    administrarConta.administrarConta();
            case 4: TransacaoDao transacaoDao = new TransacaoDao();
                    transacaoDao.efetuarTransacao();
            default:
                System.out.println("Opção inválida!");
        }

        sc.close();

    }
}