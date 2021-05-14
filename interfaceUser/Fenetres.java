package interfaceUser;

import java.awt.*;
import javax.swing.*;

import modele.*;

/*public enum EnumFenetre {
	MENU,MENU_PAUSE,PLATEAU;
}*/

public class Fenetres {
	int f;

	Jeu j;
	/*IA ia1;
	IA ia2;*/

	JFrame frame;
	AireDeDessin aire;

	//GestionUser g;

	public Fenetres (Jeu j)
	{
		this.j = j;
		this.f = 0;
	}

	public void ChangerFenetres (int i)
	{
		this.f = i;
	}

	public void gestionFenetre ()
	{
		switch (f)
		{
			case 0 :	
			    //Toolkit.getDefaultToolkit().getScreenSize();
    			Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); 
				frame = new JFrame("Test plateau");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(650, 400);
				frame.setLocation(tailleEcran.width/2 - frame.getSize().width/2,tailleEcran.height/2 - frame.getSize().height/2);
				
				aire = new AireDeDessin (j);
				frame.add(aire);

				frame.setVisible(true);
			break;
			default :
				frame.setVisible(false);
				frame = new JFrame("default");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(650, 400);
				frame.setVisible(true);
				frame.repaint();
		}
	}


} 