package modele;

import java.util.ArrayList;
import structure.Coup;

public class Historique{
    int taille = 0;
    int position = 0;
    ArrayList<Coup> historique;

    public Historique(){} // A faire : Chargement de partie ...

    // Ajoute un coup à l'historique et supprime le futur si il y en a un. 
    public void ajouteCoup(Coup c){
        if (taille == position){
            historique.add(c);
            taille ++;
            position ++;
       } else {
            historique.subList(position, taille).clear();
            position = taille = historique.size();
            historique.add(c);
            taille ++;
            position ++;
        }
    }

    // Reviens en arrière de un coup.
    public Coup annuler(){
        position --;
        return historique.get(position);
    }

    // Rétablie un coup, aucune robustesse.
    public Coup retablir(){
        position ++;
        return historique.get(position);
    }

    // Vérifie qu'un coup puisse être annulé.
    public boolean verifAnnulerCoup(){
        return position > 0;
    }

    // Vérifie qu'un coup puisse être rétablie.
    public boolean verifRetablirCoup(){
        return position < taille;
    }

    // Vérifie l'existence d'un coup.
    public boolean existeCoup(int n){
        return n < taille;
    }

    // Obtiens le coup n, non robuste.
    public Coup obtenirCoup(int n){
        return historique.get(n);
    }
}