package composants;

public class Debit extends Flux {

	

	public Debit(String commentaire, int identifiant, double montant, int numCompteCible, boolean effectue) {
		super(commentaire, identifiant, montant, numCompteCible, effectue);

	}

	@Override
	public String toString() {
		return "Debit [commentaire=" + commentaire + ", identifiant=" + identifiant + ", montant=" + montant
				+ ", numCompteCible=" + numCompteCible + ", effectue=" + effectue + ", dateFlux=" + dateFlux + "]";
	}
	
	

}
