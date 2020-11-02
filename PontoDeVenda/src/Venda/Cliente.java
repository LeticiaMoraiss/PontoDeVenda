package Venda;

import java.util.List;

import Estoque.Loja;
import Estoque.Produto;

public class Cliente {
	private Integer codigo = 0;
	private String nome;
	private String cpf;
	private String endereco;

	public Cliente() {

	}

	public Cliente(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public void print() {
		if (!isNull()) {
			System.out.print(
					"\n|  Código (" + this.codigo + ") | " + " " + this.nome + "      | " + " " + this.cpf + "    |");
		} else
			System.out.print("\n|  Cadastro com pendencias, favor verificar!  |");
	}

	public boolean isNull() {
		boolean retorno = true;

		if (this.codigo != null && this.codigo > 0)
			if (this.nome != null && !this.nome.isEmpty() && !this.cpf.isEmpty() && this.cpf.length() > 0)
				retorno = false;

		return retorno;
	}

	public boolean validarExistencia(List<Cliente> listCliente) {
		boolean retorno = false;

		if (!isNull()) {
			if (listCliente != null && !listCliente.isEmpty() && listCliente.size() > 0) {
				for (Cliente cliente : listCliente) {
					if (!cliente.isNull()) {
						if (cliente.getCpf().equals(this.cpf)) {
							retorno = true;
							break;
						}
					}
				}
			}
		}
		return retorno;
	}

	

}
