package test.modele;

import modele.Coup;
import modele.Historique;
import modele.Jeu;
import modele.Plateau;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structure.Point;
import structure.VerificateurEtage;
import structure.VerificateurPion;

public class TestJeu {
    Jeu jeu;

    @BeforeEach
    public void setup() {
        jeu = new Jeu();

        //test pour la classe de jeu de création
        Assertions.assertEquals(5,jeu.getHauteurPlateau());
        Assertions.assertEquals(5,jeu.getLargeurPlateau());
        Assertions.assertEquals(1,jeu.getJoueurEnJeu());
    }

    @Test
    public void testConstruire() {
        Point point = new Point(1,1);
        jeu.Construire(point);

        // vérifier si Constructible renvoie vrai
        // vérifier nb etage
        Assertions.assertTrue(jeu.Constructible(point));
        Assertions.assertEquals(1,jeu.getNbEtage(point));

        jeu.Construire(point);
        jeu.Construire(point);

        //vérifier nb etage
        Assertions.assertEquals(3,jeu.getNbEtage(point));

        jeu.Construire(point);
        jeu.Construire(point);

        // vérifier si la construction retourne false après l'étage 4
        // vérifier etage après etage > 4
        Assertions.assertFalse(jeu.Constructible(point));
        Assertions.assertEquals(4,jeu.getNbEtage(point));
    }

    @Test
    public void testConstruireWithNegativePoint() {
        Point point = new Point(-1,-1);

        jeu.Construire(point);

        // vérifier si Constructible renvoie false
        // vérifier nb etage
        Assertions.assertFalse(jeu.Constructible(point));
        Assertions.assertEquals(-1,jeu.getNbEtage(point));
    }

    @Test
    public void testConstruireWithBiggerThanBoardPoint() {
        Point point = new Point(6,7);

        jeu.Construire(point);

        // vérifier si Constructible renvoie false
        // vérifier nb etage
        Assertions.assertFalse(jeu.Constructible(point));
        Assertions.assertEquals(-1,jeu.getNbEtage(point));
    }

    @Test
    public void testDetruireEtage() {
        Point point = new Point(1,1);

        // detruire etage avant Construire
        jeu.detruireEtage(point);

        Assertions.assertEquals(0, jeu.getNbEtage(point));

        jeu.Construire(point);
        jeu.Construire(point);

        jeu.detruireEtage(point);

        Assertions.assertEquals(1,jeu.getNbEtage(point));

        jeu.Construire(point);
        jeu.Construire(point);
        jeu.Construire(point);

        jeu.detruireEtage(point);

        Assertions.assertEquals(3,jeu.getNbEtage(point));

        jeu.Construire(point);
        jeu.Construire(point);
        jeu.Construire(point);

        jeu.detruireEtage(point);

        // vérifier après avoir exécuté la méthode Construire apres etage > 4
        Assertions.assertEquals(3,jeu.getNbEtage(point));
    }

    @Test
    public void testConstructible() {
        // tester les valeurs négatives
        Point pointNegative = new Point(-1,-1);

        Assertions.assertFalse(jeu.Constructible(pointNegative));

        //test plus grand que les valeurs de la plateau
        Point pointEnorme = new Point(7,7);

        Assertions.assertFalse(jeu.Constructible(pointEnorme));
    }

    @Test
    public void testPeutPoserUnPerso() {
        jeu.poserPersonnage(new Point(1,1), 1);
        jeu.poserPersonnage(new Point(3,3), 1);

        jeu.poserPersonnage(new Point(2,3), 2);
        jeu.poserPersonnage(new Point(4,4), 2);

        //vérifier si la personne peut être placée après poserPersonnage
        Assertions.assertFalse(jeu.peutPoserUnPerso(new Point(1,1)));
        Assertions.assertFalse(jeu.peutPoserUnPerso(new Point(3,3)));

        // changer la position de la joueur
        jeu.deplacerPersonnage(new Point(1,1), new Point(2,2));

        // checks if new position is available to poser personnage
        Assertions.assertFalse(jeu.peutPoserUnPerso(new Point(2,2)));

        //vérifie si l'ancienne position est plausible pour poseur personnage
        Assertions.assertTrue(jeu.peutPoserUnPerso(new Point(1,3)));

        // vérifier hors frontière
        Assertions.assertFalse(jeu.peutPoserUnPerso(new Point(7,7)));

        // vérifier les valeurs négatives
        Assertions.assertFalse(jeu.peutPoserUnPerso(new Point(-2,-2)));
    }

