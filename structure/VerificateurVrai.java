package structure;

import modele.Jeu;

public class VerificateurVrai implements Verificateur {
    Jeu j;

    public VerificateurVrai(Jeu jeu){
        j = jeu;
    }

    public boolean verifie(Point p, Point p2){
        return true;
    }
}
