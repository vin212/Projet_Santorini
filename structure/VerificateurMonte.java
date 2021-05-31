package structure;

import modele.Jeu;


public class VerificateurMonte implements Verificateur {
    Jeu j;

    public VerificateurMonte(Jeu jeu){
        j = jeu;
    }

    // Retourne l'occupabilité de p si p est supérieur à p2.
    public boolean verifie(Point p, Point p2){
        return (j.peutPoserUnPerso(p, p2) &&
               j.getNbEtage(p) < j.getNbEtage(p2));
    }
}

