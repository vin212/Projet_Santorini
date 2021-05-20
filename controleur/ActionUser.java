package controleur;

import modele.*;
import structure.*;

public class ActionUser {

	Jeu j;
	Point posi_init;

	public ActionUser (Jeu j)
	{
		this.j = j;
		posi_init = new Point(-1,-1);
	}

	public void jouerAction (Point posi_final)
	{
		Action a = j.getAction (j.getJoueurEnJeu());

		if (!j.estGagnant()){
			switch (a)
			{
				case A_DEPLACER :
					selectionnerPerso(posi_final);
				break;
				case EN_COURS_DE_DEPLACEMENT :
					avancerPerso (posi_final);
				break;
				case A_CONSTRUIRE :
					construireIci (posi_final);
				break;
				case PREMIER_PLACEMENT :
					placerPerso (posi_final,a);
				break;
				case DEUXIEME_PLACEMENT :
					placerPerso (posi_final,a);
				break;
				default:
			}
		}
		//avancerPerso (posi_final);
	}

	public void selectionnerPerso(Point posi_final){
		int perso;
		if (posi_final.getx() >= 0 && posi_final.getx() < j.getLargeurPlateau () && posi_final.gety() >= 0 && posi_final.gety() < j.getHauteurPlateau ()){
			perso = j.quiEstIci (posi_final);
			if (perso == j.getJoueurEnJeu()) {
				System.out.println("ok select perso");
				this.posi_init = posi_final;
				j.setAction (j.getJoueurEnJeu(),Action.EN_COURS_DE_DEPLACEMENT);
			}
		}
	}

	public void avancerPerso (Point posi_final){
		int perso;
		perso = j.quiEstIci (posi_final);
		if (posi_final.getx() >= 0 && posi_final.getx() < j.getLargeurPlateau () && posi_final.gety() >= 0 && posi_final.gety() < j.getHauteurPlateau ()){
			if (Math.abs(posi_final.getx() - this.posi_init.getx()) + Math.abs(posi_final.gety() - this.posi_init.gety()) == 1 ) {
				if (j.peutPoserUnPerso (posi_final) && j.getNbEtage (posi_final) - j.getNbEtage (posi_init) <= 1  )
				{
					System.out.println("ok deplacer");
					j.deplacerPersonnage (posi_init,posi_final);
					j.setAction (j.getJoueurEnJeu(),Action.A_CONSTRUIRE);
					this.posi_init = posi_final;
				}
				else
				{
					System.out.println("Coup INVALIDE deplacer !!! " + posi_final+ posi_init);
				}
				
			}
			else if (Math.abs(posi_final.getx() - this.posi_init.getx()) == 1 && Math.abs(posi_final.gety() - this.posi_init.gety()) == 1 && perso != j.getJoueurEnJeu())
			{
				if (j.peutPoserUnPerso (posi_final) && j.getNbEtage (posi_final) - j.getNbEtage (posi_init) <= 1 )
				{
					System.out.println("ok deplacer");
					j.deplacerPersonnage (posi_init,posi_final);
					j.setAction (j.getJoueurEnJeu(),Action.A_CONSTRUIRE);
					this.posi_init = posi_final;

				}
				else
				{
					System.out.println("Coup INVALIDE deplacer!!! " + posi_final+ posi_init);
				}
			}
			else if (perso == j.getJoueurEnJeu())
			{
				System.out.println("ok select perso");
				this.posi_init = posi_final;
			}
			else
				{
					System.out.println("Coup INVALIDE autre!!! " + posi_final + posi_init);
				}
		}

	}

