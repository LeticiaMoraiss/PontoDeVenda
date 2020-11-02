package Pagamento;

import java.util.*;

public class Cartao implements Pagamento {
	private String codCartao;
	private String titular;
	private String cvc;
	private static final Scanner scanner = new Scanner(System.in);

	public void setCodCartao(String codigo) {
		this.codCartao = codigo;
	}

	public String getCodCartao() {
		return this.codCartao;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getTitular() {
		return this.titular;
	}

	public void setCvc(String cvc) {
		this.cvc = cvc;
	}

	public String getCvc() {
		return this.cvc;
	}

	@Override
	public boolean validarPagamento(Double total, Double valor) {
		boolean retorno = true;
		if (total < 0) {
			retorno = false;
			System.out.print("\n O valor total informado n�o � valido. \n");
		} else if (valor < 0) {
			retorno = false;
			System.out.print("\n O valor a pagar informado n�o � valido. \n");
		} else if (valor < total) {
			retorno = false;
			System.out.print("\n O valor a pagar � menor que o valor total. \n");
		} else if (valor > total) {
			retorno = false;
			System.out.print("\n O valor a pagar � maior que o valor total. \n");
		}

		return retorno;
	}

	@Override
	public void pagamentoManual(Double total, Double valor) {

		System.out.print("\n Pagamento manual \n");
		System.out.print("\n Valor total: R$ " + valor + "\n");
		System.out.print("\n Titular do cart�o: ");
		this.setTitular(scanner.nextLine());
		System.out.print("\n N�meo do cart�o: ");
		this.setCodCartao(scanner.nextLine());
		System.out.print("\n CVC do cart�o: ");
		this.setCvc(scanner.nextLine());
		System.out.print("\n Pagamento efetuado com sucesso!");

	}

	public void pagamentoAutomatico(Double valor) {

		System.out.print("\n Pagamento automatico \n");
		System.out.print("\n Valor total: R$ " + valor + "\n");
		System.out.print("\n Insira o cart�o \n");
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.print("\n Informe o senha: ");
		scanner.nextLine();
		System.out.print("\n Processando  ");
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.print("\n Pagamento efetuado com sucesso");
	}
}
