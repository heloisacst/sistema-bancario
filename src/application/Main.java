package application;

import services.CadastroProdutos;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("-----SISTEMA BANCÁRIO-----");
        CadastroProdutos cadastroProdutos = new CadastroProdutos();
        cadastroProdutos.cadastroProudtos();

        sc.close();

    }
}