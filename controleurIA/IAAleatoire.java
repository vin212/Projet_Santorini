package controleurIA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import modele.Jeu;
import structure.Point;
import structure.Verificateur;

public class IAAleatoire extends IA {
    Random r;
    
    public IAAleatoire(Jeu jeu){
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
        for(int i = 0; i < j.getHLargeurPlateau(); i++){
            for(int k = 0; k < j.getHauteurPlateau(); k++){
                if(!(j.aPersonnage(new Point(i, k)))){
                    liste.add(new Point(i, k));
                }
            }
        }
        return new Coup(liste.get(r.nextInt(liste.size())));
    }

    @Override
    public Coup joue(){
        ArrayList<Point> liste = new ArrayList<Point>(0);
        VerificateurPion vp = new VerificateurPion(j);
        VerificateurEtage ve = new VerificateurEtage(j);
        boolean b = r.nextBoolean();
        Point[] p = jeu.getPosiPions();
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
        deplacement = listeAccessible.get(r.nextInt(taille));
        liste = getVoisin(deplacement, ve);
        taille = liste.size(); 
        // Si on a pu bouger, c'est qu'on peut poser de là d'où on vient. Donc liste ne peut pas être vide
        construction = liste.get(r.nextInt(taille));

        return new Coup(pion, deplacement, construction, jeu.getJoueurEnJeu());
    }
}
