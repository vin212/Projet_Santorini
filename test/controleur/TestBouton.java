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
        Assertions.assertEquals("RETOUR_MENU", Bouton.RETOUR_MENU.toString());
        Assertions.assertEquals("SAUVEGARDER", Bouton.SAUVEGARDER.toString());
        Assertions.assertEquals("VALIDER_SAUVEGARDE", Bouton.VALIDER_SAUVEGARDE.toString());
        Assertions.assertEquals("ANNULER_SAUVEGARDE", Bouton.ANNULER_SAUVEGARDE.toString());
        Assertions.assertEquals("CHARGER", Bouton.CHARGER.toString());
        Assertions.assertEquals("NOUVELLE_PARTIE", Bouton.NOUVELLE_PARTIE.toString());
        Assertions.assertEquals("LANCER_PARTIE", Bouton.LANCER_PARTIE.toString());
    }
}
