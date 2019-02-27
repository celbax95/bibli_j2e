package persistantdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import loader.Loader;
import mediatheque.Document;
import mediatheque.Mediatheque;
import mediatheque.PersistentMediatheque;
import mediatheque.Utilisateur;
import user.User;

// classe mono-instance  dont l'unique instance n'est connue que de la bibliotheque
// via une auto-déclaration dans son bloc static

public class MediathequeData implements PersistentMediatheque {
	// Jean-François Brette 01/01/2018

	private static Connection c;

	static {
		c = connectMySQL(Loader.URL, Loader.LOG, Loader.MDP);
		if (c != null) {
			System.out.println("Connecte a la BDD");
		} else {
			System.out.println("Pas connecte a la BDD");
		}
		Mediatheque.getInstance().setData(new MediathequeData());
	}

	public static Connection connectMySQL(String url, String log, String mdp) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			return DriverManager.getConnection(url, log, mdp);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private MediathequeData() {
	}

	// va récupérer le document de numéro numDocument dans la BD
	// et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Document getDocument(int numDocument) {
		String req1 = "SELECT type FROM DocType WHERE id = ?";
		String req2 = "SELECT * FROM Document d, ? t WHERE id = ?";
		ResultSet res = null;
		try {
			PreparedStatement s = c.prepareStatement(req1);
			s.setInt(1, numDocument);
			res = s.executeQuery();

			if (!res.next())
				return null;

			String tablenName = "";
			tablenName = res.getString(0);

			if (tablenName.equals(""))
				return null;

			s = c.prepareStatement(req2);
			s.setString(1, tablenName);
			s.setInt(2, numDocument);
			res = s.executeQuery();

			if (res.next()) {
				// TODO factory doc
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// va récupérer le User dans la BD et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Utilisateur getUser(String login, String password) {
		String req = "SELECT log, pass, bibli FROM Utilisateur WHERE login = ? AND pass = ?";
		ResultSet res = null;
		try {
			PreparedStatement s = c.prepareStatement(req);
			s.setString(1, login);
			s.setString(2, password);
			res = s.executeQuery();

			if (res.next()) {
				return new User(res.getString("log"), res.getString("pass"), res.getInt("bibli") == 1 ? true : false);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void nouveauDocument(int type, Object... args) {
		// args[0] -> le titre
		// args [1] --> l'auteur
		// etc...
	}

	// renvoie la liste de tous les documents de la bibliothèque
	@Override
	public List<Document> tousLesDocuments() {
		return null;
	}

}
