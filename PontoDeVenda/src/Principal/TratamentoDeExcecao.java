package Principal;

public class TratamentoDeExcecao extends RuntimeException{

	public TratamentoDeExcecao(String message)
	{
		super("| (!**) " + message);
	}
}