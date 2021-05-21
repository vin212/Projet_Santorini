package modele;

import structure.*;

public class Coup {
    Point initial;
    Point arrive;
    Point construction;
    int joueur;

    // Crée un coup vide.
    public Coup ()
    {
        initial = null;
        arrive = null;
        construction = null;
        joueur = 0;
    }

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

    // Permet de mettre un deplacement dans un coup.
    public void setDeplacement (Point initial, Point arrive){
        this.initial = initial;
        this.arrive = arrive;

    }

    // Permet de mettre le point de construction dans un coup.
    public void setConstruction (Point construction){
        this.construction = construction;
    }

    // Permet de mettre le numero d'un joueur dans un coup.
    public void setJoueur (int joueur){
        this.joueur = joueur;
    }

    public String toString(){
        if (estDeplacement())
            return "Joueur : " + joueur + " de " + initial + " vers " + arrive + ". A construit en " + construction;
        else
            return "Joueur : " + joueur + " Placé en " + initial;
    }
}