	public void construireIci (Point posi_final)
	{
		if (posi_final.getx() >= 0 && posi_final.getx() < j.getLargeurPlateau () && posi_final.gety() >= 0 && posi_final.gety() < j.getHauteurPlateau ())
		{
			if (Math.abs(posi_final.getx() - this.posi_init.getx()) + Math.abs(posi_final.gety() - this.posi_init.gety()) == 1 )
			{
				if (j.Constructible (posi_final))
				{
					System.out.println("ok constuir");
					j.setAction (j.getJoueurEnJeu(),Action.AFK);
					j.Construire (posi_final);
				}
				else
				{
					System.out.println("Coup INVALIDE !!! " + posi_final + posi_init);
				}
			}
			else if (Math.abs(posi_final.getx() - this.posi_init.getx()) == 1 && Math.abs(posi_final.gety() - this.posi_init.gety()) == 1 )
			{
				if (j.Constructible (posi_final))
				{
					System.out.println("ok constuir");
					j.setAction (j.getJoueurEnJeu(),Action.AFK);
					j.Construire (posi_final);
				}
				else
				{
					System.out.println("Coup INVALIDE !!! " + posi_final + posi_init);
				}
			}
			else
				{
					System.out.println("Coup INVALIDE !!! " + posi_final + posi_init);
				}
		}
	}

	public void placerPerso (Point posi_final,Action a)
	{
		if (posi_final.getx() >= 0 && posi_final.getx() < j.getLargeurPlateau () && posi_final.gety() >= 0 && posi_final.gety() < j.getHauteurPlateau ())
		{
			if (j.peutPoserUnPerso (posi_final) && j.getNbEtage (posi_final) - j.getNbEtage (posi_init) <= 1  )
			{
				System.out.println("ok placer");
				j.poserPersonnage (posi_final, j.getJoueurEnJeu ());
				if (a == Action.PREMIER_PLACEMENT)
				{
					j.setAction (j.getJoueurEnJeu(),Action.DEUXIEME_PLACEMENT);
				}
				else
				{
					j.setAction (j.getJoueurEnJeu(),Action.AFK);
				}
				this.posi_init = posi_final;
			}
				
		}
		
	}

	public void annulerCoup(){
		int pos;
		Coup c;
		try{
			c = j.histoAnnulerCoup();
		} catch(ArrayIndexOutOfBoundsException e){
			System.err.println("Plus de coup a annuler");
			return;
		}
		int nbJ2 = c.getJoueur() %2 + 1;
		
		if(c.estDeplacement()){
			selectionnerPerso(c.getArrive());
			avancerPerso(c.getDepart());
			j.detruireEtage(c.getConstruction());
			j.setAction(c.getJoueur(), Action.A_DEPLACER);
			j.setAction(nbJ2, Action.AFK);
		}
		else{
			pos = j.histoPosition();
			if(pos % 2 == 0){
				j.enleverPerso(c.getDepart());
				j.setAction(c.getJoueur(), Action.PREMIER_PLACEMENT);
				j.setAction(nbJ2, Action.AFK);
			}
			else{
				j.enleverPerso(c.getDepart());
				j.setAction(c.getJoueur(), Action.DEUXIEME_PLACEMENT);
				j.setAction(nbJ2, Action.AFK);
			}
		}

	}

	public void retablirCoup(){
		Coup c;
		try{
			c = j.histoRetablir();
		} catch(ArrayIndexOutOfBoundsException e){
			System.err.println("Plus de coup à rétablir.");
			return;
		}
		
		if (c.estDeplacement()){
			selectionnerPerso(c.getDepart());
			avancerPerso(c.getArrive());
			construireIci(c.getConstruction());
		} else {
			placerPerso(c.getDepart(), j.getAction(j.getJoueurEnJeu()));
		}
	}

	// Fonction spécial pour l'IA.
	public void tour(Coup c){
		j.histoAjouterCoup(c);
		if (c.estDeplacement()){
			selectionnerPerso(c.getDepart());
			avancerPerso(c.getArrive());
			construireIci(c.getConstruction());
		} else {
			placerPerso(c.getDepart(), j.getAction(j.getJoueurEnJeu()));
		}
	}


}