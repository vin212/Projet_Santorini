package modele;

import java.util.ArrayList;

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
        if (verifAnnulerCoup()){
            position --;
            return historique.get(position);
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    // Rétablie un coup, aucune robustesse.
    public Coup retablir(){
        if(verifRetablirCoup()){
            position ++;
            return historique.get(position);
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    // Vérifie qu'un coup puisse être annulé.
    public boolean verifAnnulerCoup(){
        return position > 0;
    }

    // Vérifie qu'un coup puisse être rétablie.
    public boolean verifRetablirCoup(){
        return position < taille;
    }

    // Retourne la position dans l'historique.
    public int positionnement(){
        return position;
    }

    // Vérifie l'existence d'un coup.
    public boolean existeCoup(int n){
        return n < taille;
    }

    // Obtiens le coup n, non robuste.
    public Coup obtenirCoup(int n){
        if (existeCoup(n)){
            return historique.get(n);
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public String toString(){
        String msg = "Historique :\n";
        for(int i = 0; i < taille; i++){
            msg += historique.get(i) + "\n";
        }
        return msg;
    }
}