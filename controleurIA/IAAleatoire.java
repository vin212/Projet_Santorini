package controleurIA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import modele.Jeu;
import structure.Point;

public class IAAleatoire extends IA {
    Random r;
    
    public IAaleatoire(Jeu jeu){
        r = new Random((long) 0);
        j = jeu;
    }

    @Override
    public void initialise(){
        System.out.println("Systeme de log défaillant, IA Aléatoire activée");
    }

    @Override
    public Coup joue(){
        ArrayList<Point> liste = new ArrayList<Point>(0);
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
        liste = getVoisinAccessible(pion);
        taille = liste.size();
        if(taille == 0){
            // Pas de case accessible
        }
        destination = listeAccessible.get(r.nextInt(taille));
        liste = getVoisinConstructible(destination);
        taille = liste.size();
        construction = liste.get(r.nextInt(taille));

        return new Coup(pion, deplacement, construction, jeu.getJoueur());
    }
}
