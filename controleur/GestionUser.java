package controleur;


import java.util.concurrent.TimeUnit;
//import javax.swing.*;

import modele.*;



public class GestionUser
{
	Jeu j;
	boolean commencer;

	public GestionUser (Jeu j)
	{
		this.j = j;
		this.commencer = false;

	}

	public void tictac ()
	{
		int nbAfk= 0 ;
		int numJoueur;
		Action action;

		//System.out.println("ici");
		if (j != null && !j.estGagnant())
		{
			for (int i = 1; i < 3; i++ )
			{
				if (j.getAction(i) == Action.AFK)
				{
					nbAfk++;
				}
			}

			if (nbAfk == 2 && j.getTour () >= 1)
			{	
				j.addTour();
				numJoueur = j.getJoueurEnJeu();
				j.setAction (numJoueur,Action.A_DEPLACER);
			}
			else if (nbAfk == 2 && j.getTour() < 1)
			{
				if (commencer == false)
				{
					commencer = true;
				}
				else
				{
					j.addTour();
				}
				numJoueur = j.getJoueurEnJeu();
				j.setAction (numJoueur,Action.PREMIER_PLACEMENT);

				
			}

			numJoueur = j.getJoueurEnJeu();
			action = j.getAction(numJoueur);
			System.out.println(" Au joueur : " + numJoueur + " de " + action);
			//System.out.println(" Au joueur : " + (numJoueur%2 + 1) + " de " + j.getAction(numJoueur%2+1));
		}
		else
		{
			numJoueur = j.getJoueurEnJeu();
			System.out.println(" Le Joueur : " + numJoueur + " A gagnée ! ");
		}
	}
}