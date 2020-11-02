package Pagamento;

import java.util.Scanner;

public class Dinheiro implements Pagamento {
	private static final Scanner scanner = new Scanner(System.in);
	private double troco;
	private double falta;

	public double getTroco() {
		return troco;
	}

	public void setTroco(double troco) {
		this.troco = troco;
	}

	public double getFalta() {
		return falta;
	}

	public void setFalta(double falta) {
		this.falta = falta;
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
			this.falta = total - valor;
			System.out.print("\n O valor a pagar é menor que o valor total. \n Falta R$ " + this.falta + "\n");
		}
		
		return retorno;
	}

	@Override
	public void pagamentoManual(Double total, Double valor) {

		System.out.print("\n Pagamento manual \n");
		System.out.print("\n Valor total: R$ " + total + "\n");

		if (valor > total) {
			this.troco = valor - total;
			System.out.print("\n Pagamento efetuado! \n Troco: R$ " + this.troco + "\n");
		} else if (valor == total) {
			System.out.print("\n Pagamento efetuado!\n");
		}

	}
}
