package user;

import mediatheque.Utilisateur;

public class User implements Utilisateur {

	private int id;
	private String login;
	private String pass;
	private boolean bibli;

	public User(Integer id, String login, String pass, boolean bibli) {
		this.id = id;
		this.login = login;
		this.pass = pass;
	}

	public int getId() {
		return id;
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
