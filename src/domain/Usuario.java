package domain;

public class Usuario {
	
	private int id;
	private String login;
	private String senha;

	public void setId(int id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}

	/**
	 * @param String login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return String
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param String senha
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * @return int
	 */
	public int getId() {
		return id;
	}
}
