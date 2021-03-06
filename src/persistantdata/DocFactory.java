package persistantdata;

import java.util.Map;

import mediatheque.Document;

public class DocFactory {
	public static Document create(String T, Map<String, Object> m) {
		switch (T) {
		case "CD":
			return new CD((int) m.get("id"), (String) m.get("type"), (String) m.get("nom"), (String) m.get("auteur"),
					(int) m.get("prix"),
					((int) m.get("emprunte")) == 1, (String) m.get("album"));
		case "DVD":
			return new DVD((int) m.get("id"), (String) m.get("type"), (String) m.get("nom"), (String) m.get("auteur"),
					(int) m.get("prix"),
					((int) m.get("emprunte")) == 1, (String) m.get("resolution"));

		case "Livre":
			return new Livre((int) m.get("id"), (String) m.get("type"), (String) m.get("nom"), (String) m.get("auteur"),
					(int) m.get("prix"),
					((int) m.get("emprunte")) == 1, (String) m.get("editeur"));
		default:
			return null;
		}
	}

	public static String getTableName(int type) {
		switch (type) {
		case 0:
			return "Livre";
		case 1:
			return "CD";
		case 2:
			return "DVD";
		default:
			return null;
		}
	}
}
