package Pagamento;

import java.util.Scanner;

public class Cheque implements Pagamento {
	private String nuIdentidade;
	private String codCheque;
	private String titular;
	private static final Scanner scanner = new Scanner(System.in);
	
	public String getNuIdentidade() {
		return nuIdentidade;
	}
	public void setNuIdentidade(String nuIdentidade) {
		this.nuIdentidade = nuIdentidade;
	}
	public String getCodCheque() {
		return codCheque;
	}
	public void setCodCheque(String codCheque) {
		this.codCheque = codCheque;
	}
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	
	@Override
	public boolean validarPagamento(Double total, Double valor) {
		boolean retorno = true;
		if (total < 0) {
			retorno = false;
			System.out.print("\n O valor total informado não é valido. \n");
		} else if (valor < 0) {
			retorno = false;
			System.out.print("\n O valor a pagar informado não é valido. \n");
		} else if (valor < total) {
			retorno = false;
			System.out.print("\n O valor a pagar é menor que o valor total. \n");
		} else if (valor > total) {
			retorno = false;
			System.out.print("\n O valor a pagar é maior que o valor total. \n");
		}

		return retorno;
	}
	
	@Override
	public void pagamentoManual(Double total, Double valor) {

		System.out.print("\n Pagamento manual \n");
		System.out.print("\n Valor total: R$ " + valor + "\n");
		System.out.print("\n Titular do cheque: ");
		this.setTitular(scanner.nextLine());
		System.out.print("\n Númeo da identidade: ");
		this.setNuIdentidade(scanner.nextLine());
		System.out.print("\n Codigo do cheque: ");
		this.setCodCheque(scanner.nextLine());
		System.out.print("\n Pagamento efetuado com sucesso!");
	}

	
}
