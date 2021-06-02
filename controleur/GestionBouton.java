package controleur;

import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.*;

import modele.*;
import interfaceUser.*;
import controleurIA.*;
import global.*;
import save.*;

public class GestionBouton extends JButton implements ActionListener
{
	Bouton type;
	Jeu j;
	PlateauInterface_2 aire2;
	ActionUser action;
	Fenetres f;

	JTextArea texte;

	JFrame popUp;
	JLabel messageErreur;

	JComboBox<String> j1;
	JComboBox<String> j2;

	Configuration prop;

	public GestionBouton (Jeu j, Bouton type, Fenetres f,JComboBox<String> j1, JComboBox<String> j2,Configuration prop)
	{
		this.j1 = j1;
		this.j2 = j2;

		this.j = j;
		this.f = f;

		this.type = type;
		this.prop = prop;
	}

	public GestionBouton (Bouton type, Fenetres f,Configuration prop)
	{
		this.type = type;
		this.f = f;
		this.prop = prop;
	}

	public GestionBouton (Jeu j, PlateauInterface_2 aire2, Bouton type, Fenetres f,Configuration prop)
	{this.type = type;
		this.action = new ActionUser(j);
		this.aire2 = aire2;

		this.f = f;
		this.prop = prop;
	}

	public GestionBouton (Jeu j, Bouton type, Fenetres f, JTextArea texte, JFrame popUp, JLabel messageErreur)
	{
		this.type = type;
		this.action = new ActionUser(j);

		this.f = f;
		this.texte = texte;

		this.popUp = popUp;
		this.messageErreur = messageErreur;

		this.j = j;
	}

	public void actionPerformed(ActionEvent e) 
	{
		System.out.println("Le bouton : " + type + " a ete presser");
		switch (this.type)
		{
			case RETOUR :
				try
				{
					if (f.ia1 != null && f.ia2 != null && (f.ia1.estActive() || f.ia2.estActive()) )
					{
						System.out.println("je passe ici");
                        f.g.iaJoue = true;
                        action.annulerCoup();
                        f.ia1.desactiverIA();
                        f.ia2.desactiverIA();
                        System.out.println(f.g.iaJoue);
					}
                    else if (f.ia1 != null && f.ia1.estActive() && f.ia2 == null)
                    {
                        System.out.println("je passe ici");
                        f.g.iaJoue = true;
                        action.annulerCoup();
                        f.ia1.desactiverIA();
                        System.out.println(f.g.iaJoue);
                    }
                     else if (f.ia2 != null && f.ia2.estActive() && f.ia1 == null)
                    {
                        System.out.println("je passe ici");
                        f.g.iaJoue = true;
                        action.annulerCoup();
                        f.ia2.desactiverIA();
                        System.out.println(f.g.iaJoue);
                    }
                    else
                    {
                        action.annulerCoup();
                    }
					
				}
				catch (IndexOutOfBoundsException except)
				{
					System.err.println("Impossible d'annuler");
				}
				aire2.repaint();
			break;
			case RETABLIR :
				try
				{
					action.retablirCoup();
				}
				catch (IndexOutOfBoundsException except)
				{
					System.err.println("Impossible de rétablir");
				}
				aire2.repaint();
			break;
			case PAUSE :
				f.ChangerFenetres (NomFenetres.MENU_PAUSE);
				f.gestionFenetre ();
				f.frame.repaint();
			break;
			case RETOUR_JEU :
				f.ChangerFenetres (NomFenetres.JEU);
				f.gestionFenetre ();
				f.frame.repaint();
			break;
			case RECOMMENCER :
				f.j = new Jeu(prop);


				if (f.ia1 != null)
				{
					System.out.println("type ia : " + f.ia1.type());
					if (f.ia1.type().equals("IA Facile"))
					{
						System.out.println("ia1 : Facile");
						f.ia1 = IA.nouvelle(f.j,prop.recupValeur("IAFacile"),f.ia1.type());
					}
					else if (f.ia1.type().equals("IA Normal"))
					{
						System.out.println("ia1 : Normal");
						f.ia1 = IA.nouvelle(f.j,prop.recupValeur("IANormal"),f.ia1.type());
					}
					else if (f.ia1.type().equals("IA Difficile"))
					{
						System.out.println("ia1 : Difficile	");
						f.ia1 = IA.nouvelle(f.j,prop.recupValeur("IADifficile"),f.ia1.type());
					}
					f.ia1.activeIA();
				}

				if (f.ia2 != null)
				{
					System.out.println("type ia : " + f.ia2.type());
					if (f.ia2.type().equals("IA Facile"))
					{
						System.out.println("ia2 : Facile");
						f.ia2 = IA.nouvelle(f.j,prop.recupValeur("IAFacile"),f.ia2.type());
					}
					else if (f.ia2.type().equals("IA Normal"))
					{
						System.out.println("ia2 : Normal");
						f.ia2 = IA.nouvelle(f.j,prop.recupValeur("IANormal"),f.ia2.type());
					}
					else if (f.ia2.type().equals("IA Difficile"))
					{
						System.out.println("ia2 : Difficile");
						f.ia2 = IA.nouvelle(f.j,prop.recupValeur("IADifficile"),f.ia2.type());
					}
					f.ia2.activeIA();
				}
				
				f.ChangerFenetres (NomFenetres.JEU);
				f.gestionFenetre ();
				f.frame.repaint();
			break;
			case RETOUR_MENU:
				f.ia1 = null;
				f.ChangerFenetres(NomFenetres.PAGE_ACCUEIL);
				f.gestionFenetre();
				f.frame.repaint();
			break;
			case SAUVEGARDER:
				f.ChangerFenetres(NomFenetres.POPUP_SAUVEGARDE);
				f.gestionFenetre ();
				f.frame.repaint();
			break;
			case VALIDER_SAUVEGARDE :

				if (texte.getText().length() <= 0)
				{
					this.messageErreur.setText("Erreur fichier deja existant ou nom inccorect");
				}
				else
				{
					Save s = new Save(j);
					s.sauver(texte.getText());

					System.out.println(texte.getText());
					this.messageErreur.setText("");
					popUp.setVisible(false);
				}

			break;
			case ANNULER_SAUVEGARDE :
				popUp.setVisible(false); break;
			case CHARGER :
				f.ChangerFenetres(NomFenetres.CHARGER);
				f.gestionFenetre();
				f.frame.repaint();
			break;
			case NOUVELLE_PARTIE :
				f.ChangerFenetres(NomFenetres.NOUVELLE_PARTIE);
				f.gestionFenetre();
				f.frame.repaint();
			break;
			case LANCER_PARTIE :
				System.out.println("j1 : " + j1.getSelectedItem());
				System.out.println("j2 : " + j2.getSelectedItem());
				
				if (gestionComboBox())
				{
					
					f.ChangerFenetres(NomFenetres.JEU);
					f.gestionFenetre();
					f.frame.repaint();
				}
				/*f.ChangerFenetres(NomFenetres.JEU);
				f.gestionFenetre();
				f.frame.repaint();*/
			break;
			case QUITTER :
				f.frame.dispose();
			break;
			case OPTION :
				f.ChangerFenetres(NomFenetres.OPTION,f.f);
				f.gestionFenetre ();
				f.frame.repaint();
			break;
			case RETOUR_OPTION:
				f.ChangerFenetres(f.f_avant);
				f.gestionFenetre();
				f.frame.repaint();

		}
	}