    @Test
    public void testAPersonnage() {
        jeu.poserPersonnage(new Point(2,1), 1);
        jeu.poserPersonnage(new Point(1,3), 1);

        jeu.poserPersonnage(new Point(2,2), 2);
        jeu.poserPersonnage(new Point(4,3), 2);

        // vérifier s'il y a une personne
        Assertions.assertTrue(jeu.aPersonnage(new Point(2,1)));
        Assertions.assertTrue(jeu.aPersonnage(new Point(4,3)));
        Assertions.assertFalse(jeu.aPersonnage(new Point(1,2)));

        // changer la position de la personne
        jeu.deplacerPersonnage(new Point(1,3), new Point(2,2));

        // vérifie l'ancienne position
        Assertions.assertTrue(jeu.aPersonnage(new Point(2,2)));

        // vérifie la nouvelle position
        Assertions.assertFalse(jeu.aPersonnage(new Point(1,4)));

        // vérifier hors frontière
        Assertions.assertFalse(jeu.aPersonnage(new Point(7,7)));

        // vérifier les valeurs négatives
        Assertions.assertFalse(jeu.aPersonnage(new Point(-1,-3)));
    }

    // TODO CHECK FOR nbPerso2
    @Test
    public void testDeplacerEtPoserPersonnage() {
        jeu.poserPersonnage(new Point(3,1),1);
        jeu.poserPersonnage(new Point(2,1),1);

        jeu.poserPersonnage(new Point(3,3),2);
        jeu.poserPersonnage(new Point(4,5),2);

        jeu.deplacerPersonnage(new Point(2,1),new Point(2,2));

        Assertions.assertEquals(0, jeu.quiEstIci(new Point(2,1)));
        Assertions.assertEquals(1,jeu.quiEstIci(new Point(3,1)));
    }

    @Test
    public void testEnleverPerso() {
        // vérifier avant poser personnage
        Assertions.assertEquals(-1,jeu.enleverPerso(new Point(3,2)));

        jeu.poserPersonnage(new Point(3,1),1);
        jeu.poserPersonnage(new Point(2,1),1);

        jeu.poserPersonnage(new Point(3,3),2);
        jeu.poserPersonnage(new Point(4,3),2);

        // vérifier si la personne existe dans cette position avant de retirer la personne
        Assertions.assertEquals(2, jeu.quiEstIci(new Point(4,3)));

        // enlever personnage
        jeu.enleverPerso(new Point(4,3));

        // vérifier si la personne a été supprimée
        Assertions.assertEquals(0,jeu.quiEstIci(new Point(4,3)));

        // poser le personnage
        jeu.poserPersonnage(new Point(4,3),2);

        // changer la position de la personne
        jeu.deplacerPersonnage(new Point(2,1), new Point(2,2));

        // enlever personnage apres deplacer
        jeu.enleverPerso(new Point(2,2));

        Assertions.assertEquals(0,jeu.quiEstIci(new Point(2,2)));
        Assertions.assertEquals(0,jeu.quiEstIci(new Point(2,1)));

        // vérifier les valeurs négatives
        jeu.poserPersonnage(new Point(-1,-2),1);
        jeu.enleverPerso(new Point(-1, -2));

        Assertions.assertEquals(-1,jeu.quiEstIci(new Point(-1,-2)));

        // vérifier les grands nombres
        jeu.poserPersonnage(new Point(50000,60000),1);
        jeu.enleverPerso(new Point(50000,60000));

        Assertions.assertEquals(-1,jeu.quiEstIci(new Point(50000,60000)));
    }

