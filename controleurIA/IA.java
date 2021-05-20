package controleurIA;

import modele.Jeu;
import modele.Coup;
import structure.*;
import java.util.ArrayList;

public abstract class IA {
    public Jeu j;
    private boolean active = false;

    // Instancie l'IA demandé et la renvoie.
    public static IA nouvelle(Jeu j, String classIaString){
        IA instance = null;

        try {
            instance = (IA) ClassLoader.getSystemClassLoader().loadClass(classIaString).getDeclaredConstructor().newInstance();
            instance.j = j;
        } catch(Exception e){
            System.err.println(e);
        }
        return instance;
    }
    
    // Active l'IA.
    public final void activeIA(){
        initialise();
        this.active = true;
    }
    
    // Désactive l'IA.
    public void desactiverIA(){
        this.active = false;
    }

    // Renvoie vrai si l'IA est active.
    public boolean estActive(){
        return this.active;
    }

    // Fonction a Override pour déclarer via le logger que l'IA est activée.
    public void initialise(){}

    // Fonction à Override pour qu'elle pose un pion à un endroit valide.
    public Coup debuterPartie(){
        return null;
    }

    // Fonction a Override pour qu'elle retourne un coup valide.
    public Coup joue(){
        return null;
    }

    // Retourne les voisins p en fonction de v.
    public ArrayList<Point> getVoisin(Point p, Verificateur v){
        int x = p.getx(), y = p.gety();
        int xmax = j.getLargeurPlateau();
        int ymax = j.getHauteurPlateau();
        ArrayList<Point> voisins = new ArrayList<Point>(0); 
        if (0 < x){
            if (v.verifie(p, new Point(x-1, y)))
                voisins.add(new Point(x-1, y));
        }
        if (0 < y){
            if (v.verifie(p, new Point(x, y-1)))
                voisins.add(new Point(x, y-1));
        }
        if (x < xmax){
            if (v.verifie(p, new Point(x+1, y)))
                voisins.add(new Point(x+1, y));
        }
        if (y < ymax){
            if (v.verifie(p, new Point(x, y+1)))
                voisins.add(new Point(x, y+1));
        }
        if (0 < x && 0 < y){
            if (v.verifie(p, new Point(x-1, y-1)))
                voisins.add(new Point(x-1, y-1));
        }
        if (x < xmax && y < ymax){
            if (v.verifie(p, new Point(x+1, y+1)))
                voisins.add(new Point(x+1, y+1));
        }
        if (x < xmax && 0 < y){
            if (v.verifie(p, new Point(x+1, y-1)))
                voisins.add(new Point(x+1, y-1));
        }
        if (0 < x && y < ymax){
            if (v.verifie(p, new Point(x-1, y+1)))
                voisins.add(new Point(x-1, y+1));
        }
        return voisins;
    }

    public String toString(){
        String msg ="";

        if (active){
            msg += "L'IA est active et " + j;
        } else {
            msg += "L'IA est désactive et " + j;
        }

        return msg;
    }

}