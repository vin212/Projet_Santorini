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

        // deplacer perso
        actionUser.selectionnerPerso(new Point(4,4));
        actionUser.avancerPerso(new Point(5,4),true,false);

        // annuler coup
        actionUser.annulerCoup();

        // check positions after annuler coup
        Assertions.assertEquals(-1, jeu.quiEstIci(new Point(5,4)));
        Assertions.assertEquals(2, jeu.quiEstIci(new Point(4,4)));
    }
}
