package test.structure;

import modele.Jeu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structure.Point;
import structure.VerificateurPion;

public class TestVerificateurPion {
    Jeu jeu;
    VerificateurPion vPion;

    @BeforeEach
    public void setup() {
        jeu = new Jeu();
        vPion = new VerificateurPion(jeu);
    }

    @Test
    public void testVerifiePion() {
        jeu.poserPersonnage(new Point(1,2),1);
        jeu.poserPersonnage(new Point(2,2),1);

        jeu.poserPersonnage(new Point(3,4),2);
        jeu.poserPersonnage(new Point(4,3),2);
        jeu.deplacerPersonnage(new Point(1,2), new Point(2,3));

        // faire un geste valable
        Assertions.assertTrue(vPion.verifie(new Point(3,4), new Point(4,4)));

        // essayez de sortir de l'espace autorisé
        Assertions.assertFalse(vPion.verifie(new Point(2,3), new Point(3,4)));

        // passer à un point négatif
        Assertions.assertFalse(vPion.verifie(new Point(0,0),new Point(0,-1)));

        // déplacer vers un point plus grand que la taille du tableau
        Assertions.assertFalse(vPion.verifie(new Point(5,5),new Point(6,5)));
    }
}
