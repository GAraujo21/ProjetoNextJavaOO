package br.edu.cesarschool.next.oo.entidade;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class RegistroIdentificavel implements Serializable{
    private LocalDateTime dataHoraCriacao;

    public RegistroIdentificavel() {
    }

    public RegistroIdentificavel(LocalDateTime dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public LocalDateTime getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public abstract String obterChave();

    public int obterTempoDeCriacao(){
        LocalDateTime dataAtual = LocalDateTime.now();
        int dataAtualEmDia = dataAtual.getDayOfYear();
        int dataHoraCriacaoEmDia = dataHoraCriacao.getDayOfYear();
        return dataAtualEmDia - dataHoraCriacaoEmDia;
     }
}
