package composants;

public class Credit extends Flux {

	public Credit(String commentaire, int identifiant, double montant, int numCompteCible, boolean effectue) {
		super(commentaire, identifiant, montant, numCompteCible, effectue);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Credit [commentaire=" + commentaire + ", identifiant=" + identifiant + ", montant=" + montant
				+ ", numCompteCible=" + numCompteCible + ", effectue=" + effectue + "]";
	}
	

}