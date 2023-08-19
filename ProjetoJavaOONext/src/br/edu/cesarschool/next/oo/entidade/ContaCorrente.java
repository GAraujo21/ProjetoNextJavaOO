package br.edu.cesarschool.next.oo.entidade;

public class ContaCorrente extends Conta{
    private static final long serialVersionUID = 1L;
    private String numero;
    private Double saldo;
    private String nomeCorrentista;
    
    public ContaCorrente() {
    }

    public ContaCorrente(String numero, Double saldo, String nomeCorrentista) {
        super();
        this.numero = numero;
        this.saldo = saldo;
        this.nomeCorrentista = nomeCorrentista;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Double getSaldo() {
        return saldo;
    }

    public String getNomeCorrentista() {
        return nomeCorrentista;
    }

    public void setNomeCorrentista(String nomeCorrentista) {
        this.nomeCorrentista = nomeCorrentista;
    }

    public void creditar(double valor){
        this.saldo += valor;
    }

    @Override
    public void debitar(double valor){
        saldo -= (valor + valor * obterAliquotaCpmf());
    }

    @Override
    public String toString() {
        return "Número da conta corrente:" + numero + ", Saldo=" + saldo + ", Nome do titular da conta=" + nomeCorrentista + ".";
    }
    
    @Override
    public String obterChave(){
        return this.numero;
    }

    @Override
    public double obterAliquotaCpmf(){
        return 0.003;
    }
}

