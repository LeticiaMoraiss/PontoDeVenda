package Principal;

import java.io.IOException;
import java.util.*;

import Outros.*;
import Estoque.*;
import Venda.*;
import Pagamento.*;

public class Main {

	private static Scanner scanner = new Scanner(System.in);
	private static String TpUser;
	private static Usuario logado;
	private static List<Usuario> listUsuario;
	private static List<Produto> listProduto;
	private static List<Estoque> listEstoque;
	private static List<Cliente> listCliente;
	private static List<Venda> listVenda;
	private static List<Loja> listLoja;
	private static boolean retorno;
	private static Double valorPago;
	private static List<Venda> listVendaFinalizada;
	
	public static void main(String[] args) 
	{
		inicializarApp();
		boolean sair = false;
		
		while(sair == false)
		{
			try
			{
				// Exibi a mensagem padrão de bem vindo
				mensagemPadrao(); 				
				// Le a opção do teclado para o menu principal
				String _opcao = lerTecladoString(); 
				// Converte para inteiro
				int opcao = Integer.parseInt(_opcao); 
				System.out.println("|------------------------------------------------|");
				
				switch (opcao) 
				{
					case 1: TpUser = interfaceLogin(); break;
					case 0: sair = true; break;
					default: throw new TratamentoDeExcecao("Opção Invalida!!");
				}
		
				if (TpUser == "GERENTE") 
				{
					System.out.print("\n\n Bem vindo Gerente! \n");
					executarFuncaoGerente();
				} else if (TpUser == "OPERADOR") {
					System.out.print("\n\n Bem vindo Operador! \n");
					System.out.print("\n Id: " + logado.getID().toString());
				}
				
			}
			catch (NumberFormatException e) {
				interfaceTelaClear();
				System.out.println("|------------------------------------------------|");
				System.out.println("| (!**) Favor entrar somente com numero.         |");
		    	System.out.println("|------------------------------------------------|\n\n");
			}
		    catch (IllegalArgumentException e) {
		    	interfaceTelaClear();
		    	System.out.println("|------------------------------------------------|");
		    	System.out.println("| (!**) " + e.getMessage());
		    	System.out.println("|------------------------------------------------|\n\n");
		    }
			catch (TratamentoDeExcecao e) 
			{
				interfaceTelaClear();
				System.out.println("|------------------------------------------------|");
		    	System.out.println(e.getMessage());
		    	System.out.println("|------------------------------------------------|\n\n");	
		    }
		}
		fecharApp();
	}

	/* ============================================= Funções Padrões =========================================*/
	
	/**
	 * [START-APP] CONJUNTO DE VARIAVEIS GLOBAIS SENDO INICIALIZADA
	 */

	private static void inicializarApp() {
		logado = new Usuario();
		listUsuario = new ArrayList<Usuario>();
		listProduto = new ArrayList<Produto>();
		listEstoque = new ArrayList<Estoque>();
		listVenda = new ArrayList<Venda>();
		listLoja = new ArrayList<Loja>();
		listCliente = new ArrayList<Cliente>();
		listVendaFinalizada = new ArrayList<Venda>();
	}

	/**
	 * [CLOSE-APP] CONJUNTO DE SENDO LIBERADA
	 */

	private static void fecharApp() {
		logado = null;
		listUsuario.clear();
		listProduto.clear();
		listEstoque.clear();
		listVenda.clear();
		listLoja.clear();
		listCliente.clear();
		listVendaFinalizada.clear();
		scanner = null;
		System.exit(0);
	}

	/**
	 * [LIMPEZA-INTERFACE] CONJUNTO DE \N PARA 'LIMPAR' A TELA.
	 */

