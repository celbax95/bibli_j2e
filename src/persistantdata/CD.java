package persistantdata;

public class CD extends Doc {
	private String album;

	public CD(int id, String nom, String auteur, int prix, boolean emprunte, String album) {
		super(id, nom, auteur, prix, emprunte);
		this.album = album;
	}
}
