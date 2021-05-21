package test.modele;

import modele.Jeu;
import modele.Plateau;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structure.Point;

public class TestJeu {
    Jeu jeu;

    @BeforeEach
    public void setup() {
        jeu = new Jeu();

        // test for creation jeu class
        Assertions.assertEquals(5,jeu.getHauteurPlateau());
        Assertions.assertEquals(5,jeu.getLargeurPlateau());
        Assertions.assertEquals(1,jeu.getJoueurEnJeu());
    }

    @Test
    public void testAfficherPlateau() {
        // a tester
    }

    @Test
    public void testConstruire() {
        Point point = new Point(1,1);
        jeu.Construire(point);

        // check if Constructible returns true
        // check nb etage
        Assertions.assertTrue(jeu.Constructible(point));
        Assertions.assertEquals(1,jeu.getNbEtage(point));

        jeu.Construire(point);
        jeu.Construire(point);

        //check nb etage
        Assertions.assertEquals(3,jeu.getNbEtage(point));

        jeu.Construire(point);
        jeu.Construire(point);

        // check if construction returns false after etage 4
        // check etage after etage > 4
        Assertions.assertFalse(jeu.Constructible(point));
        Assertions.assertEquals(4,jeu.getNbEtage(point));
    }

    @Test
    public void testConstruireWithNegativePoint() {
        Point point = new Point(-1,-1);

        jeu.Construire(point);

        // check if Constructible returns false
        // check nb etage
        Assertions.assertFalse(jeu.Constructible(point));
        Assertions.assertEquals(-1,jeu.getNbEtage(point));
    }

    @Test
    public void testConstruireWithBiggerThanBoardPoint() {
        Point point = new Point(6,7);

        jeu.Construire(point);

        // check if Constructible returns false
        // check nb etage
        Assertions.assertFalse(jeu.Constructible(point));
        Assertions.assertEquals(-1,jeu.getNbEtage(point));
    }

    @Test
    public void testDetruireEtage() {
        Point point = new Point(1,1);

        // detruire etage before Construire
        jeu.detruireEtage(point);

        Assertions.assertEquals(0, jeu.getNbEtage(point));

        jeu.Construire(point);
        jeu.Construire(point);

        jeu.detruireEtage(point);

        Assertions.assertEquals(1,jeu.getNbEtage(point));

        jeu.Construire(point);
        jeu.Construire(point);
        jeu.Construire(point);

        jeu.detruireEtage(point);

        Assertions.assertEquals(3,jeu.getNbEtage(point));

        jeu.Construire(point);
        jeu.Construire(point);
        jeu.Construire(point);

        jeu.detruireEtage(point);

        // check after running Construire method over etage > 4
        Assertions.assertEquals(3,jeu.getNbEtage(point));
    }

    @Test
    public void testConstructible() {
        // test negative values
        Point pointNegative = new Point(-1,-1);

        Assertions.assertFalse(jeu.Constructible(pointNegative));

        // test bigger than board values
        Point pointEnorme = new Point(7,7);

        Assertions.assertFalse(jeu.Constructible(pointEnorme));
    }

    @Test
    public void testPeutPoserUnPerso() {
        jeu.poserPersonnage(new Point(1,1), 1);
        jeu.poserPersonnage(new Point(3,3), 1);

        jeu.poserPersonnage(new Point(2,3), 2);
        jeu.poserPersonnage(new Point(4,4), 2);

        //check if person can be placed after poserPersonnage
        Assertions.assertFalse(jeu.peutPoserUnPerso(new Point(1,1)));
        Assertions.assertFalse(jeu.peutPoserUnPerso(new Point(3,3)));

        // change position of the person
        jeu.deplacerPersonnage(new Point(1,1), new Point(2,2));

        // checks if new position is available to poser personnage
        Assertions.assertFalse(jeu.peutPoserUnPerso(new Point(2,2)));

        //checks if old position is placable for poser personnage
        Assertions.assertTrue(jeu.peutPoserUnPerso(new Point(1,3)));

        // check for out of border
        Assertions.assertFalse(jeu.peutPoserUnPerso(new Point(7,7)));

        // check for negative values
        Assertions.assertFalse(jeu.peutPoserUnPerso(new Point(-2,-2)));
    }

