package controleur;

import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

import modele.*;
import interfaceUser.*;
import controleurIA.*;

public class GestionBouton extends JButton implements ActionListener
{
	Bouton type;
	//Jeu j;
	PlateauInterface_2 aire2;
	ActionUser action;
	Fenetres f;

	public GestionBouton (Jeu j, PlateauInterface_2 aire2, Bouton type, Fenetres f)
	{
		this.type = type;
		this.action = new ActionUser(j);
		this.aire2 = aire2;

		this.f = f;
	}
	public void actionPerformed(ActionEvent e) 
	{
		System.out.println("Le bouton : " + type + " a ete presser");
		switch (this.type)
		{
			case RETOUR :
				try
				{
                                    if (f.ia1 != null && f.ia1.estActive())
                                    {
                                        System.out.println("je passe ici");
                                        f.g.iaJoue = true;
                                        action.annulerCoup();
                                        f.ia1.desactiverIA();
                                        System.out.println(f.g.iaJoue);
                
                                    }
                                    else
                                    {
                                        action.annulerCoup();
                                    }
					
				}
				catch (IndexOutOfBoundsException except)
				{
					System.err.println("Impossible d'annuler");
				}
				aire2.repaint();
			break;
			case RETABLIR :
				try
				{
					action.retablirCoup();
				}
				catch (IndexOutOfBoundsException except)
				{
					System.err.println("Impossible de rétablir");
				}
				aire2.repaint();
			break;
			case PAUSE :
				f.ChangerFenetres (NomFenetres.MENU_PAUSE);
				f.gestionFenetre ();
				f.frame.repaint();
			break;
			case RETOUR_JEU :
				f.ChangerFenetres (NomFenetres.JEU);
				f.gestionFenetre ();
				f.frame.repaint();
			break;
			case RECOMMENCER :
				f.j = new Jeu();
				f.ia1 = IA.nouvelle(f.j,"controleurIA.IAAleatoire");
				f.ia1.activeIA();
				f.ChangerFenetres (NomFenetres.JEU);
				f.gestionFenetre ();
				f.frame.repaint();
			break;
			case AVEC_IA:
				f.j = new Jeu();
				f.ia1 = IA.nouvelle(f.j,"controleurIA.IAAleatoire");
				f.ia1.activeIA();
				f.ChangerFenetres (NomFenetres.JEU);
				f.gestionFenetre ();
				f.frame.repaint();
			break;
			case SANS_IA:
				f.j = new Jeu();
				f.ChangerFenetres (NomFenetres.JEU);
				f.gestionFenetre ();
				f.frame.repaint();
			break;
			case RETOUR_MENU:
				f.ia1 = null;
				f.ChangerFenetres(NomFenetres.PAGE_ACCUEIL);
				f.gestionFenetre();
				f.frame.repaint();
			break;

		}
	}
}