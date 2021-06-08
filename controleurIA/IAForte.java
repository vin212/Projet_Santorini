package controleurIA;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;

import controleur.ActionUser;
import global.TypeLogger;
import modele.Coup;
import modele.Jeu;
import structure.*;

public class IAForte extends IA {

    public IAForte () {
        horizonMax = 4;
    }

    @Override
    public void initialise(){
        prop.envoyerLogger("IA Forte activée", TypeLogger.INFO);
    }

    @Override
    public Coup joue(Jeu jeu) {
        Jeu j = jeu.clone();
        control = new ActionUser(j, j.prop);
        table = new Hashtable<BigInteger, Integer>();
        ArrayList<Coup> successeur = successeur(j);
        ListGagnant gagnant = new ListGagnant();
        Iterator<Coup> I = successeur.iterator();
        int taille = successeur.size();
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
            tour(c, j);
            valeur = calcul(j, horizon - 1, -valeur);
            valeur = gagnant.ajouter(valeur, c);
                //System.out.println("C'est juste pour le bp");
            }
            //System.out.println(j + "  " + valeur);
            control.annulerCoup();
        }
        return gagnant.extraire();
    }

        ArrayList<Coup> succ = successeur(j);
        Iterator<Coup> I = succ.iterator();
        Integer chiffrage = table.get(j.getHashCode());
        // Max value pour moi, et min value pour l'adversaire
        Coup c;

        if (chiffrage != null) {
        } else if (j.estGagnant() || horizon <= 0) {
            //return (int) ((int) chiffrage(j) * Math.pow(-1, horizonMax - horizon + 1));
        } else {
        }

        do {
            c = I.next();
            tour(c, j);
            chiffrage = max(chiffrage, chiffragetmp);
            table.put(j.getHashCode(), chiffrage);
            control.annulerCoup();
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
    
    public Integer chiffrage(Jeu j) {
        // Variable qui represente la valeur du plateau 
        Integer valuation = 0;

        Heuristique h = new Heuristique(j);
        
        // Les pions du joueur qui n'est pas en jeu
        Point[] p1 = j.getPosiPions(j.getJoueurEnJeu() % 2 + 1);

        // Si le joueur qui n'est pas en jeu est sur un 3eme etage
        if (h.victoireEtage(p1) == -1) {
            // Alors le joueur en jeu perd
            return 1000; 
        }
        // Les deplacements et constructions possibles du joueur qui n'est pas en jeu
        ArrayList<ArrayList<Point>> successeurP1J2 = h.observes(p1[0]);
        ArrayList<ArrayList<Point>> successeurP2J2 = h.observes(p1[1]);
        
        // Les pions du joueur en jeu ainsi que leurs déplacements et constructions
        Point[] p0 = j.getPosiPions(j.getJoueurEnJeu());
        ArrayList<ArrayList<Point>> successeurP1J1 = h.observes(p0[0]);
        ArrayList<ArrayList<Point>> successeurP2J1 = h.observes(p0[1]);
        
        // Tampon prend tout les déplacements du joueur en jeu
        ArrayList<Point> tampon = new ArrayList<Point>(successeurP1J1.get(0));
        tampon.addAll(successeurP2J1.get(0));

        // Si le joueur en jeu est bloque
        if (h.victoireBlocage(tampon) == -1) {
            // Alors il a perdu
            return 1000; 
        }
        
        // On recupere la liste des sommets
        ArrayList<Point> sommetsVictorieux = h.peutMonter3(tampon);
        // Si il y a au moins un sommet a 3 etages
        if (sommetsVictorieux.size() >= 1){
            // Alors il va pouvoir monter dessus et c'est gagnant
            return -1000;
        }

        // Le tampon recupere les deplacements 
        tampon = new ArrayList<Point>(successeurP1J2.get(0));
        tampon.addAll(successeurP2J2.get(0));

        // Les sommets de hauteur 3 accessibles par l'adversaire
        sommetsVictorieux = h.peutMonter3(tampon);
        // Si l'adversaire en possede plus d'un
        if (sommetsVictorieux.size() > 1){
            // Alors il est pas possible de les bloque tous donc on a perdu
            return 1000;
        } 
        // Sinon Si l'adversaire en possede un seul
        else if(sommetsVictorieux.size() == 1) { // Un étage à 3
            // Alors 
            // Le tampon recupere les constructions
            tampon = new ArrayList<Point>(successeurP1J1.get(1));
            tampon.addAll(successeurP2J1.get(1));

            
            // Si le joueur en jeu peu construire dessus
                // Alors on va perdre forcement
                return 1000;
            }
        }

        // Si le joueur en jeu a son premier pion bloque
        if (h.peutBouger(successeurP1J1.get(0)) == 0){
            // Alors
            // Si il a son autre pion bloque
            if (h.peutBouger(successeurP2J1.get(0)) == 0){
                // Alors c'est perdu (mais le cas est deja traiter au dessus)
                return -1000;
            } else {
                // Sinon on a un pion bloque et c'est pas bien
                valuation += 50;
            }
        
        } 
        // Sinon Si l'autre pion est bloque
        else if (h.peutBouger(successeurP2J1.get(0)) == 0){
            // On a un pion bloque et c'est pas bien
            valuation += 50;
        }

        // Si le joueur adverse ne peut pas bouger un pion
        if (h.peutBouger(successeurP1J2.get(0)) == 0){
            // Alors
            // Si il a son autre pion bloque
            if (h.peutBouger(successeurP2J2.get(0)) == 0){
                // Situation compliqué à calculer, non pris en compte pour le moment.
            } else {
                // Sinon il a un pion bloque et c'est pas bien
                valuation -= 50;
            }
        } 
        // Sinon Si l'autre pion est bloque
        else if (h.peutBouger(successeurP2J2.get(0)) == 0){
            // L'adversaire a l'autre pion bloqué
            valuation -= 50;
        }
        
        // Calcule des hauteurs de chacun des pions des joueurs.
        Integer hauteurJ1 = h.evaluationHauteur(p0);
        Integer hauteurJ2 = h.evaluationHauteur(p1);

//        valuation += hauteurJ2 * 3; // hauteurJ2 < 5

        // Calcul du nombre de case qui peuvent faire monter pour le joueur en jeu
        Integer peutMonterJ1 = h.peutMonter(p0[0], successeurP1J1.get(0));
        peutMonterJ1 += h.peutMonter(p0[1], successeurP2J1.get(0));

        // Calcul du nombre de case qui peuvent faire monter pour l'adversaire
        Integer peutMonterJ2 = h.peutMonter(p1[0], successeurP1J2.get(0));
        peutMonterJ2 += h.peutMonter(p1[1], successeurP2J2.get(0));

//        valuation += peutMonterJ2 * 3;        // peutMonterJ2 < 17

        // Tampon recupere les constructions du joueur en jeu
        tampon = new ArrayList<Point>(successeurP1J1.get(1));
        tampon.addAll(successeurP2J1.get(1));

        // On regarde si on peut bloquer des monter
        Integer bloquant = h.constructionBloquante(p1, tampon);
        Integer bloquant3 = h.constructionBloquante3(p1, tampon);

        valuation -= 0 * bloquant;

        // Si on peut bloque un 3eme etage
        if(bloquant3 == 1){
        }
        /* 
         * Sinon Si il y en a plus que 1 
         * Alors on a perdu et c'est deja traiter
         * Sinon y a rien d'interessant a traiter
         */

        // Tampon prend les constructions de l'autre joueur
        tampon = new ArrayList<Point>(successeurP1J2.get(1));
        tampon.addAll(successeurP2J2.get(1));

        // Tampon2 prend les constructions du joueur en jeu
        ArrayList<Point> tampon2 = new ArrayList<Point>(successeurP1J1.get(1));
        tampon2.addAll(successeurP2J1.get(1));

        // Si on peut construire un 3eme etage non contestable par l'adversaire
        if(h.nonContester(tampon2,tampon) == 1){
            // Alors c'est plus souvent que non avantageux
        }

        /*
        * public int nonContester(ArrayList<Point> constructionMoi, ArrayList<Point> constructionToi)
        * public int peutTomber(Point p, ArrayList<Point> deplacement)
        */

        return valuation;
    }
}
