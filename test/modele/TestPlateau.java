package modele;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structure.Point;

public class TestPlateau {
    Plateau plateau;

    @BeforeEach
    public void setup() {
        plateau = new Plateau(5,6);
    }
    
    @Test
    public void testInit() {
        Assertions.assertEquals(5, plateau.getHauteur());
        Assertions.assertEquals(6, plateau.getLargeur());
    }

    @Test
    public void testConstruire() {
        Point point = new Point(1,0);

        plateau.Construire(point);
        Assertions.assertEquals(1, plateau.getNbEtage(point));

        plateau.Construire(point);
        plateau.Construire(point);

        Assertions.assertEquals(3,plateau.getNbEtage(point));

        // construire plus de 4 etage
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

        // detruire apres 0 etage
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

        // vérifier si la personne peut être placée après poserPersonnage
        Assertions.assertFalse(plateau.peutPoserUnPerso(new Point(1,1)));
        Assertions.assertFalse(plateau.peutPoserUnPerso(new Point(1,3)));

        // changer la position de la personne
        plateau.deplacerPersonnage(new Point(1,3), new Point(2,2));

        // vérifie si un nouveau poste est disponible pour poser personnage
        Assertions.assertFalse(plateau.peutPoserUnPerso(new Point(2,2)));

        // vérifie si l'ancienne position est plausible pour poseur personnage
        Assertions.assertTrue(plateau.peutPoserUnPerso(new Point(1,3)));

        // vérifier hors frontière
        Assertions.assertFalse(plateau.peutPoserUnPerso(new Point(7,7)));

        // vérifier les valeurs négatives
        Assertions.assertFalse(plateau.peutPoserUnPerso(new Point(-2,-2)));
    }

    @Test
    public void testApersonnage() {
        plateau.poserPersonnage(new Point(1,1), 1);
        plateau.poserPersonnage(new Point(1,3), 2);

        // vérifier s'il y a une personne
        Assertions.assertTrue(plateau.aPersonnage(new Point(1,1)));
        Assertions.assertTrue(plateau.aPersonnage(new Point(1,3)));
        Assertions.assertFalse(plateau.aPersonnage(new Point(1,2)));

        // changer la position de la personne
        plateau.deplacerPersonnage(new Point(1,3), new Point(2,2));

        // vérifie l'ancienne position
        Assertions.assertTrue(plateau.aPersonnage(new Point(2,2)));

        //vérifie la nouvelle position
        Assertions.assertFalse(plateau.aPersonnage(new Point(1,3)));

        // vérifier hors frontière
        Assertions.assertFalse(plateau.aPersonnage(new Point(7,7)));

        // vérifier les valeurs négatives
        Assertions.assertFalse(plateau.aPersonnage(new Point(-1,-3)));
    }
}
