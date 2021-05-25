package test.controleur;

import controleur.Bouton;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestBouton {
    Bouton bouton;

    @Test
    public void testBouton() {
        Assertions.assertNotNull(Bouton.PAUSE);
        Assertions.assertNotNull(Bouton.RETABLIR);
        Assertions.assertNotNull(Bouton.RETOUR);
        Assertions.assertNotNull(Bouton.RETOUR_JEU);
        Assertions.assertNotNull(Bouton.RECOMMENCER);
        Assertions.assertNotNull(Bouton.AVEC_IA);
        Assertions.assertNotNull(Bouton.SANS_IA);
        Assertions.assertNotNull(Bouton.RETOUR_MENU);
    }
}
