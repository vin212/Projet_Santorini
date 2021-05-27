package controleurIA;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.Iterator;
import java.lang.Math;

//import controleur.ActionUser;
import modele.Jeu;
import modele.Coup;
import structure.*;

public class IAMinMax extends IA {
    Random r;
    int horizonMax;
    
    public IAMinMax(){
        r = new Random((long) 0);
        horizonMax = 3;
    }

    @Override
    public void initialise(){
        System.err.println("Systeme de log absent, IA MinMax activée");
    }

    @Override
    public Coup debuterPartie(){
        ArrayList<Point> liste = new ArrayList<Point>(0);
        for(int i = 0; i < j.getLargeurPlateau(); i++){
            for(int k = 0; k < j.getHauteurPlateau(); k++){
                if(!(j.aPersonnage(new Point(i, k)))){
                    liste.add(new Point(i, k));
                }
            }
        }
/*        if (j.aPersonnage(new Point(4, 0)))
            return new Coup(new Point(0, 4), j.getJoueurEnJeu());
        else 
            return new Coup(new Point(4, 0), j.getJoueurEnJeu());*/
        return new Coup(liste.get(r.nextInt(liste.size())), j.getJoueurEnJeu());
    }

    @Override
    public Coup joue(){
//        ActionUser control = new ActionUser(this.j);
        ArrayList<Coup> successeur = successeur(j);
        ListGagnant gagnant = new ListGagnant();
        Iterator<Coup> I = successeur.iterator();
        int taille = successeur.size();
        int valeur; 
        Coup c;

        int horizon = horizonMax;

        if (taille == 0){
            System.err.println("Aucun coup possible !");
            return null;
        } else if (taille == 1){
            return I.next();
        }

        while(I.hasNext()){
            c = I.next();
            tour(c);
            valeur = calcul(j, horizon-1, Integer.MAX_VALUE);
            gagnant.ajouter(valeur, c);
            annulerCoup();
        }
        return gagnant.extraire();
    }

    private int calcul(Jeu j, int horizon, int maximum){
        ArrayList<Coup> succ = successeur(j);
        Iterator<Coup> I = succ.iterator();
        // int chiffrage = table.get(j.hashCode());
        int chiffrage = (int) ((int)Integer.MAX_VALUE * Math.pow(-1, horizonMax - horizon)); // -1^ (horizonMax - hori)
        // Max value pour moi, et min value pour l'adversaire
        Coup c;
        
        /*if (chiffrage != null) {
            return table.get(j.hashcode())
        }else */if (j.estGagnant() || horizon == 0){
            return -chiffrage(j);
        //} else { (int) ((int)Integer.MAX_VALUE * Math.pow(-1, horizonMax - horizon));
        // Max value pour moi, et min value pour l'adversaire
        }

        while (I.hasNext() && (maximum >= chiffrage)){
            c = I.next();
            tour(c);
            chiffrage = Math.max(chiffrage, calcul(j, horizon -1, chiffrage));
            //table.put(j.hashCode(), chiffrage);
            annulerCoup();
        }
        return chiffrage * -1;
    }

    // Max
    private int calcul_moi(Jeu j, int horizon, int maximum){
        // Si il renvoie supérieur au max en argument, ça ne sert à rien.
        // ActionUser control = new ActionUser(j);
        ArrayList<Coup> succ = successeur(j);
        Iterator<Coup> I = succ.iterator();
        int chiffrage = Integer.MIN_VALUE; //table.get(j.hashcode())
        Coup c;

        // Si config final ou horizon atteint
        if(j.estGagnant() || horizon == 0){
            return -chiffrage(j);
        } /*elif (chiffrage != null) {
            return table.get(j.hashcode())
        } else { chiffrage = Integer.MIN_VALUE}*/

        while(I.hasNext() && (maximum >= chiffrage)){
            c = I.next();
            tour(c);
            chiffrage = Math.max(chiffrage, calcul_toi(j, horizon - 1, chiffrage));
            //table.put(j.hashcode(), chiffrage);
            annulerCoup();
        }
        return chiffrage/2;

    }

    // Min
    private int calcul_toi(Jeu j, int horizon, int maximum){
        // ActionUser control = new ActionUser(j);
        ArrayList<Coup> succ = successeur(j);
        Iterator<Coup> I = succ.iterator();
        int chiffrage = Integer.MIN_VALUE; // table.get(j.hashcode())
        Coup c;

        if(j.estGagnant() || horizon == 0){
            return chiffrage(j);
        }/*elif (chiffrage != null) {
            return chiffrage
        } else {
            chiffrage = Integer.MAX_VALUE;
        } */

        while(I.hasNext() && (maximum >= chiffrage)){
            c = I.next();
            tour(c);
            chiffrage = Math.max(chiffrage, calcul_moi(j, horizon - 1, chiffrage));
            //table.put(j.hashcode(), chiffrage);
            annulerCoup();
        }
        return -chiffrage/2;
    }

