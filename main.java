import interfaceUser.*;
import modele.*;
import structure.*;

import javax.swing.*;

public class main 
{
	public static void main (String[] args)
	{
		System.out.println("Hello World");

		Jeu j = new Jeu();

		/*for (int i = 0; i < j.getLargeurPlateau(); i++)
		{
			for (int k = 0; k < j.getHauteurPlateau (); k++)
			{
				for (int t = 0; t < 4; t++)
				{
					j.Construire(new Point(i,k));
				}
			}
		}*/
		j.Construire(new Point(1,1));
		j.Construire(new Point(1,1));
		j.Construire(new Point(1,1));
		j.Construire(new Point(1,1));
		j.Construire(new Point(1,2));
		j.Construire(new Point(1,2));
		j.Construire(new Point(1,3));
		j.Construire(new Point(2,2));
		j.Construire(new Point(0,0));
		j.Construire(new Point(0,0));
		j.Construire(new Point(0,0));
		j.Construire(new Point(0,0));
		j.AfficherPlateau ();

		SwingUtilities.invokeLater(new Fenetre(j));

		
		j.poserPersonnage (new Point (3,3), 1);
		j.poserPersonnage (new Point (0,1), 1);
		j.poserPersonnage (new Point (0,2), 2);
		j.poserPersonnage (new Point (1,2), 2);
		j.deplacerPersonnage(new Point(1,2), new Point(1,3));

		System.out.println("qui est ici: " + j.quiEstIci (new Point(0,0)));
	}	
}