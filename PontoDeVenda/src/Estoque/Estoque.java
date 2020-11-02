package Estoque;

import java.util.List;

import Principal.TratamentoDeExcecao;

public class Estoque /*extends Loja*/
{
	private Integer codigo;
	private Produto produto;
	private Loja loja;
	private Double volume;
	//private Double volumeReservado;
	
	public Estoque()
	{
		this.loja = new Loja();
		this.produto = new Produto();
		this.codigo = 0;
		this.volume = 0.0;
		//this.volumeReservado = 0.0;
	}
	
	public Estoque(Integer codigo)
	{		
		this.loja = new Loja();
		this.produto = new Produto();
		this.codigo = codigo;
		this.volume = 0.0;
		//this.volumeReservado = 0.0;
	}
	
	/**
	 * [GET][SET] METODO DE ACESSO AO CODIGO IDENTIFICADOR DO ESTOQUE
	 * 
	 */
	public Integer getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
		
	/**
	 * [GET][SET] METODO DE ACESSO AO PRODUTO PERTENCENTE AO ESTOQUE
	 * 
	 */
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) 
	{		
		try
		{
			if(produto == null || produto.isNull())
				throw new TratamentoDeExcecao("Produto nao foi informado, favor informar.");		
			
			this.produto = produto;
		}
		catch(TratamentoDeExcecao e)
		{
			throw e;
		}
	}

	/**
	 * [GET][SET] METODO DE ACESSO A LOJA QUE CONTEM O ESTOQUE
	 * 
	 */
	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) 
	{		
		try
		{
			if(loja == null || loja.isNull())
				throw new TratamentoDeExcecao("Loja nao foi informada, favor informar.");		
			
			this.loja = loja;
		}
		catch(TratamentoDeExcecao e)
		{
			throw e;
		}
	}

	/**
	 * [GET][SET] METODO DE ACESSO AO VOLUME ESTIPULADO PARA MERCADORIA NO ESTOQUE
	 * 
	 */
	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	
	//------------------------------------------------------------------------------------
	
	
	/**
	 * 
	 * ATENCAO: SERA COLOCADO NA TELA PARA O USUARIO OS DADOS DA ESTOQUE
	 * SOMENTE SE OS VALORES ESTIVEREM CORRETOR CONFORME A FUNCAO (isNull).
	 * ABAIXO.
	 * 
	 */
	public void print()
	{
		if(!isNull())
		{
			System.out.println("  | COD.(" + this.codigo +")"
							  + " | " + this.produto.getDescricao()					          
							  + " | " + this.volume
							  + " | " + this.produto.getPreco()
							  + " | " + this.loja.getRazao() + ".");
		}
		else System.out.println("Cadastro com pendencias, favor verificar!");
	}

	/**
	 * 
	 * ATENCAO: SERA VERIFICADO SE OS CAMPOS DO ESTOQUE ESTAO VAZIOS OU NAO ESTAO PREENCHIDOS
	 * 
	 * Somente vai retornar 'False' quando de fato a Loja estiver null ou
	 * com um valor invalido. Caso contrÃ¡rio retorna o default inicial 'True'.
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
			if(this.loja == null || this.loja.isNull())
				 return true;
			if(this.produto == null || this.produto.isNull())
				 return true;
			else return false;
			
		}
		catch (NullPointerException e) {
			return true;
		}
		catch (TratamentoDeExcecao e) {
			return true;
		}
	}

	/**
	 * 
	 * ATENCAO: POSSUI UMA DIFERENCA ENTRE ESTA FUNCAO PARA A FUNCAO (Equals ==).
	 * 
	 * A funcao abaixo verifica se na lista possui um estoque com mesmo Produto
	 * e a mesma Loja
	 * 
	 * Somente vai retornar 'True' quando de fato encontrar um estoque com 
	 * mesmo 'Produto' e 'Loja'. Caso contrario retorna o default inicial 'False'.
	 * 
	 * */
	public boolean validarExistencia(List<Estoque> listEstoque)
	{
		try
		{
			boolean retorno = false;
			
			if(!isNull())
			{
				if(listEstoque != null && !listEstoque.isEmpty() && listEstoque.size() > 0)
				{
					for (Estoque estoque : listEstoque) 
					{
						if(!estoque.isNull())
						{
							if(this.loja == estoque.getLoja() && this.produto == estoque.getProduto())						
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

	
	
	
	public boolean retirarVolumeEstoque(Double valorDesejado)
	{
		boolean retorno = false;
		
		if(!isNull())
		{
			if(valorDesejado != Double.NaN && valorDesejado > 0.0)
				this.volume -= valorDesejado;
		}
		
		return retorno;
	}
	
	
	public boolean validarEstoque()
	{
		try
		{			
			if(this.volume == Double.NaN)
				return false;
			else if(this.volume <= 0.0)			
				return false;
			else return true;
		}
		catch (NullPointerException e) {
			return false;
		}
	}
	
	public boolean validarEstoque(Double valorDesejado)
	{
		boolean retorno = false;
		
		if(!isNull())
		{
			if(valorDesejado != Double.NaN && valorDesejado > 0.0)
				if(this.volume >= valorDesejado)
					return true;
		}
		
		return retorno;
	}
	
}