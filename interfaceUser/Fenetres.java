package interfaceUser;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;

import modele.*;
import controleur.*;
import controleurIA.*;
import javax.swing.border.Border;

/*public enum EnumFenetre {
	MENU,MENU_PAUSE,PLATEAU;
}*/

public class Fenetres {
	NomFenetres f;

	public Jeu j;
	public IA ia1;
	public IA ia2;

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
			case POPUP_SAUVEGARDE :
				afficherPopUpSauvegarde();
				frame.repaint();
			break;
			case CHARGER :
				afficherCharger();
				frame.repaint();
			break;
			case NOUVELLE_PARTIE :
				afficherNouvellePartie ();
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

		JPanel leJoueur = new JPanel ();
		JLabel action = new JLabel("AFK");
		leJoueur.setPreferredSize(new Dimension(200, 25));
		leJoueur.setBackground(new Color(0,110,255));
		action.setForeground(new Color(255,255,255));

		leJoueur.add(action);

		
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

		this.g = new GestionUser( this.j, ia1, ia2, aire2,leJoueur,action);
		this.chrono = new Timer( 50, new EvenementTemp(g));
		chrono.start();


		aire2.setFocusable(false);

		aire2.addMouseListener(new EcouteurDeSouris(aire2,g,ia1,ia2));
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
		JButton boutonSauvegarde = new JButton ("Sauvegarder");
		JButton boutonRecommencer = new JButton ("Recommencer");
		JButton boutonMenu = new JButton ("Retour Menu (sans sauvegarde)");

		boutonRetourJeu.addActionListener(new GestionBouton(this.j,this.aire2,Bouton.RETOUR_JEU,this));
		boutonSauvegarde.addActionListener(new GestionBouton(this.j,this.aire2,Bouton.SAUVEGARDER,this));
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
		container.add(boutonSauvegarde,gbc);

		gbc.gridx = 0;
		gbc.gridy = 100;
		container.add(boutonRecommencer,gbc);

		gbc.gridx = 0;
		gbc.gridy = 150;
		container.add(boutonMenu,gbc);

		frame.add(container);

		clavier = new EcouteurDeClavier(toucheAppuier,this,j,aire2);
		frame.addKeyListener(clavier);
		frame.setVisible(true);
	}

	public void afficherAccueil ()
	{
		frame.getContentPane().removeAll(); 
		JButton boutonNouvellePartie = new JButton ("Nouvelle Partie");
		//JButton boutonSansIA = new JButton ("Jouer Sans IA");
		JButton boutonCharger = new JButton ("Charger");

		boutonNouvellePartie.addActionListener(new GestionBouton(this.j,this.aire2,Bouton.NOUVELLE_PARTIE,this));
		//boutonSansIAboutonCharger.addActionListener(new GestionBouton(this.j,this.aire2,Bouton.SANS_IA,this));
		boutonCharger.addActionListener(new GestionBouton(this.j,this.aire2,Bouton.CHARGER,this));

		JPanel container = new JPanel();
		container.setBackground(new Color(150,150,150));

		container.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;

		container.add(boutonNouvellePartie,gbc);


		/*gbc.gridx = 0;
		gbc.gridy = 50;
		container.add(boutonAvecIA,gbc);*/

		gbc.gridx = 0;
		gbc.gridy = 100;
		container.add(boutonCharger,gbc);

		frame.add(container);
		frame.setVisible(true);
	}

	public void afficherPopUpSauvegarde()
	{
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		JFrame popUp = new JFrame("Sauvegarder");
		popUp.setSize(400, 200);
		popUp.setLocation(tailleEcran.width/2 - popUp.getSize().width/2,tailleEcran.height/2 - popUp.getSize().height/2);
		popUp.setVisible(true);

		JPanel container = new JPanel();
		container.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		JPanel containerHaut = new JPanel();
		containerHaut.setLayout(new GridBagLayout());

		JPanel containerBouton = new JPanel();
		containerBouton.setLayout(new GridBagLayout());

		JPanel containerErreur = new JPanel();
		containerErreur.setLayout(new GridBagLayout());


		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel label = new JLabel ("Entrez le nom de la sauvegarde : ");
		containerHaut.add(label,gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		JTextArea textArea = new JTextArea(1,20);
		containerHaut.add(textArea,gbc);

		gbc.gridx = 0;
		gbc.gridy = 0;
		
		JButton boutonValider = new JButton("Valider");
		containerBouton.add(boutonValider,gbc);

		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel messageErreur = new JLabel ("");
		messageErreur.setForeground(new Color(255,0,0));
		containerErreur.add(messageErreur,gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		JButton boutonAnnuler = new JButton("Annuler");
		containerBouton.add(boutonAnnuler,gbc);

		gbc.gridx = 0;
		gbc.gridy = 0;
		container.add(containerHaut,gbc);

		gbc.gridx = 0;
		gbc.gridy = 100;
		container.add(containerBouton,gbc);

		gbc.gridx = 0;
		gbc.gridy = 200;
		container.add(containerErreur,gbc);

		
		
		

		popUp.add(container);

		boutonValider.addActionListener(new GestionBouton(this.j,Bouton.VALIDER_SAUVEGARDE,this,textArea, popUp, messageErreur));
		boutonAnnuler.addActionListener(new GestionBouton(this.j,Bouton.ANNULER_SAUVEGARDE,this,textArea, popUp, messageErreur));

	}

	public void afficherCharger ()
	{
		frame.getContentPane().removeAll();

		ChargerPage aire = new ChargerPage(frame,this );
		frame.add(aire);
	
		frame.setVisible(true);

	}

	public void afficherNouvellePartie()
	{
		frame.getContentPane().removeAll();
		
		GridBagConstraints gbc = new GridBagConstraints();
		JPanel container = new JPanel ();
		JPanel containerMain = new JPanel ();

		containerMain.setLayout(new GridBagLayout());
		String [] element = new String[] {"IA Facile", "IA Normal", "IA Difficile", "Joueur"};

		JComboBox menuBar1 = new JComboBox(element);
		JComboBox menuBar2 = new JComboBox(element);
		/*menuBar.add(new JLabel("Element 1"));
		menuBar.add(new JLabel("Element 2"));*/
		container.add(menuBar1);
		container.add(new JLabel("VS"));
		container.add(menuBar2);

		gbc.gridx = 0;
		gbc.gridy = 0;
		containerMain.add(container,gbc);


		JButton boutonCestParti = new JButton ("C'est partie !"); 

		gbc.gridx = 0;
		gbc.gridy = 1;
		containerMain.add(boutonCestParti,gbc);

		//container = new JPanel ();

		JButton boutonRetourMenu = new JButton ("Retour Menu");

		gbc.gridx = 0;
		gbc.gridy = 2;
		containerMain.add(boutonRetourMenu,gbc);
		boutonRetourMenu.addActionListener(new GestionBouton (j,aire2,Bouton.RETOUR_MENU,this));
		boutonCestParti.addActionListener(new GestionBouton (j,Bouton.LANCER_PARTIE,this,menuBar1,menuBar2));


		


		frame.add(containerMain);

		frame.setVisible(true);
	}


} 