package test.controleur;

import controleur.ActionUser;
import modele.Jeu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structure.Point;

public class TestActionUser {
    ActionUser actionUser;
    Jeu jeu = new Jeu();

    @BeforeEach
    public void setup() {
        actionUser = new ActionUser(jeu);
    }

    // TODO CHECK FOR RETABLIR COUP
    @Test
    public void testJouerAction() {
        // place players
        jeu.poserPersonnage(new Point(1,1),1);
        jeu.poserPersonnage(new Point(2,1),1);

        jeu.poserPersonnage(new Point(3,3),2);
        jeu.poserPersonnage(new Point(4,4),2);

        // select and move person
        actionUser.selectionnerPerso(new Point(2,1));

        actionUser.avancerPerso(new Point(1,2),true,false);

        // test new position
        Assertions.assertEquals(1, jeu.quiEstIci(new Point(1,2)));

        // construct after deplacement
        actionUser.construireIci(new Point(2,2),true);

        // check if construct worked
        Assertions.assertEquals(1,jeu.getNbEtage(new Point(2,2)));

        // check position
        Assertions.assertEquals(0, actionUser.recupPosiPerso().CompareTo(new Point(1,2)));
    }

    @Test
    public void testDeplacer() {
        jeu.poserPersonnage(new Point(1,1),1);
        jeu.poserPersonnage(new Point(2,1),1);

        jeu.poserPersonnage(new Point(3,3),2);
        jeu.poserPersonnage(new Point(4,4),2);

        // test before selectionner perso
        actionUser.deplacer(new Point(2,2),true);

        Assertions.assertEquals(0,jeu.quiEstIci(new Point(2,2)));

        // select and move person
        actionUser.selectionnerPerso(new Point(2,1));
        actionUser.deplacer(new Point(2,2),true);

        Assertions.assertEquals(1,jeu.quiEstIci(new Point(2,2)));

        actionUser.construireIci(new Point(2,3), true);

        // test coup annuler
        actionUser.annuler();
        Assertions.assertEquals(0, jeu.getNbEtage(new Point(2,3)));

        // retablir coup
        actionUser.retablirCoup();
        Assertions.assertEquals(1, jeu.getNbEtage(new Point(2,3)));
    }
}
