package structure;

import modele.Jeu;


public class VerificateurPion implements Verificateur {
    Jeu j;

    public VerificateurPion(Jeu jeu){
        j = jeu;
    }

    // Retourne l'occupabilité de p.
    //@Override
    public boolean verifie(Point p){
        return j.peutPoserUnPerso(p);
    }
}
