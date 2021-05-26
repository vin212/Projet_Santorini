package test.modele;

import modele.Action;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestAction {

    @Test
    public void testToString() {
        Assertions.assertEquals("AFK", Action.AFK.toString());
        Assertions.assertEquals("A_CONSTRUIRE", Action.A_CONSTRUIRE.toString());
        Assertions.assertEquals("EN_COURS_DE_DEPLACEMENT", Action.EN_COURS_DE_DEPLACEMENT.toString());
        Assertions.assertEquals("A_DEPLACER", Action.A_DEPLACER.toString());
        Assertions.assertEquals("DEUXIEME_PLACEMENT", Action.DEUXIEME_PLACEMENT.toString());
        Assertions.assertEquals("PREMIER_PLACEMENT", Action.PREMIER_PLACEMENT.toString());
    }
}
