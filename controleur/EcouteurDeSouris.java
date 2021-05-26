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
    IA ia1;
    IA ia2;

    NomFenetres name;
    String nomFichier;

    public EcouteurDeSouris(PlateauInterface_2 plateau, GestionUser g, IA ia1, IA ia2)
	{
		this.plateau = plateau;
		this.g = g;
        this.ia1 = ia1;
        this.ia2 = ia2;
        this.name = NomFenetres.JEU;
	}

	public EcouteurDeSouris(String nomFichier)
	{
		this.name = NomFenetres.CHARGER;
		this.nomFichier = nomFichier;
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
        if (name == NomFenetres.JEU && ia1 != null && !ia1.estActive() && g.iaJoue )
        {
            System.out.println("reactover ia-----------------------");
            ia1.activeIA();
        }
        else if (name == NomFenetres.JEU && ia2 != null && !ia2.estActive() && g.iaJoue )
        {
            System.out.println("reactover ia-----------------------");
            ia2.activeIA();
        }
		else if (name == NomFenetres.JEU && !g.iaJoue)
		{
			System.out.println("faire action: ");
			plateau.FaireActionUser (e.getX(),e.getY());
		}
		else if (name == NomFenetres.CHARGER)
		{
			System.out.println("nom fichier : " + nomFichier);
		}
	}
}