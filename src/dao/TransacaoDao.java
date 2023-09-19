package dao;

import enums.TipoTransacao;
import model.Transacao;

import java.util.Scanner;

public class TransacaoDao {
    Scanner sc = new Scanner(System.in);
    ConexaoDao conexaoDao = new ConexaoDao();
    Transacao transacao = new Transacao();

    public void efetuarTransacao(){
        System.out.println("Informe qual operação será realizada | [DEPOSITO, SAQUE, ENVIAR]");
        TipoTransacao tipoTransacao = TipoTransacao.valueOf(sc.next());
        System.out.println("Informe o seu CPF: ");
        sc.nextLine();
        String cpf = sc.nextLine();

        if(tipoTransacao == TipoTransacao.DEPOSITO){

        }
        System.out.println("Informe qual conta receberá a operação de " + tipoTransacao);

    }

}
