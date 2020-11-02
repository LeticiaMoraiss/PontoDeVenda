package Venda;

import Pagamento.*;
import Principal.TratamentoDeExcecao;

import java.util.ArrayList;
import java.util.List;

import Estoque.*;
import Outros.Usuario;

public class Venda {
	private Integer pedido;
	private Loja loja = new Loja();
	private String cpf;
	private double total;
	private List<ItemVendido> listItemVendido;
	private Integer cdVenda;
	private boolean finalizada;
	
	public Venda() {
		this.pedido = 0;
		this.total = 0;
		this.cpf = "0";
		this.listItemVendido = new ArrayList<ItemVendido>();
		this.finalizada = false;
	}

	public Venda(Integer codigo) {
		this.cdVenda = codigo;
		this.pedido = 0;
		this.total = 0;
		this.cpf = "0";
		this.loja = new Loja();
		this.listItemVendido = new ArrayList<ItemVendido>();
		this.finalizada = false;
	}

	public Venda(Integer codigo, Loja loja, String cpf) {
		this.pedido = 0;
		this.total = 0;
		this.cpf = cpf;
		this.loja = loja;
		this.cdVenda = codigo;
		this.listItemVendido = new ArrayList<ItemVendido>();
	}

	public void setCodigo(Integer codigo) {
		this.cdVenda = codigo;
	}

	public Integer getCodigo() {
		return this.cdVenda;
	}

	public Integer getPedido() {
		return pedido;
	}

	public void setPedido(Integer pedido) {
		this.pedido = pedido;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public List<ItemVendido> getListItemVendido() {
		return listItemVendido;
	}

	public void setListItemVendido(List<ItemVendido> listItemVendido) {
		this.listItemVendido = listItemVendido;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	/**
	 * 
	 * ATENCAO: SERA VERIFICADO SE OS CAMPOS DO ESTOQUE ESTAO VAZIOS OU NAO
	 * ESTAO PREENCHIDOS
	 * 
	 * Somente vai retornar 'False' quando de fato a Loja estiver null ou com um
	 * valor invalido. Caso contrÃƒÂ¡rio retorna o default inicial 'True'.
	 * 
	 */
	public boolean isNull() {
		boolean retorno = true;

		/*
		 * if(!this.loja.isNull()) if(!this.produto.isNull()) if(this.preco !=
		 * Double.NaN && this.preco > 0.0) if(this.volume != Double.NaN &&
		 * this.volume > 0.0) retorno = false;
		 */

		return retorno;
	}

	public boolean validarExistencia(ItemVendido item) {
		boolean retorno = false;
		if (listItemVendido != null && !listItemVendido.isEmpty() && listItemVendido.size() > 0) {
			for (ItemVendido produto : listItemVendido) {
				if (!produto.isNull()) {
					if (produto == item) {
						retorno = true;
						// System.out.print("\n Produto ja cadastrado na lista!
						// \n");
						break;
					}
				}
			}
		}
		return retorno;
	}

	public boolean addItemVendido(ItemVendido item, List<Estoque> listEstoque) {

		boolean retorno = false;

		if (item.isNull()) {
			if (validarExistencia(item) == false) {

				for (Estoque estoque : listEstoque) {
					if (!estoque.isNull()) {
						if (estoque.getProduto() == item.getItem())
							if (estoque.getLoja() == this.loja)
								if (estoque.validarEstoque(item.getQuantidade())) {
									retorno = true;
									listItemVendido.add(item);
									break;
								}
					}
				}
			} else
				System.out.print("\nItem de Venda ja existe na venda.");
		} else
			System.out.print("\nItem de Venda escolhido nao contem dados.");

		return retorno;

	}

	public boolean removerItemVendido(Integer indice) {
		try {
			if (indice != null && indice >= 0 && indice < listItemVendido.size()) {
				listItemVendido.remove((int) indice);
				return true;
			} else
				throw new TratamentoDeExcecao("Indice do Item de Venda escolhido nao Ã© valido.");
		} catch (TratamentoDeExcecao e) {
			throw e;
		} catch (Exception g) {
			throw g;
		}
	}

	public List<Estoque> confirmarfaturarVenda(boolean faturar, List<Estoque> listEstoque) {
		try {
			if (!isNull()) {
				for (ItemVendido item : listItemVendido) {
					for (Estoque estoque : listEstoque) {
						if (!estoque.isNull())
							if (estoque.getProduto() == item.getItem())
								if (estoque.getLoja() == this.loja)
									estoque.retirarVolumeEstoque(item.getQuantidade());
					}
				}

				return listEstoque;
			} else
				throw new TratamentoDeExcecao("Item de Venda escolhido nao contem dados.");
		} catch (TratamentoDeExcecao e) {
			throw e;
		} catch (Exception g) {
			throw g;
		}

	}

	public void printItemVenda() {
		Double total = 0.0;
		if (this.listItemVendido.isEmpty()) {
			System.out.print("\n|--------------------------------------------------------|");
			System.out.print("\n|                Nenhum item cadastrado!                 |");
			System.out.print("\n|--------------------------------------------------------|");
		} else {
			for (ItemVendido item : this.listItemVendido) {
				System.out.print("\n|--------------------------------------------------------|");
				System.out.print("\n| Codigo pedido ( " + item.getPedido() + " )");
				System.out.print("\n|--------------------------------------------------------|");
				System.out.print("\n| Produto: " + item.getItem().getDescricao());
				System.out.print("\n|--------------------------------------------------------|");
				System.out.print("\n|Codigo Produto: " + item.getItem().getCodigo());
				System.out.print("\n|--------------------------------------------------------|");
				System.out.print("\n| Valor: R$ " + item.getItem().getPreco());
				System.out.print("\n|--------------------------------------------------------|");
				System.out.print("\n| Quantidade: " + item.getQuantidade());
				System.out.print("\n|--------------------------------------------------------|");
				System.out.print("\n| Total: R$ " + item.getValor());
				System.out.print("\n|--------------------------------------------------------|");
				System.out.print("\n");
				total = total + item.getValor();
			}
			this.setTotal(total);
			System.out.print("\n|--------------------------------------------------------|");
			System.out.print("\n| Valor Total da Venda: R$ " + this.getTotal());
			System.out.print("\n|--------------------------------------------------------|");
		}
	}

	public boolean validarEstoque(Double valorDesejado, Estoque estoque) {
		boolean retorno = false;

		if (valorDesejado != Double.NaN && valorDesejado > 0.0)
			if (estoque.getVolume() >= valorDesejado) {
				return true;
			}
		if (retorno == false && valorDesejado > 0.0) {
			System.out.print("\n Quantidade informada é maior que a quatidade em estoque \n");
		}
		else if(retorno == false && valorDesejado == 0.0){
			System.out.print("\n Quantidade não pode ser '0' \n");
		}
		return retorno;
	}

	
	public boolean isFinalizada() {
		return this.finalizada;
	}

	
	public void setFinalizada(boolean finalizada) {
		this.finalizada = finalizada;
	}
}
