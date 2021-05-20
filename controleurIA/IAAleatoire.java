package controleurIA;

import java.util.ArrayList;
import java.util.Random;

import modele.Coup;
import structure.*;

public class IAAleatoire extends IA {
    Random r;
    
    public IAAleatoire(){
        r = new Random((long) 0);
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
        ArrayList<Point> liste = new ArrayList<Point>(0);
        VerificateurPion vp = new VerificateurPion(j);
        VerificateurEtage ve = new VerificateurEtage(j);
        boolean b = r.nextBoolean();
        Point[] p = j.getPosiPions(j.getJoueurEnJeu());
        int taille;
        Point pion;
        Point deplacement;
        Point construction;

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
        // Si on a pu bouger, c'est qu'on peut poser de là d'où on vient.
        if (taille == 0){
            return new Coup(pion, deplacement, pion, j.getJoueurEnJeu());
        }
        construction = liste.get(r.nextInt(taille));

        return new Coup(pion, deplacement, construction, j.getJoueurEnJeu());
    }
}
