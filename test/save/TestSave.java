package test.save;

import modele.Jeu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import save.Save;
import structure.Point;

import java.io.File;

public class TestSave {
    Save save;
    Jeu jeu = new Jeu();

    @BeforeEach
    public void setup() {
        save = new Save(jeu);
    }

    @Test
    public void testSauver() {
        jeu.poserPersonnage(new Point(2,1),1);
        jeu.poserPersonnage(new Point(1,1),1);

        jeu.poserPersonnage(new Point(3,3),2);
        jeu.poserPersonnage(new Point(4,4),2);

        jeu.deplacerPersonnage(new Point(2,1), new Point(2,2));

        save.sauver("test");

        // check creation of the file
        File file = new File("sauvegardes/test.data");
        Assertions.assertTrue(file.exists());

        // try to create same file
        save.sauver("");

        // check creation of the file for empty string
        File file1 = new File("sauvegardes/.data");
        Assertions.assertTrue(file1.exists());
    }

    @Test
    public void testLoad() {
        // a tester
    }

    @Test
    public void testSupprimer() {
        save.supprSave("test");

        // check creation of the file
        File file = new File("sauvegardes/test.data");
        Assertions.assertFalse(file.exists());

        // try to create same file
        save.supprSave("");

        // check creation of the file for empty string
        File file1 = new File("sauvegardes/.data");
        Assertions.assertFalse(file1.exists());
    }
}
