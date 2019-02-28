package persistantdata;

public class DVD extends Doc {
	private String resolution;

	public DVD(int id, String type, String nom, String auteur, int prix, boolean emprunte, String resolution) {
		super(id, type, nom, auteur, prix, emprunte);
		this.resolution = resolution;
	}

	@Override
	public Object[] affiche() {
		return super.affiche(new Object[] { "Resolution : " + resolution });
	}
}
