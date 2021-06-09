package test.modele;

import modele.Coup;
import modele.Historique;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structure.Point;

public class TestHistorique {
    Historique historique;

    @BeforeEach
    public void setup() {
        historique = new Historique();
    }

    @Test
    public void testAjouteCoup() {
        Coup coup1 = new Coup(new Point(2,1), 1);
        Coup coup2 = new Coup(new Point(2,2), 1);
        Coup coup3 = new Coup(new Point(3,2), 1);

        historique.ajouteCoup(coup1);
        historique.ajouteCoup(coup2);
        historique.ajouteCoup(coup3);

        String expected = "Joueur : " + coup2.getJoueur() + " Placé en " + coup2.getDepart();

        Assertions.assertEquals(expected, historique.obtenirCoup(1).toString());
        Assertions.assertEquals(3,historique.positionnement());
    }

    @Test
    public void testAnnulerCoup() {
        //test annuler avant initialisation
        Assertions.assertFalse(historique.verifAnnulerCoup());

        Coup coup1 = new Coup(new Point(2,1), 1);
        Coup coup2 = new Coup(new Point(2,2), 1);
        Coup coup3 = new Coup(new Point(3,2), 1);

        historique.ajouteCoup(coup1);
        historique.ajouteCoup(coup2);
        historique.ajouteCoup(coup3);

        // vérifier si peut annuler
        Assertions.assertTrue(historique.verifAnnulerCoup());

        // annuler coups
        historique.annuler();
        historique.annuler();

        Assertions.assertEquals(1,historique.positionnement());

        // annuler plus qu'ajouter des coups
        historique.annuler();

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> historique.annuler());
    }

    @Test
    public void testRetablirCoup() {
        Coup coup1 = new Coup(new Point(2,1), 1);
        Coup coup2 = new Coup(new Point(2,2), 1);
        Coup coup3 = new Coup(new Point(3,2), 1);

        historique.ajouteCoup(coup1);
        historique.ajouteCoup(coup2);
        historique.annuler();
        historique.ajouteCoup(coup3);
        historique.annuler();
        historique.annuler();
        historique.retablir();

        String expected = "Joueur : " + coup3.getJoueur() + " Placé en " + coup3.getDepart();

        Assertions.assertEquals(expected, historique.obtenirCoup(1).toString());
        Assertions.assertFalse(historique.existeCoup(2));

        // test retablir plus que coups
        historique.retablir();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> historique.retablir());
    }

    @Test
    public void testExisteCoup() {
        Assertions.assertFalse(historique.existeCoup(0));

        Coup coup1 = new Coup(new Point(2,1), 1);
        Coup coup2 = new Coup(new Point(2,2), 1);
        Coup coup3 = new Coup(new Point(3,2), 2);
        Coup coup4 = new Coup(new Point(4,2), 2);

        historique.ajouteCoup(coup1);
        historique.ajouteCoup(coup2);
        historique.ajouteCoup(coup3);
        historique.ajouteCoup(coup4);

        Assertions.assertTrue(historique.existeCoup(3));

        historique.annuler();
        historique.ajouteCoup(coup3);
        historique.annuler();
        historique.annuler();
        historique.retablir();

        Assertions.assertFalse(historique.existeCoup(4));
        Assertions.assertTrue(historique.existeCoup(3));
    }

    @Test
    public void testToString() {
        Coup coup1 = new Coup(new Point(2,1), 1);
        Coup coup2 = new Coup(new Point(2,2), 1);

        historique.ajouteCoup(coup1);
        historique.ajouteCoup(coup2);

        String expected = "Historique :\n" +  "Joueur : " + coup1.getJoueur() + " Placé en " + coup1.getDepart() +  "\n" +
                          "Joueur : " + coup2.getJoueur() + " Placé en " + coup2.getDepart() + "\n";

        Assertions.assertEquals(expected,historique.toString());
    }

}
