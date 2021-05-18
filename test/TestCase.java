package test;

import java.io.*;
import modele.*;
import structure.*;


public class TestCase {

	public static void testInitialisation() {

		System.out.println("--Initialisation case normale--");
		try {
			Case caseNorm = new Case();
			System.out.println(0==caseNorm.nbEtage);
			System.out.println(0==caseNorm.nbPerso);
		}
		catch (Throwable e) {
			System.out.println("Erreur : initialisation case normale");
			System.out.println(e);
		}
	}

	public static void testModificationEtage() {

		System.out.println("--Modification Etage--");
		try {
			System.out.println("Initialisation");
			Case caseTestEtage = new Case();
			System.out.println(1==caseTestEtage.Constructible());

			System.out.println("Ajout d'étages");
			caseTestEtage.ajoutEtage(0);
			caseTestEtage.ajoutEtage(0);
			caseTestEtage.ajoutEtage(0);
			caseTestEtage.ajoutEtage(0);
			System.out.println(0==caseTestEtage.Constructible());
			caseTestEtage.ajoutEtage(0);
			System.out.println(0==caseTestEtage.Constructible());
			System.out.println(4==caseTestEtage.getNbEtage());

			System.out.println("Enlever des étages");
			caseTestEtage.detruireEtage(0);
			caseTestEtage.detruireEtage(0);
			caseTestEtage.detruireEtage(0);
			caseTestEtage.detruireEtage(0);
			caseTestEtage.detruireEtage(0);
			System.out.println(0==caseTestEtage.getNbEtage());
		}
		catch (Throwable e) {
			System.out.println("Erreur : modification Etage");
			System.out.println(e);
		}
	}

	public static void testModificationPerso() {

		System.out.println("--Modification Perso--");
		try {
			System.out.println("Initialisation");
			Case caseTestPerso = new Case();	//On teste l'initialisation.
			System.out.println(0==caseTestPerso.aPersonnage());
			System.out.println(1==caseTestPerso.peutPoserUnPerso());
			System.out.println(0==caseTestPerso.getNbPerso());
			caseTestPerso.enleverPerso();		//On teste d'enlever un perso pas présent.
			System.out.println(0==caseTestPerso.aPersonnage());
			System.out.println(1==caseTestPerso.peutPoserUnPerso());

			System.out.println("Personnage invalide (joueur à numéro négatif)");
			caseTestPerso.mettrePerso(-1);		//On teste de mettre un personnage inexistant (négatif).
			System.out.println(1==caseTestPerso.aPersonnage());
			System.out.println(0==caseTestPerso.peutPoserUnPerso());
			System.out.println(-1==caseTestPerso.getNbPerso());

			System.out.println("Personnage du joueur 1");
			caseTestPerso.mettrePerso(1);		//On teste de remplacer le personnage non valide sans bouger le premier.
			System.out.println(1==caseTestPerso.aPersonnage());
			System.out.println(0==caseTestPerso.peutPoserUnPerso());
			System.out.println(1==caseTestPerso.getNbPerso());
			caseTestPerso.enleverPerso();
			caseTestPerso.mettrePerso(1);		//On teste le personnage du joueur 1..
			System.out.println(1==caseTestPerso.aPersonnage());
			System.out.println(0==caseTestPerso.peutPoserUnPerso());
			System.out.println(1==caseTestPerso.getNbPerso());

			System.out.println("Personnage du joueur 2");
			caseTestPerso.mettrePerso(2);		//On teste de remplacer un personnage valide sans le bouger.
			System.out.println(1==caseTestPerso.aPersonnage());
			System.out.println(0==caseTestPerso.peutPoserUnPerso());
			System.out.println(2==caseTestPerso.getNbPerso());
			caseTestPerso.enleverPerso();
			caseTestPerso.mettrePerso(2);		//On teste le personnage du joueur 2.
			System.out.println(1==caseTestPerso.aPersonnage());
			System.out.println(0==caseTestPerso.peutPoserUnPerso());
			System.out.println(2==caseTestPerso.getNbPerso());

			System.out.println("Personnage invalide (joueur à numéro élevé)");
			caseTestPerso.enleverPerso();
			caseTestPerso.mettrePerso(5000000);	//On teste un personnage inexistant (trop haut).
			System.out.println(1==caseTestPerso.aPersonnage());
			System.out.println(0==caseTestPerso.peutPoserUnPerso());
			System.out.println(5000000==caseTestPerso.getNbPerso());
		}
		catch (Throwable e) {
			System.out.println("Erreur : modification Perso");
			System.out.println(e);
		}

	}
}