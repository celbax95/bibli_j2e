package user;

import mediatheque.Utilisateur;

public class User implements Utilisateur {

	private String login;
	private String pass;
	private boolean bibli;

	public User(String login, String pass, boolean bibli) {
		this.login = login;
		this.pass = pass;
	}

	public String getLogin() {
		return login;
	}

	public String getPass() {
		return pass;
	}

	@Override
	public boolean isBibliothecaire() {
		return bibli;
	}
}
