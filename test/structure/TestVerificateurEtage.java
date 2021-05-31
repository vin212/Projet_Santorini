package test.structure;

import modele.Jeu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structure.Point;
import structure.VerificateurEtage;

public class TestVerificateurEtage {
    Jeu jeu;
    VerificateurEtage vEtage;

    @BeforeEach
    public void setup() {
        jeu = new Jeu();
        vEtage = new VerificateurEtage(jeu);
    }

    @Test
    public void testVerificationEtage() {
        jeu.poserPersonnage(new Point(1,2),1);
        jeu.poserPersonnage(new Point(2,2),1);

        jeu.poserPersonnage(new Point(3,3),2);
        jeu.poserPersonnage(new Point(4,3),2);
        jeu.deplacerPersonnage(new Point(1,2), new Point(2,3));

        Assertions.assertTrue(vEtage.verifie(new Point(2,3),new Point(3,4)));

        // essayez de construire une personne sur ce point
        Assertions.assertFalse(vEtage.verifie(new Point(2,3),new Point(4,3)));

        // essayez une valeur négative
        Assertions.assertFalse(vEtage.verifie(new Point(2,3),new Point(-4,-3)));

        // essayez plus grand que la taille de la planche
        Assertions.assertFalse(vEtage.verifie(new Point(2,3),new Point(7,7)));
    }
}
