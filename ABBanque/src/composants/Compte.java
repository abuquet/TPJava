package composants;
//1.2.1 classe Compte
public abstract class Compte {
	protected String libelle;
	protected Flux solde;
	protected int numCompte;
	protected static int count=1;
	protected Client client;
	
	public Compte(String libelle, Client client) {
		this.libelle = libelle;
		this.client = client;
		this.numCompte = count++;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Flux getSolde() {
		return solde;
	}

	public void setSolde(Flux solde) {
		this.solde = solde;
	}

	public int getNumCompte() {
		return numCompte;
	}

	public void setNumCompte(int numCompte) {
		this.numCompte = numCompte;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return libelle + " : solde=" + solde + ", numCompte=" + numCompte + ", client=" + client;
	}
}
