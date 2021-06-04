package controleurIA;

import java.util.ArrayList;
import java.util.Random;

import modele.Coup;
import structure.*;
import global.*;

public class IAalea3 extends IA {
    Random r;
    
    public IAalea3(){
        r = new Random((long) 0);
    }

    @Override
    public void initialise(){
        prop.envoyerLogger("IA Alea 3 activer",TypeLogger.INFO);
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
        ArrayList<Point> liste = new ArrayList<Point>(0), liste2 = new ArrayList<Point>(0);
        VerificateurMonte vm = new VerificateurMonte(j);
        VerificateurPion vp = new VerificateurPion(j);
        VerificateurEscalier ves = new VerificateurEscalier(j);
        Point[] p = j.getPosiPions(j.getJoueurEnJeu());
        boolean b = r.nextBoolean();
        int taille, taille2;
        Point pion;
        Point deplacement;
        Point construction;

        liste = getVoisin(p[0], vm);
        liste2 = getVoisin(p[1], vm);
        taille = liste.size();
        taille2 = liste2.size();
        if(taille == 0 && taille2 == 0){
            if (b)
                pion = p[0];
            // Pion 2 qui joue
            else
                pion = p[1];
            liste = getVoisin(pion, vp);
            taille = liste.size();
            if (taille == 0){
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
        } else {
            if (taille == 0){
                pion = p[1];
                deplacement = liste2.get(r.nextInt(taille2));
            }    
            else if (taille2 == 0){
                deplacement = liste.get(r.nextInt(taille));
                pion = p[0];
            } else {
                if(b){
                    pion = p[0];
                    deplacement = liste.get(r.nextInt(taille));
                }else{
                    pion = p[1];
                    deplacement = liste2.get(r.nextInt(taille2));
                }
            }

        }
        liste = getVoisin(deplacement, ves);
        taille = liste.size();
        // Si on a pu bouger, c'est qu'on peut poser de là d'où on vient.
        if (taille == 0){
            return new Coup(pion, deplacement, pion, j.getJoueurEnJeu());
        }
        construction = liste.get(r.nextInt(taille));

        return new Coup(pion, deplacement, construction, j.getJoueurEnJeu());
    }
}
