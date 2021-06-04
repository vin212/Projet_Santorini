package controleurIA;

import java.util.ArrayList;

import modele.Jeu;
import structure.*;

public class IAForte extends IAMinMax {

    @Override
    public void initialise(){
        System.err.println("System de log absent, IA Forte Activé");
    }

    @Override
    public Integer chiffrage(Jeu j) {
        Integer valuation = 0;
        Heuristique h = new Heuristique(j);
        Point[] p1 = j.getPosiPions(j.getJoueurEnJeu() % 2 + 1);
        if (h.victoireEtage(p1) == -1) {
            return 1000; // Le joueur dont c'est le tour perds, l'adversaire a tout posé
        }
        ArrayList<ArrayList<Point>> successeurP1J2 = h.observes(p1[0]);
        ArrayList<ArrayList<Point>> successeurP2J2 = h.observes(p1[1]);
        Point[] p0 = j.getPosiPions(j.getJoueurEnJeu());
        ArrayList<ArrayList<Point>> successeurP1J1 = h.observes(p0[0]);
        ArrayList<ArrayList<Point>> successeurP2J1 = h.observes(p0[1]);
        ArrayList<Point> tampon = new ArrayList<Point>(successeurP1J1.get(0));
        tampon.addAll(successeurP2J1.get(0));
        if (h.victoireBlocage(tampon) == -1) {
            return 1000; // Le joueur dont c'est le tour ne peut pas bouger. Il perd.
        }
        // Calcule des hauteurs de chacun des pions des joueurs.
        Integer hauteurJ1 = h.evaluationHauteur(p0);
        valuation -= hauteurJ1 * 5; // hauteurJ1 < 5
        Integer hauteurJ2 = h.evaluationHauteur(p1);
        valuation += hauteurJ2 * 3; // hauteurJ2 < 5

        // Calcul du nombre de case qui peuvent faire monter
        Integer peutMonterJ1 = h.peutMonter(p0[0], successeurP1J1.get(0));
        peutMonterJ1 += h.peutMonter(p0[1], successeurP2J1.get(0));

        Integer peutMonterJ2 = h.peutMonter(p1[0], successeurP1J2.get(0));
        peutMonterJ2 += h.peutMonter(p1[1], successeurP2J2.get(0));

        valuation -= peutMonterJ1 * 5;        // peutMonterJ1 < 17
        valuation += peutMonterJ2 * 3;        // peutMonterJ2 < 17
        
        ArrayList<Point> sommetsVictorieux = h.peutMonter3(tampon);
        if (sommetsVictorieux.size() >= 1){
            return -1000; // Le joueur a au moins deux possibilités de gagner.
        }
        tampon = new ArrayList<Point>(successeurP1J2.get(0));
        tampon.addAll(successeurP2J2.get(0));

        sommetsVictorieux = h.peutMonter3(tampon);
        if (sommetsVictorieux.size() > 1){
            return 1000; // L'adversaire a au moins deux possibilités de gagner
        } else if(sommetsVictorieux.size() == 1) { // Un étage à 3
            tampon = new ArrayList<Point>(successeurP1J1.get(1));
            tampon.addAll(successeurP2J1.get(1));
            if (h.constructionAdverse(sommetsVictorieux.get(0), tampon)){
                // On peut contrer la possible victoire de l'ennemie.
                return -1000;
            } else {
                // On ne peut pas contrer la victoire de l'ennemie. Défaite assuré.
                return 1000;
            }
        }

        // Fusion avec ligne 162-156 ?
        if (h.peutBouger(successeurP1J1.get(0)) == 0){
            if (h.peutBouger(successeurP2J1.get(0)) == 0){
                // Déjà vu en 162
                return -1000;
            } else {
                valuation += 50;
                // On a un pion bloqué
            }
        } else if (h.peutBouger(successeurP2J1.get(0)) == 0){
            // On a un autre pion bloqué, encore
            valuation += 50;
        }

        if (h.peutBouger(successeurP1J2.get(0)) == 0){
            if (h.peutBouger(successeurP2J2.get(0)) == 0){
                // Situation compliqué à calculer, non pris en compte pour le moment.
            } else {
                valuation -= 50;
                // L'adversaire a un pion bloqué
            }
        } else if (h.peutBouger(successeurP2J2.get(0)) == 0){
            // L'adversaire a l'autre pion bloqué
            valuation -= 50;
        }
        
        tampon = new ArrayList<Point>(successeurP1J1.get(1));
        tampon.addAll(successeurP2J1.get(1));
        Integer bloquant = h.constructionBloquante(p1, tampon);
        Integer bloquant3 = h.constructionBloquante3(p1, tampon);

        valuation -= 0 * bloquant;
        valuation -= 5 * bloquant3;

        tampon = new ArrayList<Point>(successeurP1J2.get(1));
        tampon.addAll(successeurP2J2.get(1));
        ArrayList<Point> tampon2 = new ArrayList<Point>(successeurP1J1.get(1));
        tampon2.addAll(successeurP2J1.get(1));
        if(h.nonContester(tampon2,tampon) == 1){
            valuation -= 200;
        }

        /*
        * public int nonContester(ArrayList<Point> constructionMoi, ArrayList<Point> constructionToi)
        * public int peutTomber(Point p, ArrayList<Point> deplacement)
        */

        return valuation;
    }
}
