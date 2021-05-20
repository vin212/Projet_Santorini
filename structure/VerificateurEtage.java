package structure;

import modele.Jeu;

public class VerificateurEtage implements Verificateur {
    Jeu j;

    public VerificateurEtage(Jeu jeu){
        j = jeu;
    }

    // Retourne la constructabilité de p.
    //@Override
    public boolean verifie(Point p1, Point p2){
        return j.Constructible(p2);
    }
}
