package br.edu.cesarschool.next.oo.dao;

import java.io.Serializable;

import br.edu.cesarschool.next.oo.entidade.ContaCorrente;
import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;

public class DAOContaCorrente{
   private DAOGenerico daoGenerico = new DAOGenerico(ContaCorrente.class);
   CadastroObjetos cadastro = new CadastroObjetos(ContaCorrente.class);

   public boolean incluir(ContaCorrente conta){
      return daoGenerico.incluir(conta);
   }

   public boolean alterar(ContaCorrente conta){
      return daoGenerico.alterar(conta);
   }

   public ContaCorrente buscar(String numero){
      return (ContaCorrente)daoGenerico.buscar(numero);
   }

   public ContaCorrente[] buscarTodos(){
      //Serializable[] rets = daoGenerico.buscarTodos();
      Serializable[] rets = cadastro.buscarTodos(ContaCorrente.class);
      ContaCorrente[] conta = new ContaCorrente[rets.length]; 
      for(int i=0; i<rets.length; i++) {
			conta[i] = (ContaCorrente)rets[i];
		}
		return conta;
   }

   public boolean excluir(String numero){
        return daoGenerico.excluir(numero);
   }
}
