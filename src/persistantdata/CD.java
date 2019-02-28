package persistantdata;

public class CD extends Doc {
	private String album;

	public CD(int id, String type, String nom, String auteur, int prix, boolean emprunte, String album) {
		super(id, type, nom, auteur, prix, emprunte);
		this.album = album;
	}

	@Override
	public Object[] affiche() {
		return super.affiche(new Object[] { album });
	}
}
