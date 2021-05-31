package controleurIA;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentSkipListSet;

import modele.Jeu;
import structure.Point;
import structure.VerificateurEtage;
import structure.VerificateurPion;

public class Heuristique {
    Jeu j;

    public Heuristique(Jeu jeu) {
        this.j = jeu;
    }

    public ArrayList<ArrayList<Point>> observes(Point p) {
        ArrayList<Point> deplacement = new ArrayList<Point>(0);
        ConcurrentSkipListSet<Point> construction = new ConcurrentSkipListSet<Point>();
        ArrayList<ArrayList<Point>> successeur = new ArrayList<ArrayList<Point>>(2);
        VerificateurPion vp = new VerificateurPion(j);
        VerificateurEtage ve = new VerificateurEtage(j);

        deplacement = j.getVoisin(p, vp);
        Iterator<Point> It1 = deplacement.iterator();
        Point depl;
        construction.add(p);
        while (It1.hasNext()) {
            depl = It1.next();
            Iterator<Point> It2 = j.getVoisin(depl, ve).iterator();
            while (It2.hasNext()) {
                construction.add(It2.next());
            }
        }
        successeur.add(0, deplacement);
        successeur.add(1, new ArrayList<Point>(construction));
        return successeur;
    }

    // Retourne le nombre d'étage des cases des pions de la liste.
    public int evaluationHauteur(Point p[]) {
        return j.getNbEtage(p[0]) + j.getNbEtage(p[1]);
    }

    // Retourne -1 si il n'y a aucun élèment dans la liste.
    public int victoireBlocage(ArrayList<Point> deplacement) {
        if (deplacement.size() == 0) {
            return -1;
        }
        return 0;
    }

    // Retourne -1 si le joueur dont c'est le tour gagne par 3° étage.
    public int victoireEtage(Point[] p1) {
        if (j.getNbEtage(p1[0]) == 3 || j.getNbEtage(p1[1]) == 3) {
            return -1;
        }
        return 0;
    }

    // Retourne le nombre déplacement pouvant faire monter
    public int peutMonter(Point p, ArrayList<Point> deplacement) {
        Iterator<Point> it = deplacement.iterator();
        Point d;
        int res = 0;
        while (it.hasNext()) {
            d = it.next();
            if (j.getNbEtage(p) < j.getNbEtage(d)) {
                res++;
            }
        }
        return res;
    }

    // Retourne le nombre de déplacement pouvant faire tomber de au moins 2 de
    // hauteur.
    public int peutTomber(Point p, ArrayList<Point> deplacement) {
        Iterator<Point> it = deplacement.iterator();
        Point d;
        int res = 0;
        while (it.hasNext()) {
            d = it.next();
            if (j.getNbEtage(p) > (j.getNbEtage(d) + 1)) {
                res++;
            }
        }
        return res;
    }

    // Retourne vrai si l'adversaire peut construire sur le point donné.
    public boolean constructionAdverse(Point p, Collection<Point> c) {
        if (c.contains(p)) {
            return true;
        }
        return false;
    }

    // Retourne une liste de point de troisième étage de la liste.
    public ArrayList<Point> peutMonter3(ArrayList<Point> deplacement) {
        Iterator<Point> it = deplacement.iterator();
        Point d;
        ConcurrentSkipListSet<Point> res = new ConcurrentSkipListSet<Point>();
        while (it.hasNext()) {
            d = it.next();
            if (j.getNbEtage(d) == 3) {
                res.add(d);
            }
        }
        return new ArrayList<Point>(res);
    }

    // Retourne le nombre de déplacement possible.
    public int peutBouger(ArrayList<Point> deplacement) {
        return deplacement.size();
    }

    // Retourne le nombre de construction qui empêche un pion adverse de monter
    public int constructionBloquante(Point [] pionAdverse, ArrayList<Point> construction) {
        Iterator<Point> it = construction.iterator();
        Point c;
        int res = 0;
        while (it.hasNext()) {
            c = it.next();
            // Si l'ennemi peut monter
            if ((j.getNbEtage(c) == (j.getNbEtage(pionAdverse[0]) + 1)) && (j.peutPoserUnPerso(pionAdverse[0], c))) {
                res++;
            } else if (( j.getNbEtage(c) == (j.getNbEtage(pionAdverse[1]) + 1)) && (j.peutPoserUnPerso(pionAdverse[1], c))) {
                res ++;
            }
        }
        return res;
    }

    // Retourne le nombre de construction qui empêche un pion adverse de monter sur
    // un 3eme etage
    public int constructionBloquante3(Point [] pionAdverse, ArrayList<Point> construction) {
        Iterator<Point> it = construction.iterator();
        Point c;
        int res = 0;
        while (it.hasNext()) {
            c = it.next();
            if ((j.getNbEtage(c) == 3) && (j.peutPoserUnPerso(pionAdverse[0], c) || j.peutPoserUnPerso(pionAdverse[1], c))) {
                res++;
            }
        }
        return res;
    }

    // Retourne 1 si il y a un étage 3 constuctible et non contestable
    public int nonContester(ArrayList<Point> constructionMoi, ArrayList<Point> constructionToi) {
        Iterator<Point> it1 = constructionMoi.iterator();
        Point p;
        while (it1.hasNext()) {
            p = it1.next();
            if (j.getNbEtage(p) == 2) {
                if (constructionToi.contains(p)) {
                    return 1;
                }
            }
        }
        return 0;
    }

}