	private static void interfaceTelaClear() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}

	/**
	 * [LIMPEZA] LIMPEZA DE SCANNER Foi adicionado pois depois de ler uma
	 * string, int, double...na ] hora que ia ler outra variavel dava problema
	 */

	protected static void limparBuffer() {
		scanner = new Scanner(System.in);
	}

	/**
	 * [ENTRADA-STRING] FUNÇÃO GERAL PARA PEGAR O QUE FOI DIGITADO
	 */

	private static String lerTecladoString() {
		return scanner.nextLine();
	}
	
	/**
	 * [ENTRADA-DOUBLE] FUNÇÃO GERAL PARA PEGAR O QUE FOI DIGITADO
	 */

	protected static Double lerTecladoDouble() 
	{	
		try
		{
			String valor = lerTecladoString();				
			Double retorno = 0.0;
			
			if(valor.matches("[0-9]+"))						 
				retorno = (Double) Double.parseDouble(valor);
			
			return (Double) retorno;
		}
		catch(TratamentoDeExcecao e)
		{
			throw e;
		}
	}
	
	/**
	 * [ENTRADA-INT] FUNÇÃO GERAL PARA PEGAR O QUE FOI DIGITADO
	 */
	
	protected static int lerTecladoInt() 
	{	
		try
		{
			String valor = lerTecladoString();				
			int retorno = 0;
			
			if(valor.matches("[0-9]+"))						 
				retorno = (int) Integer.parseInt(valor);
			
			return (int) retorno;
		}
		catch(TratamentoDeExcecao e)
		{
			throw e;
		}
	}
 
	
	private static void mensagemPadrao() 
	{
		limparBuffer();
		System.out.println("|------------------------------------------------|");
		System.out.println("|--> Dupla: Marcos Paulo e Letícia Morais - CCO5");
		System.out.println("|------------------------------------------------|");
		System.out.println("|          Bem vindo ao sistema de PDV!          |");
		
		
		System.out.println("\n\n\t\t MENU PRINCIPAL");
		System.out.println("|------------------------------------------------|");
		System.out.println("| 1: Efetuar Login");
		System.out.println("| 0: Sair");
		System.out.println("|------------------------------------------------|");
		System.out.print("| Informe a opção --> ");
	}

	private static String interfaceLogin() 
	{
		limparBuffer();
		try 
		{
			interfaceTelaClear();
			System.out.print("\n");
			System.out.println("\t|----------------------|");
			System.out.println("\t|        Login	       |");
			System.out.println("\t|----------|-----------|");
			System.out.print("\t|  ID      |");
			logado.setID(lerTecladoString());
			System.out.print("\t|  Senha   |");
			logado.setSenha(lerTecladoString());
			System.out.println("|----------|-----------|");
			logado.validarLogin(listUsuario);

		} catch (IllegalArgumentException e) {
			System.out.print(e.getMessage());
			interfaceLogin();
		}
		interfaceTelaClear();
		try {
			logado.efetuarLogin(listUsuario);
		} catch (IllegalArgumentException e) {
			System.out.print(e.getMessage());
		}
		return logado.getTipoUser().toString();
	}

	/**
	 * FUNÇÃO QUE EXIBE TODAS AS FUNCIONALIDADES DO GERENTE DENTRO DO SISTEMA
	 */
	
	private static void executarFuncaoGerente() 
	{
		Gerente gerente = new Gerente();
		int opcao = 1;

		do {
			System.out.println("\n\n\n");
			gerente.funcoes();
			opcao = scanner.nextInt();
			limparBuffer();
			switch (opcao) {
			case 1:
				interfaceCadOperador();
				break;
			case 2:
				interfaceCadProduto();
				break;
			case 3:
				interfaceCadEstoque();
				break;
			case 4:
				interfaceCadLoja();
				break;
			case 5:
				printOperadores();
				break;
			case 6:
				interfaceTelaClear();
				printListProduto();
				break;
			case 7:
				interfaceTelaClear();
				printListLoja();
				break;
			case 8:
				interfaceTelaClear();
				printListEstoque();
				break;
			case 9:
				interfaceCadVenda();
				break;
			case 10:
				interfaceCadCliente();
				break;
			case 11:
				interfaceTelaClear();
				printListCliente();
				break;
			case 12:
				try {
					logado.efetuarLogoff(listUsuario);
				} catch (IllegalArgumentException e) {
					System.out.print(e.getMessage());
				} finally {
					TpUser = interfaceLogin();
					if (TpUser == "GERENTE") {
						System.out.print("\n\n Bem vindo Gerente! \n");
						executarFuncaoGerente();
					} else if (TpUser == "OPERADOR") {
						System.out.print("\n\n Bem vindo Operador! \n");
						System.out.print("\n Id: " + logado.getID().toString());
						executaFuncaoOperador();
					}
				}
				break;
			case 13:
				printListVendaFinalizada();
				break;
			case 0:
				try {
					logado.efetuarLogoff(listUsuario);
				} catch (IllegalArgumentException e) {
					System.out.print(e.getMessage());
				}
				System.out.print("\n Volte Sempre!\n\n");
				System.exit(0);
				break;
			default:
				System.out.print("\n Opção Invalida!!");
				break;
			}
			limparBuffer();
		} while (opcao != 0 && opcao != 12);
	}

	private static void executaFuncaoOperador() {
		// limparBuffer();
		Operador operador = new Operador();
		int opcao = 1;
		do {
			System.out.println("\n\n\n");
			operador.funcoes();
			opcao = scanner.nextInt();

			switch (opcao) {
			case 1:
				interfaceCadVenda();
				break;
			case 2:
				try {
					logado.efetuarLogoff(listUsuario);
				} catch (IllegalArgumentException e) {
					System.out.print(e.getMessage());
				} finally {
					TpUser = interfaceLogin();
					if (TpUser == "GERENTE") {
						System.out.print("\n\n Bem vindo Gerente! \n");
						executarFuncaoGerente();
					} else if (TpUser == "OPERADOR") {
						System.out.print("\n\n Bem vindo Operador! \n");
						System.out.print("\n Id: " + logado.getID().toString());
						executaFuncaoOperador();
					}
				}
			case 3:
				printOperadores();
				break;
			case 4:
				interfaceTelaClear();
				printListProduto();
				break;
			case 5:
				interfaceTelaClear();
				printListLoja();
				break;
			case 6:
				interfaceTelaClear();
				printListEstoque();
				break;
			case 7:
				interfaceCadCliente();
				break;
			case 8:
				interfaceTelaClear();
				printListCliente();
				break;
			case 9:
				interfaceTelaClear();
				printListVendaFinalizada();
				break;
			case 0:
				try {
					logado.efetuarLogoff(listUsuario);
				} catch (IllegalArgumentException e) {
					System.out.print(e.getMessage());
				}
				System.out.print("\n Volte Sempre!\n\n");
				System.exit(0);
				break;
			default:
				System.out.print("\n Opção Invalida!!");
				break;
			}

			limparBuffer();
		} while (opcao != 0 || opcao != 2);

	}

	private static void interfaceCadOperador() 
	{
		limparBuffer();
		interfaceTelaClear();
		System.out.print("\n Cadastro de Operador \n ");
		System.out.print("\n ID Usuario: ");
		String ID = lerTecladoString();
		System.out.print("\n Senha: ");
		String senha = lerTecladoString();
		Usuario user = new Usuario(ID, senha, false, "OPERADOR");
		listUsuario.add(user);
		System.out.println("\n Operador cadastrado com sucesso!! \n");
	}

	private static void printOperadores() {
		interfaceTelaClear();
		System.out.print("\n\tOperadores Cadastrados no sistema \n");
		if (listUsuario.isEmpty()) {
			System.out.print("\n Nenhum operador cadastrado \n");
		} else {
			for (int i = 0; i < listUsuario.size(); i++) {
				Usuario user = (Usuario) listUsuario.get(i);
				System.out.print("\n Nome: " + user.getID() + "\n Tipo: " + user.getTipoUser() + "\n");
			}
		}
	}

	/**
	 * [INTERFACE-CADASTRO-CLIENTE] FUNÇÃO PARA CADASTRAR CLIENTES PARA A VENDA
	 */
	private static void interfaceCadCliente() {
		interfaceTelaClear();
		Cliente cliente = new Cliente(listCliente.size() + 1);
		System.out.print("\n|-----------------------------------------------|");
		System.out.print("\n|             CADASTRE UM CLIENTE               |");
		System.out.print("\n|-------------|---------------------------------|");
		System.out.print("\n| Código      |" + cliente.getCodigo());
		do {
			System.out.print("\n| Nome        | ");
			cliente.setNome(lerTecladoString().toUpperCase());
			System.out.print("| CPF         | ");
			cliente.setCpf(lerTecladoString().toUpperCase());
			System.out.print("| Endereço    | ");
			cliente.setEndereco(lerTecladoString().toUpperCase());
			System.out.println("|-----------------------------------------------|");

			if (cliente.validarExistencia(listCliente) == true) {
				System.out.println("|-----------------------------------------------|");
				System.out.println("|(**!) Cliente já cadastrado para outro código  |");
				System.out.println("|-----------------------------------------------|");
			}
		} while (cliente.validarExistencia(listCliente) == true);

		listCliente.add(cliente);
		System.out.print("\n|-----------------------------------------------|");
		System.out.print("\n|          CADASTRADO COM SUCESSO!              |");
		System.out.print("\n|-----------------------------------------------|");
		cliente.print();
		System.out.print("\n|-----------------------------------------------|");
	}

	private static void printListCliente() {
		System.out.print("\n|-----------------------------------------------|");
		System.out.print("\n|       LISTA DE CLIENTES CADASTRADOS           |");
		System.out.print("\n|-----------------------------------------------|");
		if (!listCliente.isEmpty()) {
			System.out.print("\n|  Codigo ()  |     NOME      |      CPF        |");
			System.out.print("\n|-----------------------------------------------|");
			for (Cliente cliente : listCliente) {
				if (!cliente.isNull()) {
					cliente.print();
				}
			}
			System.out.print("\n|-----------------------------------------------|");
		} else {
			System.out.print("\n|-----------------------------------------------|");
			System.out.print("\n|Lista de 'Clientes' vazia, favor cadastrar.    |");
			System.out.print("\n|-----------------------------------------------|");
		}
	}

	private static Cliente getClientePorCpf(String cpf) {
		Cliente cliente = null;
		boolean find = false;

		for (Cliente item : listCliente) {
			if (item.getCpf().equals(cpf)) {
				cliente = new Cliente();
				cliente = item;
				find = true;
			}
		}
		if (find == false) {
			throw new IllegalArgumentException("\n| Cliente   CPF: " + cpf + " **não cadastrado       |");
		}
	
		return cliente;
	}

	private static Loja getLojaPorCodigo(Integer codigo) {

		Loja loja = null;

		if (listLoja.isEmpty()) {
			throw new IllegalArgumentException("\n Nenhuma loja cadastrada!.\n");
		} else {
			for (Loja item : listLoja) {
				if (item.getCodigo().equals(codigo)) {
					loja = new Loja();
					loja = item;
				}
			}
		}
		return loja;
	}

	private static void pagamento(Venda vendaAtual) {
		limparBuffer();

		Double total = vendaAtual.getTotal();
		Double valor;

		System.out.print("\n Tipos de pagamento \n");
		System.out.print("\n 1 - Dinheiro");
		System.out.print("\n 2 - Cartão");
		System.out.print("\n 3 - Cheque");
		System.out.print("\n Informe a opção: ");
		int opcao = scanner.nextInt();
		switch (opcao) {
		case 1:
			Dinheiro dinheiro = new Dinheiro();
			do {
				System.out.print("\n Informe o valor a pagar: ");
				valorPago = (Double) scanner.nextDouble();
				retorno = dinheiro.validarPagamento(vendaAtual.getTotal(), valorPago);
			} while (retorno != true);

			if (retorno == true) {
				dinheiro.pagamentoManual(total, valorPago);
			}
			break;
		case 2:
			Cartao cartao = new Cartao();
			do {
				System.out.print("\n Informe o valor a pagar: ");
				valorPago = (Double) scanner.nextDouble();
				retorno = cartao.validarPagamento(vendaAtual.getTotal(), valorPago);
			} while (retorno != true);

			if (retorno == true) {
				System.out.print("\n Formas de pagamento \n");
				System.out.print("\n 1 - Automatico");
				System.out.print("\n 2 - Manual");
				System.out.print("\n Informe a opção: ");
				int opcao2 = scanner.nextInt();

				if (opcao2 == 1) {
					cartao.pagamentoAutomatico(valorPago);
				} else {
					cartao.pagamentoManual(vendaAtual.getTotal(), valorPago);
				}
			}
			break;
		case 3:
			Cheque cheque = new Cheque();
			do {
				System.out.print("\n Informe o valor a pagar: ");
				valorPago = (Double) scanner.nextDouble();
				retorno = cheque.validarPagamento(total, valorPago);
			} while (retorno != true);

			if (retorno == true) {
				cheque.pagamentoManual(vendaAtual.getTotal(), valorPago);
			}
			break;
		default:
			System.out.print("\n Opção Invalida!!");
			break;
		}
	}

	private static void interfaceCadVenda() {

		interfaceTelaClear();

		Loja loja = new Loja();
		System.out.println("\n|---CADASTRE UMA VENDA (" + logado.getID() + ")---|");
		printListLoja();
		System.out.print("\n Informe sua loja: ");
		int cdLoja = scanner.nextInt();

		do {
			try {
				loja = getLojaPorCodigo(cdLoja);
			} catch (IllegalArgumentException e) {
				System.out.print(e.getMessage());
			}
		} while (loja == null);

		if (loja != null) {
			limparBuffer();
			System.out.print("\n Informe o CPF do cliente para iniciar a venda: ");
			String cpf = lerTecladoString();
			Venda venda = new Venda(listVenda.size() + 1, loja, cpf);
			interfaceItemVenda(venda);
		}
	}
	
	private static void interfaceItemVenda(Venda vendaAtual) {
		interfaceTelaClear();
		printListEstoque(vendaAtual.getLoja());

		Integer pararVendaItem = 1;

		System.out.print("\n Selecione os podutos para venda \n");
		do {
			ItemVendido item = new ItemVendido(vendaAtual.getListItemVendido().size() + 1);
			Estoque estoque;
			Double qtd = 0.0;
			int codigo = 0;
			do {
				estoque = new Estoque();
				System.out.println("  | (i?) Digite o codigo do estoque que contem o produto desejado.|");
				do{
					System.out.print("\n  | Codigo: ");
					codigo = scanner.nextInt();
				}while(getEstoqueCod(codigo) == null);

				System.out.print("\n  | Quantidade: ");
				qtd = (Double) scanner.nextDouble();
				estoque = getEstoqueCod(codigo);
				item.setItem(estoque.getProduto());
				item.setQuantidade(qtd);
				Double valor = (Double) (estoque.getProduto().getPreco() * qtd);
				item.setValor(valor);
			} while (!vendaAtual.validarEstoque(qtd, estoque));

			vendaAtual.addItemVendido(item, listEstoque);
			System.out.print("\n\n");
			vendaAtual.printItemVenda();
			
			System.out.println("\n\n\n| (?) Deseja parar de digitar itens para venda?  |");
			System.out.println("| --> Digite ( 1 ) para add mais produto.              |");
			System.out.println("| --> Digite ( 0 ) para finalizar venda                |");
			System.out.print("\n| --> Opção: ");
			pararVendaItem = (int) scanner.nextInt();
		} while (pararVendaItem != 0);
		
		interfaceReciboVenda(vendaAtual);
	}
	private static void interfaceReciboVenda(Venda vendaAtual) {
		interfaceTelaClear();
		System.out.print("\n|--------------------------------------------------------|");
		System.out.print("\n|     	 Dados da Venda       			         |");
		System.out.print("\n|--------------------------------------------------------|");
		System.out.print("\n| codigo (" + vendaAtual.getCodigo() +")");
		System.out.print("\n|--------------------------------------------------------|");
		System.out.print("\n| loja (" + vendaAtual.getLoja().getCodigo() + ")  |" + vendaAtual.getLoja().getRazao());
		System.out.print("\n|--------------------------------------------------------|");
		try {
			Cliente cliente = new Cliente();
			cliente = getClientePorCpf(vendaAtual.getCpf());
			cliente.print();
		} catch (IllegalArgumentException e) {
			System.out.print(e.getMessage());
		}
		
		System.out.print("\n|--------------------------------------------------------|");
		System.out.print("\n|      Itens da Venda       			         |");
		vendaAtual.printItemVenda();
		
		interfaceFaturamento(vendaAtual);
	}

	private static Estoque getEstoqueCod(Integer cod) {
		Estoque estoque = null;
		for (Estoque item : listEstoque) {
			if (item.getCodigo().equals(cod)) {
				estoque = new Estoque();
				estoque = item;
			}
		}
		if(estoque == null){
			System.out.print("\n O codigo informado não existe\n");
		}
		
		return estoque;
	}

	
	private static void interfaceFaturamento(Venda vendaAtual) {
		interfaceTelaClear();
		pagamento(vendaAtual);
		finalizarVenda(vendaAtual);
		System.out.print("\n|--------------------------------------------------------|");
		System.out.print("\n|     	FATURAMENTO       			         |");
		System.out.print("\n|--------------------------------------------------------|");
		System.out.print("\n|Total Venda: R$ " + vendaAtual.getTotal());
		System.out.print("\n|--------------------------------------------------------|");
		System.out.print("\n|Total Pago: R$ " + valorPago);
		System.out.print("\n|--------------------------------------------------------|");
		System.out.print("\n|Troco: R$ " + (valorPago - vendaAtual.getTotal()));
		System.out.print("\n|--------------------------------------------------------|");
		System.out.print("\n|    Venda Faturada e Cadastrada com sucesso!            |");
		System.out.print("\n|--------------------------------------------------------|");
		
	}

	private static void finalizarVenda(Venda vendaAtual){
		vendaAtual.setFinalizada(true);
		listVendaFinalizada.add(vendaAtual);
		Loja loja = vendaAtual.getLoja();
		List<ItemVendido> item = vendaAtual.getListItemVendido();
		Double qtdAtualizado = 0.0;
		
		for (Estoque estoque : listEstoque) {
			if (!estoque.isNull()){
				if (estoque.getLoja() == loja){
					for(ItemVendido i : item){
						if(estoque.getProduto().getCodigo() == i.getItem().getCodigo() ){
							qtdAtualizado = (Double) (estoque.getVolume() - i.getQuantidade());
							estoque.setVolume(qtdAtualizado);
							qtdAtualizado = 0.0;
						}
					}						
				}
			}			
		}		
	}
	
	private static void printListVendaFinalizada(){
		System.out.print("\n|--------------------------------------------------------|");
		System.out.print("\n|		Vendas Finalizadas                 |");
		System.out.print("\n|--------------------------------------------------------|");
		Double total = 0.0;
		for(Venda venda : listVendaFinalizada){
			System.out.print("\n|--------------------------------------------------------|");
			System.out.print("\n| Codigo venda (" + venda.getCodigo()+")" );
			System.out.print("\n| Loja: " + venda.getLoja().getCodigo()+ venda.getLoja().getRazao()  );
			try{
				System.out.print("\n| "+ getClientePorCpf(venda.getCpf()));
			}catch(IllegalArgumentException e){
				System.out.print(e.getMessage());
			}
			System.out.print("\n|--------------------------------------------------------|");
			System.out.print("\n|         Itens da venda                  |");
			System.out.print("\n|--------------------------------------------------------|");
			for(ItemVendido item : venda.getListItemVendido()){
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
			System.out.print("\n|--------------------------------------------------------|");
			System.out.print("\n| Valor Total da Venda: R$ " + venda.getTotal());
			System.out.print("\n|--------------------------------------------------------|\n\n");
		}
		total = 0.0;
	}
	
	/**
	 * [INTERFACE-CAD] CADASTRO DE [LOJAS]
	 */

	protected static void interfaceCadLoja() 
	{

		Loja loja = new Loja(listLoja.size() + 1);

		while(loja.isNull() || loja.validarExistencia(listLoja))
		{
			interfaceTelaClear();
			
			try
			{
				System.out.print("\n|             CADASTRE UM LOJA                |");
				System.out.print("\n|-------------|-------------------------------|");
				System.out.print("\n|  Codigo     | " + loja.getCodigo());
				
				System.out.print("\n|  Razao      | ");
				loja.setRazao(lerTecladoString().toUpperCase());
				System.out.print("|  Logradouro | ");
				loja.setLogradouro(lerTecladoString().toUpperCase());
				
				if(loja.validarExistencia(listLoja))
				{
					System.out.println("|-------------|-------------------------------|");
					System.out.println("| (!**) Loja já cadastrada para outro codigo.");
					System.out.println("|-------------|-------------------------------|");
				}			
			}
			catch (TratamentoDeExcecao e) 
			{
				System.out.println("|-------------|-------------------------------|");
				System.out.println(e.getMessage());
				System.out.println("|-------------|-------------------------------|");
					//continue;
			}
		}
		System.out.print("\n|-------------|-------------------------------|");
		listLoja.add(loja);

		System.out.println("\n|           CADASTRADO COM SUCESSO!           |");
		System.out.println("|---------------------------------------------|");
		System.out.print("| LOJA --> ") ;loja.print();
		System.out.println("|---------------------------------------------|");
		interfaceTelaClear();
	}

	/**
	 * [INTERFACE-CAD] CADASTRO DE [PRODUTO]
	 */

	protected static void interfaceCadProduto()
	{		
		Produto produto = new Produto(listProduto.size()+1);		
			
		while(produto.isNull() || produto.validarExistencia(listProduto) || produto.validaPreco() == false)
		{			
			try
			{		
				while(produto.isNull() || produto.validarExistencia(listProduto))
				{				
					interfaceTelaClear();
					System.out.print("\n|            CADASTRE UM PRODUTO              |");			
					System.out.print("\n|-------------|-------------------------------|");						
					System.out.print("\n|  Codigo     | " + produto.getCodigo());
					System.out.print("\n|  Descricao  | ");
					produto.setDescricao(lerTecladoString().toUpperCase());
					
					if(produto.validarExistencia(listProduto))
					{
						System.out.println("|-------------|-------------------------------|");
						System.out.println("| (!**) Produto já cadastrado para outro codigo.");
						System.out.println("|-------------|-------------------------------|");
					}
				}
					
				while(produto.validaPreco() == false)
				{		
					interfaceTelaClear();
					System.out.print("\n|            CADASTRE UM PRODUTO              |");			
					System.out.print("\n|-------------|-------------------------------|");						
					System.out.print("\n|  Codigo     | " + produto.getCodigo());
					System.out.print("\n|  Descricao  | " + produto.getDescricao());
					System.out.print("\n|  Preco      | ");
					produto.setPreco(lerTecladoDouble());																							
				}
			}
			catch (TratamentoDeExcecao e) 
			{				
				System.out.println("|-------------|-------------------------------|");
				System.out.println(e.getMessage());
				System.out.println("|-------------|-------------------------------|");
					//continue;
			}
		}
		
		System.out.println("|-------------|-------------------------------|");
		listProduto.add(produto);
			
		System.out.println("\n|           CADASTRADO COM SUCESSO!           |");
		System.out.println("|---------------------------------------------|");
		System.out.print("| PRODUTO --> ") ;produto.print();			
		System.out.println("|---------------------------------------------|");
		limparBuffer();
		interfaceTelaClear();	
	}
	
	/**
	 * [INTERFACE-CAD-PRINCIPAL] CADASTRO DE [ESTOQUE]
	 * 
	 * O metodo abaixo cadastra um estoque. Ele chama a função :
	 * 
	 * --> 'interfaceCadEstoque_Produto' para receber um produto; -->
	 * 'interfaceCadEstoque_Loja' para receber uma loja;
	 * 
	 * Logo apos receber ele valida se existe um estoque cadastrado para [Loja X
	 * Produto], e somente permite cadastrar caso nao tenha nenuma ocorrencia
	 * entre os dois na lista de Estoque.
	 * 
	 */

	protected static void interfaceCadEstoque() 
	{
		interfaceTelaClear();
		
		if (listLoja != null && !listLoja.isEmpty() && listLoja.size() > 0) 
		{
			if (listProduto != null && !listProduto.isEmpty() && listProduto.size() > 0) 
			{
				Estoque estoque = new Estoque(listEstoque.size() + 1);
				
				while(estoque.isNull() || estoque.validarExistencia(listEstoque) /*|| estoque.validarEstoque() == false*/)
				{
					interfaceTelaClear();
					System.out.println("|-------------------------------------------------|");
					System.out.println("|     CADASTRE O ESTOQUE E PREÇO P/ O PRODUTO     |");
					System.out.println("|-------------------------------------------------|");
					try
					{
						while(estoque.isNull() || estoque.validarExistencia(listEstoque))
						{				
							estoque.setProduto(interfaceCadEstoque_Produto());							
							estoque.setLoja(interfaceCadEstoque_Loja());
							
							if(estoque.validarExistencia(listEstoque))
							{
								System.out.println("|-------------|-------------------------------|");
								System.out.println("| (!**) Estoque já cadastrado para outro codigo.");
								System.out.println("|-------------|-------------------------------|");
							}
						}
						
						while(estoque.validarEstoque() == false)
						{
							interfaceTelaClear();
							System.out.println("\n|---CADASTRE O ESTOQUE E PREÇO P/ O PRODUTO---|");
							System.out.println("|----------------------------|---------------|");
							System.out.print("\n|  Código Produto Escolhido  |--> (" + estoque.getProduto().getCodigo() + ") " + estoque.getProduto().getDescricao());
							System.out.print("\n|  Código Loja Escolhida     |--> (" + estoque.getLoja().getCodigo() + ") " + estoque.getLoja().getRazao());
							System.out.print("\n|  Volume                    | ");
							estoque.setVolume(lerTecladoDouble());
						}									
					}
					catch (TratamentoDeExcecao e) 
					{
						System.out.println("|-------------|-------------------------------|");
						System.out.println(e.getMessage());
						System.out.println("|-------------|-------------------------------|");
							//continue;
					}																	
				}
				System.out.println("\n|     CADASTRE O ESTOQUE E PREÇO P/ O PRODUTO     |");
				    System.out.print("|--------------|----------------------------------|");
				listEstoque.add(estoque);

				System.out.println("\n|           CADASTRADO COM SUCESSO!           |");
				System.out.println("|---------------------------------------------|");
				estoque.print();	
			} 
			else System.out.println("(**!) Lista de 'Produtos' vazia, favor cadastrar o produto primeiramente.");
		} 
		else System.out.println("(**!) Lista de 'Lojas' vazia, favor cadastrar uma loja primeiramente.");
		
		System.out.println("|-------------------------------------------------|");

		interfaceTelaClear();
	}

	/**
	 * [INTERFACE-CAD-COMPLEMENTO] CADASTRO [PRDUTO] PARA O ESTOQUE
	 */

	protected static Produto interfaceCadEstoque_Produto() 
	{
		try
		{
			Produto estoqueProduto;	
			printListProduto();
	
			int indexCodigoEscolhido = 0;
	
			do 
			{
				System.out.println("|-------------------------------------------------|");	
				System.out.println("  (?) Inicialmente leia a lista acima, e informe");
				System.out.println("      o PRODUTO que vai receber o estoque.");
				System.out.println("|-------------------------------------------------|\n\n");
				
				System.out.print("\n  |  Código Produto Escolhido  |-->");
				indexCodigoEscolhido = lerTecladoInt();
	
				estoqueProduto = getProdutoNaLista(indexCodigoEscolhido - 1);
	
				if (estoqueProduto.isNull())
					System.out.println("|  (**!) Produto não cadastrado.");
			} while (estoqueProduto.isNull());
	
			return estoqueProduto;
		}
		catch(TratamentoDeExcecao e)
		{
			throw e;
		}
	}

	/**
	 * [INTERFACE-CAD-COMPLEMENTO] CADASTRO [LOJA] PARA O ESTOQUE
	 */

	protected static Loja interfaceCadEstoque_Loja() 
	{
		try
		{
			int indexCodigoEscolhido = 0;
			Loja estoqueLoja;	
			printListLoja();
			
			do 
			{
				System.out.println("\n\n\n\n|-------------------------------------------------|");				
				System.out.println("  (?) Inicialmente leia a lista acima, e informe");
				System.out.println("      a LOJA que vai receber o estoque.");
				System.out.println("|-------------------------------------------------|");
				System.out.print("\n  |  Código Loja Escolhida     |-->");
				indexCodigoEscolhido = lerTecladoInt();
	
				estoqueLoja = getLojaNaLista(indexCodigoEscolhido - 1);
	
				if (estoqueLoja.isNull())
					System.out.println("|  (**!) Loja não cadastrada.");
			} while (estoqueLoja.isNull());
	
			return estoqueLoja;
		}
		catch(TratamentoDeExcecao e)
		{
			throw e;
		}
	}

	/**
	 * [PRINT-LIST] IMPRIME TODOS OS [PRODUTOS] CADASTRADOS
	 */

	
	protected static void printListProduto() 
	{
		System.out.println("\n  |------------------------------------------|");
		System.out.println("  |       LISTA DE PRODUTO CADASTRADO        |");
		System.out.println("  |------------------------------------------|");	
		
		if (listProduto != null && !listProduto.isEmpty() && listProduto.size() > 0) 
		{
			System.out.println("  | COD.(-) |   Descricao    |   Preco ($)   |");
			System.out.println("  |------------------------------------------|");
			
			for (Produto produto : listProduto) {
				if (!produto.isNull())
					produto.print();
			}

			System.out.println("  |------------------------------------------|");
		} 
		else System.out.println("(**!) Lista de 'Produtos' vazia, favor cadastrar produto.");
	}

	
	/**
	 * [PRINT-LIST] IMPRIME TODOS AS [LOJAS] CADASTRADAS
	 */	
	

	protected static void printListLoja() {
		System.out.println("\n  |------------------------------------------|");
		System.out.println("  |         LISTA DE LOJA CADASTRADA         |");
		System.out.println("  |------------------------------------------|");
		if (listLoja != null && !listLoja.isEmpty() && listLoja.size() > 0) {
			System.out.println("  |  Código ()  |     RAZAO    |   LOGRAD.   |");
			System.out.println("  |------------------------------------------|");
			for (Loja loja : listLoja) {
				if (!loja.isNull())
					loja.print();
			}

			System.out.println("  |------------------------------------------|");
		} else
			System.out.println("(**!) Lista de 'Lojas' vazia, favor cadastrar loja.");
	}

	
	/**
	 * [PRINT-LIST] IMPRIME TODOS O [ESTOQUE] CADASTRADO
	 */	
	

	protected static void printListEstoque() {
		System.out.println("\n  |------------------------------------------|");
		System.out.println("  |       LISTA DE ESTOQUE CADASTRADO        |");
		System.out.println("  |------------------------------------------|");

		if (listEstoque != null && !listEstoque.isEmpty() && listEstoque.size() > 0) {
			System.out.println("  | COD.(-) | PROD.  | QTD | $ |  LOJA        |");
			System.out.println("  |------------------------------------------|");
			for (Estoque estoque : listEstoque) {
				if (!estoque.isNull())
					estoque.print();
			}

			System.out.println("  |------------------------------------------|");
		} else
			System.out.println("(**!) Lista de 'Estoque' vazia, favor cadastrar estoque.");
	}
	

	/**
	 * [PRINT-LIST] IMPRIME TODOS O [ESTOQUE] ESPECIFICO
	 */	
	
	protected static void printListEstoque(Loja loja) {

		if (listEstoque != null && !listEstoque.isEmpty() && listEstoque.size() > 0) {
			if (!loja.isNull()) {
				System.out.println("\n|------------------------------------------|");
				System.out.println("  |       LISTA DE ESTOQUE CADASTRADO        |");
				System.out.println("  |------------------------------------------|");
				System.out.println("  | LOJA (" + loja.getCodigo() + ") -->" + loja.getRazao());
				System.out.println("  |------------------------------------------|");
				System.out.println("  | COD.() | PROD.  | QTD | $ |  LOJA | LOGR.|");
				System.out.println("  |------------------------------------------|");

				for (Estoque estoque : listEstoque) {
					if (!estoque.isNull())
						if (estoque.getLoja() == loja)
							estoque.print();
				}

				System.out.println("  |------------------------------------------|");
			} else
				System.out.println("(**!) Dados sobre a loja se encontra nulo.");
		} else
			System.out.println("(**!) Lista de 'Estoque' vazia, favor cadastrar estoque.");
	}

	
	/**
	 * [GET-LOJA-LIST] FUNÇÃO PARA RETORNAR UMA LOJA CADASTRADA E QUE SE
	 * ENCONTRA NA LISTA.
	 * 
	 * A funcao recebe o parametro codigo já com o -1, pois ele é o indice de
	 * acesso na lista de cadastro [listLoja]
	 * 
	 */
	

	protected static Loja getLojaNaLista(int codigo) {
		if (listLoja != null && !listLoja.isEmpty() && listLoja.size() > 0) {
			if (codigo >= 0 && codigo < listLoja.size())
				// return (Loja) listLoja.get(codigo);
				return listLoja.get(codigo);
		}
		return null;
	}

	
	/**
	 * [GET-PRODUTO-LIST] FUNÇÃO PARA RETORNAR UM PRODUTO CADASTRADO E QUE SE
	 * ENCONTRA NA LISTA.
	 * 
	 * A funcao recebe o parametro codigo já com o -1, pois ele é o indice de
	 * acesso na lista de cadastro [listProduto]
	 * 
	 */

	protected static Produto getProdutoNaLista(int codigo) 
	{
		try
		{
			if (listProduto != null && !listProduto.isEmpty() && listProduto.size() > 0) 
			{
				if (codigo >= 0 && codigo < listProduto.size())
				{					
					return listProduto.get(codigo);
				}
				else throw new TratamentoDeExcecao("Codigo da Loja invalido, favor verifica o codigo digitado");
			}
			else throw new TratamentoDeExcecao("Lista de 'Produtos' vazia, favor cadastrar o produto primeiramente.");			
		}
		catch(NullPointerException e)
		{
			throw new TratamentoDeExcecao("Codigo da Loja invalido, favor verifica o codigo digitado");
		}
	}

	/**
	 * [GET-ESTOQUE-LIST] FUNÇÃO PARA RETORNAR O ESTOQUE CADASTRADO E QUE SE
	 * ENCONTRA NA LISTA.
	 * 
	 * A funcao recebe o parametro codigo já com o -1, pois ele é o indice de
	 * acesso na lista de cadastro [listEstoque]
	 * 
	 */

	private static Estoque getEstoqueNaLista(int codigo) {
		if (listEstoque != null && !listEstoque.isEmpty() && listEstoque.size() > 0) {
			if (codigo >= 0 && codigo < listEstoque.size())
				// return (Estoque) listEstoque.get(codigo);
				return listEstoque.get(codigo);
		}
		return null;
	}
}
