package test.modele;

import modele.Case;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCase {
    Case caseNorm;
    //TODO
    // test initialization

    @BeforeEach
    public void setup() {
        caseNorm = new Case();
        Assertions.assertEquals(0,caseNorm.getNbPerso());
        Assertions.assertEquals(0,caseNorm.getNbEtage());
    }

    @Test
    public void testModificationEtage() {
        Assertions.assertTrue(caseNorm.Constructible());

        System.out.println("Ajout d'étages");
        caseNorm.ajoutEtage();
        caseNorm.ajoutEtage();
        caseNorm.ajoutEtage();
        caseNorm.ajoutEtage();

        Assertions.assertFalse(caseNorm.Constructible());

        caseNorm.ajoutEtage();

        Assertions.assertFalse(caseNorm.Constructible());
        Assertions.assertEquals(4,caseNorm.getNbEtage());

        System.out.println("Enlever des étages");
        caseNorm.detruireEtage();
        caseNorm.detruireEtage();
        caseNorm.detruireEtage();
        caseNorm.detruireEtage();
        caseNorm.detruireEtage();

        Assertions.assertEquals(0,caseNorm.getNbEtage());
    }

    @Test
    public void testModificationPerso() {
        Assertions.assertFalse(caseNorm.aPersonnage());

        Assertions.assertTrue(caseNorm.peutPoserUnPerso());

        Assertions.assertEquals(0, caseNorm.getNbPerso());

        caseNorm.enleverPerso();		//On teste d'enlever un perso pas présent.

        Assertions.assertFalse(caseNorm.aPersonnage());
        Assertions.assertTrue(caseNorm.peutPoserUnPerso());

        //Personnage invalide (joueur à numéro négatif)
        caseNorm.mettrePerso(-1);		//On teste de mettre un personnage inexistant (négatif).

        Assertions.assertFalse(caseNorm.aPersonnage());
        Assertions.assertFalse(caseNorm.peutPoserUnPerso());
        Assertions.assertEquals(-1,caseNorm.getNbPerso());

        //Personnage du joueur 1
        caseNorm.mettrePerso(1);		//On teste de remplacer le personnage non valide sans bouger le premier.

        Assertions.assertTrue(caseNorm.aPersonnage());
        Assertions.assertFalse(caseNorm.peutPoserUnPerso());
        Assertions.assertEquals(1,caseNorm.getNbPerso());

        caseNorm.enleverPerso();
        caseNorm.mettrePerso(1);		//On teste le personnage du joueur 1..

        Assertions.assertTrue(caseNorm.aPersonnage());
        Assertions.assertFalse(caseNorm.peutPoserUnPerso());
        Assertions.assertEquals(1,caseNorm.getNbPerso());

        //Personnage du joueur 2
        caseNorm.mettrePerso(2);		//On teste de remplacer un personnage valide sans le bouger.

        Assertions.assertTrue(caseNorm.aPersonnage());
        Assertions.assertFalse(caseNorm.peutPoserUnPerso());
        Assertions.assertEquals(2, caseNorm.getNbPerso());

        caseNorm.enleverPerso();
        caseNorm.mettrePerso(2);		//On teste le personnage du joueur 2.

        Assertions.assertTrue(caseNorm.aPersonnage());
        Assertions.assertFalse(caseNorm.peutPoserUnPerso());
        Assertions.assertEquals(2,caseNorm.getNbPerso());

        //Personnage invalide (joueur à numéro élevé)
        caseNorm.enleverPerso();
        caseNorm.mettrePerso(5000000);	//On teste un personnage inexistant (trop haut).

        Assertions.assertTrue(caseNorm.aPersonnage());
        Assertions.assertFalse(caseNorm.peutPoserUnPerso());
        Assertions.assertEquals(5000000,caseNorm.getNbPerso());
    }
}
