package br.edu.cesarschool.next.oo.negocio;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import br.edu.cesarschool.next.oo.dao.DAOContaCorrente;
import br.edu.cesarschool.next.oo.entidade.ContaCorrente;
import br.edu.cesarschool.next.oo.entidade.ContaPoupanca;

public class MediatorContaCorrente {
    private DAOContaCorrente daoContaCorrente = new DAOContaCorrente();

    public MediatorContaCorrente() {
    }

    public MediatorContaCorrente(DAOContaCorrente daoContaCorrente){
        this.daoContaCorrente = daoContaCorrente;
    }

    public boolean stringNulaOuVazia(String dado){
        if (dado == null || dado.trim().equals("")){
            return true;
        } else {
            return false;
        }
    }

    public String incluir(ContaCorrente conta){
        if (conta == null){
            return "Conta não encontrada!";
        }else if (stringNulaOuVazia(conta.getNumero())){
            return "Númeoro da conta deve ser diferente de nulo ou branco";
        }else if (conta.getNumero().length() < 5 && conta.getNumero().length() > 8){
            return "A conta deve conter mais de 5 númeors e ter menos que 8 números!";
        }else if(conta.getSaldo() < 0){
            return "O saldo é negativo!";
        }else if (stringNulaOuVazia(conta.getNomeCorrentista())){
            return "Nome de cadastro do usuário está em branco ou nulo";
        }else if (conta.getNomeCorrentista().length() > 60){
            return "Nome do usuário excede a quantidade de caracteres";
        }else if(conta instanceof ContaPoupanca){
            ContaPoupanca contaP = (ContaPoupanca)conta;
            if (contaP.getPercentualBonus() < 0){
                return "Conta Poupansa sem percentual de bônus";
            }else {
                contaP.setDataHoraCriacao(LocalDateTime.now());
                boolean retorno = daoContaCorrente.incluir(contaP);
                if (!retorno) {
                    return "A conta já existe";
                } else {
                    return null;
                }
            }
        }else {
            conta.setDataHoraCriacao(LocalDateTime.now());
            boolean retorno = daoContaCorrente.incluir(conta);
                if (!retorno) {
                    return "A conta já existe";
                } else {
                    return null;
                }
        }
    }

    public String creditar(double valor, String numero){
        if (valor < 0){
            return "Não é possível realizar a operação, pois o valor inserido foi negativo.";
        }else if(stringNulaOuVazia(numero)){
            return "Número da conta inválido.";
        }else {
            ContaCorrente contaBusca = daoContaCorrente.buscar(numero);
            if(contaBusca == null){
                return "Número da conta não existente";
            }else{
                contaBusca.creditar(valor);
                daoContaCorrente.alterar(contaBusca);
                return null;
            }

        }
           
    }

    public String debitar(double valor, String numero){
        if (valor < 0){
            return "O valor a ser debitado é inválido";
        }else if(stringNulaOuVazia(numero)){
            return "Número da conta inválido.";
        }else{
            ContaCorrente contaBusca = daoContaCorrente.buscar(numero);
            if (contaBusca == null) {
                return "O número da conta não existe";
            } else{
                double valorComCPMF = valor + (valor * contaBusca.obterAliquotaCpmf());
                if(contaBusca.getSaldo() < valorComCPMF){
                    return "Saldo insuficiente para realizar o débito";
                }else{
                    contaBusca.debitar(valorComCPMF);
                    daoContaCorrente.alterar(contaBusca);
                    return null;
                }
            }
        }
    }

    public ContaCorrente buscar(String numero){
        if (stringNulaOuVazia(numero)) {
            return null;
        } else {
            return daoContaCorrente.buscar(numero);
        }
    }

    public List<ContaCorrente> gerarRelatorioGeral(){
        ContaCorrente[] contas = daoContaCorrente.buscarTodos();
        List<ContaCorrente> listaContas = Arrays.asList(contas);
		listaContas.sort(new ComparadorContaCorrenteSaldo());
		return listaContas;
    }

    public String excluir(String numero){
		if (stringNulaOuVazia(numero)){
			return "Produto não informado";
		}else {
			daoContaCorrente.excluir(numero);
			return null;
		}
	}
}

