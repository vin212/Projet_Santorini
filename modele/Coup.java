package modele;

import structure.*;

public class Coup {
    Point initial;
    Point arrive;
    Point construction;
    int joueur;

    // Crée un coup de positionnement.
    public Coup(Point dep, int j){
        initial = dep;
        arrive = null;
        construction = null;
        joueur = j;
    }

    // Crée un coup de déplacement.
    public Coup(Point dep, Point arr, Point cons, int j){
        initial = dep;
        arrive = arr;
        construction = cons;
        joueur = j;
    }

    // Récupère le point de départ d'un coup.
    public Point getDepart(){
        return initial;
    }

    // Récupère le point d'arrivé du coup.
    public Point getArrive(){
        return arrive;
    }

    // Récupère le point de construction du coup.
    public Point getConstruction(){
        return construction;
    }
    
    // Retoure vrai si le coup est un déplacement.
    public Boolean estDeplacement(){
        return !(construction == null);
    }

    public int getJoueur(){
        return joueur;
    }

    public String toString(){
        if (estDeplacement())
            return "Joueur : " + joueur + " de " + initial + " vers " + arrive + ". A construit en " + construction;
        else
            return "Joueur : " + joueur + " Placé en " + initial;
    }
}

