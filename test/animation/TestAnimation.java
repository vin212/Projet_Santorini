package test.animation;

import animation.Animation;
import controleur.ActionUser;
import global.Configuration;
import interfaceUser.PlateauInterface_2;
import modele.Jeu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structure.Point;

public class TestAnimation {
    Animation animationAvancer;
    Animation animationClignoter;
    Jeu jeu = new Jeu();
    Configuration prop = new Configuration();
    PlateauInterface_2 plateauInterface_2 = new PlateauInterface_2(jeu, new ActionUser(jeu, prop), prop);

    @BeforeEach
    public void setup() {
        animationAvancer = new Animation(new Point(2,1), new Point(2,2), plateauInterface_2, prop);
        animationClignoter = new Animation(plateauInterface_2, prop, new Point(2,2));
    }

    @Test
    public void test() {
        // vérifier le type
        Assertions.assertEquals("CLIGNOTER",animationClignoter.getType().toSrting());
        Assertions.assertEquals("AVANCER",animationAvancer.getType().toSrting());

        // vérifier les positions finales
        Assertions.assertNull(animationClignoter.getPointFinal());
        Assertions.assertEquals(0,animationAvancer.getPointFinal().compareTo(new Point(2,2)));

        Assertions.assertEquals(0,animationAvancer.getEtat());

        // changer l'etat
        animationAvancer.changerEtat();

        Assertions.assertEquals(1, animationAvancer.getEtat());

        // changer l'etat
        animationAvancer.changerEtat();

        Assertions.assertEquals(0, animationAvancer.getEtat());

    }
}
