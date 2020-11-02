package Venda;

import java.util.List;

import Estoque.*;

public class ItemVendido extends Produto
{	
	private Integer pedido;
	private Produto item;
	private Double quantidade;
	private static Integer codigo;
	private Double valor;
	public ItemVendido()
	{
		this.pedido = 0;
		this.item = new Produto();
		this.quantidade = 0.0;
	}
	
	public ItemVendido(Integer pedido)
	{
		this.pedido = pedido;
		this.item = new Produto();
		this.quantidade = 0.0;
	}
	
	public Integer gerarCodigo(){
		return codigo++;
	}
	
	public Integer getPedido() {
		return pedido;
	}

	public void setPedido(Integer pedido) {
		this.pedido = pedido;
	}

	public Produto getItem() {
		return item;
	}

	public void setItem(Produto item) {
		this.item = item;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
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
		boolean retorno = true;
		
		if(this.pedido != null && this.pedido > 0)
			if(!this.item.isNull())
				if(this.quantidade == Double.NaN && this.quantidade <= 0.0)
					retorno = false;
		
		return retorno;
	}

	
	public Double getValor() {
		return this.valor;
	}

	
	public void setValor(Double valor) {
		this.valor = valor;
	}

}