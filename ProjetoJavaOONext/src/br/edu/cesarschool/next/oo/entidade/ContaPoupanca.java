package br.edu.cesarschool.next.oo.entidade;

public class ContaPoupanca extends ContaCorrente{
    private Double percentualBonus;

    public ContaPoupanca() {
        super();
    }

    public ContaPoupanca(Double percentualBonus) {
        this.percentualBonus = percentualBonus;
    }

    public ContaPoupanca(String numero, double saldo, String nomeCorrentista, double percentualBonus) {
        super(numero, saldo, nomeCorrentista);
        this.percentualBonus = percentualBonus;
    }

    public Double getPercentualBonus() {
        return percentualBonus;
    }

    public void setPercentualBonus(Double percentualBonus) {
        this.percentualBonus = percentualBonus;
    }

    @Override
    public void creditar (double valor){
        super.creditar(super.getSaldo() + valor * (1 + percentualBonus/100));
    }

    @Override
    public String toString() {
        return super.toString() + " Percentual de bônus=" + percentualBonus;
    }

    @Override
    public double obterAliquotaCpmf(){
        return 0.0;
    }
}

