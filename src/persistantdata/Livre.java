package persistantdata;

public class Livre extends Doc {
	private String editeur;

	public Livre(int id, String nom, String auteur, int prix, boolean emprunte, String editeur) {
		super(id, nom, auteur, prix, emprunte);
		this.editeur = editeur;
	}

	@Override
	public Object[] affiche() {
		return super.affiche(new Object[] { editeur });
	}
}
