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

        // vérifier la construction
        Assertions.assertEquals(1,jeu.getNbEtage(new Point(1,1)));

        // vérifier les différents ngEtages
        Assertions.assertFalse(vEscalier.verifie(new Point(1,1),new Point(1,2)));

        jeu.Construire(new Point(1,0));

        // vérifier la construction
        Assertions.assertEquals(1,jeu.getNbEtage(new Point(1,0)));

        // vérifier les mêmes nbEtages
        Assertions.assertTrue(vEscalier.verifie(new Point(1,1),new Point(1,0)));

        // vérifier les points négatifs
        Assertions.assertFalse(vEscalier.verifie(new Point(-1,-2),new Point(-1,-1)));

        // vérifier les points plus gros que le tableau
        Assertions.assertFalse(vEscalier.verifie(new Point(5,5),new Point(6,6)));

        // vérifier l'impossibilité de construire sur soi-même
        Assertions.assertFalse(vEscalier.verifie(new Point(1,0), new Point(1,0)));

        jeu.Construire(new Point(1,0));
        jeu.Construire(new Point(1,0));
        jeu.Construire(new Point(1,0));
        jeu.Construire(new Point(1,1));
        jeu.Construire(new Point(1,1));
        jeu.Construire(new Point(1,1));

        // vérifier l'impossibilité de construire un escalier via étage 4
        Assertions.assertFalse(vEscalier.verifie(new Point(1,0), new Point(1,1)));

        // vérifier l'impossibilité de construire trop loin
        Assertions.assertFalse(vEscalier.verifie(new Point(2,2), new Point(4,4)));
    }
}
