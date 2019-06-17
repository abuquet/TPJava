package composants;
//1.2.1 classe Compte
public abstract class Compte {
	protected String libelle;
	protected double solde;
	protected int numCompte;
	protected static int count=1;
	protected Client client;
	
	public Compte(String libelle, Client client) {
		this.libelle = libelle;
		this.client = client;
		this.numCompte = count++;
		this.solde = 0;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(Flux flux) {
		if (flux.getClass() == Credit.class && this.getNumCompte() == flux.getNumCompteCible()) {
			this.solde = this.solde + flux.getMontant();			
		}
		else if (flux.getClass() == Debit.class && this.getNumCompte() == flux.getNumCompteCible()) {
			this.solde = this.solde - flux.getMontant();
		}
	}
	
	public void setSolde(Virement flux) {
		if (this.getNumCompte() == flux.getNumCompteCible()) {
			this.solde = this.solde + flux.getMontant();
		}
		else if (this.getNumCompte() == flux.getCompteEmet()) {
			this.solde = this.solde - flux.getMontant();
		}
		
		
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
