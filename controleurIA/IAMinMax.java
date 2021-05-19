package controleurIA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Iterator;
import java.awt.Point;
import java.lang.Math;

import modele.Jeu;
import modele.Coup;
import structure.*;
import structure.Verificateur;

public class IAMinMax extends IA {
    Random r;
    
    public IAMinMax(Jeu jeu){
        r = new Random((long) 0);
        j = jeu;
    }

    @Override
    public void initialise(){
        System.err.println("Systeme de log absent, IA Aléatoire activée");
    }

    @Override
    public Coup debuterPartie(){
        ArrayList<Point> liste = new ArrayList<Point>(0);
        for(int i = 0; i < j.getLargeurPlateau(); i++){
            for(int k = 0; k < j.getHauteurPlateau(); k++){
                if(!(j.aPersonnage(new Point(i, k)))){
                    liste.add(new Point(i, k));
                }
            }
        }
        return new Coup(liste.get(r.nextInt(liste.size())), j.getJoueurEnJeu());
    }

    @Override
    public Coup joue(){
        //Controleur control = new Controleur(this.j);
        ArrayList<Coup> successeur = successeur(j);
        ListGagnant gagnant = new ListGagnant();
        Point[] p = j.getPosiPions(j.getJoueurEnJeu());
        Iterator<Coup> I = successeur.iterator();
        int taille = successeur.size();
        int valeur; 
        Coup c;

        int horizon = 5;

        if (taille == 0){
            System.err.println("Aucun coup possible !");
            return null;
        } else if (taille == 1){
            return I.next();
        }

        while(I.hasNext()){
            c = I.next();
            //control.tour(c);
            valeur = calcul_toi(j, horizon, Integer.MAX_VALUE);
            gagnant.ajouter(valeur, c);
            //control.annulerTour();
        }
        return gagnant.extraire();

        /*
        // Pion 1 qui joue
        if (b)
            pion = p[0];
        // Pion 2 qui joue
        else
            pion = p[1];
        liste = getVoisin(pion, vp);
        taille = liste.size();
        if(taille == 0){
            // On regarde l'autre pion
            // Si il n'a pas de voisin possible
            // C'est qu'il n'y a pas de coup possible et donc une erreur
            b = !b;
            if (b)
                pion = p[0];
            else
                pion = p[1];
            liste = getVoisin(pion, vp);
            taille = liste.size();
            if (taille == 0){
                // Ne devrait pas arriver. Seul le futur nous dira si c'est vrai ou pas.
                System.err.println("Mouvement impossible. Doit être traité avant");
            }
        }
        deplacement = liste.get(r.nextInt(taille));
        liste = getVoisin(deplacement, ve);
        taille = liste.size(); 
        // Si on a pu bouger, c'est qu'on peut poser de là d'où on vient. Donc liste ne peut pas être vide
        construction = liste.get(r.nextInt(taille));

        return new Coup(pion, deplacement, construction, j.getJoueurEnJeu());
        */
    }

    // Max
    private int calcul_moi(Jeu j, int horizon, int maximum){
        // Si il renvoie supérieur au max en argument, ça ne sert à rien.
        //Controleur control = new Controleur(j);
        ArrayList<Coup> succ = successeur(j);
        Iterator<Coup> I = succ.iterator();
        int chiffrage = Integer.MIN_VALUE;
        Coup c;

        // Si config final ou horizon atteint
        if(j.estGagnant() || horizon == 0){
            return chiffrage(j);
        }

        while(I.hasNext() || (maximum >= chiffrage)){
            c = I.next();
            //control.tour(c);
            chiffrage = Math.max(chiffrage, calcul_toi(j, horizon - 1, chiffrage));
            //control.annulerTour();
        }
        return chiffrage;

    }

    // Min
    private int calcul_toi(Jeu j, int horizon, int maximum){
        //Controle control = new Controleur(j);
        ArrayList<Coup> succ = successeur(j);
        Iterator<Coup> I = succ.iterator();
        int chiffrage = Integer.MAX_VALUE;
        Coup c;

        if(j.estGagnant()){
            return -chiffrage(j);
        } else if (horizon == 0) {
            return -chiffrage(j);
        }

        while(I.hasNext() || (maximum >= chiffrage)){
            c = I.next();
            //control.tour(c);
            chiffrage = Math.max(chiffrage, calcul_toi(j, horizon - 1, chiffrage));
            //control.annulerTour();
        }
        return -chiffrage;
    }

    private ArrayList<Coup> successeur (Jeu j){
        ArrayList<Point> deplacement = new ArrayList<Point>(0);
        ArrayList<Point> construction = new ArrayList<Point>(0);
        ArrayList<Coup> successeur = new ArrayList<Coup>(0);
        VerificateurPion vp = new VerificateurPion(j);
        VerificateurEtage ve = new VerificateurEtage(j);
        Point[] p = j.getPosiPions(j.getJoueurEnJeu());
        // Controle control = new Controleur(j);
        
        for(int i = 0; i< 2; i++){
            deplacement = getVoisin(p[i], vp);
            Iterator<Point> It1 = deplacement.iterator();
            Point depl;
            while(It1.hasNext()){
                depl = It1.next();
                //control.joue(coup);
                construction = getVoisin(depl, ve);
                Iterator<Point> It2 = contruction.iterator();
                while(It2.hasNext()){
                    successeur.add(new Coup(p[i], depl, It2.next()));
                }
                //control.annulerCoup()
            }
        }

        return successeur;
    }

    /* Chiffrage :
     * retour positif = plus de chance de gagner que de perdre
     * retour négatif  = plus de chance de perdre que de gagner
     */

    private int chiffrage(Jeu j){
        if (r.nextBoolean())
            return r.nextInt();
        else
            return - r.nextInt();
    }

}


class ListGagnant {
    int max;
    ArrayList<Coup> gagnant;

    public ListGagnant(){
        max = Integer.MIN_VALUE;
        gagnant = new ArrayList<Coup>();
    }

    public ListGagnant(int m, Coup c){
        max = m;
        gagnant = new ArrayList<Coup>(0);
        gagnant.add(c);
    }

    public void ajouter(int m, Coup c){
        if (m == max){
            gagnant.add(c);
        } else if (m > max){
            gagnant = new ArrayList<Coup>(0);
            gagnant.add(c);
        }
    }
    public Coup extraire(){
        Random r = new Random((long) 0);
        return gagnant.get(r.nextInt(gagnant.size()));
    }
}