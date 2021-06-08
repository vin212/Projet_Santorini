package test.modele;

import modele.Action;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestAction {

    @Test
    public void testToString() {
        Assertions.assertEquals("AFK", Action.AFK.toString());
        Assertions.assertEquals("Choisir case de construction", Action.A_CONSTRUIRE.toString());
        Assertions.assertEquals("Choisir la destination", Action.EN_COURS_DE_DEPLACEMENT.toString());
        Assertions.assertEquals("Choisir le pion", Action.A_DEPLACER.toString());
        Assertions.assertEquals("Placer 2nd pion", Action.DEUXIEME_PLACEMENT.toString());
        Assertions.assertEquals("Placer 1er pion", Action.PREMIER_PLACEMENT.toString());
    }
}
