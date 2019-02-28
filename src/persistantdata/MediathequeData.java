package persistantdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	private static String[] tName = new String[] { "CD", "DVD", "Livre" };

	static {
		c = connectMySQL(Loader.URL, Loader.LOG, Loader.MDP);
		if (c != null) {
			System.out.println("Connecte a la BDD");
		} else {
			System.out.println("Pas connecte a la BDD");
		}
		Mediatheque.getInstance().setData(new MediathequeData());
	}

	private MediathequeData() {
	}

	// va récupérer le document de numéro numDocument dans la BD
	// et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Document getDocument(int numDocument) {

		String req = "SELECT d.*, t.* FROM Document d, TABLE as t WHERE d.id = ? && t.idDoc = d.id";
		ResultSet res = null;
		try {

			String tableName = getTableName(numDocument);

			if (tableName == null) {
				return null;
			}

			req = req.replaceFirst("TABLE", tableName);

			PreparedStatement s = c.prepareStatement(req);
			s = c.prepareStatement(req);
			s.setInt(1, numDocument);
			res = s.executeQuery();

			if (res.next()) {
				ResultSetMetaData rs = res.getMetaData();
				int nb = rs.getColumnCount();

				Map<String, Object> m = new HashMap<>();
				m.put("type", tableName);
				for (int i = 1; i <= nb; i++)
					m.put(rs.getColumnName(i), res.getObject(i));

				return DocFactory.create(tableName, m);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getTableName(int numDocument) {
		String req = "SELECT type FROM DocType WHERE idDoc = ?";
		ResultSet res = null;

		try {
			PreparedStatement s = c.prepareStatement(req);
			s.setInt(1, numDocument);
			res = s.executeQuery();

			if (!res.next())
				return null;

			String tableName = "";
			tableName = res.getString(1);

			return tableName;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// va récupérer le User dans la BD et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Utilisateur getUser(String login, String password) {
		String req = "SELECT id, login, pass, bibli FROM Utilisateur WHERE login = ? AND pass = ?";
		ResultSet res = null;
		try {
			PreparedStatement s = c.prepareStatement(req);
			s.setString(1, login);
			s.setString(2, password);
			res = s.executeQuery();

			if (res.next()) {
				return new User(res.getInt("id"), res.getString("login"), res.getString("pass"),
						res.getInt("bibli") == 1 ? true : false);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void nouveauDocument(int type, Object... o) {
		String reqDoc = "INSERT INTO Document VALUES (?, ?, ?, ?, ?)";
		String reqSpe = "INSERT INTO ? VALUES (?, ?)";

		ResultSet res = null;

		int idDoc = 0;

		try {
			int i = 0;
			PreparedStatement s = c.prepareStatement(reqDoc, Statement.RETURN_GENERATED_KEYS);
			s.setInt(i + 1, (int) o[i++]);
			s.setString(i + 1, (String) o[i++]);
			s.setString(i + 1, (String) o[i++]);
			s.setInt(i + 1, (int) o[i++]);
			s.setInt(i + 1, (int) o[i++]);
			s.executeUpdate();

			res = s.getGeneratedKeys();

			if (!res.next())
				return;

			idDoc = res.getInt(0);

			s = c.prepareStatement(reqSpe, Statement.RETURN_GENERATED_KEYS);
			s.setString(0, tName[type]);
			s.setInt(1, idDoc);
			s.setString(2, (String) o[i++]);
			s.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// renvoie la liste de tous les documents de la bibliothèque
	@Override
	public List<Document> tousLesDocuments() {

		List<Document> docs = new ArrayList<>();

		String req = "SELECT d.id FROM Document d";
		ResultSet res = null;
		try {
			PreparedStatement s = c.prepareStatement(req);
			res = s.executeQuery();

			List<Integer> ids = new ArrayList<>();
			while (res.next())
				ids.add(res.getInt(1));

			for (Integer idDoc : ids)
				docs.add(getDocument(idDoc));

			System.out.println(getDocument(1).affiche()[0]);
			System.out.println(docs.get(0).affiche()[1]);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return docs;
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
}
