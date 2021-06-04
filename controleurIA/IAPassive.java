package controleurIA;

import java.util.ArrayList;

//import controleur.ActionUser;
import modele.Jeu;
import structure.*;
import global.*;

public class IAPassive extends IAMinMax{

    @Override
    public void initialise(){
        prop.envoyerLogger("IA Passive",TypeLogger.INFO);
    }

    @Override
    public Integer chiffrage(Jeu j){
        Heuristique h = new Heuristique(j);
        Point[] p1 = j.getPosiPions(j.getJoueurEnJeu() % 2 + 1);
        if (h.victoireEtage(p1) == -1) {
            return -3000; // Le joueur dont c'est le tour perds, l'adversaire a tout posé
        }
        ArrayList<ArrayList<Point>> successeurP1J2 = h.observes(p1[0]);
        ArrayList<ArrayList<Point>> successeurP2J2 = h.observes(p1[1]);
        Point[] p0 = j.getPosiPions(j.getJoueurEnJeu());
        ArrayList<ArrayList<Point>> successeurP1J1 = h.observes(p0[0]);
        ArrayList<ArrayList<Point>> successeurP2J1 = h.observes(p0[1]);
        ArrayList<Point> tampon = new ArrayList<Point>(successeurP1J1.get(0));
        tampon.addAll(successeurP2J1.get(0));
        Integer hauteurJ1 = h.evaluationHauteur(p0);
        Integer hauteurJ2 = h.evaluationHauteur(p1);

        Integer peutMonterJ1 = h.peutMonter(p0[0], successeurP1J1.get(0));
        peutMonterJ1 += h.peutMonter(p0[1], successeurP2J1.get(0));
        //TODO
        Integer peutMonterJ2 = h.peutMonter(p1[0], successeurP1J2.get(0));
        peutMonterJ2 += h.peutMonter(p1[1], successeurP2J2.get(0));
        //TODO
        
        ArrayList<Point> sommetsVictorieux = h.peutMonter3(tampon);
        if (sommetsVictorieux.size() >= 1){
            return -3000; // Le joueur a au moins une possibilités de gagner
        }
        tampon = new ArrayList<Point>(successeurP1J2.get(0));
        tampon.addAll(successeurP2J2.get(0));
        sommetsVictorieux = h.peutMonter3(tampon);

        /*
        if (sommetsVictorieux.size() > 1){
            return 100; // L'adversaire a au moins deux possibilités de gagner
        } else if(sommetsVictorieux.size() == 1) { // Un étage à 3
            tampon = new ArrayList<Point>(successeurP1J1.get(1));
            tampon.addAll(successeurP2J1.get(1));
            if (h.constructionAdverse(sommetsVictorieux.get(0), tampon)){
                // On peut contrer la possible victoire de l'ennemie.
                //TODO
            } else {
                // On ne peut pas contrer la victoire de l'ennemie. Défaite assuré.
                return 100;
            }
        }
        */
        /*
        // Fusion avec ligne 152-154 ?
        if (h.peutBouger(successeurP1J1.get(0)) == 0){
            if (h.peutBouger(successeurP2J1.get(0)) == 0){
                // Déjà vu en 152
                return -100;
            } else {
                //TODO
                // On a un pion bloqué
            }
        } else if (h.peutBouger(successeurP2J1.get(0)) == 0){
            // On a un pion bloqué, encore
            //TODO
        }*/
        /*
        if (h.peutBouger(successeurP1J2.get(0)) == 0){
            if (h.peutBouger(successeurP2J2.get(0)) == 0){
                // Situation compliqué à calculer, non pris en compte pour le moment.
            } else {
                //TODO
                // L'adversaire a un pion bloqué
            }
        } else if (h.peutBouger(successeurP2J2.get(0)) == 0){
            // L'adversaire a un pion bloqué
            //TODO
        }*/
        
        tampon = new ArrayList<Point>(successeurP1J1.get(1));
        tampon.addAll(successeurP2J1.get(1));
        Integer bloquant = h.constructionBloquante(p1, tampon);
        Integer bloquant3 = h.constructionBloquante3(p1, tampon);

        tampon = new ArrayList<Point>(successeurP1J2.get(1));
        tampon.addAll(successeurP2J2.get(1));
        ArrayList<Point> tampon2 = new ArrayList<Point>(successeurP1J1.get(1));
        tampon2.addAll(successeurP2J1.get(1));
        int uncontested = 0;
        if(h.nonContester(tampon2,tampon) == 1){
            uncontested = -100;
        }

        /*
        * public int nonContester(ArrayList<Point> constructionMoi, ArrayList<Point> constructionToi)
        * public int peutTomber(Point p, ArrayList<Point> deplacement)
        */

        return (-40*hauteurJ2) + hauteurJ1 + uncontested +(-4*peutMonterJ2) + peutMonterJ1;
    }
}
