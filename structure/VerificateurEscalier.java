package structure;

import modele.Jeu;

public class VerificateurEscalier implements Verificateur {
    Jeu j;

    public VerificateurEscalier(Jeu jeu){
        j = jeu;
    }

    // Retourne la constructabilité de p1 si p1 est à la même auteur que p2.
    public boolean verifie(Point p1, Point p2){
        return (j.Constructible(p2) &&
                j.getNbEtage(p1) == j.getNbEtage(p2));
    }
}
