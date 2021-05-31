package test.main;

import modele.Jeu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structure.Point;

public class TestMain {
    Jeu jeu;

    @BeforeEach
    public void setup() {
        jeu = new Jeu();
    }

    @Test
    public void simulateJeu() {
        jeu.poserPersonnage(new Point(2,1),1);
        jeu.poserPersonnage(new Point(1,1),1);

        jeu.poserPersonnage(new Point(3,3),2);
        jeu.poserPersonnage(new Point(4,4),2);

        // commencer le jeu
        jeu.deplacerPersonnage(new Point(2,1),new Point(2,2));
        jeu.Construire(new Point(2,3));


        Assertions.assertEquals(1,jeu.getNbEtage(new Point(2,3))); // check height of the etage
        Assertions.assertEquals(1, jeu.quiEstIci(new Point(2,2))); // check if player at new position
        Assertions.assertEquals(0,jeu.quiEstIci(new Point(2,1))); // check player's old position

        // joueur 2
        jeu.deplacerPersonnage(new Point(3,3), new Point(3,4));
        jeu.Construire(new Point(3,3));

        Assertions.assertEquals(1,jeu.getNbEtage(new Point(3,3))); // check height of the etage
        Assertions.assertEquals(2, jeu.quiEstIci(new Point(3,4))); // check if player at new position
        Assertions.assertEquals(0,jeu.quiEstIci(new Point(3,3))); // check player's old position


        // joueur 1
        jeu.deplacerPersonnage(new Point(1,1), new Point(1,2));
        jeu.Construire(new Point(2,1));

        Assertions.assertEquals(1,jeu.getNbEtage(new Point(2,1))); // check height of the etage
        Assertions.assertEquals(1, jeu.quiEstIci(new Point(1,2))); // check if player at new position
        Assertions.assertEquals(0,jeu.quiEstIci(new Point(1,1))); // check player's old position

        // joueur 2
        jeu.deplacerPersonnage(new Point(4,4), new Point(4,3));
        jeu.Construire(new Point(3,3));

        Assertions.assertEquals(2,jeu.getNbEtage(new Point(3,3))); // check height of the etage
        Assertions.assertEquals(2, jeu.quiEstIci(new Point(4,3))); // check if player at new position
        Assertions.assertEquals(0,jeu.quiEstIci(new Point(4,4))); // check player's old position

        // joueur 1
        jeu.deplacerPersonnage(new Point(1,2), new Point(2,3));
        jeu.Construire(new Point(3,4));

        Assertions.assertEquals(1,jeu.getNbEtage(new Point(3,4))); // check height of the etage
        Assertions.assertEquals(1, jeu.quiEstIci(new Point(2,3))); // check if player at new position
        Assertions.assertEquals(0,jeu.quiEstIci(new Point(1,2))); // check player's old position
    }
}
