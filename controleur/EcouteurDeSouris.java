package controleur;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import modele.*;

import interfaceUser.*;

public class EcouteurDeSouris extends MouseAdapter {

	PlateauInterface_2 plateau;
	GestionUser g;

	public EcouteurDeSouris(PlateauInterface_2 plateau, GestionUser g)
	{
		this.plateau = plateau;
		this.g = g;
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		if (!g.iaJoue)
		{
			plateau.FaireActionUser (e.getX(),e.getY());
		}
	}
}