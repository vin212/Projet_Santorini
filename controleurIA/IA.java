package controleurIA;

import modele.Jeu;
import modele.Coup;
import structure.*;
import java.util.ArrayList;

import java.util.Hashtable;
import java.util.Random;
import java.math.BigInteger;

import controleur.ActionUser;
import global.Configuration;
import global.TypeLogger;

public abstract class IA {
    private boolean active = false;
    public Configuration prop;
    public String type;
    public int horizonMax;
    public Hashtable<BigInteger, Integer> table;
    public ActionUser control;
    public Random r;

    // Instancie l'IA demandé et la renvoie.
    public static IA nouvelle(Jeu j, String classIaString, String type){
        IA instance = null;
        

        try {
            instance = (IA) ClassLoader.getSystemClassLoader().loadClass(classIaString).getDeclaredConstructor().newInstance();
            instance.type = type;
            instance.prop = j.prop;
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
    public Coup debuterPartie(Jeu j) {
        ArrayList<Point> liste = new ArrayList<Point>(0);
        for (int i = 0; i < j.getLargeurPlateau(); i++) {
            for (int k = 0; k < j.getHauteurPlateau(); k++) {
                if (!(j.aPersonnage(new Point(i, k)))) {
                    liste.add(new Point(i, k));
                }
            }
        }
        return new Coup(liste.get(r.nextInt(liste.size())), j.getJoueurEnJeu());
    }

    // Fonction a Override pour qu'elle retourne un coup valide.
    public Coup joue(Jeu j){
        return null;
    }

    public final String type() {
        return this.type;
    }

    public void tour(Coup c, Jeu j) {
        if (c.estDeplacement()) {
            control.jouerAction(c.getDepart(), true);
            control.jouerAction(c.getArrive(), true);
            control.jouerAction(c.getConstruction(), true);
			j.addTour();
			control.initActionUser (j, j.prop);
			int numJoueur = j.getJoueurEnJeu();
			j.setAction (numJoueur, modele.Action.A_DEPLACER);
        } else {
            j.prop.envoyerLogger("Coup anormale, l'IA essaie de placer un pion dans un deplacement", TypeLogger.SEVERE);
        }
    }

    public String toString(){
        String msg ="";

        if (active){
            msg += "L'IA est active et est de type " + type;
        } else {
            msg += "L'IA est désactivée et est de type " + type;
        }

        return msg;
    }

}

class ListGagnant {
    int max;
    ArrayList<Coup> gagnant;
    Random r;

    public ListGagnant() {
        max = Integer.MIN_VALUE;
        gagnant = new ArrayList<Coup>();
        r = new Random((long) 0);
    }

    public ListGagnant(int m, Coup c) {
        r = new Random((long) 0);
        max = m;
        gagnant = new ArrayList<Coup>(0);
        gagnant.add(c);
    }

    public int ajouter(int m, Coup c) {
        if (m == max) {
            gagnant.add(c);
        } else if (m > max) {
            max = m;
            gagnant = new ArrayList<Coup>(0);
            gagnant.add(c);
        }
        return max;
    }

    public Coup extraire() {
        return gagnant.get(r.nextInt(gagnant.size()));
    }
}