//1.1.2 classe Main
package tests;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import composants.Client;
import composants.Compte;
import composants.CompteCourant;
import composants.CompteEpargne;
import composants.Credit;
import composants.Debit;
import composants.Flux;
import composants.Virement;


public class Main {

	static ArrayList<Client> alClients = new ArrayList<Client>();
	static ArrayList<Compte> alComptes = new ArrayList<Compte>();
	static Hashtable<Integer, Compte> hComptes = new Hashtable<Integer, Compte>();
	static ArrayList<Flux> tabFlux = new ArrayList<Flux>();


	public static void main(String[] args) {
		genererClient(3);
		afficherClients(alClients);
		genererComptes(alClients);
		//afficherComptes(alComptes);
		hashCompte(alComptes);
		afficherHCompte(hComptes);
		genererFlux();
		afficherFlux();

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

	public static Hashtable<Integer,Compte> hashCompte(ArrayList<Compte> comptes) {
		for(int i = 0; i < comptes.size(); i++)
		{
			hComptes.put(comptes.get(i).getNumCompte(), comptes.get(i));
		}
		return hComptes;
	}

	public static void afficherHCompte(Hashtable<Integer,Compte> ht) {
		Enumeration e = ht.elements();
		Enumeration k = ht.keys();

		while(e.hasMoreElements())
			System.out.println(k.nextElement() + " : " + e.nextElement());
	}

	public static ArrayList<Flux> genererFlux() {
		Debit deb50 = new Debit("débit de 50€", 1, 50, 3, true);
		Credit cred100 = new Credit("crédit de 100,50€", 2, 100.5, 4, true);
		Credit cred1500 = new Credit("crédit de 1500 €", 3, 1500, 1, true);
		Virement vir50 = new Virement("virement de 50€ du compte 1 vers le 2", 4, 50, 2, true, 1);
		tabFlux.add(deb50);
		tabFlux.add(cred100);
		tabFlux.add(cred1500);
		tabFlux.add(vir50);
		return tabFlux;
	}

	public static void afficherFlux() {
		for(int i = 0; i < tabFlux.size(); i++)
		{
			System.out.println(tabFlux.get(i));
		}
	}

}