    @Test
    public void testAPersonnage() {
        jeu.poserPersonnage(new Point(2,1), 1);
        jeu.poserPersonnage(new Point(1,3), 1);

        jeu.poserPersonnage(new Point(2,2), 2);
        jeu.poserPersonnage(new Point(4,3), 2);

        //check if there is a person
        Assertions.assertTrue(jeu.aPersonnage(new Point(2,1)));
        Assertions.assertTrue(jeu.aPersonnage(new Point(4,3)));
        Assertions.assertFalse(jeu.aPersonnage(new Point(1,2)));

        // change position of the person
        jeu.deplacerPersonnage(new Point(1,3), new Point(2,2));

        // checks old position
        Assertions.assertTrue(jeu.aPersonnage(new Point(2,2)));

        //checks new position
        Assertions.assertFalse(jeu.aPersonnage(new Point(1,4)));

        // check for out of border
        Assertions.assertFalse(jeu.aPersonnage(new Point(7,7)));

        // check for negative values
        Assertions.assertFalse(jeu.aPersonnage(new Point(-1,-3)));
    }

    // TODO CHECK FOR nbPerso2
    @Test
    public void testDeplacerEtPoserPersonnage() {
        jeu.poserPersonnage(new Point(3,1),1);
        jeu.poserPersonnage(new Point(2,1),1);

        jeu.poserPersonnage(new Point(3,3),2);
        jeu.poserPersonnage(new Point(4,5),2);

        jeu.deplacerPersonnage(new Point(2,1),new Point(2,2));

        Assertions.assertEquals(0, jeu.quiEstIci(new Point(2,1)));
        Assertions.assertEquals(1,jeu.quiEstIci(new Point(3,1)));
    }

    @Test
    public void testEnleverPerso() {
        // check before poser personnage
        Assertions.assertEquals(-1,jeu.enleverPerso(new Point(3,2)));

        jeu.poserPersonnage(new Point(3,1),1);
        jeu.poserPersonnage(new Point(2,1),1);

        jeu.poserPersonnage(new Point(3,3),2);
        jeu.poserPersonnage(new Point(4,3),2);

        // check if person exists in that position before enlever person
        Assertions.assertEquals(2, jeu.quiEstIci(new Point(4,3)));

        // enlever personnage
        jeu.enleverPerso(new Point(4,3));

        // check if person removed
        Assertions.assertEquals(0,jeu.quiEstIci(new Point(4,3)));

        // poser le personnage
        jeu.poserPersonnage(new Point(4,3),2);

        // change position of the person
        jeu.deplacerPersonnage(new Point(2,1), new Point(2,2));

        // enlever personnage apres deplacer
        jeu.enleverPerso(new Point(2,2));

        Assertions.assertEquals(0,jeu.quiEstIci(new Point(2,2)));
        Assertions.assertEquals(0,jeu.quiEstIci(new Point(2,1)));

        // check for negative values
        jeu.poserPersonnage(new Point(-1,-2),1);
        jeu.enleverPerso(new Point(-1, -2));

        Assertions.assertEquals(-1,jeu.quiEstIci(new Point(-1,-2)));

        // check for big numbers
        jeu.poserPersonnage(new Point(50000,60000),1);
        jeu.enleverPerso(new Point(50000,60000));

        Assertions.assertEquals(-1,jeu.quiEstIci(new Point(50000,60000)));
    }

