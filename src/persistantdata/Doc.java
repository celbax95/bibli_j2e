package persistantdata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import loader.Loader;
import mediatheque.Document;
import mediatheque.EmpruntException;
import mediatheque.Utilisateur;

public abstract class Doc implements Document {

	protected Integer id;
	protected String type;
	protected String nom;
	protected String auteur;
	protected Integer prix;
	protected Boolean emprunte;

	public Doc(int id, String type, String nom, String auteur, int prix, boolean emprunte) {
		super();
		this.id = id;
		this.type = type;
		this.nom = nom;
		this.auteur = auteur;
		this.prix = prix;
		this.emprunte = emprunte;
	}

	protected Object[] affiche(Object[] oChild) {

		Object[] oThis = new Object[] { id, type, nom, auteur, prix, emprunte };

		int length = oThis.length + oChild.length;
		Object[] o = new Object[length];

		System.arraycopy(oThis, 0, o, 0, oThis.length);
		System.arraycopy(oChild, 0, o, oThis.length, oChild.length);

		return o;
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