    @Test
    public void testQuiEstIci() {
        // test avant poseur personnage
        Assertions.assertEquals(0,jeu.quiEstIci(new Point(1,2)));

        // poser les personnages
        jeu.poserPersonnage(new Point(1,1),1);
        jeu.poserPersonnage(new Point(2,1),1);

        jeu.poserPersonnage(new Point(3,2),2);
        jeu.poserPersonnage(new Point(4,1),2);

        // vérifier les postes
        Assertions.assertEquals(1,jeu.quiEstIci(new Point(2,1)));
        Assertions.assertEquals(1,jeu.quiEstIci(new Point(1,1)));
        Assertions.assertEquals(2,jeu.quiEstIci(new Point(3,2)));
        Assertions.assertEquals(2,jeu.quiEstIci(new Point(4,1)));

        // deplacer les personnages
        jeu.deplacerPersonnage(new Point(2,1), new Point(2,2));
        jeu.deplacerPersonnage(new Point(4,1), new Point(4,2));

        // vérifier un nouveau poste
        Assertions.assertEquals(1,jeu.quiEstIci(new Point(2,2)));
        Assertions.assertEquals(2,jeu.quiEstIci(new Point(4,2)));

        // vérifier les anciens postes
        Assertions.assertEquals(0,jeu.quiEstIci(new Point(2,1)));
        Assertions.assertEquals(0, jeu.quiEstIci(new Point(4,1)));

        // deplacer a la position negative
        jeu.deplacerPersonnage(new Point(1,1), new Point(-1,-1));

        // vérifier si la position est la même
        Assertions.assertEquals(1,jeu.quiEstIci(new Point(1,1)));

        // vérifier que le déplacement négatif est faux
        Assertions.assertEquals(-1, jeu.quiEstIci(new Point(-1,-1)));
    }

    @Test
    public void testTour() {
        jeu.poserPersonnage(new Point(3,1),1);
        jeu.poserPersonnage(new Point(2,1),2);

        Assertions.assertEquals(0,jeu.getTour());

        jeu.subTour();

        // vérifier si renvoie négatif
        Assertions.assertEquals(0,jeu.getTour());

        jeu.addTour();
        jeu.addTour();

        Assertions.assertEquals(2,jeu.getTour());

        jeu.subTour();

        Assertions.assertEquals(1,jeu.getTour());

        jeu.subTour();
        jeu.subTour();

        //vérifier si renvoie négatif
        Assertions.assertEquals(0,jeu.getTour());
    }

    @Test
    public void testgetPosiPions() {
        // vérifier avant de poser les personnages
        Assertions.assertNull(jeu.getPosiPions(1));
        Assertions.assertNull(jeu.getPosiPions(2));

        // poser les personnages
        jeu.poserPersonnage(new Point(1,1),1);
        jeu.poserPersonnage(new Point(2,1),1);

        jeu.poserPersonnage(new Point(3,2),2);
        jeu.poserPersonnage(new Point(4,1),2);

        // vérifier posiPions
        Assertions.assertEquals(0, jeu.getPosiPions(1)[0].compareTo(new Point(1,1)));
        Assertions.assertEquals(0, jeu.getPosiPions(1)[1].compareTo(new Point(2,1)));
        Assertions.assertEquals(0, jeu.getPosiPions(2)[0].compareTo(new Point(3,2)));
        Assertions.assertEquals(0, jeu.getPosiPions(2)[1].compareTo(new Point(4,1)));

        // vérifier la position vide
        Assertions.assertEquals(-1, jeu.getPosiPions(2)[0].compareTo(new Point(3,4)));

        // vérifier la position négative après le deplacer
        jeu.deplacerPersonnage(new Point(3,2), new Point(-1,-2));

        Assertions.assertEquals(1, jeu.getPosiPions(2)[0].compareTo(new Point(-1,-2)));
        Assertions.assertEquals(0, jeu.getPosiPions(2)[0].compareTo(new Point(3,2)));
    }

    @Test
    public void testHistoire() {
        Historique h = new Historique();
        jeu.poserPersonnage(new Point(1,2), 1);
        jeu.poserPersonnage(new Point(2,2), 1);

        jeu.poserPersonnage(new Point(3,3),2);
        jeu.poserPersonnage(new Point(4,4),2);

        // ajoute le coup a la historique
        jeu.deplacerPersonnage(new Point(2,2), new Point(2,3));
        jeu.histoAjouterCoup(new Coup(new Point(2,2),1));

        jeu.deplacerPersonnage(new Point(2,3), new Point(3,3));
        jeu.histoAjouterCoup(new Coup(new Point(2,3),1));

        // test si coup a ajoute a la positionne
        Assertions.assertEquals(2,jeu.histoPosition());

        // annuler coup
        jeu.histoAnnulerCoup();

        // test annuler coup
        Assertions.assertEquals(1, jeu.histoPosition());

        // retablir coup
        jeu.histoRetablir();

        // test retablir coup
        Assertions.assertEquals(2,jeu.histoPosition());

        // avec une valeur inexistante
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> jeu.histoRetablir());

