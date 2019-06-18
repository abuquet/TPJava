//1.1.2 classe Main
package tests;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
	static int compteurcc = 1;
	static int compteurce;
	static ArrayList<Flux> fluxs;


	public static void main(String[] args) throws ParseException {

		genererClient(3);
		Logger logger = Logger.getLogger("logger");
		logger.log(Level.INFO, "Tableau des clients\n");
		afficherClients(alClients);

		Logger logComptes = Logger.getLogger("logComptes");
		logComptes.log(Level.INFO, "Affichage des comptes");
		genererComptes(alClients);
		afficherComptes(alComptes);

		Logger logHash = Logger.getLogger("logHash");
		logHash.log(Level.INFO, "Hashtable des comptes (avant XML)");
		genererHashtable(alComptes);
		afficherHCompte(hComptes);

		Logger logFlux1 = Logger.getLogger("logFlux1");
		logFlux1.log(Level.INFO, "Création des fluxs par fichier json");
		tabFlux = jsonFlux();
		afficherFlux();

		Logger logHash2 = Logger.getLogger("logHash2");
		logHash2.log(Level.INFO, "Hashtable après XML");
		alComptes = xmlComptes();
		hComptes = genererHashtable(alComptes);
		afficherHCompte(hComptes);

		Logger logComptes2 = Logger.getLogger("logComptes2");
		logComptes2.log(Level.INFO, "Comptes après application des fluxs");
		majSolde(tabFlux);
		afficherComptes(alComptes);
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
			CompteCourant compteC = new CompteCourant("compte courant", tabClients.get(i));
			alComptes.add(compteC);
			CompteEpargne compteE = new CompteEpargne("compte épargne", tabClients.get(i));
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

	public static Hashtable<Integer,Compte> genererHashtable(ArrayList<Compte> comptes) {
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

	

	public static void afficherFlux() {
		for(int i = 0; i < tabFlux.size(); i++)
		{
			System.out.println(tabFlux.get(i));
		}
	}

	public static void majSolde(ArrayList<Flux> flux) {
		for(int i = 0; i < flux.size(); i++) {
			for (int j = 0; j < alComptes.size(); j++) {
				if (flux.get(i).getClass() == Virement.class) {
					alComptes.get(j).setSolde((Virement)tabFlux.get(i));
				}else {
					alComptes.get(j).setSolde(tabFlux.get(i));
				}
			}			
		}
	}

	public static ArrayList<Flux> jsonFlux() throws ParseException{
		JSONParser parser = new JSONParser();
		Path path = Paths.get("flux.json");
		int id = 1;
		tabFlux.clear();

		try (BufferedReader json = Files.newBufferedReader(path)){
			Object obj = parser.parse(json);
			JSONArray flux = (JSONArray) obj;
			Iterator<?> iterator = flux.iterator();

			while (iterator.hasNext()) {
				JSONObject objFlux = (JSONObject) iterator.next();


				String classe = (String)objFlux.get("classe");
				String commentaire = (String)objFlux.get("commentaire");
				double montant = Double.parseDouble((String) objFlux.get("montant"));	
				int numCompteCible = (int) (long)objFlux.get("numCompteCible");
				boolean effectue = (boolean) objFlux.get("effectue");


				if(classe.equals("Debit")) {
					Debit debit = new Debit(commentaire,id++,montant,numCompteCible,effectue);
					tabFlux.add(debit);
				}
				else if(classe.equals("Credit")){
					Credit credit = new Credit(commentaire,id++,montant,numCompteCible,effectue);
					tabFlux.add(credit);
				}
				else if(classe.equals("Virement")) {
					int compteEmet = (int)(long)objFlux.get("compteEmet");
					Virement virement = new Virement(commentaire,id++,montant,numCompteCible,effectue,compteEmet);
					tabFlux.add(virement);
				}		
			}    
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tabFlux;
	}

	public static ArrayList<Compte> xmlComptes(){
		String nom;
		String prenom;
		String libelle;
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("compte.xml");

		try {
			Document document = builder.build(xmlFile);
			Element rootNode = document.getRootElement();
			List<Element> listComptes = rootNode.getChildren("compte");
			for (int i = 0; i < listComptes.size(); i++) {
				Element compte = listComptes.get(i);

				//Parsing et Creation du client
				nom = compte.getChild("client").getChildText("nom");
				prenom = compte.getChild("client").getChildText("prenom");
				Client client = new Client(nom, prenom);

				//Parsing et Creation de la liste de compte
				ArrayList<Compte> comptes = new ArrayList<Compte>();
				libelle = compte.getChildText("libelle");
				String classe = compte.getAttributeValue("classe");

				//Creation du compte
				if(classe.equals("courant")) {
					Compte compteCourant = new CompteCourant(libelle, client);
					comptes.add(compteCourant);
				} else if (classe.equals("epargne")){
					Compte compteEpargne = new CompteEpargne(libelle, client);
					comptes.add(compteEpargne);
				}
				alComptes.addAll(comptes);
			}
		} catch (IOException | JDOMException e) {
			System.out.println(e.getMessage());
		} 
		return alComptes;
	}


}