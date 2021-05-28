package structure;

import modele.Jeu;


public class VerificateurPion implements Verificateur {
    Jeu j;

    public VerificateurPion(Jeu jeu){
        j = jeu;
    }

    // Retourne l'occupabilité de p.
    public boolean verifie(Point p, Point p2){
        return j.peutPoserUnPerso(p, p2);
    }
}
