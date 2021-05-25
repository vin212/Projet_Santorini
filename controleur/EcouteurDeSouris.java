package controleur;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import modele.*;
import controleurIA.*;

import interfaceUser.*;

public class EcouteurDeSouris extends MouseAdapter {

	PlateauInterface_2 plateau;
	GestionUser g;
    IA ia;

    public EcouteurDeSouris(PlateauInterface_2 plateau, GestionUser g, IA ia)
	{
		this.plateau = plateau;
		this.g = g;
        this.ia = ia;
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
        if (ia != null && !ia.estActive() && g.iaJoue)
        {
            System.out.println("reactover ia-----------------------");
            ia.activeIA();
        }
		else if (!g.iaJoue)
		{
			plateau.FaireActionUser (e.getX(),e.getY());
		}
	}
}