	public boolean gestionComboBox()
	{
		String j1ToString = j1.getSelectedItem().toString();
		String j2ToString = j2.getSelectedItem().toString();

		boolean retour = false;
		f.j = new Jeu(prop);

		f.ia1 = null;
		f.ia2 = null;

		if (j1ToString == "Joueur" && j2ToString == "Joueur")
		{
			System.out.println("joueur vs joueur");
			retour = true;
		}
		else if (j2ToString =="IA Facile")
		{
			f.ia1 = IA.nouvelle(f.j,prop.recupValeur("IAFacile"),"IA Facile");
			f.ia1.activeIA();
			System.out.println("joueur vs ia facile");
			retour = true;
		}
		else if (j2ToString =="IA Normal")
		{
			f.ia1 = IA.nouvelle(f.j,prop.recupValeur("IANormal"),"IA Normal");
			f.ia1.activeIA();
			System.out.println("joueur vs ia normal");
			retour = true;
		}
		else if (j2ToString =="IA Difficile")
		{
			f.ia1 = IA.nouvelle(f.j,prop.recupValeur("IADifficile"),"IA Difficile");
			f.ia1.activeIA();
			System.out.println("joueur vs ia difficile");
			retour = true;
		}


		if (j1ToString =="IA Facile")
		{
			f.ia2 = IA.nouvelle(f.j,prop.recupValeur("IAFacile"),"IA Facile");
			f.ia2.activeIA();
			System.out.println("joueur vs ia facile");
			retour = true;
		}
		else if (j1ToString =="IA Normal")
		{
			f.ia2 = IA.nouvelle(f.j,prop.recupValeur("IANormal"),"IA Normal");
			f.ia2.activeIA();
			System.out.println("joueur vs ia facile");
			retour = true;
		}
		else if (j1ToString =="IA Difficile")
		{
			f.ia2 = IA.nouvelle(f.j,prop.recupValeur("IADifficile"),"IA Difficile");
			f.ia2.activeIA();
			System.out.println("joueur vs ia facile");
			retour = true;
		}
		System.out.println(j1ToString + " vs " + j2ToString);

		return retour;
	} 
}