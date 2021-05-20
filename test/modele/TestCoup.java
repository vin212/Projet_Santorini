package test.modele;

import modele.Coup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structure.Point;

public class TestCoup {
    Coup coup;
    Coup coupDeplacement;
    Coup c;

    @BeforeEach
    public void setup() {
        coup = new Coup(new Point(2,1),1);
        coupDeplacement = new Coup(new Point(3,2),new Point(2,2), new Point(3,3), 1);
    }

    @Test
    public void testCoupNonDeplacement() {
        Assertions.assertEquals(0, new Point(2,1).CompareTo(coup.getDepart()));
        Assertions.assertEquals(1,coup.getJoueur());
        Assertions.assertNull(coup.getArrive());
        Assertions.assertFalse(coup.estDeplacement());
    }

    @Test
    public void testCoupDeplacement() {
        Assertions.assertEquals(0, new Point(3,2).CompareTo(coupDeplacement.getDepart()));
        Assertions.assertEquals(1,coupDeplacement.getJoueur());
        Assertions.assertEquals(0, new Point(2,2).CompareTo(coupDeplacement.getArrive()));
        Assertions.assertTrue(coupDeplacement.estDeplacement());
        Assertions.assertEquals(0, new Point(3,3).CompareTo(coupDeplacement.getConstruction()));
    }

    @Test
    public void testToString() {
        String expectedDeplacement = "Joueur : " + coupDeplacement.getJoueur() + " de " + coupDeplacement.getDepart() + " vers " + coupDeplacement.getArrive() + ". A construit en " + coupDeplacement.getConstruction();
        String expected = "Joueur : " + coup.getJoueur() + " Placé en " + coup.getDepart();

        Assertions.assertEquals(expectedDeplacement, coupDeplacement.toString());
        Assertions.assertEquals(expected, coup.toString());
    }
}
