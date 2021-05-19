package modele;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structure.Point;

public class TestPlateau {
    Plateau plateau;

    @BeforeEach
    public void setup() {
        plateau = new Plateau(5,5);
    }

    //TODO
    @Test
    public void testAfficherPlateau_CMD() {
        //doCallRealMethod().when(plateau2).afficher_CMD();
        //plateau2.afficher_CMD();
        //verify(plateau2, times(1)).afficher_CMD();
    }

    @Test
    public void testConstruire() {
        Point point = new Point(1,0);

        plateau.Construire(point);
        Assertions.assertEquals(1, plateau.getNbEtage(point));

        plateau.Construire(point);
        plateau.Construire(point);

        Assertions.assertEquals(3,plateau.getNbEtage(point));

        // construire more than 4 etage
        plateau.Construire(point);
        plateau.Construire(point);
        plateau.Construire(point);

        Assertions.assertEquals(4,plateau.getNbEtage(point));
    }

    @Test
    public void testDetruireEtage() {
        Point point = new Point(1,0);

        plateau.Construire(point);
        plateau.Construire(point);

        plateau.detruireEtage(point);

        Assertions.assertEquals(1, plateau.getNbEtage(point));

        // detruire after 0 etage
        plateau.detruireEtage(point);
        plateau.detruireEtage(point);

        Assertions.assertEquals(0,plateau.getNbEtage(point));
    }

    @Test
    public void testConstructible() {
        Point point = new Point(1,0);

        plateau.Construire(point);
        plateau.Construire(point);
        plateau.Construire(point);

        Assertions.assertTrue(plateau.Constructible(point));


        plateau.Construire(point);

        Assertions.assertFalse(plateau.Constructible(point));
    }


    @Test
    public void testDeplacerPersonnage() {
        plateau.poserPersonnage(new Point(1,1),1);
        plateau.poserPersonnage(new Point(2,1),2);

        plateau.deplacerPersonnage(new Point(2,1),new Point(2,2));

        Assertions.assertEquals(0, plateau.quiEstIci(new Point(2,1)));
        Assertions.assertEquals(2,plateau.quiEstIci(new Point(2,2)));
        Assertions.assertEquals(1,plateau.quiEstIci(new Point(1,1)));
    }

    @Test
    public void testPeutPoserUnPerson() {
        plateau.poserPersonnage(new Point(1,1), 1);
        plateau.poserPersonnage(new Point(1,3), 2);

        //check if person can be placed after poserPersonnage
        Assertions.assertFalse(plateau.peutPoserUnPerso(new Point(1,1)));
        Assertions.assertFalse(plateau.peutPoserUnPerso(new Point(1,3)));

        // change position of the person
        plateau.deplacerPersonnage(new Point(1,3), new Point(2,2));

        // checks if new position is available to poser personnage
        Assertions.assertFalse(plateau.peutPoserUnPerso(new Point(2,2)));

        //checks if old position is placable for poser personnage
        Assertions.assertTrue(plateau.peutPoserUnPerso(new Point(1,3)));

        // check for out of border
        Assertions.assertFalse(plateau.peutPoserUnPerso(new Point(7,7)));

        // check for negative values
        Assertions.assertFalse(plateau.peutPoserUnPerso(new Point(-2,-2)));
    }

    @Test
    public void testApersonnage() {
        plateau.poserPersonnage(new Point(1,1), 1);
        plateau.poserPersonnage(new Point(1,3), 2);

        //check if there is a person
        Assertions.assertTrue(plateau.aPersonnage(new Point(1,1)));
        Assertions.assertTrue(plateau.aPersonnage(new Point(1,3)));
        Assertions.assertFalse(plateau.aPersonnage(new Point(1,2)));

        // change position of the person
        plateau.deplacerPersonnage(new Point(1,3), new Point(2,2));

        // checks old position
        Assertions.assertTrue(plateau.aPersonnage(new Point(2,2)));

        //checks new position
        Assertions.assertFalse(plateau.aPersonnage(new Point(1,3)));

        // check for out of border
        Assertions.assertFalse(plateau.aPersonnage(new Point(7,7)));

        // check for negative values
        Assertions.assertFalse(plateau.aPersonnage(new Point(-1,-3)));
    }
}
