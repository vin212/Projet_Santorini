package structure;

public class VerificateurEtage implements Verificateur {
    Jeu j;

    public VerificateurEtage(Jeu jeu){
        j = jeu;
    }

    // Retourne la constructabilité de p.
    @Override
    boolean verifie(Point p){
        return j.Constructible(p);
    }
}
