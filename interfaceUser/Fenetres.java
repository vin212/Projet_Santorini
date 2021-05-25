package interfaceUser;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;

import modele.*;
import controleur.*;
import controleurIA.*;

/*public enum EnumFenetre {
	MENU,MENU_PAUSE,PLATEAU;
}*/

public class Fenetres {
	NomFenetres f;

	public Jeu j;
	public IA ia1;
	IA ia2;

	Timer chrono;

	public JFrame frame;
	PlateauInterface_1 aire1;
	PlateauInterface_2 aire2;

	EcouteurDeClavier clavier;


	public GestionUser g;

	Integer [] toucheAppuier;

	ActionUser action;

	public Fenetres (Jeu j)
	{
		this.j = j;
		this.f = NomFenetres.PAGE_ACCUEIL ;

		this.toucheAppuier = new Integer [2]; 
		this.toucheAppuier[0] = -1;
		this.toucheAppuier[1] = -1;
		
		//ia1.activeIA();


		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		frame = new JFrame("Santorini");

		try {
			InputStream  in = new FileInputStream("ressource/texture/total.png");
			Image icon = ImageIO.read(in);
			frame.setIconImage(icon); 
		}
		catch (Exception except)
		{
			System.err.println("erreur icone introuvable");
		}

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 500);
		frame.setLocation(tailleEcran.width/2 - frame.getSize().width/2,tailleEcran.height/2 - frame.getSize().height/2);
		this.action = new ActionUser(j);
	}

	public NomFenetres getNomFenetres()
	{
		return this.f;
	}

	public void ChangerFenetres (NomFenetres i)
	{
		this.f = i;
	}

	public void gestionFenetre ()
	{
		switch (f)
		{
			case JEU :
				afficherFenetre1 ();
				frame.repaint();
			break;
			case MENU_PAUSE :
				afficherMenuPause(); 
				frame.repaint();
			break;
			case PAGE_ACCUEIL :
				afficherAccueil();
				frame.repaint();
			break;
			case AUTRE :	
			    //Toolkit.getDefaultToolkit().getScreenSize();
    			Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); 
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
		frame.removeKeyListener(clavier);
		
		frame.getContentPane().removeAll();
	 
		aire2 = new PlateauInterface_2 (j);
		JLabel leJoueur = new JLabel ("A toto de jouer");
		JLabel gagnant = new JLabel ("");
		
		JButton boutonRetour = new JButton("<");
		JButton boutonPause = new JButton("||");
		JButton boutonRetablir = new JButton(">");

		boutonRetour.setFocusable(false);
		boutonPause.setFocusable(false);
		boutonRetablir.setFocusable(false);

		boutonRetour.addActionListener(new GestionBouton(this.j,this.aire2,Bouton.RETOUR,this));
		boutonPause.addActionListener(new GestionBouton(this.j,this.aire2,Bouton.PAUSE,this));
		boutonRetablir.addActionListener(new GestionBouton(this.j,this.aire2,Bouton.RETABLIR,this));

	


		
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); 

		
		JPanel container = new JPanel();
		JPanel containerEst = new JPanel();
		container.setFocusable(false);
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

		containerEst.setFocusable(false);
		container.setFocusable(false);
	
		
		frame.add(aire2,BorderLayout.CENTER);
		frame.add(containerEst,BorderLayout.EAST);
		frame.setVisible(true);

		this.g = new GestionUser( this.j, ia1, ia2, aire2,leJoueur, gagnant);
		this.chrono = new Timer( 50, new EvenementTemp(g));
		chrono.start();


		aire2.setFocusable(false);

		aire2.addMouseListener(new EcouteurDeSouris(aire2,g,ia1));
		clavier = new EcouteurDeClavier(toucheAppuier,this,j,aire2);
		frame.addKeyListener(clavier);

        frame.requestFocus();
	}



	public void afficherMenuPause()
	{
		//frame.add(new JPanel ());
		frame.removeKeyListener(clavier);
		frame.setEnabled(false);
		frame.setEnabled(true);
		chrono.stop();
		frame.getContentPane().removeAll();

		JButton boutonRetourJeu = new JButton ("Retour au Jeu");
		JButton boutonRecommencer = new JButton ("Recommencer");
		JButton boutonMenu = new JButton ("Retour Menu (sans sauvegarde)");

		boutonRetourJeu.addActionListener(new GestionBouton(this.j,this.aire2,Bouton.RETOUR_JEU,this));
		boutonRecommencer.addActionListener(new GestionBouton(this.j,this.aire2,Bouton.RECOMMENCER,this));
		boutonMenu.addActionListener(new GestionBouton(this.j,this.aire2,Bouton.RETOUR_MENU,this));

		JPanel container = new JPanel();
		container.setBackground(new Color(150,150,150));

		container.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;

		container.add(boutonRetourJeu,gbc);


		gbc.gridx = 0;
		gbc.gridy = 50;
		container.add(boutonRecommencer,gbc);

		gbc.gridx = 0;
		gbc.gridy = 100;
		container.add(boutonMenu,gbc);

		frame.add(container);

		clavier = new EcouteurDeClavier(toucheAppuier,this,j,aire2);
		frame.addKeyListener(clavier);
		frame.setVisible(true);
	}

	public void afficherAccueil ()
	{
		frame.getContentPane().removeAll(); 
		JButton boutonAvecIA = new JButton ("Jouer Avec IA");
		JButton boutonSansIA = new JButton ("Jouer Sans IA");

		boutonAvecIA.addActionListener(new GestionBouton(this.j,this.aire2,Bouton.AVEC_IA,this));
		boutonSansIA.addActionListener(new GestionBouton(this.j,this.aire2,Bouton.SANS_IA,this));

		JPanel container = new JPanel();
		container.setBackground(new Color(150,150,150));

		container.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;

		container.add(boutonSansIA ,gbc);


		gbc.gridx = 0;
		gbc.gridy = 50;
		container.add(boutonAvecIA,gbc);
		frame.add(container);
		frame.setVisible(true);
	}


} 