package test.controleurIA;

import controleurIA.IA;
import modele.Jeu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structure.Point;
import structure.VerificateurEtage;
import structure.VerificateurPion;

public class TestIA extends IA{
    IA ia;

    @BeforeEach
    public void setup() {
        j = new Jeu();
        ia = IA.nouvelle(j,"controleurIA.IAAleatoire", "IA Facile");
        ia.activeIA();
    }

    @Test
    public void testSiActive() {
        Assertions.assertTrue(ia.estActive());

        ia.desactiverIA();

        Assertions.assertFalse(ia.estActive());
    }

    @Test
    public void testGetVoisin() {
        VerificateurEtage ve = new VerificateurEtage(j);
        VerificateurPion vp = new VerificateurPion(j);

        System.out.println(ia.getVoisin(new Point(2,1), ve));
        System.out.println(ia.getVoisin(new Point(2,1), vp));

        String expectedVE = "[( 1, 1), ( 2, 0), ( 3, 1), ( 2, 2), ( 1, 0), ( 3, 2), ( 3, 0), ( 1, 2)]";
        String expectedVP = "[( 1, 1), ( 2, 0), ( 3, 1), ( 2, 2), ( 1, 0), ( 3, 2), ( 3, 0), ( 1, 2)]";

        Assertions.assertEquals(expectedVE,ia.getVoisin(new Point(2,1), ve).toString());
        Assertions.assertEquals(expectedVP,ia.getVoisin(new Point(2,1), ve).toString());

        // test after place personnages
        j.poserPersonnage(new Point(1,1),1);
        j.poserPersonnage(new Point(2,2),1);

        // test after contruire
        j.Construire(new Point(3,1));
        j.Construire(new Point(1,0));

        String expectedVE1 = "[( 2, 0), ( 3, 1), ( 1, 0), ( 3, 2), ( 3, 0), ( 1, 2)]";
        String expectedVP1 = "[( 2, 0), ( 3, 1), ( 1, 0), ( 3, 2), ( 3, 0), ( 1, 2)]";

        Assertions.assertEquals(expectedVE1,ia.getVoisin(new Point(2,1), ve).toString());
        Assertions.assertEquals(expectedVP1,ia.getVoisin(new Point(2,1), ve).toString());

        // tester apres enleverPerson et detruire l'etage
        j.enleverPerso(new Point(1,1));
        j.detruireEtage(new Point(1,0));

        String expectedVE2 = "[( 1, 1), ( 2, 0), ( 3, 1), ( 1, 0), ( 3, 2), ( 3, 0), ( 1, 2)]";
        String expectedVP2 = "[( 1, 1), ( 2, 0), ( 3, 1), ( 1, 0), ( 3, 2), ( 3, 0), ( 1, 2)]";

        Assertions.assertEquals(expectedVE2,ia.getVoisin(new Point(2,1), ve).toString());
        Assertions.assertEquals(expectedVP2,ia.getVoisin(new Point(2,1), ve).toString());
    }

    @Test
    public void testToString() {
        // Active IA
        String expectedIAActive =  "L'IA est active et " + j;

        Assertions.assertEquals(expectedIAActive,ia.toString());

        // Desactive l'IA
        ia.desactiverIA();

        String expectedIADesactive = "L'IA est désactive et " + j;

        Assertions.assertEquals(expectedIADesactive,ia.toString());
    }
}
