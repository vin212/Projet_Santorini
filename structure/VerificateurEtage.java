package structure;

public class VerificateurEtage implements Verificateur {
    Jeu j;

    public VerificateurEtage(Jeu jeu){
        j = jeu;
    }

    @Override
    boolean verifie(Point p){
        return j.Constructible(p);
    }
}
