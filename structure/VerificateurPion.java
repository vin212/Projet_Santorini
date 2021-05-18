package structure;

public class VerificateurPion implements Verificateur {
    Jeu j;

    public VerificateurPion(Jeu jeu){
        j = jeu;
    }

    @Override
    boolean verifie(Point p){
        return j.peutPoserUnPerso(p);
    }
}
