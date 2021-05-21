package test;

import modele.*;

public class TestCase {

	// Teste la création d'une case.
	public static void testInitialisation() {

		System.out.println("--Initialisation case normale--");
		try {
			Case caseNorm = new Case();
			System.out.println("Fonctionnalité des étages (aucun étage=true) : " + (0==caseNorm.getNbEtage()));
			System.out.println("Fonctionnalité des personnages (aucun personnage=true) : " + (0==caseNorm.getNbPerso()));
		}
		catch (Throwable e) {
			System.out.println("Erreur : initialisation case normale");
			System.out.println(e);
		}
	}

	// Teste de modifier les étages d'une case.
	public static void testModificationEtage() {

		System.out.println("--Modification Etage--");
		try {
			System.out.println("Initialisation");
			Case caseTestEtage = new Case();
			System.out.println("Fonctionnalité de la constructibilité (psosible=true) : " + (true==caseTestEtage.Constructible()));

			System.out.println("Ajout d'étages");
			caseTestEtage.ajoutEtage();
			caseTestEtage.ajoutEtage();
			caseTestEtage.ajoutEtage();
			caseTestEtage.ajoutEtage();
			System.out.println("4 étages construits, constructibilité (impossible=true) : " + (false==caseTestEtage.Constructible()));
			caseTestEtage.ajoutEtage();
			System.out.println("Nombre d'étages construits (4=true, 5=false) : " + (4==caseTestEtage.getNbEtage()));
			System.out.println("Constructibilité (impossible=true) : " + (false==caseTestEtage.Constructible()));

			System.out.println("Enlever des étages");
			caseTestEtage.detruireEtage();
			caseTestEtage.detruireEtage();
			caseTestEtage.detruireEtage();
			caseTestEtage.detruireEtage();
			caseTestEtage.detruireEtage();
			System.out.println("Nombre d'étages final (0=true, -1=false) : " + (0==caseTestEtage.getNbEtage()));
		}
		catch (Throwable e) {
			System.out.println("Erreur : modification Etage");
			System.out.println(e);
		}
	}

	// Teste de modifier les personnages d'une case.
	public static void testModificationPerso() {

		System.out.println("--Modification Perso--");
		try {
			System.out.println("Initialisation");
			Case caseTestPerso = new Case();	// On teste l'initialisation.
			System.out.println("Test de présence à l'initialisation (aucun personnage=true) : " + (false==caseTestPerso.aPersonnage()));
			System.out.println("Test pour pouvoir poser un personnage (possible=true) : " + (true==caseTestPerso.peutPoserUnPerso()));
			System.out.println("Test du personnage présent (personnage 0=true) : " + (0==caseTestPerso.getNbPerso()));
			caseTestPerso.enleverPerso();		// On teste d'enlever un perso pas présent.
			System.out.println("Test de présence après avoir retiré un personnage non présent (aucun personnage=true) : " + (false==caseTestPerso.aPersonnage()));
			System.out.println("Test pour pouvoir poser un personnage (possible=true) : " + (true==caseTestPerso.peutPoserUnPerso()));

			System.out.println("Personnage invalide (joueur à numéro négatif)");
			caseTestPerso.mettrePerso(-1);		// On teste de mettre un personnage inexistant (négatif).
			System.out.println("Test de présence de personnage négatif (pas de personnage=true) : " + (false==caseTestPerso.aPersonnage()));
			System.out.println("Test pour poser un personnage sur un personnage négatif (possible=true) : " + (true==caseTestPerso.peutPoserUnPerso()));
			System.out.println("Test de présence du personnage (personnage -1=false)" + (-1!=caseTestPerso.getNbPerso()));

			System.out.println("Personnage du joueur 1");
			caseTestPerso.enleverPerso();
			caseTestPerso.mettrePerso(1);		// On teste le personnage du joueur 1..
			System.out.println("Test de présence du personnage du joueur 1 (présent=true) : " + (true==caseTestPerso.aPersonnage()));
			System.out.println("Test de possibilité de poser un personnage (impossible=true) : " + (false==caseTestPerso.peutPoserUnPerso()));
			System.out.println("Test du numéro du personnage (personnage 1=true) : " + (1==caseTestPerso.getNbPerso()));

			System.out.println("Personnage du joueur 2");
			caseTestPerso.mettrePerso(2);		// On teste de remplacer un personnage valide sans le bouger.
			System.out.println("Test de présence du personnage du joueur 1 (présent=true) : " + (false==caseTestPerso.aPersonnage()));
			System.out.println("Test de possibilité de mettre un autre personnage (impossible=true) : " + (false==caseTestPerso.peutPoserUnPerso()));
			System.out.println("Test d'override du personnage du joueur 1 par le joueur 2 (personnage 1=true) : " + (1==caseTestPerso.getNbPerso()));
			caseTestPerso.enleverPerso();
			caseTestPerso.mettrePerso(2);		// On teste le personnage du joueur 2.
			System.out.println("Test de présence du personnage du joueur 2 (présent=true) : " + (true==caseTestPerso.aPersonnage()));
			System.out.println("Test de possibilité de mettre un autre personnage (impossible=true) : " + (false==caseTestPerso.peutPoserUnPerso()));
			System.out.println("Test du numéro du personnage (personnage 2=true) : " + (2==caseTestPerso.getNbPerso()));

			System.out.println("Personnage invalide (joueur à numéro élevé)");
			caseTestPerso.enleverPerso();
			caseTestPerso.mettrePerso(5000000);	// On teste un personnage inexistant (trop haut).
			System.out.println("Test de présence du personnage à numéro trop élevé (absent=true) : " + (false==caseTestPerso.aPersonnage()));
			System.out.println("Test de possibilité de mettre un personnage (possible=true) : " + (true==caseTestPerso.peutPoserUnPerso()));
			System.out.println("Test de présence du personnage (personnage 0=true)" + (5000000!=caseTestPerso.getNbPerso()));
		}
		catch (Throwable e) {
			System.out.println("Erreur : modification Perso");
			System.out.println(e);
		}

	}
}