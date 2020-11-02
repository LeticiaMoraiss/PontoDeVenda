package Outros;

import java.util.*;

public class Usuario implements Login {
	private String ID;
	private String senha;
	private static boolean session;
	private String tipoUser;

	public Usuario() {
		this.setSession(false);
	}

	public Usuario(String ID, String senha, boolean session, String tpUser) {
		this.setID(ID);
		this.setSenha(senha);
		this.setSession(session);
		this.setTipoUser(tpUser);
	}

	public String getID() {
		return ID;
	}

	public void setID(String id) {
		ID = id;
	}

	public String getSenha() {
		return senha;
	}

	public boolean getSession() {
		return session;
	}

	public void setSession(boolean session) {
		this.session = session;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipoUser() {
		return tipoUser;
	}

	public void setTipoUser(String tipo) {
		this.tipoUser = tipo;
	}

	public boolean validarLogin(List<Usuario> listUsuario) {
		boolean retorno = false;
		if (ID.equals("ADMIN") && senha.equals("ADMIN")) {
			retorno = true;
			this.setTipoUser("GERENTE");
		} else {
			if (listUsuario.isEmpty()) {
				throw new IllegalArgumentException(
						"\n Não exite operador cadastrado, faça login com o gerente para efetuar o cadastro.\n");
			} else {
				for (Usuario usuario : listUsuario) {
					if (usuario.getID().equals(this.ID) && usuario.getSenha().equals(this.senha)) {
						retorno = true;
						this.setTipoUser("OPERADOR");
						break;
					}
				}
			}
		}
		if (retorno == false)
			throw new IllegalArgumentException("\n Login não encontrado, verifique as informações. \n");
		return retorno;
	}

	public void efetuarLogin(List<Usuario> lstUsuario) {
		if (ID.equals("ADMIN") && senha.equals("ADMIN")) {
			this.setSession(true);
		} else {
			if (lstUsuario.isEmpty()) {
				throw new IllegalArgumentException("\n Nenhum usuario cadastrado!.\n");
			} else {
				for (Usuario usuario : lstUsuario) {
					if (usuario.getID().equals(this.ID) && usuario.getSenha().equals(this.senha)) {
						this.setSession(true);
					}
				}
			}
		}
	}

	public void efetuarLogoff(List<Usuario> lstUsuario) {
		if (ID.equals("ADMIN") && senha.equals("ADMIN")) {
			this.setSession(false);
		} else {
			if (lstUsuario.isEmpty()) {
				throw new IllegalArgumentException("\n Nenhum usuario cadastrado!.\n");
			} else {
				for (Usuario usuario : lstUsuario) {
					if (usuario.getID().equals(this.ID) && usuario.getSenha().equals(this.senha)) {
						this.setSession(false);
					}
				}
			}
		}
	}
}
