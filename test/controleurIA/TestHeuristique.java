package test.controleurIA;

import controleurIA.Heuristique;
import modele.Jeu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structure.Point;

public class TestHeuristique {
    Heuristique heuristique;
    Jeu jeu = new Jeu();

    @BeforeEach
    public void setup() {
        heuristique = new Heuristique(jeu);
    }

    @Test
    public void testObserve() {
        jeu.poserPersonnage(new Point(1,1),1);
        jeu.poserPersonnage(new Point(2,2),1);

        jeu.poserPersonnage(new Point(4,2),2);
        jeu.poserPersonnage(new Point(3,1),2);

        // check if observe contains position of the nbPersons
        Assertions.assertFalse(heuristique.observes(new Point(2,1)).toString().contains("( 1, 1)"));
        Assertions.assertFalse(heuristique.observes(new Point(2,1)).toString().contains("( 2, 2)"));
        Assertions.assertTrue(heuristique.observes(new Point(2,1)).toString().contains("( 2, 3)"));

        jeu.deplacerPersonnage(new Point(2,2),new Point(2,3));
        jeu.Construire(new Point(2,4));

        // check point after deplacer et construire
        Assertions.assertFalse(heuristique.observes(new Point(2,1)).toString().contains("( 2, 3)"));
        Assertions.assertFalse(heuristique.observes(new Point(2,1)).toString().contains("( 2, 4)"));
        Assertions.assertTrue(heuristique.observes(new Point(2,1)).toString().contains("( 2, 2)"));
    }
}
