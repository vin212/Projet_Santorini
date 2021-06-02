package interfaceUser;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.Properties;
import java.util.ArrayList;

import modele.*;
import controleur.*;
import controleurIA.*;
import global.*;
import save.*;
import javax.swing.border.Border;
import java.awt.event.KeyEvent;

public class Fenetres {
	public NomFenetres f;
	public NomFenetres f_avant;

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

	ActionUser actionUser;

	Configuration prop;

	public Fenetres (Jeu j, Configuration prop)
	{
		this.j = j;
		this.f = NomFenetres.PAGE_ACCUEIL ;

		this.toucheAppuier = new Integer [2]; 
		this.toucheAppuier[0] = -1;
		this.toucheAppuier[1] = -1;

		this.prop = prop;
		this.actionUser= new ActionUser(j,prop);
		
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		frame = new JFrame("Santorini");

		try {
			InputStream  in = new FileInputStream("ressource/texture/total.png");
			Image icon = ImageIO.read(in);
			frame.setIconImage(icon); 
		}
		catch (Exception except)
		{
			prop.envoyerLogger("erreur icone introuvable",TypeLogger.WARNING);
		}

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(Integer.parseInt(this.prop.recupValeur("largeur_fenetre")),Integer.parseInt(prop.recupValeur("hauteur_fenetre")));
		frame.setLocation(tailleEcran.width/2 - frame.getSize().width/2,tailleEcran.height/2 - frame.getSize().height/2);
		//this.action = new ActionUser(j);
	}

	public NomFenetres getNomFenetres()
	{
		return this.f;
	}

	public void ChangerFenetres (NomFenetres i)
	{
		this.f = i;
	}

	public void ChangerFenetres (NomFenetres i, NomFenetres f_avant)
	{
		this.f = i;
		this.f_avant = f_avant;
	}

	public void gestionFenetre ()
	{
		switch (f)
		{
			case JEU :
				prop.envoyerLogger("Fenetre jeu ouverte",TypeLogger.INFO);
				afficherFenetre1 ();
				frame.repaint();
			break;
			case MENU_PAUSE :
				prop.envoyerLogger("Fenetre menu pause ouverte",TypeLogger.INFO);
				afficherMenuPause(); 
				frame.repaint();
			break;
			case PAGE_ACCUEIL :
				prop.envoyerLogger("Fenetre d'accueil",TypeLogger.INFO);
				afficherAccueil();
				frame.repaint();
			break;
			case POPUP_SAUVEGARDE :
				prop.envoyerLogger("Pop up sauvegarde",TypeLogger.INFO);
				afficherPopUpSauvegarde();
				frame.repaint();
			break;
			case CHARGER :
				prop.envoyerLogger("Fenetre charger sauvegarde",TypeLogger.INFO);
				afficherCharger();
				frame.repaint();
			break;
			case NOUVELLE_PARTIE :
				prop.envoyerLogger("Fenetre faire nouvelle partie",TypeLogger.INFO);
				afficherNouvellePartie ();
				frame.repaint();
			break;
			case OPTION :
				prop.envoyerLogger("Fenetre option",TypeLogger.INFO);
				afficherOption ();
				frame.repaint();
			break;
			case AUTRE :	
			    //Toolkit.getDefaultToolkit().getScreenSize();
				prop.envoyerLogger("easter egg",TypeLogger.INFO);
    			Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); 
				frame.setLocation(tailleEcran.width/2 - frame.getSize().width/2,tailleEcran.height/2 - frame.getSize().height/2);
				aire1 = new PlateauInterface_1 (j);
				frame.add(aire1);
				frame.setVisible(true);
			break;
			default :
				prop.envoyerLogger("Fenetre non defini",TypeLogger.WARNING);
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
	 
		


		JPanel leJoueur = new JPanel ();
		JLabel action = new JLabel("AFK");
		leJoueur.setPreferredSize(new Dimension(200, 25));
		leJoueur.setBackground(new Color(0,110,255));
		action.setForeground(new Color(255,255,255));

		leJoueur.add(action);

		aire2 = new PlateauInterface_2 (j,actionUser,prop);
		this.g = new GestionUser( this.j, ia1, ia2, aire2,leJoueur,action, actionUser,prop);

		
		JButton boutonRetour = new JButton("<");
		JButton boutonPause = new JButton("||");
		JButton boutonRetablir = new JButton(">");

		boutonRetour.setFocusable(false);
		boutonPause.setFocusable(false);
		boutonRetablir.setFocusable(false);

		boutonRetour.addActionListener(new GestionBouton(this.j,this.aire2,Bouton.RETOUR,this,prop));
		boutonPause.addActionListener(new GestionBouton(this.j,this.aire2,Bouton.PAUSE,this,prop));
		boutonRetablir.addActionListener(new GestionBouton(this.j,this.aire2,Bouton.RETABLIR,this,prop));

	


		
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); 

		
		JPanel container = new JPanel();
		JPanel containerEst = new JPanel();
		container.setFocusable(false);
		containerEst.setPreferredSize(new Dimension(200, 400));
		container.setPreferredSize(new Dimension(200, 400));
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

		
		this.chrono = new Timer( 50, new EvenementTemp(g));
		chrono.start();


