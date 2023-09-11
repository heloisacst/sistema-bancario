package view;

import dao.ClienteDao;
import dao.GerenteDao;
import dao.ProdutosDao;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("-----SISTEMA BANCÁRIO-----");
        System.out.println("O que deseja fazer? (Digite o número da opção desejada)");
        System.out.println("(1)- Administrar Produtos");
        System.out.println("(2)- Administrar Gerentes");
        System.out.println("(3)- Administrar Clientes");
        System.out.print("--> ");
        int op = sc.nextInt();

        switch (op) {
            case 1: ProdutosDao administrarProdutos = new ProdutosDao();
                    administrarProdutos.administrarProdutos();
                    break;
            case 2: GerenteDao cadastrarGerente = new GerenteDao();
                    cadastrarGerente.cadastrarGerente();
                    break;
            case 3: ClienteDao administrarCliente = new ClienteDao();
                    administrarCliente.administrarCliente();
            default:
                System.out.println("Opção inválida!");
        }

        sc.close();

    }
}