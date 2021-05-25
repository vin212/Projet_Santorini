package test.controleurIA;

import controleur.ActionUser;
import controleurIA.IA;
import interfaceUser.Fenetres;
import modele.Jeu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestIA extends IA{
    IA ia;

    @BeforeEach
    public void setup() {
        j = new Jeu();
        ia = IA.nouvelle(j,"controleurIA.IAAleatoire");
        ia.activeIA();
    }

    @Test
    public void test() {
        // a tester
    }
}
