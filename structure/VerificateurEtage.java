package structure;

import modele.Jeu;

public class VerificateurEtage implements Verificateur {
    Jeu j;

    public VerificateurEtage(Jeu jeu){
        j = jeu;
    }

    // Retourne la constructabilité de p.
    //@Override
    public boolean verifie(Point p){
        return j.Constructible(p);
    }
}
