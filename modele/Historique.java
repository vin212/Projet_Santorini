package modele;

import java.util.ArrayList;

public class Historique implements Cloneable {
    int taille = 0;
    int position = 0;
    ArrayList<Coup> historique;

    public Historique(){
        historique = new ArrayList<Coup>();
    } // A faire : Chargement de partie ...

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
            taille  = position + 1;
            position ++;
        }
    }

    public int getTaille(){
        return this.taille;
    }

    // Reviens en arrière de un coup.
    public Coup annuler(){
        if (verifAnnulerCoup()){
            position --;
            return historique.get(position);
        }
        throw new IndexOutOfBoundsException();
    }

    // Rétablie un coup, aucune robustesse.
    public Coup retablir(){
        Coup c;
        if(verifRetablirCoup()){
             c = historique.get(position);
            position ++; 
            return c;
        }
        throw new IndexOutOfBoundsException();
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

    public ArrayList<Coup> getHisto(){
        return this.historique;
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
        throw new IndexOutOfBoundsException();
    }

    @Override
    public Historique clone(){
        try{
            Historique resultat = (Historique) super.clone();
            resultat.taille = taille;
            resultat.position = position;
            ArrayList<Coup> histo = new ArrayList<Coup>(0);
            for(int i = 0; i<taille; i++){
                histo.add(i, historique.get(i).clone());
            }
            resultat.historique = histo;
            return resultat;
        } catch (CloneNotSupportedException e) {
            System.out.println("Bug interne, historique non clonable");
        }
        return null;
    }

    public String toString(){
        String msg = "Historique :\n";
        for(int i = 0; i < taille; i++){
            msg += historique.get(i) + "\n";
        }
        return msg;
    }

    public void setPosition (int i)
    {
        this.position = i;
    }
}