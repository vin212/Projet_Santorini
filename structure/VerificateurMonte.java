package structure;

import modele.Jeu;


public class VerificateurMonte implements Verificateur {
    Jeu j;

    public VerificateurMonte(Jeu jeu){
        j = jeu;
    }

    // Retourne l'occupabilité de p2 si p2 est supérieur à p.
    public boolean verifie(Point p, Point p2){
        return (j.peutPoserUnPerso(p, p2) &&
               j.getNbEtage(p) < j.getNbEtage(p2));
    }
}
