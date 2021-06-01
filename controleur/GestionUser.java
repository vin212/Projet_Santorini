package controleur;


import controleurIA.*;
import javax.swing.*;
import java.awt.Color;

import modele.*;
import interfaceUser.*;
import java.util.concurrent.TimeUnit;
import global.*;
import structure.*;

public class GestionUser
{
	Jeu j;
	boolean commencer;
	public boolean iaJoue;
	boolean humainJoue;

	IA ia1;
	IA ia2;

	PlateauInterface_2 aire1;

	JPanel leJoueur;
	JLabel gagnant;
	JLabel labelAction;

	Configuration prop;
	ActionUser actionUser;

	Coup coupIA;

	public GestionUser (Jeu j, IA ia1, IA ia2, PlateauInterface_2 aire1, JPanel leJoueur,JLabel labelAction, ActionUser actionUser)
	{
		this.j = j;
		this.commencer = false;
		this.ia1 = ia1;
		this.ia2 = ia2;
		this.humainJoue = true;

		this.aire1 = aire1;

		this.leJoueur = leJoueur;
		this.gagnant = gagnant;

		this.labelAction = labelAction;
		this.actionUser = actionUser;

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
            if ((ia1 == null && ia2 == null) || (ia1 != null && numJoueur == 1 && ia2 == null && ia1.estActive()) || (ia2 != null && numJoueur == 2  && ia1 == null && ia2.estActive())) 
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
				iaJoue = true;
				JouerIA (1);
			}
			else if (numJoueur == 1 && ia2 != null && ia2.estActive())	
			{
				iaJoue = true;
				JouerIA (2);
			}

			action = j.getAction(numJoueur);
			if (numJoueur == 1)
			{
				leJoueur.setBackground(new Color(0,110,255));
			}
			else
			{
				leJoueur.setBackground(new Color(255,0,0));
			}

			labelAction.setText(action.toString());
		}
		else
		{
			//numJoueur = j.getJoueurEnJeu();
			if (quiGagne == 1)
			{
				leJoueur.setBackground(new Color(0,110,255));
			}
			else
			{
				leJoueur.setBackground(new Color(255,0,0));
			}

			labelAction.setText("Gagné !!");
			//leJoueur.setText("Le Joueur " + quiGagne + " a gagné ! ");

		}
		
		aire1.repaint();
	}

	private void JouerIA(int numIA)
	{

		int numJoueur = j.getJoueurEnJeu();
		//System.out.println("ia action : " + j.getTour() +"----------------------------------------------------------------");


		IA iaLocal;

		/*try
		{
			TimeUnit.SECONDS.sleep(1);
		}
		catch (Exception exept)
		{

		}*/

		if (numIA ==1 )
		{
			iaLocal = ia1;
		}
		else
		{
			iaLocal = ia2;
		}

		if (aire1.anim != null)
		{
			System.out.println("fini anim : " + aire1.anim.estFini());
		}
		

		if (j.getTour() <= 1 && (j.getAction(numJoueur) == modele.Action.AFK ||j.getAction(numJoueur) == modele.Action.PREMIER_PLACEMENT))
		{
			actionUser.initActionUser (j);
			j.setAction (numJoueur,modele.Action.PREMIER_PLACEMENT);
			coupIA = iaLocal.debuterPartie();
			actionUser.jouerAction (coupIA.getDepart());
			coupIA = iaLocal.debuterPartie();
			actionUser.jouerAction (coupIA.getDepart());
			j.addTour();
			numJoueur = j.getJoueurEnJeu();
			if (j.getTour() > 1)
			{
				j.setAction (numJoueur,modele.Action.A_DEPLACER);
			}
			else
			{	
				j.setAction (numJoueur,modele.Action.PREMIER_PLACEMENT);
			}
		}
        else if (j.getTour() <= 1 && modele.Action.DEUXIEME_PLACEMENT == j.getAction(numJoueur))
        {
        	actionUser.initActionUser (j);
            j.setAction (numJoueur,modele.Action.DEUXIEME_PLACEMENT);
			coupIA = iaLocal.debuterPartie();
			actionUser.jouerAction (coupIA.getDepart());
			j.addTour();
			numJoueur = j.getJoueurEnJeu();
			if (j.getTour() > 1)
			{
				j.setAction (numJoueur,modele.Action.A_DEPLACER);
			}
			else
			{	
				j.setAction (numJoueur,modele.Action.PREMIER_PLACEMENT);
			}
        }
		else if (j.getAction(numJoueur) != modele.Action.A_CONSTRUIRE)
		{
			actionUser.initActionUser (j);
			j.setAction (numJoueur,modele.Action.A_DEPLACER);
			coupIA = iaLocal.joue();
			actionUser.jouerAction(coupIA.getDepart());
			actionUser.jouerAction(coupIA.getArrive());
			j.setAction (numJoueur,modele.Action.A_CONSTRUIRE);
			aire1.animationIA(coupIA.getDepart(),coupIA.getArrive());
		}
		else if (j.getAction(numJoueur) == modele.Action.A_CONSTRUIRE && aire1.anim.estFini())
		{
			actionUser.jouerAction(coupIA.getConstruction());
			j.addTour();
			actionUser.initActionUser (j);
			numJoueur = j.getJoueurEnJeu();
			j.setAction (numJoueur,modele.Action.A_DEPLACER);
			aire1.anim.anim.posiInit = new Point(-1,-1);

		}

	}
}