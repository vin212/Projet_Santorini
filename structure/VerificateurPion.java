package structure;

public class VerificateurPion implements Verificateur {
    Jeu j;

    public VerificateurPion(Jeu jeu){
        j = jeu;
    }

    // Retourne l'occupabilité de p.
    @Override
    boolean verifie(Point p){
        return j.peutPoserUnPerso(p);
    }
}
