package structure;

import modele.Jeu;

public class VerificateurEscalier implements Verificateur {
    Jeu j;

    public VerificateurEscalier(Jeu jeu){
        j = jeu;
    }


    // Retourne la constructabilité de p2 si p1 et p2 sont à la même hauteur.
    public boolean verifie(Point p1, Point p2){
        return (j.Constructible(p2) &&
                j.getNbEtage(p1) == j.getNbEtage(p2));
    }
}
