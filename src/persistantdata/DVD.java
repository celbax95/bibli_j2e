package persistantdata;

public class DVD extends Doc {
	private String resolution;

	public DVD(int id, String nom, String auteur, int prix, boolean emprunte, String resolution) {
		super(id, nom, auteur, prix, emprunte);
		this.resolution = resolution;
	}
}