		aire2.setFocusable(false);

		aire2.addMouseListener(new EcouteurDeSouris(aire2,g,ia1,ia2));
		clavier = new EcouteurDeClavier(toucheAppuier,this,j,aire2,prop);
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
		JButton boutonOption = new JButton ("Option");
		JButton boutonMenu = new JButton ("Retour Menu (sans sauvegarde)");

		boutonRetourJeu.addActionListener(new GestionBouton(this.j,this.aire2,Bouton.RETOUR_JEU,this,prop));
		boutonSauvegarde.addActionListener(new GestionBouton(this.j,this.aire2,Bouton.SAUVEGARDER,this,prop));
		boutonRecommencer.addActionListener(new GestionBouton(this.j,this.aire2,Bouton.RECOMMENCER,this, prop));
		boutonOption.addActionListener(new GestionBouton(this.j,this.aire2,Bouton.OPTION,this, prop));
		boutonMenu.addActionListener(new GestionBouton(this.j,this.aire2,Bouton.RETOUR_MENU,this,prop));

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
		container.add(boutonOption,gbc);

		gbc.gridx = 0;
		gbc.gridy = 200;
		container.add(boutonMenu,gbc);

		frame.add(container);

		clavier = new EcouteurDeClavier(toucheAppuier,this,j,aire2,prop);
		frame.addKeyListener(clavier);
		frame.setVisible(true);
	}

	public void afficherAccueil ()
	{
		frame.getContentPane().removeAll(); 
		JButton boutonNouvellePartie = new JButton ("Nouvelle Partie");
		JButton boutonCharger = new JButton ("Charger");
		JButton boutonOption = new JButton ("Option");
		JButton boutonQuitter = new JButton ("Quitter");

		boutonNouvellePartie.addActionListener(new GestionBouton(this.j,this.aire2,Bouton.NOUVELLE_PARTIE,this,prop));
		boutonCharger.addActionListener(new GestionBouton(this.j,this.aire2,Bouton.CHARGER,this,prop));
		boutonOption.addActionListener(new GestionBouton(this.j,this.aire2,Bouton.OPTION,this,prop));
		boutonQuitter.addActionListener(new GestionBouton(this.j,this.aire2,Bouton.QUITTER,this,prop));
		

		JPanel container = new JPanel();
		container.setBackground(new Color(150,150,150));

		container.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;

		container.add(boutonNouvellePartie,gbc);

		gbc.gridx = 0;
		gbc.gridy = 100;
		container.add(boutonCharger,gbc);

		gbc.gridx = 0;
		gbc.gridy = 150;
		container.add(boutonOption,gbc);

		gbc.gridx = 0;
		gbc.gridy = 2000;
		container.add(boutonQuitter,gbc);

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
		//frame.setLayout(null);

		int width = frame.getSize().width;
		int height = frame.getSize().height;

		Save saves = new Save(this.j);

		ArrayList <String> nomSave;
		nomSave = saves.lesSauvegardes();

		JPanel container = new JPanel();
		JPanel containerMain = new JPanel();
		JScrollPane scrPane = new JScrollPane(container);
		container.setBackground(new Color(150,150,150));

		container.setLayout(new GridBagLayout());
		containerMain.setLayout(new GridBagLayout());

		JPanel test = new JPanel ();
		test.setLayout(new GridBagLayout());
		test.setPreferredSize(new Dimension(width,height));

		test.setBackground(new Color(50,0,0));

		GridBagConstraints gbc = new GridBagConstraints();

		JPanel save;
		for (int i = 0; i < nomSave.size(); i++)
		{
			Border lineborder = BorderFactory.createLineBorder(Color.black, 2);


    		//associer à JLabel
			save = new JPanel();
			gbc.gridx = 0;
			gbc.gridy = i*height/5;
			save.add(new JLabel(nomSave.get(i)),gbc);
			save.addMouseListener(new EcouteurDeSouris(nomSave.get(i), this.j, this));
			save.setBorder(lineborder);
			save.setPreferredSize(new Dimension(width * 7/8, height/5));
			//save.setResizable(true);

			container.add(save,gbc);
			
		}
		/*JScrollPane scroll1 = new JScrollPane();
		container.add(scroll1);*/
		containerMain.setPreferredSize(new Dimension(width,height));
		container.setPreferredSize(new Dimension(width - width/10,height/5*nomSave.size()));
		scrPane.setPreferredSize(new Dimension(width, 3*height/4));
		gbc.gridx = 0;
		gbc.gridy = 0;
		containerMain.add(scrPane,gbc);
		JButton bouton = new JButton ("Retour Menu");
		bouton.addActionListener(new GestionBouton(Bouton.RETOUR_MENU,this,prop));
		gbc.gridx = 0;
		gbc.gridy = height/2;
		containerMain.add (bouton,gbc);

		//test.add(containerMain);
		frame.add(containerMain);

		frame.setVisible(true);

	}

	public void afficherNouvellePartie()
	{
		frame.getContentPane().removeAll();
		
		GridBagConstraints gbc = new GridBagConstraints();
		JPanel container = new JPanel ();
		JPanel containerMain = new JPanel ();
		JPanel couleur = new JPanel ();

		couleur.setLayout(new GridBagLayout());
		containerMain.setLayout(new GridBagLayout());
		String [] element = new String[] {"Joueur","IA Facile", "IA Normal", "IA Difficile"};

		JComboBox menuBar1 = new JComboBox(element);
		JComboBox menuBar2 = new JComboBox(element);
		/*menuBar.add(new JLabel("Element 1"));
		menuBar.add(new JLabel("Element 2"));*/

		JPanel boite = new JPanel();
		boite.setBackground(new Color(0,110,255));
		boite.setPreferredSize(new Dimension(100, 25));

		gbc.gridx = 0;
		gbc.gridy = 0;

		couleur.add(boite,gbc);

		boite = new JPanel ();
		boite.setBackground(new Color(238,238,238));
		boite.setPreferredSize(new Dimension(25, 25));

		gbc.gridx = 1;
		gbc.gridy = 0;

		couleur.add(boite,gbc);

		boite = new JPanel ();
		boite.setBackground(new Color(255,0,0));
		boite.setPreferredSize(new Dimension(100, 25));

		gbc.gridx = 2;
		gbc.gridy = 0;

		couleur.add(boite,gbc);

		gbc.gridx = 0;
		gbc.gridy = 0;

		containerMain.add(couleur,gbc);

		container.add(menuBar1);
		container.add(new JLabel("VS"));
		container.add(menuBar2);

		gbc.gridx = 0;
		gbc.gridy = 1;
		containerMain.add(container,gbc);


		container = new JPanel();
		container.setBackground(new Color(238,238,238));
		container.setPreferredSize(new Dimension(100, 100));

		gbc.gridx = 0;
		gbc.gridy = 2;
		containerMain.add(container,gbc);

		JButton boutonCestParti = new JButton ("C'est parti !"); 

		gbc.gridx = 0;
		gbc.gridy = 3;
		containerMain.add(boutonCestParti,gbc);

		//container = new JPanel ();

		JButton boutonRetourMenu = new JButton ("Retour Menu");

		gbc.gridx = 0;
		gbc.gridy = 4;
		containerMain.add(boutonRetourMenu,gbc);
		boutonRetourMenu.addActionListener(new GestionBouton (j,aire2,Bouton.RETOUR_MENU,this,prop));
		boutonCestParti.addActionListener(new GestionBouton (j,Bouton.LANCER_PARTIE,this,menuBar1,menuBar2,prop));


		


		frame.add(containerMain);

		frame.setVisible(true);
	}

	public void afficherOption ()
	{
		System.out.println();
		frame.getContentPane().removeAll();

		ArrayList <String> clefs;

		GridBagConstraints gbc = new GridBagConstraints();

		Border lineborder = BorderFactory.createLineBorder(Color.black, 2);


		JPanel containerMain = new JPanel ();
		JPanel container = new JPanel ();
		JPanel bloc = new JPanel(); 

		JCheckBox checkBox = new JCheckBox();

		containerMain.setLayout(new GridBagLayout());
		container.setLayout(new GridBagLayout());

		clefs = prop.recupClesModifiable ();
		//System.out.println("taille : " + clefs.size());
		for (int i = 0; i < clefs.size(); i++)
		{
			//System.out.println("celf : " + clefs.get(i));
			bloc = new JPanel ();
			JLabel label = new JLabel (clefs.get(i) + ":");
			//System.out.println("valeur : " + prop.recupValeur(clefs.get(i)));
			gbc.gridx = 0;
			gbc.gridy = 0;
			bloc.add (label,gbc);

			String [] valDecoup = prop.recupValeur(clefs.get(i)).split(" ");
			if (valDecoup.length < 2 && !prop.recupValeur(clefs.get(i)).equals("true") && !prop.recupValeur(clefs.get(i)).equals("false"))
			{

				gbc.gridx = 1;
				gbc.gridy = 0;
				label = new JLabel(KeyEvent.getKeyText( Integer.parseInt(prop.recupValeur(clefs.get(i)))));
				label.setBorder(lineborder);
				label.addMouseListener(new EcouteurDeSouris(prop,clefs.get(i),0,frame,this));

				bloc.add (label,gbc);
				gbc.gridx = 0;
				gbc.gridy = i;
				container.add(bloc,gbc);
			}
			else if (valDecoup.length < 2 && (prop.recupValeur(clefs.get(i)).equals("true") || prop.recupValeur(clefs.get(i)).equals("false")))
			{
				
				checkBox = new JCheckBox();
				gbc.gridx = 1;
				gbc.gridy = 0;
				if (prop.recupValeur(clefs.get(i)).equals("true") )
				{
					checkBox.setSelected(true);
				}
				else
				{
					checkBox.setSelected(false);
				}
				checkBox.addActionListener(new EcouteurCheckBox(clefs.get(i),prop,j));
				bloc.add (checkBox,gbc);
				gbc.gridx = 0;
				gbc.gridy = clefs.size() + i;
				container.add(bloc,gbc);

			}
			else if (valDecoup.length == 2)
			{
				gbc.gridx = 1;
				gbc.gridy = 0;
				label = new JLabel(KeyEvent.getKeyText( Integer.parseInt(valDecoup[0])));
				label.setBorder(lineborder);
				label.addMouseListener(new EcouteurDeSouris(prop,clefs.get(i),0,frame,this));

				bloc.add (label,gbc);

				label = new JLabel (" + ");
				gbc.gridx = 2;
				gbc.gridy = 0;
				bloc.add (label,gbc);

				gbc.gridx = 3;
				gbc.gridy = 0;
				label = new JLabel(KeyEvent.getKeyText( Integer.parseInt(valDecoup[1])));
				label.addMouseListener(new EcouteurDeSouris(prop,clefs.get(i),1,frame,this));
				label.setBorder(lineborder);

				bloc.add (label,gbc);

				gbc.gridx = 0;
				gbc.gridy = i;
				container.add(bloc,gbc);

			}

			
		}

		gbc.gridx = 0;
		gbc.gridy = 0;
		containerMain.add(container,gbc);

		container = new JPanel ();
		JButton boutonRetour = new JButton ("Retour");
		boutonRetour.addActionListener(new GestionBouton(Bouton.RETOUR_OPTION,this,prop));
		container.add(boutonRetour);
		boutonRetour.setFocusable(false);


		gbc.gridx = 0;
		gbc.gridy = 2;
		containerMain.add(container,gbc);

		container = new JPanel ();
		JButton boutonDefaut = new JButton("Rétablir par defaut");
		boutonDefaut.addActionListener(new GestionBouton(Bouton.RETABLIR_DEFAUT,this,prop));
		container.add(boutonDefaut);

		gbc.gridx = 0;
		gbc.gridy = 1;
		containerMain.add(container,gbc);

		
		frame.add(containerMain);

		frame.setVisible(true);
		frame.requestFocus();


	}


} 