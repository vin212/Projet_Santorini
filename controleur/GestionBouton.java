package controleur;

import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

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

	JComboBox j1;
	JComboBox j2;

	Configuration prop;

	public GestionBouton (Jeu j, Bouton type, Fenetres f,JComboBox j1, JComboBox j2,Configuration prop)
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
		this.action = new ActionUser(j,prop);
		this.aire2 = aire2;

		this.f = f;
		this.prop = prop;
	}

	public GestionBouton (Jeu j, Bouton type, Fenetres f, JTextArea texte, JFrame popUp, JLabel messageErreur)
	{
		this.type = type;
		this.action = new ActionUser(j,prop);

		this.f = f;
		this.texte = texte;

		this.popUp = popUp;
		this.messageErreur = messageErreur;

		this.j = j;
	}

	public void actionPerformed(ActionEvent e) 
	{
		prop.envoyerLogger("Le bouton : " + type + " a ete presser",TypeLogger.INFO);
		switch (this.type)
		{
			case RETOUR :
				try
				{
					if (f.ia1 != null && f.ia2 != null && (f.ia1.estActive() || f.ia2.estActive()) )
					{
                        f.g.iaJoue = true;
                        action.annulerCoup();
                        f.ia1.desactiverIA();
                        f.ia2.desactiverIA();
                        System.out.println(f.g.iaJoue);
					}
                    else if (f.ia1 != null && f.ia1.estActive() && f.ia2 == null)
                    {
                        f.g.iaJoue = true;
                        action.annulerCoup();
                        f.ia1.desactiverIA();
                        System.out.println(f.g.iaJoue);
                    }
                     else if (f.ia2 != null && f.ia2.estActive() && f.ia1 == null)
                    {
                        f.g.iaJoue = true;
                        action.annulerCoup();
                        f.ia2.desactiverIA();
                        System.out.println(f.g.iaJoue);
                    }
                    else
                    {
                        action.annulerCoup();
                    }
                    prop.envoyerLogger("coup annuler",TypeLogger.INFO);
					
				}
				catch (IndexOutOfBoundsException except)
				{
					prop.envoyerLogger("Impossible d'annuler",TypeLogger.WARNING);
				}
				aire2.repaint();
			break;
			case RETABLIR :
				try
				{
					action.retablirCoup();
					prop.envoyerLogger("coup reatbli",TypeLogger.INFO);
				}
				catch (IndexOutOfBoundsException except)
				{
					prop.envoyerLogger("Impossible de reatblir",TypeLogger.WARNING);
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
					if (f.ia1.type().equals("IA Facile"))
					{
						f.ia1 = IA.nouvelle(f.j,prop.recupValeur("IAFacile"),f.ia1.type());
					}
					else if (f.ia1.type().equals("IA Normal"))
					{
						f.ia1 = IA.nouvelle(f.j,prop.recupValeur("IANormal"),f.ia1.type());
					}
					else if (f.ia1.type().equals("IA Difficile"))
					{
						f.ia1 = IA.nouvelle(f.j,prop.recupValeur("IADifficile"),f.ia1.type());
					}
					f.ia1.activeIA();
				}

				if (f.ia2 != null)
				{
					if (f.ia2.type().equals("IA Facile"))
					{
						f.ia2 = IA.nouvelle(f.j,prop.recupValeur("IAFacile"),f.ia2.type());
					}
					else if (f.ia2.type().equals("IA Normal"))
					{
						f.ia2 = IA.nouvelle(f.j,prop.recupValeur("IANormal"),f.ia2.type());
					}
					else if (f.ia2.type().equals("IA Difficile"))
					{
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
				prop.envoyerLogger(j1.getSelectedItem() + " VS " + j2.getSelectedItem(),TypeLogger.INFO);
				
				if (gestionComboBox())
				{
					f.ChangerFenetres(NomFenetres.JEU);
					f.gestionFenetre();
					f.frame.repaint();
				}

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
			break;
			case RETABLIR_DEFAUT :
				prop.retablirDefaut();
				f.gestionFenetre();
				f.frame.repaint();
			break;

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
			retour = true;
		}
		else if (j2ToString =="IA Facile")
		{
			f.ia1 = IA.nouvelle(f.j,prop.recupValeur("IAFacile"),"IA Facile");
			f.ia1.activeIA();
			retour = true;
		}
		else if (j2ToString =="IA Normal")
		{
			f.ia1 = IA.nouvelle(f.j,prop.recupValeur("IANormal"),"IA Normal");
			f.ia1.activeIA();
			retour = true;
		}
		else if (j2ToString =="IA Difficile")
		{
			f.ia1 = IA.nouvelle(f.j,prop.recupValeur("IADifficile"),"IA Difficile");
			f.ia1.activeIA();
			retour = true;
		}


		if (j1ToString =="IA Facile")
		{
			f.ia2 = IA.nouvelle(f.j,prop.recupValeur("IAFacile"),"IA Facile");
			f.ia2.activeIA();
			retour = true;
		}
		else if (j1ToString =="IA Normal")
		{
			f.ia2 = IA.nouvelle(f.j,prop.recupValeur("IANormal"),"IA Normal");
			f.ia2.activeIA();
			retour = true;
		}
		else if (j1ToString =="IA Difficile")
		{
			f.ia2 = IA.nouvelle(f.j,prop.recupValeur("IADifficile"),"IA Difficile");
			f.ia2.activeIA();
			retour = true;
		}

		return retour;
	} 
}