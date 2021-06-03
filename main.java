import interfaceUser.*;
import modele.*;
import global.*;

import javax.swing.SwingUtilities;
import java.awt.GraphicsEnvironment;

public class Main {
	public static void main (String[] args) {
		

		Configuration prop = new Configuration();

		prop.envoyerLogger("Demarage du jeu",TypeLogger.INFO);

		Jeu j = new Jeu(prop);

		SwingUtilities.invokeLater(new Fenetre(j,prop));

  
	}

		
}