        // vérifier si annuler lancer une erreur
        jeu.histoAnnulerCoup();
        jeu.histoAnnulerCoup();

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> jeu.histoAnnulerCoup());
    }

    @Test
    public void testGagnant() {
        // vérifier avant de commencer le jeu
        Assertions.assertEquals(0,jeu.quiGagnant());
        Assertions.assertFalse(jeu.estGagnant());

        jeu.poserPersonnage(new Point(1,2), 1);
        jeu.poserPersonnage(new Point(2,2), 1);

        jeu.poserPersonnage(new Point(3,3),2);
        jeu.poserPersonnage(new Point(4,4),2);

        jeu.Construire(new Point(2,3));
        jeu.deplacerPersonnage(new Point(2,2), new Point(2,3));

        jeu.Construire(new Point(3,4));
        jeu.Construire(new Point(3,4));

        // vérifier le temps aléatoire dans le jeu
        Assertions.assertEquals(0,jeu.quiGagnant());
        Assertions.assertFalse(jeu.estGagnant());

        jeu.deplacerPersonnage(new Point(2,3), new Point(3,4));

        jeu.Construire(new Point(2,3));
        jeu.Construire(new Point(2,3));

        jeu.deplacerPersonnage(new Point(3,4),new Point(2,3));

        // après que perso1 gagne
        Assertions.assertEquals(1,jeu.quiGagnant());
        Assertions.assertTrue(jeu.estGagnant());
    }

    @Test
    public void testCalculJouerEnJeu() {
        Assertions.assertEquals(0,jeu.calculJoueurEnJeu());

        jeu.poserPersonnage(new Point(1,2), 1);
        jeu.poserPersonnage(new Point(2,2), 1);

        Assertions.assertEquals(0,jeu.calculJoueurEnJeu());

        jeu.poserPersonnage(new Point(3,3),2);
        jeu.poserPersonnage(new Point(4,4),2);

        jeu.deplacerPersonnage(new Point(1,2),new Point(2,3));
        jeu.Construire(new Point(1,2));

        jeu.deplacerPersonnage(new Point(2,2),new Point(2,4));
        jeu.Construire(new Point(1,2));

        Assertions.assertEquals(0,jeu.calculJoueurEnJeu());
    }

    @Test
    public void testGetNbVoisin() {
        VerificateurPion vp = new VerificateurPion(jeu);
        VerificateurEtage ve = new VerificateurEtage(jeu);

        jeu.poserPersonnage(new Point(1,2), 1);
        jeu.poserPersonnage(new Point(2,2), 1);

        jeu.poserPersonnage(new Point(3,3),2);
        jeu.poserPersonnage(new Point(4,4),2);

        jeu.deplacerPersonnage(new Point(4,4), new Point(3,4));
        jeu.Construire(new Point(4,4));

        Assertions.assertEquals(7, jeu.getNbVoisin(new Point(1,2), vp));
        Assertions.assertEquals(6, jeu.getNbVoisin(new Point(3,3), vp));

        Assertions.assertEquals(4, jeu.getNbVoisin(new Point(3,4), ve));

        // test avec des valeurs négatives
        Assertions.assertEquals(0, jeu.getNbVoisin(new Point(1,-2), vp));
        Assertions.assertEquals(0, jeu.getNbVoisin(new Point(-3,4), ve));

        // tester avec des valeurs supérieures à celles de la carte
        Assertions.assertEquals(0, jeu.getNbVoisin(new Point(7,8), vp));
        Assertions.assertEquals(0, jeu.getNbVoisin(new Point(9,8), ve));
    }

    @Test
    public void testToStringEtgetJoueurEnJeu() {
        Plateau p = new Plateau(5, 5);
        // avant de placer des personnages
        String expected = "Au joueur " + jeu.getJoueurEnJeu() + " de jouer sur le plateau :\n" + p;

        Assertions.assertEquals(expected, jeu.toString());

        // poser les personnages sur le plateau
        p.poserPersonnage(new Point(1,1),1);
        p.poserPersonnage(new Point(2,1),1);

        p.poserPersonnage(new Point(3,2),2);
        p.poserPersonnage(new Point(4,1),2);

        // poser les personnages
        jeu.poserPersonnage(new Point(1,1),1);
        jeu.poserPersonnage(new Point(2,1),1);

        jeu.poserPersonnage(new Point(3,2),2);
        jeu.poserPersonnage(new Point(4,1),2);

        String expected2 = "Au joueur " + jeu.getJoueurEnJeu() + " de jouer sur le plateau :\n" + p;

        Assertions.assertEquals(expected2, jeu.toString());

    }
}
