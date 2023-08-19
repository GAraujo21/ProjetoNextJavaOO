package br.edu.cesarschool.next.oo.apresentacao;

import java.util.List;
import java.util.Scanner;

import br.edu.cesarschool.next.oo.entidade.ContaCorrente;
import br.edu.cesarschool.next.oo.entidade.ContaPoupanca;
import br.edu.cesarschool.next.oo.negocio.MediatorContaCorrente;


public class TelaContaCorrente {
    private MediatorContaCorrente mediatorContaCorrente = new MediatorContaCorrente();

    Scanner sc = new Scanner(System.in);

    public TelaContaCorrente() {
    }

    public void iniciarTela(){
        int escolha = 0;
        do {
            System.out.println("Escolha uma opção, digitando o número que vem a frente: ");
            System.out.println("1 - Incluir conta");
            System.out.println("2 - Creditar");
            System.out.println("3 - Debitar");
            System.out.println("4 - Buscar conta");
            System.out.println("5 - Gerar relatório");
            System.out.println("6 - Excluir Conta");
            System.out.println("7 - Excluir contas com saldo zerado");
            System.out.println("8 - ENCERRAR");
            escolha = sc.nextInt();
            switch (escolha){
                case 1:
                    incluir();
                    break;
                case 2:
                    creditar();
                    break;
                case 3:
                    debitar();
                    break;
                case 4:
                    buscar();
                    break;
                case 5:
                    gerarRelatorioGeral();
                    break;
                case 6:
                    excluir();
                    break;
                case 7:
                    excluirContasZeradas();
                    break;
                case 8:
                    System.out.println("Operação encerrada!");
                    break;
            }
        }while (escolha != 8);
    }

    private void incluir(){
        System.out.println("Por favor, insira os dados desta conta: ");
        System.out.println("O núemro da conta deve conter no mínimo 5 números e no máximo 8");
        System.out.println("Digite o número da conta: ");
        String numero = sc.next();
        System.out.println("Insira o Saldo da conta: ");
        double saldo = sc.nextDouble();
        System.out.println("Qual o nome do titular da conta? ");
        String nomeCorrentista = sc.next();

        System.out.println("");
        System.out.println("A conta a ser criada, é uma conta corrente ou conta poupana? ");
        System.out.println("Digite:\n 1 - Conta Corrente\n 2 - Conta Poupança");
        int escolha = sc.nextInt();

        if(escolha == 1){
            ContaCorrente conta = new ContaCorrente(numero, saldo, nomeCorrentista);
            String resultado = mediatorContaCorrente.incluir(conta);
            if (resultado == null) {
                System.out.println("Inclusão realizada com sucesso!");
            } else {
                System.out.println(resultado);
            }

        }else if(escolha == 2){
            System.out.println("Qual o percentual bônus da conta? ");
            double percentualBonus = sc.nextDouble();
            ContaPoupanca conta = new ContaPoupanca(numero, saldo, nomeCorrentista, percentualBonus);
            
            String resultado = mediatorContaCorrente.incluir(conta);
            if(resultado == null){
                System.out.println("Inclusão realizada com sucesso!");
            }else{
                System.out.println(resultado);
            }

        }else{
            System.out.println("Opção inválida");
        }
    }

    private void creditar(){
        System.out.println("Digite o número da conta: ");
        String numero = sc.next();
        System.out.println("Digite o valor a ser creditado: ");
        double valor = sc.nextDouble();

        String resultado = mediatorContaCorrente.creditar(valor, numero);
        if (resultado == null){
            System.out.println("Sucesso no creditar");
        }else{
            System.out.println(resultado);
        }
    }


    private void debitar(){
        System.out.println("Digite o número da conta: ");
        String numero = sc.next();
        System.out.println("Digite o valor a ser debitado: ");
        double valor = sc.nextDouble();

        String resultado = mediatorContaCorrente.debitar(valor, numero);
        if (resultado == null){
            System.out.println("Sucesso no debitar");
        }else{
            System.out.println(resultado);
        }
    }

    private void buscar(){
        System.out.println("Digite o número da conta: ");
        String numero = sc.next();

        ContaCorrente conta = mediatorContaCorrente.buscar(numero);
        if (conta == null){
            System.out.println("Conta não existente");
        }else{
            System.out.println(conta);
        }
    }

    public void excluir(){
        System.out.println("Digite o número da conta que deseja excluir: ");
        String numero = sc.next();

        String mensagem = mediatorContaCorrente.excluir(numero);
        if (mensagem == null) {
            System.out.println("Conta excluída com sucesso");
        } else {
            System.out.println(mensagem);
        }
    }

    public void excluirContasZeradas(){
        
    }

    private void gerarRelatorioGeral(){
        List<ContaCorrente> listaContas = mediatorContaCorrente.gerarRelatorioGeral();
        for(ContaCorrente conta: listaContas){
            if(conta instanceof ContaPoupanca){
                ContaPoupanca contaP = (ContaPoupanca)conta;
                System.out.println(contaP.toString()); 
            } else {
                System.out.println(conta.toString());
            } 
        }
    }
}
