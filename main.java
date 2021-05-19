import interfaceUser.*;
import modele.*;
import structure.*;

import javax.swing.SwingUtilities;

public class main 
{
	public static void main (String[] args)
	{
		System.out.println("Hello World");

		Jeu j = new Jeu();

		/*j.Construire(new Point(1,1));
		j.Construire(new Point(1,1));
		j.Construire(new Point(1,1));
		j.Construire(new Point(1,1));
		j.Construire(new Point(1,2));
		j.Construire(new Point(1,2));
		j.Construire(new Point(1,2));
		j.Construire(new Point(1,3));
		j.Construire(new Point(2,2));
		j.Construire(new Point(2,2));
		j.Construire(new Point(2,3));
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
		j.deplacerPersonnage(new Point(1,2), new Point(1,3));*/

		SwingUtilities.invokeLater(new Fenetre(j));
		j.setAction (1,Action.PREMIER_PLACEMENT);

		//System.out.println("qui est ici: " + j.quiEstIci (new Point(0,0)));
	}	
}