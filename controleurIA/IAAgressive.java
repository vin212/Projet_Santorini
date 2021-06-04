package controleurIA;

import java.util.ArrayList;

//TODO doit se baser sur un instant présent.
import modele.Jeu;
import structure.*;

public class IAAgressive extends IAMinMax{

    @Override
    public void initialise(){
        System.err.println("Systeme de log absent, IA Agressive activée");
    }

    @Override
    public Integer chiffrage(Jeu j){
        Integer compte = 0;
        Heuristique h = new Heuristique(j);
        Point[] p1 = j.getPosiPions(j.getJoueurEnJeu() % 2 + 1);
        if (h.victoireEtage(p1) == -1) { }
        ArrayList<ArrayList<Point>> successeurP1J2 = h.observes(p1[0]);
        ArrayList<ArrayList<Point>> successeurP2J2 = h.observes(p1[1]);
        Point[] p0 = j.getPosiPions(j.getJoueurEnJeu());
        ArrayList<ArrayList<Point>> successeurP1J1 = h.observes(p0[0]);
        ArrayList<ArrayList<Point>> successeurP2J1 = h.observes(p0[1]);
        ArrayList<Point> tampon = new ArrayList<Point>(successeurP1J1.get(0));
        tampon.addAll(successeurP2J1.get(0));
        if (h.victoireBlocage(tampon) == -1) {
            return 100; // Le joueur dont c'est le tour ne peut pas bouger. Il perd.
        }
        Integer hauteurJ1 = h.evaluationHauteur(p0);
        Integer hauteurJ2 = h.evaluationHauteur(p1);

        Integer peutMonterJ1 = h.peutMonter(p0[0], successeurP1J1.get(0));
        peutMonterJ1 += h.peutMonter(p0[1], successeurP2J1.get(0));
        //TODO
        Integer peutMonterJ2 = h.peutMonter(p1[0], successeurP1J2.get(0));
        peutMonterJ2 += h.peutMonter(p1[1], successeurP2J2.get(0));
        //TODO
        
        ArrayList<Point> sommetsVictorieux = h.peutMonter3(tampon);
        if (sommetsVictorieux.size() >= 1){ }
        tampon = new ArrayList<Point>(successeurP1J2.get(0));
        tampon.addAll(successeurP2J2.get(0));
        sommetsVictorieux = h.peutMonter3(tampon);
        if (sommetsVictorieux.size() > 1){
            return 100; // L'adversaire a au moins deux possibilités de gagner
        } else if(sommetsVictorieux.size() == 1) { // Un étage à 3
            tampon = new ArrayList<Point>(successeurP1J1.get(1));
            tampon.addAll(successeurP2J1.get(1));
            if (h.constructionAdverse(sommetsVictorieux.get(0), tampon)){
                return -100;// On peut contrer la possible victoire de l'ennemie.
                //TODO
            } else {
                // On ne peut pas contrer la victoire de l'ennemie. Défaite assuré.
                return 100;
            }
        }

        if (h.peutBouger(successeurP1J1.get(0)) == 0){
            if (h.peutBouger(successeurP2J1.get(0)) == 0){
                return -100;
            } else {
                //TODO
                // On a un pion bloqué
            }
        } else if (h.peutBouger(successeurP2J1.get(0)) == 0){
            // On a un autre pion bloqué, encore
            //TODO
        }

        if (h.peutBouger(successeurP1J2.get(0)) == 0){
            if (h.peutBouger(successeurP2J2.get(0)) == 0){
                return -300;
                // Situation compliqué à calculer, non pris en compte pour le moment.
            } else {
                compte -= 50;
                //TODO
                // L'adversaire a un pion bloqué
            }
        } else if (h.peutBouger(successeurP2J2.get(0)) == 0){
            compte -= 50;
            // L'adversaire a un pion bloqué
            //TODO
        }
        
        tampon = new ArrayList<Point>(successeurP1J1.get(1));
        tampon.addAll(successeurP2J1.get(1));
        Integer bloquant = h.constructionBloquante(p1, tampon);
        Integer bloquant3 = h.constructionBloquante3(p1, tampon);

        compte += successeurP1J2.get(0).size()*2;
        compte -= successeurP1J1.get(0).size()*2;
        compte += successeurP2J2.get(0).size()*2;
        compte -= successeurP2J1.get(0).size()*2;

        /*
        * public int nonContester(ArrayList<Point> constructionMoi, ArrayList<Point> constructionToi)
        * public int peutTomber(Point p, ArrayList<Point> deplacement)
        */

        return compte - bloquant * 10 - bloquant3 * 30;
    }
}
