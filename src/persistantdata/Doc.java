package persistantdata;

import mediatheque.Document;
import mediatheque.EmpruntException;
import mediatheque.Utilisateur;

public abstract class Doc implements Document {

	protected int id;
	protected String nom;
	protected String auteur;
	protected int prix;
	protected boolean emprunte;

	public Doc(int id, String nom, String auteur, int prix, boolean emprunte) {
		super();
		this.id = id;
		this.nom = nom;
		this.auteur = auteur;
		this.prix = prix;
		this.emprunte = emprunte;
	}

	@Override
	public Object[] affiche() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void emprunter(Utilisateur arg0) throws EmpruntException {
		// TODO Auto-generated method stub

	}

	@Override
	public void retour() {
		// TODO Auto-generated method stub
	}

}
