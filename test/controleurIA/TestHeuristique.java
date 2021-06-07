package test.controleurIA;

import controleurIA.Heuristique;
import modele.Jeu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structure.Point;

import java.util.ArrayList;

public class TestHeuristique {
    Heuristique heuristique;
    Jeu jeu = new Jeu();

    @BeforeEach
    public void setup() {
        heuristique = new Heuristique(jeu);
    }

    @Test
    public void testObserveEtEvaluationHauteur() {
        jeu.poserPersonnage(new Point(1,1),1);
        jeu.poserPersonnage(new Point(2,2),1);

        jeu.poserPersonnage(new Point(4,2),2);
        jeu.poserPersonnage(new Point(3,1),2);

        // check if observe contains position of the nbPersons
        Assertions.assertFalse(heuristique.observes(new Point(2,1)).toString().contains("( 1, 1)"));
        Assertions.assertFalse(heuristique.observes(new Point(2,1)).toString().contains("( 2, 2)"));
        Assertions.assertTrue(heuristique.observes(new Point(2,1)).toString().contains("( 2, 3)"));

        // detruire etage avant construire
        jeu.detruireEtage(new Point(1,0));
        jeu.deplacerPersonnage(new Point(1,1),new Point(1,0));

        Point[] pointEval = jeu.getPosiPions(1);

        // check if returns negative after detruire
        Assertions.assertEquals(0,heuristique.evaluationHauteur(pointEval));

        // move perso1
        jeu.deplacerPersonnage(new Point(2,2),new Point(2,3));
        jeu.Construire(new Point(2,4));

        // move perso2
        jeu.deplacerPersonnage(new Point(4,2),new Point(4,1));
        jeu.Construire(new Point(4,2));
        jeu.Construire(new Point(4,2));

        jeu.deplacerPersonnage(new Point(3,1),new Point(3,2));
        jeu.Construire(new Point(3,3));

        // check point after deplacer et construire
        Assertions.assertFalse(heuristique.observes(new Point(2,1)).toString().contains("( 2, 3)"));
        Assertions.assertFalse(heuristique.observes(new Point(2,1)).toString().contains("( 2, 4)"));
        Assertions.assertTrue(heuristique.observes(new Point(2,1)).toString().contains("( 2, 2)"));

        // get posi pions for evaluationHauteur
        Point[] p1 = jeu.getPosiPions(1);
        Point[] p2 = jeu.getPosiPions(2);
        // check evaluationHauteur with both pions at 0 etages
        Assertions.assertEquals(0,heuristique.evaluationHauteur(p1));
        Assertions.assertEquals(0,heuristique.evaluationHauteur(p2));

        // deplacer perso1
        jeu.deplacerPersonnage(new Point(2,3),new Point(2,4));

        // deplacer perso2
        jeu.deplacerPersonnage(new Point(4,1),new Point(4,2));
        jeu.deplacerPersonnage(new Point(3,2),new Point(3,3));

        // check after one pawn at etage = 1
        p1 = jeu.getPosiPions(1);
        p2 = jeu.getPosiPions(2);

        Assertions.assertEquals(1,heuristique.evaluationHauteur(p1));
        Assertions.assertEquals(3,heuristique.evaluationHauteur(p2));

        ArrayList<ArrayList<Point>> successeurP1J1 = heuristique.observes(p1[0]);

        // construire 3 etages around nbPerso1[0]
        jeu.Construire(new Point(1,1));
        jeu.Construire(new Point(2,0));
        jeu.Construire(new Point(0,0));

        Assertions.assertEquals(3,heuristique.peutMonter(p1[0], successeurP1J1.get(0)));

        // check after detruire etage
        jeu.detruireEtage(new Point(0,0));

        Assertions.assertEquals(2,heuristique.peutMonter(p1[0], successeurP1J1.get(0)));
    }

    @Test
    public void testPeutBouger() {
        jeu.poserPersonnage(new Point(1,1),1);
        jeu.poserPersonnage(new Point(2,2),1);

        jeu.poserPersonnage(new Point(1,2),2);
        jeu.poserPersonnage(new Point(3,1),2);

        Point[] p1 = jeu.getPosiPions(1);
        Point[] p2 = jeu.getPosiPions(2);

        ArrayList<ArrayList<Point>> successeurP1J1 = heuristique.observes(p1[0]);

        Assertions.assertEquals(6,heuristique.peutBouger(successeurP1J1.get(0)));

        // construire autour de pion
        jeu.Construire(new Point(2,1));
        jeu.Construire(new Point(2,1));

        jeu.Construire(new Point(1,0));
        jeu.Construire(new Point(1,0));

        jeu.Construire(new Point(0,0));

        jeu.Construire(new Point(2,1));

        successeurP1J1 = heuristique.observes(p1[0]);

        Assertions.assertEquals(4,heuristique.peutBouger(successeurP1J1.get(0)));

        // detruire et verifier
        jeu.detruireEtage(new Point(1,0));

        successeurP1J1 = heuristique.observes(p1[0]);

        Assertions.assertEquals(5,heuristique.peutBouger(successeurP1J1.get(0)));
    }
}