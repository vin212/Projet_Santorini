package controleur;


import controleurIA.*;
import javax.swing.*;

import modele.*;
import interfaceUser.*;

public class GestionUser
{
	Jeu j;
	boolean commencer;
	public boolean iaJoue;
	boolean humainJoue;

	IA ia1;
	IA ia2;

	PlateauInterface_2 aire1;

	JLabel leJoueur;
	JLabel gagnant;

	public GestionUser (Jeu j, IA ia1, IA ia2, PlateauInterface_2 aire1, JLabel leJoueur, JLabel gagnant)
	{
		this.j = j;
		this.commencer = false;
		this.ia1 = ia1;
		this.ia2 = ia2;
		this.humainJoue = true;

		this.aire1 = aire1;

		this.leJoueur = leJoueur;
		this.gagnant = gagnant;

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
            if (ia1 == null || (numJoueur == 1 && ia1.estActive()) )
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
					j.addTour();
					numJoueur = j.getJoueurEnJeu();
                    if (numJoueur%2 == 0)
                    {
                        humainJoue = false;
                    }
                        else
                    {
                        humainJoue = true;
                    }
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
				
			}
			else if  (numJoueur == 2 && ia1 != null && ia1.estActive())	
			{
                            System.out.println("je suis ici");
				iaJoue = true;
				JouerIA ();
			}
			action = j.getAction(numJoueur);
			leJoueur.setText("Au joueur : " + numJoueur + " de " + action);
                        //System.out.println("valeur ia : " + ia1 + "\n");
		}
		else
		{
			numJoueur = j.getJoueurEnJeu();
			leJoueur.setText("Le Joueur " + quiGagne + " a gagné ! ");

		}
		
		aire1.repaint();
	}

	private void JouerIA()
	{

		Coup coupIA;
		int numJoueur = j.getJoueurEnJeu();
		//System.out.println("ia action : " + j.getTour() +"----------------------------------------------------------------");

		ActionUser a;

		a = new ActionUser (j);

		if (j.getTour() <= 1 && (j.getAction(numJoueur) == modele.Action.AFK ||j.getAction(numJoueur) == modele.Action.PREMIER_PLACEMENT))
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
        else if (j.getTour() <= 1 && modele.Action.DEUXIEME_PLACEMENT == j.getAction(numJoueur))
        {
            j.setAction (numJoueur,modele.Action.DEUXIEME_PLACEMENT);
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