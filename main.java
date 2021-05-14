import interfaceUser.*;
import modele.*;
import structure.*;

import javax.swing.*;

public class main 
{
	public static void main (String[] args)
	{
		System.out.println("Hello World");
		SwingUtilities.invokeLater(new Fenetre());

		Jeu j = new Jeu();

		j.Construire(new Point(1,1));
		j.Construire(new Point(1,1));
		j.AfficherPlateau ();

		System.out.println("Constructible : " + j.Constructible (new Point(0,0)));
	}
}