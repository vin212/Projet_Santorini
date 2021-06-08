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
        Assertions.assertEquals("Retour au jeu", Bouton.RETOUR_JEU.toString());
        Assertions.assertEquals("Recommencer", Bouton.RECOMMENCER.toString());
        Assertions.assertEquals("Retour au menu", Bouton.RETOUR_MENU.toString());
        Assertions.assertEquals("Sauvegarder", Bouton.SAUVEGARDER.toString());
        Assertions.assertEquals("VALIDER_SAUVEGARDE", Bouton.VALIDER_SAUVEGARDE.toString());
        Assertions.assertEquals("ANNULER_SAUVEGARDE", Bouton.ANNULER_SAUVEGARDE.toString());
        Assertions.assertEquals("Charger une partie", Bouton.CHARGER.toString());
        Assertions.assertEquals("Nouvelle partie", Bouton.NOUVELLE_PARTIE.toString());
        Assertions.assertEquals("LANCER_PARTIE", Bouton.LANCER_PARTIE.toString());
    }
}
