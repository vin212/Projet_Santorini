package test.modele;

import modele.Joueur;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structure.Point;

public class TestJoueur {
    Joueur joueur;

    @BeforeEach
    public void setup() {
        joueur = new Joueur();
    }

    @Test
    public void testPlacerPerso() {
        // tester les valeurs normales
        Point point1 = new Point(2,1);
        Point point2 = new Point(2,3);

        Assertions.assertEquals(0,joueur.placerPerso(point1));
        Assertions.assertEquals(0,joueur.placerPerso(point2));

        // tester la 3ème placement
        Point point3 = new Point(2,4);

        Assertions.assertEquals(-1,joueur.placerPerso(point3));
    }

    @Test
    public void testPlacerPersoWithNegativeValues() {
        Point pointNegative = new Point(-2,-5); // devrait retourner -1

        Assertions.assertEquals(0,joueur.placerPerso(pointNegative));
    }

    @Test
    public void testPlacerPersoWithBigValues() {
        Point pointEnorme = new Point(3000000, 500000); // devrait retourner -1 si plus grand que les valeurs du plateau

        Assertions.assertEquals(0,joueur.placerPerso(pointEnorme));
    }

    @Test
    public void testPlacerPerso1EtPlacePerso2() {
        joueur.placerPerso1(new Point(1,2));
        joueur.placerPerso2(new Point(3,2));

        Assertions.assertEquals(1, joueur.checkPerso(new Point(1,2)));
        Assertions.assertEquals(2, joueur.checkPerso(new Point(3,2)));
        Assertions.assertEquals(-1, joueur.checkPerso(new Point(2,2)));

        // change place of the first person
        joueur.deplacerPerso(new Point(1,2),new Point(4,4));

        Assertions.assertEquals(1, joueur.checkPerso(new Point(4,4)));
        Assertions.assertEquals(-1, joueur.checkPerso(new Point(1,2)));

        // check for negative values
        joueur.deplacerPerso(new Point(3,2), new Point(-3,-3));
        Assertions.assertEquals(-1,joueur.checkPerso(new Point(-3,-3)));
        Assertions.assertEquals(2,joueur.checkPerso(new Point(3,2)));

        // check for big values
        joueur.deplacerPerso(new Point(4,4), new Point(50000,50000));
        Assertions.assertEquals(1,joueur.checkPerso(new Point(50000,50000))); // devrait renvoyer -1 lorsqu'il est supérieur aux valeurs du plateau
    }

    @Test
    public void testEnleverPerso() {
        // vérifier avant création
        Assertions.assertEquals(-1,joueur.enleverPerso(new Point(1,2)));

        joueur.placerPerso1(new Point(1,2));
        joueur.placerPerso2(new Point(3,2));


        Assertions.assertEquals(0,joueur.enleverPerso(new Point(1,2)));

        // vérifier après avoir supprimé la personne
        Assertions.assertEquals(-1,joueur.enleverPerso(new Point(1,2)));

        // vérifier les valeurs négatives
        joueur.placerPerso1(new Point(-3,-3));

        Assertions.assertEquals(-1,joueur.enleverPerso(new Point(-3,-3)));
    }

    @Test
    public void testToString() {
        joueur.placerPerso1(new Point(2,1));
        joueur.placerPerso2(new Point(3,3));
        joueur.deplacerPerso(new Point(2,1), new Point(2,2));

        String expected = "Etat : " + joueur.getAction() + "Pion 1 : " + joueur.getPosiPions()[0] + "\n Pion 2 : " + joueur.getPosiPions()[1];

        Assertions.assertEquals(expected,joueur.toString());
    }
}
