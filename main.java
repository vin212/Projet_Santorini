import interfaceUser.*;
import modele.*;
import global.*;

import javax.swing.SwingUtilities;
import java.util.Properties;

public class Main {
	public static void main (String[] args) {
		System.out.println("Hello World");

		Configuration prop = new Configuration();
		Jeu j = new Jeu(prop);

		SwingUtilities.invokeLater(new Fenetre(j,prop));

		System.out.println("Gagnant  " + j.estGagnant());
	}	
}