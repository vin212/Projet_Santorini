package controleurIA;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.Hashtable;
import java.util.Iterator;
import java.lang.Math;
import java.math.BigInteger;

//import controleur.ActionUser;
import modele.Jeu;
import modele.Coup;
import structure.*;

public class IAMinMax extends IA {
    Random r;
    int horizonMax;
    Hashtable<BigInteger, Integer> table;

    public IAMinMax() {
        r = new Random((long) 0);
        horizonMax = 2;
    }

    @Override
    public void initialise() {
        System.err.println("Systeme de log absent, IA MinMax activée");
    }

    @Override
    public Coup debuterPartie() {
        ArrayList<Point> liste = new ArrayList<Point>(0);
        for (int i = 0; i < j.getLargeurPlateau(); i++) {
            for (int k = 0; k < j.getHauteurPlateau(); k++) {
                if (!(j.aPersonnage(new Point(i, k)))) {
                    liste.add(new Point(i, k));
                }
            }
        }
        /*
         * if (j.aPersonnage(new Point(4, 0))) return new Coup(new Point(0, 4),
         * j.getJoueurEnJeu()); else return new Coup(new Point(4, 0),
         * j.getJoueurEnJeu());
         */
        return new Coup(liste.get(r.nextInt(liste.size())), j.getJoueurEnJeu());
    }

    @Override
    public Coup joue() {
        // ActionUser control = new ActionUser(this.j);
        table = new Hashtable<BigInteger, Integer>();
        ArrayList<Coup> successeur = successeur(j);
        ListGagnant gagnant = new ListGagnant();
        Iterator<Coup> I = successeur.iterator();
        int taille = successeur.size();
        int valeur;
        Coup c;

        int horizon = horizonMax;

        if (taille == 0) {
            System.err.println("Aucun coup possible !");
            return null;
        } else if (taille == 1) {
            return I.next();
        }

        while (I.hasNext()) {
            c = I.next();
            tour(c);
            valeur = calcul(j, horizon - 1, Integer.MAX_VALUE);
            gagnant.ajouter(valeur, c);
            annulerCoup();
        }
        return gagnant.extraire();
    }

    private int calcul(Jeu j, int horizon, int maximum) {
        ArrayList<Coup> succ = successeur(j);
        Iterator<Coup> I = succ.iterator();
        Integer chiffrage = table.get(j.getHashCode());
        // int chiffrage = (int) ((int)Integer.MAX_VALUE * Math.pow(-1, horizonMax -
        // horizon)); // -1^ (horizonMax - hori)
        // Max value pour moi, et min value pour l'adversaire
        Coup c;

        if (chiffrage != null) {
            return chiffrage;
        } else if (j.estGagnant() || horizon == 0) {
            return chiffrage(j);
        } else {
            chiffrage = (int) ((int) Integer.MAX_VALUE * Math.pow(-1, horizonMax - horizon));
            chiffrage = Integer.MIN_VALUE;
            // Max value pour moi, et min value pour l'adversaire
        }

        while (I.hasNext() && (maximum >= chiffrage)) {
            c = I.next();
            tour(c);
            chiffrage = Math.max(chiffrage, calcul(j, horizon - 1, chiffrage));
            table.put(j.getHashCode(), chiffrage);
            annulerCoup();
        }
        return -chiffrage / 2;
    }

    private ArrayList<Coup> successeur(Jeu j) {
        ArrayList<Point> deplacement = new ArrayList<Point>(0);
        ArrayList<Point> construction = new ArrayList<Point>(0);
        ArrayList<Coup> successeur = new ArrayList<Coup>(0);
        VerificateurPion vp = new VerificateurPion(j);
        VerificateurEtage ve = new VerificateurEtage(j);
        Point[] p = j.getPosiPions(j.getJoueurEnJeu());

        for (int i = 0; i < 2; i++) {
            deplacement = getVoisin(p[i], vp);
            Iterator<Point> It1 = deplacement.iterator();
            Point depl;
            while (It1.hasNext()) {
                depl = It1.next();
                construction = getVoisin(depl, ve);
                construction.add(p[i]);
                Iterator<Point> It2 = construction.iterator();
                while (It2.hasNext()) {
                    successeur.add(new Coup(p[i], depl, It2.next(), j.getJoueurEnJeu()));
                }
            }
        }

        return successeur;
    }

