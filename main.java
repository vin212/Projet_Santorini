import interfaceUser.*;
import modele.*;
import global.*;

import javax.swing.SwingUtilities;
import java.util.Properties;

public class main {
	public static void main (String[] args) {
		System.out.println("Hello World");

	

		Configuration prop = new Configuration();
		/*System.out.println(prop.recupValeur ("raccourci_retour"));*/
		System.out.println(prop.recupValeur ("raccourci_pause"));
		prop.changerValeur ("aide","true");
		Jeu j = new Jeu(prop);


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

		//SwingUtilities.invokeLater(new Fenetre(j));

		
		j.poserPersonnage (new Point (3,3), 1);
		j.poserPersonnage (new Point (0,1), 1);
		j.poserPersonnage (new Point (2,2), 2);
		j.poserPersonnage (new Point (1,3), 2);
		//j.deplacerPersonnage(new Point(1,2), new Point(1,3));*/

		SwingUtilities.invokeLater(new Fenetre(j,prop));
		///j.setAction (1,Action.PREMIER_PLACEMENT);

		System.out.println("Gagnant  " + j.estGagnant());

	}	
}