package composants;

public class Virement extends Flux {
	protected int compteEmet;

	public Virement(String commentaire, int identifiant, double montant, int numCompteCible, boolean effectue,
			int compteEmet) {
		super(commentaire, identifiant, montant, numCompteCible, effectue);
		this.compteEmet = compteEmet;
	}

	public int getCompteEmet() {
		return compteEmet;
	}

	public void setCompteEmet(int compteEmet) {
		this.compteEmet = compteEmet;
	}

	@Override
	public String toString() {
		return "Virement [compteEmet=" + compteEmet + ", commentaire=" + commentaire + ", identifiant=" + identifiant
				+ ", montant=" + montant + ", numCompteCible=" + numCompteCible + ", effectue=" + effectue + "]";
	}
	
	
}