    private ArrayList<Coup> successeur (Jeu j){
        ArrayList<Point> deplacement = new ArrayList<Point>(0);
        ArrayList<Point> construction = new ArrayList<Point>(0);
        ArrayList<Coup> successeur = new ArrayList<Coup>(0);
        VerificateurPion vp = new VerificateurPion(j);
        VerificateurEtage ve = new VerificateurEtage(j);
        Point[] p = j.getPosiPions(j.getJoueurEnJeu());
        
        for(int i = 0; i< 2; i++){
            deplacement = getVoisin(p[i], vp);
            Iterator<Point> It1 = deplacement.iterator();
            Point depl;
            while(It1.hasNext()){
                depl = It1.next();
                construction = getVoisin(depl, ve);
                construction.add(p[i]);
                Iterator<Point> It2 = construction.iterator();
                while(It2.hasNext()){
                    successeur.add(new Coup(p[i], depl, It2.next(), j.getJoueurEnJeu()));
                }
            }
        }

        return successeur;
    }


    private ArrayList<Collection<Point>> observes (Jeu j, Point p){
        ArrayList<Point> deplacement = new ArrayList<Point>(0);
        ConcurrentSkipListSet<Point> construction = new ConcurrentSkipListSet<Point>();
        ArrayList<Collection<Point>> successeur = new ArrayList<Collection<Point>>(2);
        VerificateurPion vp = new VerificateurPion(j);
        VerificateurEtage ve = new VerificateurEtage(j);
        
        deplacement = getVoisin(p, vp);
        Iterator<Point> It1 = deplacement.iterator();
        Point depl;
        construction.add(p);
        while(It1.hasNext()){
            depl = It1.next();
            Iterator<Point> It2 = getVoisin(depl, ve).iterator();
            while(It2.hasNext()){
                construction.add(It2.next());
            }
        }
        successeur.add(0, deplacement);
        successeur.add(1, construction);
        return successeur;
    }


    /* Chiffrage :
     * retour positif = plus de chance de perdre que de gagner
     * retour négatif  = plus de chance de gagner que de perdre
     */

    private int chiffrage(Jeu j){
        Point[] p = j.getPosiPions(j.getJoueurEnJeu());
        Point[] p1 = j.getPosiPions(j.getJoueurEnJeu() %2 + 1);
        if (j.getNbEtage(p[0]) == 3 || j.getNbEtage(p[1]) == 3){
            System.err.println("Je l'ai vu");
            return Integer.MAX_VALUE;
        } else if (j.getNbEtage(p1[0]) == 3 || j.getNbEtage(p1[1]) == 3) {
            System.err.println("Je vois " + (j.getJoueurEnJeu() %2 + 1) +" gagner");
            return -Integer.MAX_VALUE;
        }

        if (r.nextBoolean())
            return 0;
        else
            return -0;
    }

    private void tour(Coup c){
        j.histoAjouterCoup(c);
        j.addTour();
        if (c.estDeplacement()){
            j.deplacerPersonnage(c.getDepart(), c.getArrive());
            j.Construire(c.getConstruction());
        } else {
            j.poserPersonnage(c.getDepart(), c.getJoueur());
        }
    }

    private void annulerCoup(){
		int pos;
		Coup c;
        j.subTour();
		try{
			c = j.histoAnnulerCoup();
		} catch(IndexOutOfBoundsException e){
			System.err.println("Plus de coup a annuler");
			return;
		}
	
		if(c.estDeplacement()){
			//System.out.println(c.getArrive());
			j.deplacerPersonnage(c.getArrive(), c.getDepart());
			j.detruireEtage(c.getConstruction());
		} else {
            //System.out.println("Annulation de placement ? Vraiment ?");
			pos = j.histoPosition();
			if(pos % 2 == 0){
				j.enleverPerso(c.getDepart());
			}
			else{
				j.enleverPerso(c.getDepart());
			}
		}
    }

}


class ListGagnant {
    int max;
    ArrayList<Coup> gagnant;

    public ListGagnant(){
        max = Integer.MIN_VALUE;
        gagnant = new ArrayList<Coup>();
    }

    public ListGagnant(int m, Coup c){
        max = m;
        gagnant = new ArrayList<Coup>(0);
        gagnant.add(c);
    }

    public void ajouter(int m, Coup c){
        if (m == max){
            gagnant.add(c);
        } else if (m > max){
            max = m;
            gagnant = new ArrayList<Coup>(0);
            gagnant.add(c);
        }
    }
    public Coup extraire(){
        Random r = new Random((long) 0);
        return gagnant.get(r.nextInt(gagnant.size()));
    }
}