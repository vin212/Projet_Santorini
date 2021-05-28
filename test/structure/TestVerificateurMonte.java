package test.structure;

import modele.Jeu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structure.Point;
import structure.VerificateurMonte;

public class TestVerificateurMonte {
    VerificateurMonte vMonte;
    Jeu jeu = new Jeu();

    @BeforeEach
    public void setup() {
        vMonte = new VerificateurMonte(jeu);
    }

    @Test
    public void testVerificateurMonte() {
        jeu.poserPersonnage(new Point(1,2),1);
        jeu.poserPersonnage(new Point(2,2),1);

        jeu.poserPersonnage(new Point(3,3),2);
        jeu.poserPersonnage(new Point(4,3),2);

        // construire une etage
        jeu.Construire(new Point(3,2));

        Assertions.assertTrue(vMonte.verifie(new Point(2,2),new Point(3,2)));

        // construire la deuxieme etage
        jeu.Construire(new Point(3,2));

        Assertions.assertFalse(vMonte.verifie(new Point(2,2),new Point(3,2)));

        // construire un autre voisin
        jeu.Construire(new Point(2,3));
        jeu.deplacerPersonnage(new Point(2,2), new Point(2,3));

        // tester avec nbEtage = 1 & nbEtage = 2
        Assertions.assertTrue(vMonte.verifie(new Point(2,3),new Point(3,2)));

        jeu.deplacerPersonnage(new Point(2,3),new Point(3,2));

        jeu.Construire(new Point(2,3));
        jeu.Construire(new Point(2,3));

        // tester avec nbEtage = 2 & nbEtage = 3
        Assertions.assertTrue(vMonte.verifie(new Point(3,2),new Point(2,3)));

        jeu.deplacerPersonnage(new Point(3,2),new Point(2,3));

        // tester apres detruire l'etage
        jeu.detruireEtage(new Point(3,2));

        Assertions.assertFalse(vMonte.verifie(new Point(3,2),new Point(2,3)));
    }
}
