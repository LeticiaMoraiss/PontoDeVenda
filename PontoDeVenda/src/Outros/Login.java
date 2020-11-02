package Outros;

import java.util.*;

interface Login {

	boolean getSession();

	boolean validarLogin(List<Usuario> listUsuario);

	void efetuarLogin(List<Usuario> listUsuario);

	void efetuarLogoff(List<Usuario> listUsuario);
}
