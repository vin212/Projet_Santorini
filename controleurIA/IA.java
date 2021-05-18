package controleurIA;

import modele.Jeu;
import structure.*;

public abstract class IA {
    public Jeu j;
    private boolean active = false;

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
    
    public final void activeIA(){
        initialise();
        this.active = true;
    }
    
    public void desactiverIA(){
        this.active = false;
    }

    public boolean estActive(){
        return this.active;
    }

    // fonction a Override
    public void initialise(){}

    // fonction a Override
    public Coup joue(){
        return null;
    }

    // Fonction à Override
    public Coup debuterPartie(){
        return null;
    }

    private ArrayList<Point> getVoisin(Point p, Verificateur v){
        int x = p.getx(), y = p.gety();
        int xmax = j.getLargeurPlateau();
        int ymax = j.getHauteurPlateau();
        ArrayList<Point> voisins = new ArrayList<Point>(0); 
        if (0 < x){
            if (v.verifie(new Point(x-1, y)))
                voisins.add(new Point(x-1, y));
        }
        if (0 < y){
            if (v.verifie(new Point(x, y-1)))
                voisins.add(new Point(x, y-1));
        }
        if (x < xmax){
            if (v.verifie(new Point(x+1, y)))
                voisins.add(new Point(x+1, y));
        }
        if (y < ymax){
            if (v.verifie(new Point(x, y+1)))
                voisins.add(new Point(x, y+1));
        }
        if (0 < x && 0 < y){
            if (v.verifie(new Point(x-1, y-1)))
                voisins.add(new Point(x-1, y-1));
        }
        if (x < xmax && y < ymax){
            if (v.verifie(new Point(x+1, y+1)))
                voisins.add(new Point(x+1, y+1));
        }
        if (x < xmax && 0 < y){
            if (v.verifie(new Point(x+1, y-1)))
                voisins.add(new Point(x+1, y-1));
        }
        if (0 < x && y < ymax){
            if (v.verifie(new Point(x-1, y+1)))
                voisins.add(new Point(x-1, y+1));
        }
        return voisins;
    }
}