package test.controleurIA;

import controleurIA.IA;
import controleurIA.IAMinMax;
import global.Configuration;
import modele.Jeu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import structure.Point;
import structure.VerificateurEtage;
import structure.VerificateurPion;

import java.util.Arrays;
import java.util.List;

public class TestIAMinMaxPassive {
    IAMinMax iaMinMax;
    Jeu jeu;
    VerificateurEtage ve;
    VerificateurPion vp;
    Configuration prop = new Configuration();

    @BeforeEach
    public void setup() {
        // initialize l'IA
        jeu = new Jeu();
        ve = new VerificateurEtage(jeu);
        vp = new VerificateurPion(jeu);
    }

    @ParameterizedTest
    @MethodSource("pointPersonnage")
    public void testIAMinMax(Point firstPoint, Point secondPoint, Point thirdPoint, Point fourthPoint, String iaType) {
        iaMinMax = (IAMinMax) IA.nouvelle(jeu,"controleurIA.IAPassive", iaType, prop);

        // simulate le jeu
        iaMinMax.activeIA();

        jeu.poserPersonnage(firstPoint,1);
        jeu.poserPersonnage(secondPoint,1);

        jeu.poserPersonnage(thirdPoint,2);
        jeu.poserPersonnage(fourthPoint, 2);

        // place ai
        iaMinMax.debuterPartie();

        // check iaa is not at the same point as the personnages
        Assertions.assertNotEquals(0,iaMinMax.debuterPartie().getDepart().compareTo(firstPoint));
        Assertions.assertNotEquals(0,iaMinMax.debuterPartie().getDepart().compareTo(secondPoint));
        Assertions.assertNotEquals(0,iaMinMax.debuterPartie().getDepart().compareTo(thirdPoint));
        Assertions.assertNotEquals(0,iaMinMax.debuterPartie().getDepart().compareTo(fourthPoint));

        // check if ia place itself at negative point or bigger than board size
        Assertions.assertEquals(1,iaMinMax.debuterPartie().getDepart().compareTo(new Point(-1,-1)));
        Assertions.assertEquals(-1,iaMinMax.debuterPartie().getDepart().compareTo(new Point(6,5)));

        // check if ia can find a place to move and build
        Assertions.assertTrue(iaMinMax.getVoisin(iaMinMax.debuterPartie().getDepart(), vp).size() > 0);
        Assertions.assertTrue(iaMinMax.getVoisin(iaMinMax.debuterPartie().getDepart(), ve).size() > 0);

        // ia joue
        iaMinMax.joue();

        // verify construction points are not equal to first personnage or second personnage or the point we arrive
        Assertions.assertNotEquals(0, iaMinMax.joue().getConstruction().compareTo(firstPoint));
        Assertions.assertNotEquals(0, iaMinMax.joue().getConstruction().compareTo(secondPoint));
        Assertions.assertNotEquals(0, iaMinMax.joue().getConstruction().compareTo(thirdPoint));
        Assertions.assertNotEquals(0, iaMinMax.joue().getConstruction().compareTo(fourthPoint));
        Assertions.assertNotEquals(0, iaMinMax.joue().getConstruction().compareTo(iaMinMax.joue().getDepart()));
    }

    private static List<Arguments> pointPersonnage() {
        return Arrays.asList(
                Arguments.of(new Point(0,0), new Point(4,4), new Point(2,1), new Point(3,2), "IA Facile"),
                Arguments.of(new Point(3,4), new Point(1,1), new Point(4,3), new Point(1,2), "IA Normal"),
                Arguments.of(new Point(1,1), new Point(2,4), new Point(2,2), new Point(4,3), "IA Difficile"),
                Arguments.of(new Point(1,2), new Point(4,3), new Point(1,1), new Point(3,3), "IA Facile"),
                Arguments.of(new Point(4,2), new Point(2,1), new Point(1,0), new Point(3,0), "IA Difficile"),
                Arguments.of(new Point(2,3), new Point(4,4), new Point(0,0), new Point(3,2), "IA Difficile"),
                Arguments.of(new Point(1,1), new Point(0,0), new Point(4,1), new Point(1,1), "IA Normal"),
                Arguments.of(new Point(4,2), new Point(3,2), new Point(1,0), new Point(2,0), "IA Facile"),
                Arguments.of(new Point(1,0), new Point(2,2), new Point(0,3), new Point(1,3), "IA Difficile"),
                Arguments.of(new Point(4,4), new Point(2,4), new Point(1,3), new Point(1,1), "IA Normal"),
                Arguments.of(new Point(0,0), new Point(4,4), new Point(3,1), new Point(3,2), "IA Difficile"));
    }
}