    @Test
    public void testQuiEstIci() {
        // test before poser personnage
        Assertions.assertEquals(0,jeu.quiEstIci(new Point(1,2)));

        //poser les personnages
        jeu.poserPersonnage(new Point(1,1),1);
        jeu.poserPersonnage(new Point(2,1),1);

        jeu.poserPersonnage(new Point(3,2),2);
        jeu.poserPersonnage(new Point(4,1),2);

        // check for positions
        Assertions.assertEquals(1,jeu.quiEstIci(new Point(2,1)));
        Assertions.assertEquals(1,jeu.quiEstIci(new Point(1,1)));
        Assertions.assertEquals(2,jeu.quiEstIci(new Point(3,2)));
        Assertions.assertEquals(2,jeu.quiEstIci(new Point(4,1)));

        // deplacer les personnages
        jeu.deplacerPersonnage(new Point(2,1), new Point(2,2));
        jeu.deplacerPersonnage(new Point(4,1), new Point(4,2));

        // check for new position
        Assertions.assertEquals(1,jeu.quiEstIci(new Point(2,2)));
        Assertions.assertEquals(2,jeu.quiEstIci(new Point(4,2)));

        // check for old positions
        Assertions.assertEquals(0,jeu.quiEstIci(new Point(2,1)));
        Assertions.assertEquals(0, jeu.quiEstIci(new Point(4,1)));

        // deplacer a la position negative
        jeu.deplacerPersonnage(new Point(1,1), new Point(-1,-1));

        // check if position is the same
        Assertions.assertEquals(1,jeu.quiEstIci(new Point(1,1)));

        // check negative deplacement is false
        Assertions.assertEquals(-1, jeu.quiEstIci(new Point(-1,-1)));
    }

    @Test
    public void testTour() {
        jeu.poserPersonnage(new Point(3,1),1);
        jeu.poserPersonnage(new Point(2,1),2);

        Assertions.assertEquals(0,jeu.getTour());

        jeu.subTour();

        // check if returns negative
        Assertions.assertEquals(0,jeu.getTour());

        jeu.addTour();
        jeu.addTour();

        Assertions.assertEquals(2,jeu.getTour());

        jeu.subTour();

        Assertions.assertEquals(1,jeu.getTour());

        jeu.subTour();
        jeu.subTour();

        // check if returns negative
        Assertions.assertEquals(0,jeu.getTour());
    }

    @Test
    public void testgetPosiPions() {
        // check before poser les personnages
        Assertions.assertNull(jeu.getPosiPions(1));
        Assertions.assertNull(jeu.getPosiPions(2));

        //poser les personnages
        jeu.poserPersonnage(new Point(1,1),1);
        jeu.poserPersonnage(new Point(2,1),1);

        jeu.poserPersonnage(new Point(3,2),2);
        jeu.poserPersonnage(new Point(4,1),2);

        // check for posi pions
        Assertions.assertEquals(0, jeu.getPosiPions(1)[0].CompareTo(new Point(1,1)));
        Assertions.assertEquals(0, jeu.getPosiPions(1)[1].CompareTo(new Point(2,1)));
        Assertions.assertEquals(0, jeu.getPosiPions(2)[0].CompareTo(new Point(3,2)));
        Assertions.assertEquals(0, jeu.getPosiPions(2)[1].CompareTo(new Point(4,1)));

        // check for empty position
        Assertions.assertEquals(-1, jeu.getPosiPions(2)[0].CompareTo(new Point(3,4)));

        // check for negative position after deplacer
        jeu.deplacerPersonnage(new Point(3,2), new Point(-1,-2));

        Assertions.assertEquals(1, jeu.getPosiPions(2)[0].CompareTo(new Point(-1,-2)));
        Assertions.assertEquals(0, jeu.getPosiPions(2)[0].CompareTo(new Point(3,2)));
    }

    @Test
    public void testHistoire() {
        // a tester
    }

    @Test
    public void testGagnant() {
        // a tester
    }

    @Test
    public void testToString() {
        Plateau p = new Plateau(5, 5);
        // before placing personnages
        String expected = "Au joueur " + jeu.getJoueurEnJeu() + " de jouer sur le plateau :\n" + p;

        Assertions.assertEquals(expected, jeu.toString());

        //poser les personnages sur le plateau
        p.poserPersonnage(new Point(1,1),1);
        p.poserPersonnage(new Point(2,1),1);

        p.poserPersonnage(new Point(3,2),2);
        p.poserPersonnage(new Point(4,1),2);

        //poser les personnages
        jeu.poserPersonnage(new Point(1,1),1);
        jeu.poserPersonnage(new Point(2,1),1);

        jeu.poserPersonnage(new Point(3,2),2);
        jeu.poserPersonnage(new Point(4,1),2);

        String expected2 = "Au joueur " + jeu.getJoueurEnJeu() + " de jouer sur le plateau :\n" + p;

        Assertions.assertEquals(expected2, jeu.toString());

    }
}
