package controleur;


import java.util.concurrent.TimeUnit;
import controleurIA.*;
//import javax.swing.*;

import modele.*;
import structure.*;
import interfaceUser.*;
import javax.swing.*;



public class GestionUser
{
	Jeu j;
	boolean commencer;
	public boolean iaJoue;
	boolean humainJoue;

	IA ia1;
	IA ia2;

	PlateauInterface_2 aire1;

	public GestionUser (Jeu j, IA ia1, IA ia2, PlateauInterface_2 aire1)
	{
		this.j = j;
		this.commencer = false;
		this.ia1 = ia1;
		this.ia2 = ia2;
		this.humainJoue = true;

		this.aire1 = aire1;

	}

	public void tictac ()
	{
		int nbAfk= 0 ;
		int numJoueur;
		modele.Action action;
		numJoueur = j.getJoueurEnJeu();
		//System.out.println("ici");
		int quiGagne = j.quiGagnant() ;
		if (j != null && quiGagne == 0)
		{
			if (numJoueur == 1)
			{
				iaJoue = false;
				for (int i = 1; i < 3; i++ )
				{
					if (j.getAction(i) == modele.Action.AFK)
					{
						nbAfk++;
					}
				}

				if (nbAfk == 2 && j.getTour () >= 1)
				{	
					humainJoue = !humainJoue;
					j.addTour();
					numJoueur = j.getJoueurEnJeu();
					j.setAction (numJoueur,modele.Action.A_DEPLACER);
				}
				else if (nbAfk == 2 && j.getTour() < 1)
				{
					if (commencer == false)
					{
						commencer = true;
					}
					else
					{
						humainJoue = !humainJoue;
						j.addTour();
					}
					numJoueur = j.getJoueurEnJeu();
					j.setAction (numJoueur,modele.Action.PREMIER_PLACEMENT);

				
				}

				numJoueur = j.getJoueurEnJeu();
				action = j.getAction(numJoueur);
				
			} //System.out.println(" Au joueur : " + (numJoueur%2 + 1) + " de " + j.getAction(numJoueur%2+1));
			else if  (numJoueur == 2 && ia1 != null && ia1.estActive())	
			{
				iaJoue = true;
				System.out.println("A l'ia de jouer");
				JouerIA ();
			}
		}
		else
		{
			numJoueur = j.getJoueurEnJeu();
			System.out.println(" Le Joueur : " + quiGagne + " A gagnée ! ");

		}
		action = j.getAction(numJoueur);
		System.out.println(" Au joueur : " + numJoueur + " de " + action);
		System.out.println(" Au joueur : " + (numJoueur%2 +1) + " de " + j.getAction(numJoueur%2 +1));
		aire1.repaint();
	}

	private void JouerJoueur()
	{

	}

	private void JouerIA()
	{

		Coup coupIA;
		int numJoueur = j.getJoueurEnJeu();
		//System.out.println("ia action : " + j.getTour() +"----------------------------------------------------------------");

		ActionUser a;

		a = new ActionUser (j);

		if (j.getTour() <= 1 )
		{
			j.setAction (numJoueur,modele.Action.PREMIER_PLACEMENT);
			coupIA = ia1.debuterPartie();
			a.jouerAction (coupIA.getDepart());
			coupIA = ia1.debuterPartie();
			a.jouerAction (coupIA.getDepart());
			j.addTour();
			numJoueur = j.getJoueurEnJeu();
			j.setAction (numJoueur,modele.Action.A_DEPLACER);
		}
		else 
		{
			j.setAction (numJoueur,modele.Action.A_DEPLACER);
			coupIA = ia1.joue();
			a.jouerAction(coupIA.getDepart());
			a.jouerAction(coupIA.getArrive());
			a.jouerAction(coupIA.getConstruction());
			j.addTour();
			numJoueur = j.getJoueurEnJeu();
			j.setAction (numJoueur,modele.Action.A_DEPLACER);
		}
		

	}
}