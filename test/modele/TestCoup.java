package test.modele;

import modele.Coup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structure.Point;

public class TestCoup {
    Coup coup;
    Coup coupDeplacement;
    Coup coupVide;

    @BeforeEach
    public void setup() {
        coupVide = new Coup();
        coup = new Coup(new Point(2,1),1);
        coupDeplacement = new Coup(new Point(3,2),new Point(2,2), new Point(3,3), 1);
    }

    @Test
    public void testCoupNonDeplacement() {
        Assertions.assertEquals(0, new Point(2,1).compareTo(coup.getDepart()));
        Assertions.assertEquals(1,coup.getJoueur());
        Assertions.assertNull(coup.getArrive());
        Assertions.assertFalse(coup.estDeplacement());
    }

    @Test
    public void testCoupDeplacement() {
        Assertions.assertEquals(0, new Point(3,2).compareTo(coupDeplacement.getDepart()));
        Assertions.assertEquals(1,coupDeplacement.getJoueur());
        Assertions.assertEquals(0, new Point(2,2).compareTo(coupDeplacement.getArrive()));
        Assertions.assertTrue(coupDeplacement.estDeplacement());
        Assertions.assertEquals(0, new Point(3,3).compareTo(coupDeplacement.getConstruction()));
    }

    @Test
    public void testCoupVide() {
        Assertions.assertNull(coupVide.getDepart());
        Assertions.assertEquals(0,coupVide.getJoueur());
        Assertions.assertNull(coupVide.getArrive());
        Assertions.assertFalse(coupVide.estDeplacement());
        Assertions.assertNull(coupVide.getConstruction());
    }

    @Test
    public void testSetters() {
        // test construction
        coupVide.setConstruction(new Point(2,2));
        coup.setConstruction(new Point(2,1));

        Assertions.assertEquals(0 , coupVide.getConstruction().compareTo(new Point(2,2)));
        Assertions.assertEquals(0 , coup.getConstruction().compareTo(new Point(2,1)));
        Assertions.assertEquals(0 , coupDeplacement.getConstruction().compareTo(new Point(3,3)));

       // test construction with negative values
        coup.setConstruction(new Point(-2,-2));
        coupVide.setConstruction(new Point(-1,-2));

        Assertions.assertEquals( 0 , coup.getConstruction().compareTo(new Point(-2,-2))); // should fail!!!
        Assertions.assertEquals( 0 , coupVide.getConstruction().compareTo(new Point(-1,-2))); // should fail!!!

        // test with bigger than plateau values
        coup.setConstruction(new Point(500,500));
        coupVide.setConstruction(new Point(400,400));

        Assertions.assertEquals( 0 , coup.getConstruction().compareTo(new Point(500,500))); // should fail!!!
        Assertions.assertEquals( 0 , coupVide.getConstruction().compareTo(new Point(400,400))); // should fail!!!

        // test deplacement
        coupVide.setDeplacement(new Point(1,1),new Point(2,1));

        Assertions.assertEquals( 0 , coupVide.getDepart().compareTo(new Point(1,1)));
        Assertions.assertEquals(0, coupDeplacement.getDepart().compareTo(new Point(3,2)));

        // with big numbers
        coupVide.setDeplacement(new Point(500,500),new Point(600, 600));

        Assertions.assertEquals( 0 , coupVide.getDepart().compareTo(new Point(500,500))); // should fail!!!

        // test joueur
        Assertions.assertEquals(0, coupVide.getJoueur());
        Assertions.assertEquals(1,coup.getJoueur());
        Assertions.assertEquals(1, coupDeplacement.getJoueur());

        coupVide.setJoueur(-1);

        Assertions.assertEquals(-1, coupVide.getJoueur());
    }

    @Test
    public void testToString() {
        String expectedDeplacement = "Joueur : " + coupDeplacement.getJoueur() + " de " + coupDeplacement.getDepart() + " vers " + coupDeplacement.getArrive() + ". A construit en " + coupDeplacement.getConstruction();
        String expected = "Joueur : " + coup.getJoueur() + " Placé en " + coup.getDepart();

        Assertions.assertEquals(expectedDeplacement, coupDeplacement.toString());
        Assertions.assertEquals(expected, coup.toString());
    }
}
