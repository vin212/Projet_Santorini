package test.interfaceUser;

import interfaceUser.NomFenetres;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestNomFenetres {

    @Test
    public void testNomFenetres() {
        Assertions.assertEquals("PAGE_ACCUEIL", NomFenetres.PAGE_ACCUEIL.toString());
        Assertions.assertEquals("MENU_PAUSE", NomFenetres.MENU_PAUSE.toString());
        Assertions.assertEquals("JEU", NomFenetres.JEU.toString());
        Assertions.assertEquals("POPUP_SAUVEGARDE", NomFenetres.POPUP_SAUVEGARDE.toString());
        Assertions.assertEquals("CHARGER", NomFenetres.CHARGER.toString());
        Assertions.assertEquals("NOUVELLE_PARTIE", NomFenetres.NOUVELLE_PARTIE.toString());
        Assertions.assertEquals("OPTION", NomFenetres.OPTION.toString());
        Assertions.assertEquals("", NomFenetres.AUTRE.toString());
    }
}
