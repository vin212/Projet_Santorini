package modele;

import java.util.ArrayList;
import structure.Coup;

public class Historique{
    int taille = 0;
    int position = 0;
    ArrayList<Coup> historique;

    public Historique(){} // A faire : Chargement de partie ...

    public void ajouteCoup(Coup c){
        if (taille == position){
            historique.add(c);
            taille ++;
            position ++;
        } else {
            historique.removeRange(position, taille);
            position = taille = historique.size();
        }
    }

    public Coup annuler(){
        position --;
        return historique.get(position);
    }

    public Coup retablir(){
        position ++;
        return historique.get(position);
    }

    public boolean verifAnnulerCoup(){
        return position > 0;
    }

    public boolean verifRetablirCoup(){
        return position < taille;
    }

    public boolean existeCoup(int n){
        return n < taille;
    }

    public Coup obtenirCoup(int n){
        return historique.get(n);
    }
}