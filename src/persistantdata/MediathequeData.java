package persistantdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import loader.Loader;
import mediatheque.Document;
import mediatheque.Mediatheque;
import mediatheque.PersistentMediatheque;
import mediatheque.Utilisateur;

// classe mono-instance  dont l'unique instance n'est connue que de la bibliotheque
// via une auto-d�claration dans son bloc static

public class MediathequeData implements PersistentMediatheque {
	// Jean-Fran�ois Brette 01/01/2018

	private static Connection c;

	static {
		c = connectMySQL(Loader.URL, Loader.LOG, Loader.MDP);
		Mediatheque.getInstance().setData(new MediathequeData());
	}

	private static Connection connectMySQL(String url, String log, String mdp) {
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

	// va r�cup�rer le document de num�ro numDocument dans la BD
	// et le renvoie
	// si pas trouv�, renvoie null
	@Override
	public Document getDocument(int numDocument) {
		return null;
	}

	// va r�cup�rer le User dans la BD et le renvoie
	// si pas trouv�, renvoie null
	@Override
	public Utilisateur getUser(String login, String password) {
		return null;
	}

	@Override
	public void nouveauDocument(int type, Object... args) {
		// args[0] -> le titre
		// args [1] --> l'auteur
		// etc...
	}

	// renvoie la liste de tous les documents de la biblioth�que
	@Override
	public List<Document> tousLesDocuments() {
		return null;
	}

}
