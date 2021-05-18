package controleur;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import modele.*;
import interfaceUser.*;

public class EcouteurDeSouris extends MouseAdapter {

	PlateauInterface_2 plateau;

	public EcouteurDeSouris(PlateauInterface_2 plateau)
	{
		this.plateau = plateau;
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		plateau.FaireActionUser (e.getX(),e.getY());
	}
}