package test.modele;

import modele.Historique;

public class TestHistorique {
    Historique historique;
/*
    @BeforeEach
    public void setup() {
        historique = new Historique();
    }

    //TODO - NEEDS REVISE
    @Test
    public void testAjouteCoup() {
        Coup coup1 = new Coup(new Point(2,1));
        Coup coup2 = new Coup(new Point(2,2));
        Coup coup3 = new Coup(new Point(3,2));

        historique.ajouteCoup(coup1);
        historique.ajouteCoup(coup2);
        historique.ajouteCoup(coup3);

        System.out.println(historique.obtenirCoup(1));
    }

    //TODO - NEEDS REVISE
    @Test
    public void testAnnulerCoup() {
        Coup coup1 = new Coup(new Point(2,1));
        Coup coup2 = new Coup(new Point(2,2));
        Coup coup3 = new Coup(new Point(3,2));

        historique.ajouteCoup(coup1);
        historique.ajouteCoup(coup2);
        historique.ajouteCoup(coup3);
        historique.annuler();

        System.out.println(historique.obtenirCoup(2));
    }

    //TODO - NEEDS REVISE
    @Test
    public void testRetablirCoup() {
        Coup coup1 = new Coup(new Point(2,1));
        Coup coup2 = new Coup(new Point(2,2));
        Coup coup3 = new Coup(new Point(3,2));

        historique.ajouteCoup(coup1);
        historique.ajouteCoup(coup2);
        historique.annuler();
        historique.ajouteCoup(coup3);
        historique.annuler();
        historique.annuler();
        historique.retablir();

        System.out.println(historique.obtenirCoup(2));
    }

    @Test
    public void testExisteCoup() {
        Assertions.assertFalse(historique.existeCoup(0));

        Coup coup1 = new Coup(new Point(2,1));
        Coup coup2 = new Coup(new Point(2,2));
        Coup coup3 = new Coup(new Point(3,2));
        Coup coup4 = new Coup(new Point(4,2));

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

        Assertions.assertFalse(historique.existeCoup(3));
        Assertions.assertTrue(historique.existeCoup(2));
    }
 */
}
