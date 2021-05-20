package interfaceUser;

import java.awt.*;
import javax.swing.*;

import modele.*;
import controleur.*;
import controleurIA.*;

/*public enum EnumFenetre {
	MENU,MENU_PAUSE,PLATEAU;
}*/

public class Fenetres {
	int f;

	Jeu j;
	IA ia1;
	IA ia2;

	JFrame frame;
	PlateauInterface_1 aire1;
	PlateauInterface_2 aire2;

	GestionUser g;

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
				afficherFenetre1 ();
			break;
			case 1 :	
			    //Toolkit.getDefaultToolkit().getScreenSize();
				
    			Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); 
				frame = new JFrame("Test plateau");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(400, 700);
				frame.setLocation(tailleEcran.width/2 - frame.getSize().width/2,tailleEcran.height/2 - frame.getSize().height/2);
				
				aire1 = new PlateauInterface_1 (j);

				

				frame.add(aire1);

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

	public void afficherFenetre1 ()
	{
		ia1 = IA.nouvelle(this.j,"controleurIA.IAAleatoire");
		//ia1.activeIA();
		aire2 = new PlateauInterface_2 (j);
		
		JButton boutonRetour = new JButton("<");
		JButton boutonPause = new JButton("||");
		JButton boutonRetablir = new JButton(">");

		boutonRetour.addActionListener(new GestionBouton(this.j,this.aire2,Bouton.RETOUR));
		boutonPause.addActionListener(new GestionBouton(this.j,this.aire2,Bouton.PAUSE));
		boutonRetablir.addActionListener(new GestionBouton(this.j,this.aire2,Bouton.RETABLIR));

		JLabel leJoueur = new JLabel ("A toto de jouer");
		JLabel gagnant = new JLabel ("");


		
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); 

		
		JPanel container = new JPanel();
		JPanel containerEst = new JPanel();
		containerEst.setPreferredSize(new Dimension(200, 500));
		container.setPreferredSize(new Dimension(200, 450));
		container.add(leJoueur,BorderLayout.NORTH);
		container.add(gagnant,BorderLayout.NORTH);
		containerEst.add(container,BorderLayout.CENTER);

		container = new JPanel();
		container.setPreferredSize(new Dimension(200, 50));
		container.add(boutonRetour,BorderLayout.WEST);
		container.add(boutonPause,BorderLayout.CENTER);
		container.add(boutonRetablir,BorderLayout.EAST);
		containerEst.add(container,BorderLayout.SOUTH);




		frame = new JFrame("Test plateau");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 500);
		frame.setLocation(tailleEcran.width/2 - frame.getSize().width/2,tailleEcran.height/2 - frame.getSize().height/2);
				
		
		frame.add(aire2,BorderLayout.CENTER);
		frame.add(containerEst,BorderLayout.EAST);
		frame.setVisible(true);

		this.g = new GestionUser( this.j, ia1, ia2, aire2,leJoueur, gagnant);
		Timer chrono = new Timer( 50, new EvenementTemp(g));
		chrono.start();


		aire2.addMouseListener(new EcouteurDeSouris(aire2,g));
	}


} 