package controleur;

import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.*;

import modele.*;
import interfaceUser.*;

public class GestionBouton extends JButton implements ActionListener
{
	Bouton type;
	Jeu j;

	public GestionBouton (Jeu j, PlateauInterface_2 aire2, Bouton type)
	{
		this.type = type;
		this.j = j;
	}
	public void actionPerformed(ActionEvent e) 
	{
		System.out.println("Le bouton : " + type + " a ete presser");
		switch (this.type)
		{
			case RETOUR :
				try
				{
					j.histoAnnulerCoup();
				}
				catch (ArrayIndexOutOfBoundsException e)
				{
					System.err.println("Impossible d'annuler");
				}
			break;
			case RETABLIR :
				try
				{
					j.histoRetablir();
				}
				catch (ArrayIndexOutOfBoundsException e)
				{
					System.err.println("Impossible de rétablir");
				}
			break;
			case PAUSE :
			break;
		}
	}
}