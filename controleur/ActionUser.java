package controleur;

import modele.*;
import structure.*;
import java.lang.*;

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

		if (!j.estGagnant())
		{
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
				case DEXIEME_PLACEMENT :
					placerPerso (posi_final,a);
				break;

			}
		}
		//avancerPerso (posi_final);
	}

	public void selectionnerPerso(Point posi_final)
	{
		int perso;
		if (posi_final.getx() >= 0 && posi_final.getx() < j.getLargeurPlateau () && posi_final.gety() >= 0 && posi_final.gety() < j.getHauteurPlateau ())
		{
			perso = j.quiEstIci (posi_final);
			if (perso == j.getJoueurEnJeu())
			{
				System.out.println("ok select perso");
				this.posi_init = posi_final;
				j.setAction (j.getJoueurEnJeu(),Action.EN_COURS_DE_DEPLACEMENT);
			}
		}
	}

	public void avancerPerso (Point posi_final)
	{
		int perso;
		perso = j.quiEstIci (posi_final);
		if (posi_final.getx() >= 0 && posi_final.getx() < j.getLargeurPlateau () && posi_final.gety() >= 0 && posi_final.gety() < j.getHauteurPlateau ())
		{
			if (Math.abs(posi_final.getx() - this.posi_init.getx()) + Math.abs(posi_final.gety() - this.posi_init.gety()) == 1 )
			{

				if (j.peutPoserUnPerso (posi_final) && j.getNbEtage (posi_final) - j.getNbEtage (posi_init) <= 1  )
				{
					System.out.println("ok deplacer");
					j.deplacerPersonnage (posi_init,posi_final);
					j.setAction (j.getJoueurEnJeu(),Action.A_CONSTRUIRE);
					this.posi_init = posi_final;
				}
				else
				{
					System.out.println("Coup INVALIDE !!! " + posi_final+ posi_init);
				}
				
			}
			else if (Math.abs(posi_final.getx() - this.posi_init.getx()) == 1 && Math.abs(posi_final.gety() - this.posi_init.gety()) == 1 )
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
					System.out.println("Coup INVALIDE !!! " + posi_final+ posi_init);
				}
			}
			else if (perso == j.getJoueurEnJeu())
			{
				System.out.println("ok select perso");
				this.posi_init = posi_final;
			}
			else
				{
					System.out.println("Coup INVALIDE !!! " + posi_final + posi_init);
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
					j.setAction (j.getJoueurEnJeu(),Action.DEXIEME_PLACEMENT);
				}
				else
				{
					j.setAction (j.getJoueurEnJeu(),Action.AFK);
				}
				this.posi_init = posi_final;
			}
				
		}
		
	}



}