package controleurIA;

import java.util.ArrayList;
import java.util.Random;
import java.util.Hashtable;
import java.util.Iterator;
import java.lang.Math;
import java.math.BigInteger;

import global.TypeLogger;
import modele.Jeu;
import modele.Coup;
import structure.*;

public abstract class IAMinMax extends IA {
    Random r;
    int horizonMax;
    Hashtable<BigInteger, Integer> table;
    //Jeu j;

    //TODO les ia devront le faire elles-mêmes si besoin.
    public IAMinMax() {
        r = new Random((long) 0);
        horizonMax = 4;
    }

    @Override
    public void initialise() {
        prop.envoyerLogger("IA MinMax activée", TypeLogger.INFO);
    }

    @Override
    public Coup debuterPartie(Jeu j) {
        ArrayList<Point> liste = new ArrayList<Point>(0);
        for (int i = 0; i < j.getLargeurPlateau(); i++) {
            for (int k = 0; k < j.getHauteurPlateau(); k++) {
                if (!(j.aPersonnage(new Point(i, k)))) {
                    liste.add(new Point(i, k));
                }
            }
        }
        /*
         * if (j.aPersonnage(new Point(4, 0))) return new Coup(new Point(0, 4),
         * j.getJoueurEnJeu()); else return new Coup(new Point(4, 0),
         * j.getJoueurEnJeu());
         */
        return new Coup(liste.get(r.nextInt(liste.size())), j.getJoueurEnJeu());
    }

    @Override
    public Coup joue(Jeu jeu) {
        Jeu j = jeu.clone();
        table = new Hashtable<BigInteger, Integer>();
        ArrayList<Coup> successeur = successeur(j);
        ListGagnant gagnant = new ListGagnant();
        Iterator<Coup> I = successeur.iterator();
        int taille = successeur.size();
        int valeur = Integer.MIN_VALUE;
        Coup c;
        
        int horizon = horizonMax;

        /*if (successeur.size() <= 30){
            horizon = horizonMax = 4;
        } else {
            horizon = horizonMax = 4;
        }*/

        if (taille == 0) {
            System.err.println("Aucun coup possible !");
            return null;
        } else if (taille == 1) {
            return I.next();
        }

        while (I.hasNext()) {
            c = I.next();
            tour(c);
            valeur = calcul(j, horizon - 1, Integer.MAX_VALUE, valeur);
            valeur = gagnant.ajouter(valeur, c);
            control.annulerCoup();
        }
        return gagnant.extraire();
    }

    public int calcul(Jeu j, int horizon, int maximum, int minimum) {
        ArrayList<Coup> succ = successeur(j);
        Iterator<Coup> I = succ.iterator();
        Integer chiffrage = table.get(j.getHashCode());
        // Max value pour moi, et min value pour l'adversaire
        Coup c;

        if (chiffrage != null) {
            return -chiffrage * 15 / 16;
        } else if (j.estGagnant() || horizon == 0) {
            return (int) ((int) chiffrage(j) * Math.pow(-1, horizonMax - horizon+1));
        } else {
            //TODO à vérifier en priorité !
            chiffrage = null;
        }

        if(!I.hasNext()){
            return -minimum;
        }

        do {
            c = I.next();
            tour(c);
            int chiffragetmp =  calcul(j, horizon - 1, (chiffrage == null) ? -minimum : (-chiffrage)-1, -maximum);
            chiffrage = max(chiffrage, chiffragetmp);
            table.put(j.getHashCode(), chiffrage);
            control.annulerCoup();
        } while (I.hasNext() && ((maximum >= chiffrage) && (chiffrage >= minimum)));
        return -chiffrage * 15 / 16;
    }

    private int max(Integer a, Integer b){
        if (a == null){
            return b;
        }
        return (a >= b) ? a : b;
    }

    public ArrayList<Coup> successeur(Jeu j) {
        ArrayList<Point> deplacement = new ArrayList<Point>(0);
        ArrayList<Point> construction = new ArrayList<Point>(0);
        ArrayList<Coup> successeur = new ArrayList<Coup>(0);
        VerificateurPion vp = new VerificateurPion(j);
        VerificateurEtage ve = new VerificateurEtage(j);
        Point[] p = j.getPosiPions(j.getJoueurEnJeu());

        for (int i = 0; i < 2; i++) {
            deplacement = j.getVoisin(p[i], vp);
            Iterator<Point> It1 = deplacement.iterator();
            Point depl;
            while (It1.hasNext()) {
                depl = It1.next();
                construction = j.getVoisin(depl, ve);
                construction.add(p[i]);
                Iterator<Point> It2 = construction.iterator();
                while (It2.hasNext()) {
                    successeur.add(new Coup(p[i], depl, It2.next(), j.getJoueurEnJeu()));
                }
            }
        }

        return successeur;
    }

    /*
     * Chiffrage : retour positif = plus de chance de perdre que de gagner retour
     * négatif = plus de chance de gagner que de perdre
     */


    public Integer chiffrage(Jeu j) {
        Heuristique h = new Heuristique(j);
        Point[] p1 = j.getPosiPions(j.getJoueurEnJeu() % 2 + 1);
        if (h.victoireEtage(p1) == -1) {
            return 100; // Le joueur dont c'est le tour perds, l'adversaire a tout posé
        }
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
        if (sommetsVictorieux.size() >= 1){
            return -100; // Le joueur a au moins deux possibilités de gagner
        }
        tampon = new ArrayList<Point>(successeurP1J2.get(0));
        tampon.addAll(successeurP2J2.get(0));
        sommetsVictorieux = h.peutMonter3(tampon);
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

        // Fusion avec ligne 162-156 ?
        if (h.peutBouger(successeurP1J1.get(0)) == 0){
            if (h.peutBouger(successeurP2J1.get(0)) == 0){
                // Déjà vu en 162
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
                // Situation compliqué à calculer, non pris en compte pour le moment.
            } else {
                //TODO
                // L'adversaire a un pion bloqué
            }
        } else if (h.peutBouger(successeurP2J2.get(0)) == 0){
            // L'adversaire a un pion bloqué
            //TODO
        }
        
        tampon = new ArrayList<Point>(successeurP1J1.get(1));
        tampon.addAll(successeurP2J1.get(1));
        Integer bloquant = h.constructionBloquante(p1, tampon);
        Integer bloquant3 = h.constructionBloquante3(p1, tampon);

        tampon = new ArrayList<Point>(successeurP1J2.get(1));
        tampon.addAll(successeurP2J2.get(1));
        ArrayList<Point> tampon2 = new ArrayList<Point>(successeurP1J1.get(1));
        tampon2.addAll(successeurP2J1.get(1));
        if(h.nonContester(tampon2,tampon) == 1){
            //TODO
        }

        /*
        * public int nonContester(ArrayList<Point> constructionMoi, ArrayList<Point> constructionToi)
        * public int peutTomber(Point p, ArrayList<Point> deplacement)
        */

        return 2*hauteurJ1 - hauteurJ2 + bloquant +3* bloquant3;
    }

    public void tour(Coup c) {
        if (c.estDeplacement()) {
            control.jouerAction(c.getDepart(), true);
            control.jouerAction(c.getArrive(), true);
            control.jouerAction(c.getConstruction(), true);
        } else {
            prop.envoyerLogger("Coup anormale, l'IA essaie de placer un pion", TypeLogger.SEVERE);

        }
    }
}
