package modele;

import structure.*;

public class Coup {
    Point initial;
    Point arrive;
    Point construction;
    Joueur j;

    // Crée un coup de positionnement.
    public Coup(Point dep){
        initial = dep;
        arrive = null;
        construction = null;
    }

    // Crée un coup de déplacement.
    public Coup(Point dep, Point arr, Point cons){
        initial = dep;
        arrive = arr;
        construction = cons;
    }

    // Crée un coup de déplacement.
    public Coup(Point dep, Point arr, Point cons, Joueur joueur){
        initial = dep;
        arrive = arr;
        construction = cons;
        j = joueur;
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
}

