package br.edu.cesarschool.next.oo.entidade;

public abstract class Conta extends RegistroIdentificavel{

    public Conta(){

    }

    public abstract double obterAliquotaCpmf();

    public abstract void debitar(double valor);
}