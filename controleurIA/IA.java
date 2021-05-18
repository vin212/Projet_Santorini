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

    // fonction a Overide
    public void initialise(){}

    // fonction a reecrire
    public Coup joue(){
        return null;
    }

    private ArrayList<Point> getVoisinAccessible(Point p, Verificateur v){
        int x = p.getx(), y = p.gety();
        int xmax = j.getLargeurPlateau();
        int ymax = j.getHauteurPlateau();
        ArrayList<Point> voisins = new ArrayList<Point>(0); 
        if (0 < x){
            if (j.peutPoserUnPerso(new Point(x-1, y)))
                voisins.add(new Point(x-1, y));
        }
        if (0 < y){
            if (j.peutPoserUnPerso(new Point(x, y-1)))
                voisins.add(new Point(x, y-1));
        }
        if (x < xmax){
            if (j.peutPoserUnPerso(new Point(x+1, y)))
                voisins.add(new Point(x+1, y));
        }
        if (y < ymax){
            if (j.peutPoserUnPerso(new Point(x, y+1)))
                voisins.add(new Point(x, y+1));
        }
        if (0 < x && 0 < y){
            if (j.peutPoserUnPerso(new Point(x-1, y-1)))
                voisins.add(new Point(x-1, y-1));
        }
        if (x < xmax && y < ymax){
            if (j.peutPoserUnPerso(new Point(x+1, y+1)))
                voisins.add(new Point(x+1, y+1));
        }
        if (x < xmax && 0 < y){
            if (j.peutPoserUnPerso(new Point(x+1, y-1)))
                voisins.add(new Point(x+1, y-1));
        }
        if (0 < x && y < ymax){
            if (j.peutPoserUnPerso(new Point(x-1, y+1)))
                voisins.add(new Point(x-1, y+1));
        }
        return voisins;
    }

    private ArrayList<Point> getVoisinConstructible(Point p, function f){
        int x = p.getx(), y = p.gety();
        int xmax = j.getLargeurPlateau();
        int ymax = j.getHauteurPlateau();
        ArrayList<Point> voisins = new ArrayList<Point>(0); 
        if (0 < x){
            if (j.Constructible(new Point(x-1, y)))
                voisins.add(new Point(x-1, y));
        }
        if (0 < y){
            if (j.Constructible(new Point(x, y-1)))
                voisins.add(new Point(x, y-1));
        }
        if (x < xmax){
            if (j.Constructible(new Point(x+1, y)))
                voisins.add(new Point(x+1, y));
        }
        if (y < ymax){
            if (j.Constructible(new Point(x, y+1)))
                voisins.add(new Point(x, y+1));
        }
        if (0 < x && 0 < y){
            if (j.Constructible(new Point(x-1, y-1)))
                voisins.add(new Point(x-1, y-1));
        }
        if (x < xmax && y < ymax){
            if (j.Constructible(new Point(x+1, y+1)))
                voisins.add(new Point(x+1, y+1));
        }
        if (x < xmax && 0 < y){
            if (j.Constructible(new Point(x+1, y-1)))
                voisins.add(new Point(x+1, y-1));
        }
        if (0 < x && y < ymax){
            if (j.Constructible(new Point(x-1, y+1)))
                voisins.add(new Point(x-1, y+1));
        }
        return voisins;
    }
}
