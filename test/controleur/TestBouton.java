package test.controleur;

import controleur.Bouton;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestBouton {

    @Test
    public void testBouton() {
        Assertions.assertEquals("PAUSE", Bouton.PAUSE.toString());
        Assertions.assertEquals("RETABLIR", Bouton.RETABLIR.toString());
        Assertions.assertEquals("RETOUR", Bouton.RETOUR.toString());
        Assertions.assertEquals("RETOUR_JEU", Bouton.RETOUR_JEU.toString());
        Assertions.assertEquals("RECOMMENCER", Bouton.RECOMMENCER.toString());
        Assertions.assertEquals("AVEC_IA", Bouton.AVEC_IA.toString());
        Assertions.assertEquals("SANS_IA", Bouton.SANS_IA.toString());
        Assertions.assertEquals("RETOUR_MENU", Bouton.RETOUR_MENU.toString());
    }
}
