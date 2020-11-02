package Estoque;

import java.util.List;

import Principal.TratamentoDeExcecao;

public class Produto
{
	private Integer codigo = 0;
	private String descricao;
	private Double preco;
	
	public Produto(){
		this.codigo =  0;
		this.preco =  0.0;
	}
	
	public Produto(Integer codigo)
	{		
		this.codigo = codigo;
		this.preco =  0.0;
	}
	
	/**
	 * [GET][SET] METODO DE ACESSO AO CODIGO IDENTIFICADOR DO PRODUTO
	 * 
	 */
	public Integer getCodigo() {		
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * [GET][SET] METODO DE ACESSO A DESCRICAO DO PRODUTO CADASTRADO
	 * 
	 */
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		try
		{
			if(descricao == null || descricao.trim() == ""|| descricao.trim().isEmpty() || descricao.length() <= 0)
				throw new TratamentoDeExcecao("Descricao do item nao informado, favor informar.");		
			else this.descricao = descricao;
		}
		catch(TratamentoDeExcecao e)
		{
			throw e;
		}
	}

	/**
	 * [GET][SET] METODO DE ACESSO AO PRECO DO PRODUTO CADASTRADO
	 * 
	 */
	public Double getPreco() 
	{
		if(preco == Double.NaN)	
			this.preco = 0.0;
		return preco;
	}

	public void setPreco(Double preco) 
	{
		try
		{	
			if(preco == Double.NaN)			
				throw new TratamentoDeExcecao("Valor invalido (Null), favor informa novamente.");				
			else if(preco <= 0.0)					
				throw new TratamentoDeExcecao("Preco requer Valor positivos e acima de zero, favor informa novamente.");					
			else this.preco = (Double) preco;
		}
		catch(TratamentoDeExcecao e)
		{
			this.preco = 0.0;
			throw e;
		}		
	}
	
	//------------------------------------------------------------------------------------
	
	
	/**
	 * 
	 * ATENCAO: SERA COLOCADO NA TELA PARA O USUARIO OS DADOS DO PRODUTO,
	 * SOMENTE SE OS VALORES ESTIVEREM CORRETOR CONFORME A FUNCAO (isNull).
	 * ABAIXO.
	 * 
	 */
	public void print()
	{
		if(!isNull())
		{			
			System.out.println("  | COD.(" + this.codigo +")"
					  			   + " | " + this.descricao
					  			   + " | " + this.preco + " .");
		}
		else System.out.println("Cadastro com pendencias, favor verificar!");
	}
	
	/**
	 * 
	 * ATENCAO: SERÁ VERIFICADO SE OS CAMPOS DO PRODUTO ESTAO VAZIOS OU NAO ESTAO PREENCHIDOS
	 * 
	 * Somente vai retornar 'False' quando de fato o Produto estiver null ou
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
			else if(this.descricao.equals(null)|| this.descricao == null)
				return true;
			else if(this.descricao.trim().isEmpty() || this.descricao.trim().length() <= 0)
				return true;
			else return false;
		}
		catch (NullPointerException e) {
			return true;
		}
	}	
	
	/**
	 * 
	 * ATENCAO: SERÁ VERIFICADO SE O PRODUTO POSSUI PRECO VALIDO
	 * 
	 * Somente vai retornar 'false' quando o preco do Produto estiver null ou
	 * com um valor invalido. Caso contrario retorna o 'True'.
	 * 
	 */
	public boolean validaPreco()
	{	
		try
		{			
			if(this.preco == Double.NaN)
				return false;
			else if(this.preco <= 0.0)			
				return false;
			else return true;
		}
		catch (NullPointerException e) {
			return false;
		}
	}
	
	/**
	 * 
	 * ATENCAO: POSSUI UMA DIFERENCA ENTRE ESTA FUNCAO PARA A FUNCAO (Equals == ).
	 * 
	 * A funcao abaixo verifica se na lista possui uma loja com mesma 'descricao'.
	 * 
	 * Somente vai retornar 'True' quando de fato encontrar um produto com a 
	 * mesma 'descricao'. Caso contrario retorna o default inicial 'False'.
	 * 
	 * */
	public boolean validarExistencia(List<Produto> listProduto)
	{
		try
		{
			boolean retorno = false;
			
			if(!isNull())
			{
				if(listProduto != null && !listProduto.isEmpty() && listProduto.size() > 0)
				{			
					for (Produto produto : listProduto) 
					{
						if(!produto.isNull())
						{
							if(produto.getDescricao().equals(this.descricao))
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