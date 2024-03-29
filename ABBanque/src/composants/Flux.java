package composants;

import java.util.Date;

public abstract class Flux {
	protected String commentaire;
	protected int identifiant;
	protected double montant;
	protected int numCompteCible;
	protected boolean effectue;
	protected Date dateFlux;
	
	public Flux(String commentaire, int identifiant, double montant, int numCompteCible, boolean effectue) {
		this.commentaire = commentaire;
		this.identifiant = identifiant;
		this.montant = montant;
		this.numCompteCible = numCompteCible;
		this.effectue = effectue;
		this.dateFlux = new Date();
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public int getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public int getNumCompteCible() {
		return numCompteCible;
	}

	public void setNumCompteCible(int numCompteCible) {
		this.numCompteCible = numCompteCible;
	}

	public boolean isEffectue() {
		return effectue;
	}

	public void setEffectue(boolean effectue) {
		this.effectue = effectue;
	}

	public Date getDateFlux() {
		return dateFlux;
	}

	public void setDateFlux(Date dateFlux) {
		this.dateFlux = dateFlux;
	}
	
	
	
	
}