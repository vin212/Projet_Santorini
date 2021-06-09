package interfaceUser;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.ArrayList;

import modele.*;
import controleur.*;
import controleurIA.*;
import global.*;
//import jdk.jfr.ContentType;
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
	public PlateauInterface_2 aire2;

	EcouteurDeClavier clavier;


	public GestionUser g;

	Integer [] toucheAppuier;

	public ActionUser actionUser;

	Configuration prop;

	Dimension EcartType;
	Dimension tailleBouton;
	Font police;
	Color backgroundColor;
	Color buttonColor;
	Color frontButtomColor;

	String lieuIcone;

	JPanel leJoueur;
	JLabel action;

	public Fenetres (Jeu j, Configuration prop)
	{
		
		this.j = j;
		this.f = NomFenetres.PAGE_ACCUEIL ;

		this.toucheAppuier = new Integer [2]; 
		this.toucheAppuier[0] = -1;
		this.toucheAppuier[1] = -1;

		this.prop = prop;
		this.actionUser= new ActionUser(j,prop);
		actionUser.initActionUser(j,prop);
		
		

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

		
		EcartType = new Dimension(frame.getSize().width/2 - frame.getSize().width/5,frame.getSize().height/12);
		
		tailleBouton = new Dimension(frame.getSize().width/2,frame.getSize().height/14);
		//police = new Font("Georgia", Font.BOLD, frame.getSize().height/25);

		
			
		police = new Font("Comic Sans MS", Font.BOLD,  frame.getSize().height/25);

   		
		backgroundColor = new Color(255,255,255);
		buttonColor = new Color(20,93,191);
		frontButtomColor = new Color(255,255,255);

		lieuIcone = "ressource/icon_bouton/";
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
			    
			break;
			default :
				prop.envoyerLogger("Fenetre non defini",TypeLogger.WARNING);
				frame.setVisible(false);
				frame = new JFrame("default");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//frame.setSize(650, 400);
				frame.setVisible(true);
				frame.repaint();
		}
	}


	public void afficherFenetre1 ()
	{
		frame.removeKeyListener(clavier);
		
		frame.getContentPane().removeAll();
		

		if (aire2 == null)
		{
			action = new JLabel("AFK");
			leJoueur = new JPanel ();
			leJoueur.setPreferredSize(new Dimension(frame.getSize().width/8*3 * 23/24, frame.getSize().height/12));
			leJoueur.setBackground(new Color(0,110,255));
			action.setForeground(new Color(255,255,255));

			action.setFont(police);

			leJoueur.add(action);
			aire2 = new PlateauInterface_2 (j,actionUser,prop);
			this.g = new GestionUser( this.j, ia1, ia2, aire2,leJoueur,action, actionUser,prop);
		}
		Dimension tailleBouton =new Dimension(frame.getSize().height/7,frame.getSize().height/7);
		

		ImageIcon icon = new ImageIcon( new ImageIcon(lieuIcone + "icone_sauvegarde.png").getImage().getScaledInstance(frame.getSize().height/16, frame.getSize().height/16, Image.SCALE_DEFAULT)); 
		JButton boutonSauvegarder = new JButton(icon);
		boutonSauvegarder.setBackground(backgroundColor);
		boutonSauvegarder.setPreferredSize(new Dimension(frame.getSize().height/16,frame.getSize().height/16));
		boutonSauvegarder.setToolTipText("sauvegarder ");
		boutonSauvegarder.addActionListener(new GestionBouton(this.j,this.aire2,Bouton.SAUVEGARDER,this,prop,actionUser));
		
		icon = new ImageIcon( new ImageIcon(lieuIcone + "icone_recommencer.png").getImage().getScaledInstance(frame.getSize().height/16, frame.getSize().height/16, Image.SCALE_DEFAULT)); 
		JButton boutonRecommencer = new JButton(icon);
		boutonRecommencer.setBackground(backgroundColor);
		boutonRecommencer.setPreferredSize(new Dimension(frame.getSize().height/16,frame.getSize().height/16));
		boutonRecommencer.setToolTipText("recommencer ");
		boutonRecommencer.addActionListener(new GestionBouton(this.j,this.aire2,Bouton.RECOMMENCER,this,prop,actionUser));

		JButton boutonRetour = new JButton(new ImageIcon(lieuIcone + "icone_retour.png"));
		boutonRetour.setBackground(buttonColor);
		boutonRetour.setPreferredSize(tailleBouton);
		JButton boutonPause = new JButton(new ImageIcon(lieuIcone + "icone_pause.png"));
		boutonPause.setBackground(buttonColor);
		boutonPause.setPreferredSize(tailleBouton);
		JButton boutonRetablir = new JButton(new ImageIcon(lieuIcone + "icone_retablir.png"));
		boutonRetablir.setBackground(buttonColor);
		boutonRetablir.setPreferredSize(tailleBouton);

		boutonRetour.setFocusable(false);
		boutonPause.setFocusable(false);
		boutonRetablir.setFocusable(false);

		boutonRetour.setToolTipText("annuler un coup ");
		//TODO modifier bubule
		boutonPause.setToolTipText("menu pause");
		boutonRetablir.setToolTipText("rétablir un coup");

		boutonRetour.addActionListener(new GestionBouton(this.j,this.aire2,Bouton.RETOUR,this,prop,actionUser));
		boutonPause.addActionListener(new GestionBouton(this.j,this.aire2,Bouton.PAUSE,this,prop,actionUser));
		boutonRetablir.addActionListener(new GestionBouton(this.j,this.aire2,Bouton.RETABLIR,this,prop,actionUser));
		
		
		JPanel containerBoutonHaut = new JPanel();
		containerBoutonHaut.setPreferredSize(new Dimension(frame.getSize().width/8*3 * 23/24, frame.getSize().height/12));
		JPanel partieHaute = new JPanel();
		JPanel container = new JPanel();
		JPanel containerEst = new JPanel();
		containerBoutonHaut.setLayout(new BoxLayout(containerBoutonHaut, BoxLayout.X_AXIS));

		containerBoutonHaut.add(boutonSauvegarder);
		containerBoutonHaut.add(boutonRecommencer);
		containerBoutonHaut.setBackground(backgroundColor);
		

		container.setFocusable(false);
		containerEst.setPreferredSize(new Dimension(frame.getSize().width/8*3, frame.getSize().height/3*2));
		container.setPreferredSize(new Dimension(frame.getSize().width/8*3, frame.getSize().height/3*2));
		partieHaute.setPreferredSize(new Dimension(frame.getSize().width/8*4 * 23/24, frame.getSize().height/12*3));
		partieHaute.setBackground(backgroundColor);
		partieHaute.add(containerBoutonHaut,BorderLayout.NORTH);
		partieHaute.add(leJoueur,BorderLayout.SOUTH);
		container.add(partieHaute,BorderLayout.NORTH);
		containerEst.add(container,BorderLayout.CENTER);

		container.setBackground(backgroundColor);
		containerEst.setBackground(backgroundColor);

		container = new JPanel();
		container.setBackground(backgroundColor);
		container.setPreferredSize(new Dimension(frame.getSize().width/8*3 * 23/24, frame.getSize().height/2));
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
		clavier = new EcouteurDeClavier(toucheAppuier,this,j,aire2,prop,actionUser);
		frame.addKeyListener(clavier);

        frame.requestFocus();
	}

	public void afficherMenuPause()
	{
		frame.removeKeyListener(clavier);
		frame.setEnabled(false);
		frame.setEnabled(true);
		chrono.stop();
		frame.getContentPane().removeAll();

		Bouton [] boutonName = {Bouton.RETOUR_JEU, Bouton.SAUVEGARDER, Bouton.RECOMMENCER, Bouton.OPTION, Bouton.RETOUR_MENU};
		JButton bouton;
		JPanel entreBouton;

		JPanel container = new JPanel();
		container.setBackground(backgroundColor);

		container.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		for (int i = 0; i < boutonName.length; i++)
		{
			gbc.gridy = i*2;
			bouton = new JButton (boutonName[i].toString());
			bouton.addActionListener(new GestionBouton(this.j,this.aire2,boutonName[i],this,prop,actionUser));
			bouton.setFont(police);
			bouton.setBackground(buttonColor);
			bouton.setForeground(frontButtomColor);
			if (boutonName[i] != Bouton.RETOUR_MENU)
			{
				bouton.setPreferredSize(tailleBouton);
			}
			container.add(bouton,gbc);
		}
		for (int i = 0; i < boutonName.length; i ++)
		{
			entreBouton=new JPanel();
			entreBouton.setPreferredSize(EcartType);
			entreBouton.setBackground(backgroundColor);

			gbc.gridy = i*2+1;
			container.add(entreBouton,gbc);
		}

		frame.add(container);

		clavier = new EcouteurDeClavier(toucheAppuier,this,j,aire2,prop,actionUser);
		frame.addKeyListener(clavier);
		frame.setVisible(true);
	}

	public void afficherAccueil ()
	{
		Bouton [] boutonName = {Bouton.NOUVELLE_PARTIE, Bouton.CHARGER, Bouton.OPTION, Bouton.QUITTER};
		frame.getContentPane().removeAll(); 

		JButton bouton;

		JPanel entreBouton = new JPanel();
		JPanel container = new JPanel();
		container.setBackground(backgroundColor);

		container.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();


		for (int i = 0; i < boutonName.length ; i++)
		{
			bouton = new JButton(boutonName[i].toString());
			bouton.addActionListener(new GestionBouton(this.j,this.aire2,boutonName[i],this,prop,actionUser));
			if (boutonName[i] != Bouton.QUITTER)
			{
				bouton.setPreferredSize(tailleBouton);
			}
			bouton.setFont(police);
			bouton.setBackground(buttonColor);
			bouton.setForeground(frontButtomColor);
			gbc.gridy = i * 2;
			container.add(bouton,gbc);
		}

		for (int i = 1; i <= 5; i += 2)
		{
			entreBouton=new JPanel();
			entreBouton.setPreferredSize(EcartType);
			entreBouton.setBackground(backgroundColor);

			gbc.gridy = i;
			container.add(entreBouton,gbc);
		}

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

		JPanel ecart = new JPanel();

		container.setBackground(backgroundColor);
		containerHaut.setBackground(backgroundColor);

		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel label = new JLabel ("Entrez le nom de la sauvegarde : ");
		label.setFont(new Font(null, Font.BOLD, frame.getSize().height/25));
		//label.setPreferredSize(new Dimension (popUp.getSize().width/3*2,popUp.getSize().height/2));
		containerHaut.add(label,gbc);

		ecart = new JPanel();
		ecart.setBackground(backgroundColor);
		ecart.setPreferredSize(new Dimension (popUp.getSize().width/3*2,popUp.getSize().height/50));
		gbc.gridx = 0;
		gbc.gridy = 1;
		containerHaut.add(ecart,gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;

		JTextArea textArea = new JTextArea(1,20);
		textArea.setFont(new Font(null, Font.BOLD, frame.getSize().height/25));
		textArea.setBackground(new Color(250,250,250));
		textArea.setBorder(BorderFactory.createLineBorder(Color.black));
		//textArea.setPreferredSize(new Dimension(popUp.getSize().width/3*2,popUp.getSize().height/5));
		containerHaut.add(textArea,gbc);

		gbc.gridx = 0;
		gbc.gridy = 0;
		
		JButton boutonValider = new JButton("Valider");
		boutonValider.setFont(police);
		boutonValider.setBackground(buttonColor);
		boutonValider.setForeground(frontButtomColor);
		containerBouton.add(boutonValider,gbc);

		
		gbc.gridx = 1;
		gbc.gridy = 0;
		ecart = new JPanel();
		ecart.setBackground(backgroundColor);
		ecart.setPreferredSize(new Dimension (popUp.getSize().width/5,popUp.getSize().height/6));
		containerBouton.add(ecart,gbc);
		containerBouton.setBackground(backgroundColor);

		gbc.gridx = 2;
		gbc.gridy = 0;
		JButton boutonAnnuler = new JButton("Annuler");
		boutonAnnuler.setFont(police);
		boutonAnnuler.setBackground(buttonColor);
		boutonAnnuler.setForeground(frontButtomColor);
		containerBouton.add(boutonAnnuler,gbc);

		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel messageErreur = new JLabel ("");
		messageErreur.setForeground(new Color(255,0,0));
		containerErreur.setBackground(backgroundColor);
		messageErreur.setBackground(backgroundColor);
		containerErreur.add(messageErreur,gbc);

		
		ecart = new JPanel();
		ecart.setBackground(backgroundColor);
		ecart.setPreferredSize(new Dimension (popUp.getSize().width/3*2,popUp.getSize().height/7));

		gbc.gridx = 0;
		gbc.gridy = 0;
		container.add(containerHaut,gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		container.add(ecart,gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		container.add(containerBouton,gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		container.add(containerErreur,gbc);

		popUp.add(container);

		boutonValider.addActionListener(new GestionBouton(this.j,Bouton.VALIDER_SAUVEGARDE,this,textArea, popUp, messageErreur,prop,actionUser));
		boutonAnnuler.addActionListener(new GestionBouton(this.j,Bouton.ANNULER_SAUVEGARDE,this,textArea, popUp, messageErreur,prop,actionUser));

	}

	public void afficherCharger ()
	{

		Font policeCharger = new Font("Comic Sans MS", Font.BOLD,  frame.getSize().height/25);
		Font policeSave = new Font(null, Font.BOLD,  frame.getSize().height/30);
		frame.getContentPane().removeAll();

		int width = frame.getSize().width;
		int height = frame.getSize().height;

		Save saves = new Save(this.j,prop,ia1,ia2);

		ArrayList <String> nomSave;
		nomSave = saves.lesSauvegardes();

		JPanel container = new JPanel();
		JPanel containerMain = new JPanel();
		JScrollPane scrPane = new JScrollPane(container);
		scrPane.getVerticalScrollBar().setUnitIncrement(16);

		container.setBackground(new Color(150,150,150));

		container.setLayout(new GridBagLayout());
		containerMain.setLayout(new GridBagLayout());
		scrPane.setBorder(BorderFactory.createLineBorder(Color.black));
		container.setBackground(backgroundColor);

		JPanel test = new JPanel ();
		test.setLayout(new GridBagLayout());
		test.setPreferredSize(new Dimension(width,height));

		test.setBackground(new Color(50,0,0));

		GridBagConstraints gbc = new GridBagConstraints();

		JPanel save;
		JPanel ecart ;
		for (int i = 0; i < nomSave.size(); i++)
		{
			Border lineborder = BorderFactory.createLineBorder(Color.black, 2);

			save = new JPanel();
			gbc.gridx = 0;
			gbc.gridy = i * 2;
			JLabel name = new JLabel(nomSave.get(i));
			name.setFont(policeSave);

			save.add(name,gbc);
			save.addMouseListener(new EcouteurDeSouris(nomSave.get(i), this.j, this, prop));
			save.setBorder(lineborder);
			save.setPreferredSize(new Dimension(width * 7/8, height/5-height/80));
			//save.setResizable(true);

			container.add(save,gbc);

			gbc.gridx = 0;
			gbc.gridy = i* 2 + 1 ;

			ecart = new JPanel();
			ecart.setPreferredSize(new Dimension(width * 7/8,height/80));
			ecart.setBackground(backgroundColor);
			container.add (ecart,gbc);	
		}

		containerMain.setBackground(backgroundColor);
		containerMain.setPreferredSize(new Dimension(width,height));
		container.setPreferredSize(new Dimension(width - width/10,height/5*nomSave.size()));
		scrPane.setPreferredSize(new Dimension(width-width/20, 3*height/4));
		gbc.gridx = 0;
		gbc.gridy = 0;
		containerMain.add(scrPane,gbc);
		
		ecart = new JPanel();
		ecart.setPreferredSize(new Dimension(width/3*2,height/80));
		ecart.setBackground(backgroundColor);
		gbc.gridx = 0;
		gbc.gridy = 1;
		containerMain.add (ecart,gbc);
		
		JButton bouton = new JButton ("Retour Menu");
		bouton.addActionListener(new GestionBouton(Bouton.RETOUR_MENU,this,prop));
		bouton.setFont(policeCharger);
		bouton.setBackground(buttonColor);
		bouton.setForeground(frontButtomColor);
		
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		containerMain.add (bouton,gbc);

		//test.add(containerMain);
		frame.add(containerMain);

		frame.setVisible(true);

	}

	public void afficherNouvellePartie()
	{
		Dimension tailleComboBox = new Dimension (frame.getSize().width/3,tailleBouton.getSize().height);
		frame.getContentPane().removeAll();
		
		GridBagConstraints gbc = new GridBagConstraints();
		JPanel container = new JPanel ();
		container.setBackground(backgroundColor);
		JPanel containerMain = new JPanel ();
		JPanel couleur = new JPanel ();

		couleur.setLayout(new GridBagLayout());
		containerMain.setLayout(new GridBagLayout());
		String [] element = new String[] {"Joueur", "IA Facile", "IA Normal", "IA Difficile"};

		JComboBox<String> menuBar1 = new JComboBox<String>(element);
		JComboBox<String> menuBar2 = new JComboBox<String>(element);

		containerMain.setBackground(backgroundColor);

		menuBar1.setPreferredSize(tailleComboBox);

		menuBar1.setFont(police);
		menuBar1.setBackground(buttonColor);
		menuBar1.setForeground(frontButtomColor);

		menuBar2.setPreferredSize(tailleComboBox);

		menuBar2.setFont(police);
		menuBar2.setBackground(buttonColor);
		menuBar2.setForeground(frontButtomColor);
		/*menuBar.add(new JLabel("Element 1"));
		menuBar.add(new JLabel("Element 2"));*/

		JPanel boite = new JPanel();
		boite.setBackground(new Color(0,110,255));
		boite.setPreferredSize(tailleComboBox);

		gbc.gridx = 0;
		gbc.gridy = 0;

		couleur.add(boite,gbc);

		boite = new JPanel ();
		boite.setBackground(backgroundColor);
		boite.setPreferredSize(new Dimension(25, 25));

		gbc.gridx = 1;
		gbc.gridy = 0;

		couleur.add(boite,gbc);

		boite = new JPanel ();
		boite.setBackground(new Color(255,0,0));
		boite.setPreferredSize(tailleComboBox);

		gbc.gridx = 2;
		gbc.gridy = 0;

		couleur.add(boite,gbc);

		gbc.gridx = 0;
		gbc.gridy = 0;

		couleur.setBackground(backgroundColor);
		containerMain.add(couleur,gbc);

		container.add(menuBar1);
		container.add(new JLabel("VS"));
		container.add(menuBar2);

		gbc.gridy = 1;
		containerMain.add(container,gbc);


		container = new JPanel();
		container.setBackground(backgroundColor);
		container.setPreferredSize(new Dimension(100, 100));

		gbc.gridy = 2;
		containerMain.add(container,gbc);

		JButton boutonCestParti = new JButton ("C'est parti !"); 
		JButton boutonRetourMenu = new JButton ("Retour Menu");

		boutonCestParti.setPreferredSize(tailleBouton);

		boutonCestParti.setFont(police);
		boutonCestParti.setBackground(buttonColor);
		boutonCestParti.setForeground(frontButtomColor);

		boutonRetourMenu.setFont(police);
		boutonRetourMenu.setBackground(buttonColor);
		boutonRetourMenu.setForeground(frontButtomColor);



		gbc.gridy = 3;
		containerMain.add(boutonCestParti,gbc);

		JPanel interBouton = new JPanel();
		interBouton.setPreferredSize(EcartType);
		interBouton.setBackground(backgroundColor);

		gbc.gridy = 4;
		containerMain.add(interBouton,gbc);

		gbc.gridy = 5;
		containerMain.add(boutonRetourMenu,gbc);
		boutonRetourMenu.addActionListener(new GestionBouton (j,aire2,Bouton.RETOUR_MENU,this,prop,actionUser));
		boutonCestParti.addActionListener(new GestionBouton (j,Bouton.LANCER_PARTIE,this,menuBar1,menuBar2,prop,actionUser));


		


		frame.add(containerMain);

		frame.setVisible(true);
	}

	public void afficherOption ()
	{
		Font textOption = new Font(null, Font.BOLD,frame.getSize().height/25);
		Font textOptionButton = new Font("Comic Sans MS", Font.BOLD,  frame.getSize().height/25);
		Dimension dimensionbloc = new Dimension(frame.getSize().width/3*2,frame.getSize().height/10);
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
		container.setBackground(backgroundColor);
		containerMain.setBackground(backgroundColor);
		JScrollPane scrPane = new JScrollPane(container);
		scrPane.getVerticalScrollBar().setUnitIncrement(16);
		scrPane.setPreferredSize(new Dimension(dimensionbloc.getSize().width+dimensionbloc.getSize().width/10, dimensionbloc.getSize().height*4));
		scrPane.setBackground(backgroundColor);
		scrPane.setBorder(BorderFactory.createLineBorder(Color.black));

		clefs = prop.recupClesModifiable ();
		for (int i = 0; i < clefs.size(); i++)
		{
			bloc = new JPanel ();
			JLabel label = new JLabel (clefs.get(i) + ":");
			bloc.setBorder(lineborder);
			container.setAlignmentX(Component.LEFT_ALIGNMENT);
			bloc.setLayout(new FlowLayout(FlowLayout.LEFT));
			label.setPreferredSize(new Dimension(dimensionbloc.getSize().width/8*5,dimensionbloc.getSize().height/8*6));
			label.setFont(textOption);
			bloc.setPreferredSize(dimensionbloc);
			bloc.setBackground(backgroundColor);
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
				label.setFont(textOption);

				bloc.add (label,gbc);
				gbc.gridx = 0;
				gbc.gridy = i;
				container.add(bloc,gbc);
			}
			else if (valDecoup.length < 2 && (prop.recupValeur(clefs.get(i)).equals("true") || prop.recupValeur(clefs.get(i)).equals("false")))
			{
				//Dimension tailleCheckBox = new Dimension(dimensionbloc.getSize().height/10*5,dimensionbloc.getSize().height/10*5);	
				checkBox = new JCheckBox();
				//checkBox.setPreferredSize(new Dimension(10,10));
				checkBox.setBackground(backgroundColor);
				checkBox.setFont(textOption);
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
				label.setFont(textOption);

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
				label.setFont(textOption);

				bloc.add (label,gbc);

				gbc.gridx = 0;
				gbc.gridy = i;
				
				container.add(bloc,gbc);

			}

			
		}

		JPanel ecart = new JPanel();
		ecart.setBackground(backgroundColor);
		ecart.setPreferredSize(new Dimension(frame.getSize().width/3*2 , frame.getSize().height/20));
		gbc.gridx = 0;
		gbc.gridy = 1;
		containerMain.add(ecart,gbc);

		ecart = new JPanel();
		ecart.setBackground(backgroundColor);
		ecart.setPreferredSize(new Dimension(frame.getSize().width/3*2 , frame.getSize().height/20));
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		containerMain.add(ecart,gbc);


		gbc.gridx = 0;
		gbc.gridy = 0;
		containerMain.add(scrPane,gbc);

		container = new JPanel ();
		container.setBackground(backgroundColor);
		JButton boutonRetour = new JButton ("Retour");
		boutonRetour.addActionListener(new GestionBouton(Bouton.RETOUR_OPTION,this,prop));
		boutonRetour.setFont(textOptionButton);
		boutonRetour.setBackground(buttonColor);
		boutonRetour.setForeground(frontButtomColor);
		container.add(boutonRetour);
		boutonRetour.setFocusable(false);



		gbc.gridx = 0;
		gbc.gridy = 4;
		containerMain.add(container,gbc);

		container = new JPanel ();
		container.setBackground(backgroundColor);
		JButton boutonDefaut = new JButton("Rétablir par defaut");
		boutonDefaut.addActionListener(new GestionBouton(Bouton.RETABLIR_DEFAUT,this,prop));
		boutonDefaut.setBackground(buttonColor);
		boutonDefaut.setFont(textOptionButton);
		boutonDefaut.setForeground(frontButtomColor);
		container.add(boutonDefaut);
		
		

		gbc.gridx = 0;
		gbc.gridy = 2;
		containerMain.add(container,gbc);

		
		frame.add(containerMain);

		frame.setVisible(true);
		frame.requestFocus();


	}


} 