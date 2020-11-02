package Pagamento;

import Venda.Cliente;

interface Pagamento 
{
/*	enum TipoPagamento 
	{
	     CHEQUE, 
	     CARTAO, 
	     DINHEIRO;
	}*/
	
	//Obtem o nome do Contato
	//TipoPagamento getTipo();
	
	//String getTipoToString();
	
	 //Obtem a informação do contato.
	//double getValor();
	
	//Obtem o nome do Contato
	//void setTipo(TipoPagamento tipo);
		
	//Obtem a informação do contato.
	//void setValor(double val);
	
	void pagamentoManual(Double total, Double valor);
	
	boolean validarPagamento(Double total, Double valor);
	//void pagamentoAutomatico(Double valor);

	//double getTroco();
	
	//void troco();
}
