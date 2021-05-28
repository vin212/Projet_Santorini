package test.global;

import global.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestConfiguration {
    Configuration prop;

    @BeforeEach
    public void setup() {
        prop = new Configuration();
    }

    @Test
    public void testRecupValeur() {
        Assertions.assertEquals("controleurIA.IAalea3", prop.recupValeur("IAFacile"));
        Assertions.assertEquals("controleurIA.IAalea2", prop.recupValeur("IANormal"));
        Assertions.assertEquals("controleurIA.IAAleatoire", prop.recupValeur("IADifficile"));
        Assertions.assertEquals("27", prop.recupValeur("raccourci_pause"));
        Assertions.assertEquals("17 90", prop.recupValeur("raccourci_retour"));
        Assertions.assertEquals("17 89", prop.recupValeur("raccourci_retablir"));
        Assertions.assertEquals("700", prop.recupValeur("largeur_fenetre"));
        Assertions.assertEquals("500", prop.recupValeur("hauteur_fenetre"));
        Assertions.assertEquals("true", prop.recupValeur("aide"));
        Assertions.assertNull(prop.recupValeur(""));

    }
}