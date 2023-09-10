package view;

import dao.GerenteDAO;
import dao.ProdutosDAO;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("-----SISTEMA BANCARIO-----");
        System.out.println("O que deseja fazer? (Digite o número da opção desejada)");
        System.out.println("(1)- Administrar Produtos");
        System.out.println("(2)- Administrar Gerentes");
        System.out.print("--> ");
        int op = sc.nextInt();

        switch (op) {
            case 1: ProdutosDAO produtosServices = new ProdutosDAO();
                    produtosServices.administrarProdutos();
                    break;
            case 2: GerenteDAO cadastrarGerente = new GerenteDAO();
                    cadastrarGerente.cadastrarGerente();
                    break;
            default:
                System.out.println("Opção inválida!");
        }

        sc.close();

    }
}