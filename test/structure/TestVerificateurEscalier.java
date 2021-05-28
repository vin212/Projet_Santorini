package test.structure;

import modele.Jeu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structure.Point;
import structure.VerificateurEscalier;

public class TestVerificateurEscalier {
    Jeu jeu;
    VerificateurEscalier vEscalier;

    @BeforeEach
    public void setup() {
        jeu = new Jeu();
        vEscalier = new VerificateurEscalier(jeu);
    }

    @Test
    public void testVerificateurEscalier() {
        jeu.poserPersonnage(new Point(1,2),1);
        jeu.poserPersonnage(new Point(2,2),1);

        jeu.poserPersonnage(new Point(3,3),2);
        jeu.poserPersonnage(new Point(4,3),2);

        jeu.deplacerPersonnage(new Point(1,2), new Point(1,1));
        jeu.Construire(new Point(1,1));

        // check construction
        Assertions.assertEquals(1,jeu.getNbEtage(new Point(1,1)));

        // check for different ngEtages
        Assertions.assertFalse(vEscalier.verifie(new Point(1,1),new Point(1,2)));

        jeu.Construire(new Point(1,0));

        // check construction
        Assertions.assertEquals(1,jeu.getNbEtage(new Point(1,0)));

        // check for same ngEtages
        Assertions.assertTrue(vEscalier.verifie(new Point(1,1),new Point(1,0)));

        // check for negative points
        Assertions.assertFalse(vEscalier.verifie(new Point(-1,-2),new Point(-1,-1)));

        // check for bigger than board points
        Assertions.assertFalse(vEscalier.verifie(new Point(5,5),new Point(5,5)));
    }
}
