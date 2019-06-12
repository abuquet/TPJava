//1.1.2 classe Main
package tests;

import java.util.*;

import composants.Client;


public class Main {
	
	static ArrayList<Client> alClients = new ArrayList<Client>();

	public static void main(String[] args) {

		genererClient(3);
		afficherClients(alClients);
		


	}

	public static void afficherClients(ArrayList<Client> clients) {
		for(int i = 0; i < clients.size(); i++)
		{
			System.out.println(clients.get(i));
		}

	}
	
	public static void genererClient(int nbrClients) {
		
		for(int i = 1; i < nbrClients+1; i++) {
			Client client = new Client("nom" + i, "prenom" + i);
			alClients.add(client);
		}
		
	}

}
