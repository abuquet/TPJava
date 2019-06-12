//1.1.2 classe Main
package tests;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import composants.Client;
import composants.Compte;
import composants.CompteCourant;
import composants.CompteEpargne;


public class Main {

	static ArrayList<Client> alClients = new ArrayList<Client>();
	static ArrayList<Compte> alComptes = new ArrayList<Compte>();
	static Hashtable<Integer, Compte> hComptes = new Hashtable<Integer, Compte>();


	public static void main(String[] args) {
		genererClient(3);
		afficherClients(alClients);
		genererComptes(alClients);
		//afficherComptes(alComptes);
		hashCompte(alComptes);
		afficherHCompte(hComptes);
	}

	public static void afficherClients(ArrayList<Client> clients) {
		for(int i = 0; i < clients.size(); i++)
		{
			System.out.println(clients.get(i));
		}
	}

	public static ArrayList<Client> genererClient(int nbrClients) {
		for(int i = 1; i < nbrClients+1; i++) {
			Client client = new Client("nom" + i, "prenom" + i);
			alClients.add(client);
		}
		return alClients;
	}

	public static ArrayList<Compte> genererComptes(ArrayList<Client> tabClients) {
		for(int i = 0; i < tabClients.size(); i++) {
			CompteCourant compteC = new CompteCourant("compte courant " + i, tabClients.get(i));
			alComptes.add(compteC);
			CompteEpargne compteE = new CompteEpargne("compte épargne " + i, tabClients.get(i));
			alComptes.add(compteE);
		}
		return alComptes;
	}

	public static void afficherComptes(ArrayList<Compte> comptes) {
		for(int i = 0; i < comptes.size(); i++)
		{
			System.out.println(comptes.get(i));
		}
	}
	
	public static Hashtable hashCompte(ArrayList<Compte> comptes) {
		for(int i = 0; i < comptes.size(); i++)
		{
			hComptes.put(i, comptes.get(i));
		}
		return hComptes;
	}
	
	public static void afficherHCompte(Hashtable ht) {
		Enumeration e = ht.elements();
		Enumeration k = ht.keys();

	    while(e.hasMoreElements())
	      System.out.println(k.nextElement() + " : " + e.nextElement());
	}

}
