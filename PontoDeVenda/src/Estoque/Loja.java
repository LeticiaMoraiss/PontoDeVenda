package Estoque;

import java.util.List;

import Outros.Usuario;
import Principal.TratamentoDeExcecao;

public class Loja {

	private Integer codigo = 0;
	private String razao, logradouro;
	
	public Loja(){}
	
	public Loja(Integer codigo)
	{		
		this.codigo = codigo;
	}
	
	
	/**
	 * [GET][SET] METODO DE ACESSO AO CODIGO IDENTIFICADOR DE LOJA
	 * 
	 */
	public Integer getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * [GET][SET] METODO DE ACESSO A RAZAO SOCIAL (NOME DO ESTOQUE) DA LOJA
	 * 
	 */
	public String getRazao() {
		return razao;
	}
	
	public void setRazao(String razao) 
	{
		try
		{
			if(razao == null || razao.trim() == ""|| razao.trim().isEmpty() || razao.length() <= 0)
				throw new TratamentoDeExcecao("Razao (nome) da loja nao foi informada, favor informar.");		
			else this.razao = razao;
		}
		catch(TratamentoDeExcecao e)
		{
			throw e;
		}
	}
		
	/**
	 * [GET][SET] METODO DE ACESSO A LOGRADOURO (LOCALIZACAO) DA LOJA
	 * 
	 */
	public String getLogradouro() {
		return logradouro;
	}
	
	public void setLogradouro(String logradouro) 
	{
		try
		{
			if(logradouro == null || logradouro.trim() == ""|| logradouro.trim().isEmpty() || logradouro.length() <= 0)
				throw new TratamentoDeExcecao("Logradouro (localizacao) da loja nao foi informada, favor informar.");		
			else this.logradouro = logradouro;
		}
		catch(TratamentoDeExcecao e)
		{
			throw e;
		}
	}
	
	
	//------------------------------------------------------------------------------------
	
	
	/**
	 * 
	 * ATENCAO: SERA COLOCADO NA TELA PARA O USUARIO OS DADOS DA LOJA
	 * SOMENTE SE OS VALORES ESTIVEREM CORRETOR CONFORME A FUNCAO (isNull).
	 * ABAIXO.
	 * 
	 */
	public void print()
	{
		if(!isNull())
		{
			System.out.println("  |  Codigo (" + this.codigo+")  | "
									   + " " + this.razao
								   	  + ", " + this.logradouro + ".");
		}
		else System.out.println("Cadastro com pendencias, favor verificar!");
	}

	/**
	 * 
	 * ATENCAO: SERÃ� VERIFICADO SE OS CAMPOS DA LOJA ESTAO VAZIOS OU NAO ESTAO PREENCHIDOS
	 * 
	 * Somente vai retornar 'False' quando de fato a Loja estiver null ou
	 * com um valor invalido. Caso contrario retorna o default inicial 'True'.
	 * 
	 */
	public boolean isNull()
	{
		try
		{			
			if(this.codigo == null)
				 return true;
			else if(this.codigo <= 0)
				 return true;
			else if(this.razao.equals(null)|| this.razao == null || this.logradouro.equals(null)|| this.logradouro == null)
				return true;
			else if(this.razao.trim().isEmpty() || this.razao.trim().length() <= 0 || this.logradouro.trim().isEmpty() || this.logradouro.trim().length() <= 0)
				return true;
			else return false;
		}
		catch (NullPointerException e) {
			return true;
		}
	}
	
	/**
	 * 
	 * ATENCAO: POSSUI UMA DIFERENCA ENTRE ESTA FUNCAO PARA A FUNCAO (Equals == ).
	 * 
	 * A funÃ§Ã£o abaixo verifica se na lista possui uma loja com mesma razao
	 * e o mesmo logradouro.
	 * 
	 * Somente vai retornar 'True' quando de fato encontrar um produto com a 
	 * mesma 'razao' e 'logradouro'. Caso contrario retorna o default inicial 'False'.
	 * 
	 * */
	public boolean validarExistencia(List<Loja> listLoja)
	{
		try
		{
			boolean retorno = false;
			
			if(!isNull())
			{
				if(listLoja != null && !listLoja.isEmpty() && listLoja.size() > 0)
				{
					for (Loja loja : listLoja) 
					{
						if(!loja.isNull())
						{
							if(loja.getRazao().equals(this.razao) && loja.getLogradouro().equals(this.logradouro))
							{							
								retorno = true;
								break;
							}
						}
					}
				}
				
			}
			
			return retorno;
		}
		catch (NullPointerException e) {
			return true;
		}
		catch (TratamentoDeExcecao e) {
			return true;
		}
	}
	
	
	
}