package persistantdata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import loader.Loader;
import mediatheque.Document;
import mediatheque.EmpruntException;
import mediatheque.Utilisateur;

public abstract class Doc implements Document {

	protected int id;
	protected String nom;
	protected String auteur;
	protected int prix;
	protected boolean emprunte;

	public Doc(int id, String nom, String auteur, int prix, boolean emprunte) {
		super();
		this.id = id;
		this.nom = nom;
		this.auteur = auteur;
		this.prix = prix;
		this.emprunte = emprunte;
	}

	@Override
	public Object[] affiche() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void emprunter(Utilisateur u) throws EmpruntException {
		Connection c = MediathequeData.connectMySQL(Loader.URL, Loader.LOG, Loader.MDP);

		String req = "UPDATE Document SET emprunte = 1 WHERE id = ?";
		try {
			PreparedStatement s = c.prepareStatement(req);
			s.setInt(1, id);
			s.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void retour() {
		Connection c = MediathequeData.connectMySQL(Loader.URL, Loader.LOG, Loader.MDP);

		String req = "UPDATE Document SET emprunte = 0 WHERE id = ?";
		try {
			PreparedStatement s = c.prepareStatement(req);
			s.setInt(1, id);
			s.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}