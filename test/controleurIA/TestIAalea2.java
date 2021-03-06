package test.controleurIA;

import controleurIA.IA;
import controleurIA.IAalea2;
import global.Configuration;
import modele.Jeu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import structure.Point;
import structure.VerificateurEtage;
import structure.VerificateurPion;

import java.util.Arrays;
import java.util.List;

public class TestIAalea2 {
    IAalea2 iaa;
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
    public void testIA(Point firstPoint, Point secondPoint, Point thirdPoint, Point fourthPoint, String iaType) {
        iaa = (IAalea2) IA.nouvelle(jeu,"controleurIA.IAalea2", iaType, prop);

        // simulate le jeu
        iaa.activeIA();

        jeu.poserPersonnage(firstPoint,1);
        jeu.poserPersonnage(secondPoint,1);

        jeu.poserPersonnage(thirdPoint,2);
        jeu.poserPersonnage(fourthPoint, 2);

        // placer l'ai
        iaa.debuterPartie();

        // vérifier que iaa n'est pas au même point que le joueur
        Assertions.assertNotEquals(0,iaa.debuterPartie().getDepart().compareTo(firstPoint));
        Assertions.assertNotEquals(0,iaa.debuterPartie().getDepart().compareTo(secondPoint));
        Assertions.assertNotEquals(0,iaa.debuterPartie().getDepart().compareTo(thirdPoint));
        Assertions.assertNotEquals(0,iaa.debuterPartie().getDepart().compareTo(fourthPoint));

        // vérifier si je me place au point négatif ou plus grand que la taille de la tableau
        Assertions.assertEquals(1,iaa.debuterPartie().getDepart().compareTo(new Point(-1,-1)));
        Assertions.assertEquals(-1,iaa.debuterPartie().getDepart().compareTo(new Point(6,5)));

        // vérifiez si ia peut trouver un endroit pour déménager et construire
        Assertions.assertTrue(iaa.getVoisin(iaa.debuterPartie().getDepart(), vp).size() > 0);
        Assertions.assertTrue(iaa.getVoisin(iaa.debuterPartie().getDepart(), ve).size() > 0);

        // ia joue
        iaa.joue();

        // vérifier que les points de construction ne sont pas égaux au premier ou au deuxième personnage ou au point auquel nous arrivons
        Assertions.assertNotEquals(0, iaa.joue().getConstruction().compareTo(firstPoint));
        Assertions.assertNotEquals(0, iaa.joue().getConstruction().compareTo(secondPoint));
        Assertions.assertNotEquals(0, iaa.joue().getConstruction().compareTo(thirdPoint));
        Assertions.assertNotEquals(0, iaa.joue().getConstruction().compareTo(fourthPoint));
        Assertions.assertNotEquals(0, iaa.joue().getConstruction().compareTo(iaa.joue().getDepart()));
    }

    @Test
    public void testAvecPointsEnormes() {
        iaa = (IAalea2) IA.nouvelle(jeu,"controleurIA.IAalea2", "IA Normal", prop);

        iaa.activeIA();

        jeu.poserPersonnage(new Point(5,5),1);
        jeu.poserPersonnage(new Point(6,5),1);

        // place ai
        iaa.debuterPartie();

        Assertions.assertThrows(IllegalArgumentException.class, () -> iaa.joue());
    }

    @Test
    public void testAvecPointsNegatives() {
        iaa = (IAalea2) IA.nouvelle(jeu,"controleurIA.IAalea2", "IA Normal", prop);

        iaa.activeIA();

        jeu.poserPersonnage(new Point(-1,-1),1);
        jeu.poserPersonnage(new Point(-2,-2),1);

        // place ai
        iaa.debuterPartie();

        Assertions.assertThrows(NullPointerException.class, () -> iaa.joue());
    }

    private static List<Arguments> pointPersonnage() {
        return Arrays.asList(
                Arguments.of(new Point(0,0), new Point(4,4), new Point(2,1), new Point(3,2), "IA Facile"),
                Arguments.of(new Point(1,0), new Point(2,3), new Point(4,3), new Point(1,2), "IA Normal"),
                Arguments.of(new Point(1,1), new Point(2,4), new Point(2,2), new Point(4,3), "IA Difficile"),
                Arguments.of(new Point(1,2), new Point(4,3), new Point(1,1), new Point(3,3), "IA Facile"),
                Arguments.of(new Point(4,2), new Point(2,1), new Point(1,0), new Point(3,0), "IA Difficile"),
                Arguments.of(new Point(2,3), new Point(4,4), new Point(0,0), new Point(3,2), "IA Difficile"),
                Arguments.of(new Point(1,1), new Point(0,0), new Point(4,1), new Point(1,0), "IA Normal"),
                Arguments.of(new Point(4,2), new Point(3,2), new Point(1,0), new Point(2,0), "IA Facile"),
                Arguments.of(new Point(3,3), new Point(3,1), new Point(2,0), new Point(1,3), "IA Difficile"),
                Arguments.of(new Point(4,4), new Point(2,4), new Point(1,3), new Point(1,1), "IA Normal"),
                Arguments.of(new Point(0,0), new Point(4,4), new Point(3,1), new Point(3,2), "IA Difficile"));
    }
}
