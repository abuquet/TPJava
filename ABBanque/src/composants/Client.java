package composants;
//1.1.1 Création de la classe client


public class Client {
	private String nom;
	private String prenom;
	private int numClient;
	private static int count = 1;
	
	public Client(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
		this.numClient = count++;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getNumClient() {
		return numClient;
	}

	/*public void setNumClient(int numClient) {
		this.numClient = numClient;
	}*/

	@Override
	public String toString() {
		return "Le client numéro " + numClient + " s'appelle " + prenom + " " + nom + ".";
	}
}