    /*
     * Chiffrage : retour positif = plus de chance de perdre que de gagner retour
     * négatif = plus de chance de gagner que de perdre
     */

    private int chiffrage(Jeu j) {
        Heuristique h = new Heuristique(j);
        Point[] p1 = j.getPosiPions(j.getJoueurEnJeu() % 2 + 1);
        if (h.victoireEtage(p1) == -1) {
            return 100; // Le joueur dont c'est le tour perds, l'adversaire a tout posé
        }
        ArrayList<Collection<Point>> successeurP1J2 = h.observes(p1[0]);
        ArrayList<Collection<Point>> successeurP2J2 = h.observes(p1[1]);
        Point[] p0 = j.getPosiPions(j.getJoueurEnJeu());
        ArrayList<Collection<Point>> successeurP1J1 = h.observes(p0[0]);
        ArrayList<Collection<Point>> successeurP2J1 = h.observes(p0[1]);
        ArrayList<Point> tampon = new ArrayList<Point>(successeurP1J1.get(0));
        tampon.addAll(successeurP1J2.get(0));
        if (h.victoireBlocage(tampon) == -1) {
            return 100; // Le joueur dont c'est le tour ne peut pas bouger. Il perd
        }
        /*
         * public int evaluationHauteur(Point p[]) public int peutMonter(Point p,
         * ArrayList<Point> deplacement) public ArrayList<Point>
         * peutMonter3(ArrayList<Point> deplacement) public int peutTomber(Point p,
         * ArrayList<Point> deplacement) public int evaluationConstructionAdverse(Point
         * p, Collection<Point> c) public int peutBouger(Point p, ArrayList<Point>
         * deplacement) public int constructionBloquante(Point pionAdverse,
         * ArrayList<Point> construction) public int constructionBloquante3(Point
         * pionAdverse, ArrayList<Point> construction) public int
         * nonContester(ArrayList<Point> constructionMoi, ArrayList<Point>
         * constructionToi)
         */

        if (r.nextBoolean())
            return 0;
        else
            return -0;
    }

    private void tour(Coup c) {
        j.histoAjouterCoup(c);
        if (c.estDeplacement()) {
            j.deplacerPersonnage(c.getDepart(), c.getArrive());
            j.Construire(c.getConstruction());
        } else {
            j.poserPersonnage(c.getDepart(), c.getJoueur());
        }
        j.addTour();
    }

    private void annulerCoup() {
        int pos;
        Coup c;
        j.subTour();
        try {
            c = j.histoAnnulerCoup();
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Plus de coup a annuler");
            return;
        }

        if (c.estDeplacement()) {
            // System.out.println(c.getArrive());
            j.deplacerPersonnage(c.getArrive(), c.getDepart());
            j.detruireEtage(c.getConstruction());
        } else {
            // System.out.println("Annulation de placement ? Vraiment ?");
            pos = j.histoPosition();
            if (pos % 2 == 0) {
                j.enleverPerso(c.getDepart());
            } else {
                j.enleverPerso(c.getDepart());
            }
        }
    }

}

class ListGagnant {
    int max;
    ArrayList<Coup> gagnant;

    public ListGagnant() {
        max = Integer.MIN_VALUE;
        gagnant = new ArrayList<Coup>();
    }

    public ListGagnant(int m, Coup c) {
        max = m;
        gagnant = new ArrayList<Coup>(0);
        gagnant.add(c);
    }

    public void ajouter(int m, Coup c) {
        if (m == max) {
            gagnant.add(c);
        } else if (m > max) {
            max = m;
            gagnant = new ArrayList<Coup>(0);
            gagnant.add(c);
        }
    }

    public Coup extraire() {
        Random r = new Random((long) 0);
        return gagnant.get(r.nextInt(gagnant.size()));
    }
}