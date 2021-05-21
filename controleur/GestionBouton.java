package controleur;

import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.*;

import modele.*;
import interfaceUser.*;

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
					action.annulerCoup();
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
				f.ChangerFenetres (2);
				f.gestionFenetre ();
				f.frame.repaint();
			break;
			case RETOUR_JEU :
				f.ChangerFenetres (0);
				f.gestionFenetre ();
				f.frame.repaint();
			break;
			case RECOMMENCER :
				f.j = new Jeu();
				f.ChangerFenetres (0);
				f.gestionFenetre ();
				f.frame.repaint();
			break;
		}
	}
}