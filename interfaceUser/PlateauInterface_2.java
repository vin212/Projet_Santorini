package interfaceUser;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.*;
import java.util.ArrayList;


import modele.*;
import structure.*;
import controleur.*;
import animation.*;
import global.*;

public class PlateauInterface_2 extends JComponent {

	Jeu j;
	
	Image plateau;
	Image rdc;
	Image etage1;
	Image etage2;
	Image coupole;
	Image rdcBord;
	Image etage1Bord;
	Image etage2Bord;
	Image coupoleBord;
	Image J2;
	Image J1;
	Image J1Transparent;
	Image J2Transparent;
	Image joueurBord;
	Image griserDeplacement;
	Image griserConstructible;
	Image animation;

	public AnimationListener anim;
	AnimationListener animSelect;

	ActionUser actionUser;
	int posiHisto;

	public boolean animFaite;

	public PlateauInterface_2(Jeu j,ActionUser actionUser, Configuration prop) {
		this.j = j;
		actionUser.initActionUser(j,prop);
		this.actionUser = actionUser;

		String dossierTexture = "ressource/texture/";
		String dossierBatiment = dossierTexture + "Batiment/";
		String dossierCase = dossierTexture + "Case/";
		String dossierPersonnage = dossierTexture + "Joueur/";
		posiHisto = j.histoPosition();

		animFaite = false;

		try {
			
			InputStream  in = new FileInputStream("ressource/texture/Plateau/Plateau_2D.jpg");
		 	plateau = ImageIO.read(in);

		 	in = new FileInputStream(dossierBatiment +"rdc_2D.png");
			rdc = ImageIO.read(in);

		 	in = new FileInputStream(dossierBatiment +"Etage_1_2D.png");
		 	etage1 = ImageIO.read(in);

		 	in = new FileInputStream(dossierBatiment +"Etage_2_2D.png");
		 	etage2 = ImageIO.read(in);

		 	in = new FileInputStream(dossierBatiment +"couple_2D.png");
		 	coupole = ImageIO.read(in);

		 	in = new FileInputStream(dossierCase +"rdc_2D_bord.png");
			rdcBord = ImageIO.read(in);

		 	in = new FileInputStream(dossierCase +"Etage_1_2D_bord.png");
		 	etage1Bord = ImageIO.read(in);

		 	in = new FileInputStream(dossierCase +"Etage_2_2D_bord.png");
		 	etage2Bord = ImageIO.read(in);

		 	in = new FileInputStream(dossierCase +"couple_2D_bord.png");
		 	coupoleBord = ImageIO.read(in);

		 	in = new FileInputStream(dossierPersonnage + "J1_2D.png");
		 	J1 = ImageIO.read(in);

		 	in = new FileInputStream(dossierPersonnage + "J2_2D.png");
		 	J2 = ImageIO.read(in);

		 	in = new FileInputStream(dossierPersonnage + "animation.png");
		 	animation = ImageIO.read(in);

		 	in = new FileInputStream(dossierPersonnage + "J2_2D_transparent.png");
		 	J2Transparent = ImageIO.read(in);

		 	in = new FileInputStream(dossierPersonnage + "J1_2D_transparent.png");
		 	J1Transparent = ImageIO.read(in);

		 	in = new FileInputStream(dossierCase + "Joueur_bord.png");
		 	joueurBord = ImageIO.read(in);

		 	in = new FileInputStream(dossierCase + "griserDeplacement.png");
		 	griserDeplacement = ImageIO.read(in);

		 	in = new FileInputStream(dossierCase + "griserConstructible.png");
		 	griserConstructible = ImageIO.read(in);

		} catch (Exception e) {
			System.out.print("erreur 2D \n");
			System.exit(1);
		}
	}

	public void FaireActionUser (int x, int y)
	{
		int width = getSize().width;
		int height = getSize().height;


		int inter_batiment_largeur = width/50;
		int inter_batiment_hauteur = height/50;

		int taille_hauteur = height/6 - height/65 + 1;
		int taille_largeur = width/6 - width/65 + 1;

		Point positionPremierBatiment = new Point(width/12,height/12);

		int x_calcul = ((x-positionPremierBatiment.getx()) / (inter_batiment_largeur + taille_largeur));
		int y_calcul =((y-positionPremierBatiment.gety())/ (inter_batiment_hauteur + taille_hauteur));

		if (x >= positionPremierBatiment.getx() && y >= positionPremierBatiment.gety() && x_calcul >= 0 && y_calcul >= 0 && x_calcul < j.getLargeurPlateau() && y_calcul < j.getHauteurPlateau())
		{
			actionUser.jouerAction(new Point(x_calcul,y_calcul), false);
			this.repaint();
		}

		
		activerAnimation();
		activerAnimationClignoter();
	}

	void activerAnimationClignoter()
	{
		int width = getSize().width;
		int height = getSize().height;


		int inter_batiment_largeur = width/50;
		int inter_batiment_hauteur = height/50;

		int taille_hauteur = height/6 - height/65 + 1;
		int taille_largeur = width/6 - width/65 + 1;

		Point positionPremierBatiment = new Point(width/12,height/12);

		if (actionUser != null && actionUser.recupPosiPerso () != null)
		{
		int x_calcul = (inter_batiment_largeur + taille_largeur) * actionUser.recupPosiPerso ().getx() + positionPremierBatiment.getx();
		int y_calcul = (inter_batiment_hauteur + taille_hauteur) *  actionUser.recupPosiPerso ().gety() + positionPremierBatiment.gety(); 
			
		Point calcul = new Point (x_calcul,y_calcul);
		if (j.getAction(j.getJoueurEnJeu()) == modele.Action.EN_COURS_DE_DEPLACEMENT)
		{
			animSelect = new AnimationListener(this,j.prop,calcul);
		}
		}
	}

	void activerAnimation()
	{
		int width = getSize().width;
		int height = getSize().height;


		int inter_batiment_largeur = width/50;
		int inter_batiment_hauteur = height/50;

		int taille_hauteur = height/6 - height/65 + 1;
		int taille_largeur = width/6 - width/65 + 1;

		Point positionPremierBatiment = new Point(width/12,height/12);

		if (anim != null && anim.estFini() == true && j.getAction (j.getJoueurEnJeu()) == modele.Action.A_CONSTRUIRE)
		{
			animFaite = true;
		}

		if (j.getAction (j.getJoueurEnJeu()) == modele.Action.A_CONSTRUIRE && animFaite == false && Boolean.parseBoolean(j.prop.recupValeur("animation_active")) && actionUser != null && actionUser.coupJouer.getDepart() != null)
		{
			int x_calcul_depart = (inter_batiment_largeur + taille_largeur) * actionUser.coupJouer.getDepart().getx() + positionPremierBatiment.getx();
			int y_calcul_depart = (inter_batiment_hauteur + taille_hauteur) *  actionUser.coupJouer.getDepart().gety() + positionPremierBatiment.gety(); 
			
			Point calcul_depart = new Point (x_calcul_depart,y_calcul_depart);

			int x_calcul_Arrive = (inter_batiment_largeur + taille_largeur) * actionUser.coupJouer.getArrive().getx() + positionPremierBatiment.getx();
			int y_calcul_Arrive = (inter_batiment_hauteur + taille_hauteur) *  actionUser.coupJouer.getArrive().gety() + positionPremierBatiment.gety(); 
			
			Point calcul_Arrive = new Point (x_calcul_Arrive,y_calcul_Arrive);

			this.anim = new AnimationListener(this,j.prop ,calcul_depart,calcul_Arrive);
		}
		else if (j.getAction (j.getJoueurEnJeu()) == modele.Action.A_CONSTRUIRE && animFaite == true)
		{
			animFaite = true;
		}
		else
		{
			animFaite = false;
			anim = null;
		}
	}
	
	public void animationIA ()
	{
		animFaite = false;
		activerAnimation();
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D drawable = (Graphics2D) g;

		int width = getSize().width;
		int height = getSize().height;


		int inter_batiment_largeur = width/50;
		int inter_batiment_hauteur = height/50;

		int taille_hauteur = height/6 - height/65 + 1;
		int taille_largeur = width/6 - width/65 + 1;


		Point positionPremierBatiment = new Point(width/12,height/12);

		drawable.clearRect(0, 0, width, height);
		drawable.drawImage(plateau,0,0,width,height,null);
		for (int i = 0; i < j.getLargeurPlateau(); i++)
		{
			for (int k = 0; k < j.getHauteurPlateau (); k++)
			{
				if (j.getNbEtage(new Point(i,k)) >= 1)
				{
					drawable.drawImage(rdc, positionPremierBatiment.getx() + taille_largeur*i +inter_batiment_largeur*i, positionPremierBatiment.gety() + taille_hauteur*k + inter_batiment_hauteur * k, taille_largeur,taille_hauteur,null);
				}
				if (j.getNbEtage(new Point(i,k)) >= 2)
				{
					drawable.drawImage(etage1, positionPremierBatiment.getx() + taille_largeur*i+inter_batiment_largeur*i, positionPremierBatiment.gety() + taille_hauteur*k + inter_batiment_hauteur * k, taille_largeur,taille_hauteur,null);
				}
				if (j.getNbEtage(new Point(i,k)) >= 3)
				{
					drawable.drawImage(etage2, positionPremierBatiment.getx() + taille_largeur*i+inter_batiment_largeur*i, positionPremierBatiment.gety() + taille_hauteur*k + inter_batiment_hauteur * k, taille_largeur,taille_hauteur,null);
				}
				if (j.getNbEtage(new Point(i,k)) >= 4)
				{
					drawable.drawImage(coupole, positionPremierBatiment.getx() + taille_largeur*i+inter_batiment_largeur*i, positionPremierBatiment.gety() + taille_hauteur*k + inter_batiment_hauteur * k, taille_largeur,taille_hauteur,null);
				}
				if ((j.aPersonnage (new Point(i,k))) && (anim == null || anim.estFini() || actionUser.coupJouer.getArrive() == null || (anim != null && actionUser.coupJouer.getArrive() != null &&actionUser.coupJouer.getArrive().compareTo(new Point(i,k)) != 0)))
				{
					int perso = j.quiEstIci (new Point(i,k));
					if (perso == 1)
					{	
						drawable.drawImage(J1, positionPremierBatiment.getx() + taille_largeur*i+inter_batiment_largeur*i, positionPremierBatiment.gety() + taille_hauteur*k + inter_batiment_hauteur * k, taille_largeur,taille_hauteur,null);
				
					}
					else if (perso == 2)
					{
						drawable.drawImage(J2,positionPremierBatiment.getx() + taille_largeur*i+inter_batiment_largeur*i, positionPremierBatiment.gety() + taille_hauteur*k + inter_batiment_hauteur * k, taille_largeur,taille_hauteur,null);
				
					}
				}
			}
		}

		modele.Action a = j.getAction (j.getJoueurEnJeu());
		if (j.aideEstActiver() && a == modele.Action.EN_COURS_DE_DEPLACEMENT && !j.estGagnant())
		{
			Point posi = actionUser.recupPosiPerso ();
			ArrayList<Point> voisins = j.getVoisin(posi, new VerificateurPion(j));
			for (int h = 0; h < voisins.size(); h++)
			{
				int i = voisins.get(h).getx();
				int k = voisins.get(h).gety();
				drawable.drawImage(griserDeplacement, positionPremierBatiment.getx() + taille_largeur*i+inter_batiment_largeur*i, positionPremierBatiment.gety() + taille_hauteur*k + inter_batiment_hauteur * k, taille_largeur,taille_hauteur,null);
			}
		}
		else if (j.aideEstActiver() && a == modele.Action.A_CONSTRUIRE && !j.estGagnant())
		
		{
			Point posi = actionUser.recupPosiPerso ();
			ArrayList<Point> voisins = j.getVoisin(posi, new VerificateurEtage(j));
			for (int h = 0; h < voisins.size(); h++)
			{
				int i = voisins.get(h).getx();
				int k = voisins.get(h).gety();
				drawable.drawImage(griserConstructible, positionPremierBatiment.getx() + taille_largeur*i+inter_batiment_largeur*i, positionPremierBatiment.gety() + taille_hauteur*k + inter_batiment_hauteur * k, taille_largeur,taille_hauteur,null);
			}
		}

		Coup c;
		Point debut = null;
		Point arriver = null;
		Point construction = null;
		try
		{
			c = j.histoDernierCoup();
		}
		catch (IndexOutOfBoundsException Except)
		{
			c = null;
		}

		
			int i = -1;
			int k = -1;
			int joueur = -1;
		
			if (c != null && c.getDepart() != null && c.getArrive() != null && c.getConstruction() != null)
			{
				debut = c.getDepart();
				arriver = c.getArrive();
				construction = c.getConstruction();
				joueur = c.getJoueur();
			}

			
			if(actionUser != null && actionUser.coupJouer.getDepart()  != null)
			{
				
				i =  actionUser.coupJouer.getDepart().getx();
				k =  actionUser.coupJouer.getDepart().gety();
				joueur = j.getJoueurEnJeu();
				//System.out.println("i : " + i + "k : " + k);
			}
			else if (debut != null)
			{
				//System.out.println("pas tout a fait");
				i = debut.getx();
				k = debut.gety();
				
			}
			else
			{
				//System.out.println("problème");
			}

			if (joueur == 1 && i != -1 && k != -1)
			{
				drawable.drawImage(J1Transparent, positionPremierBatiment.getx() + taille_largeur*i+inter_batiment_largeur*i, positionPremierBatiment.gety() + taille_hauteur*k + inter_batiment_hauteur * k, taille_largeur,taille_hauteur,null);
			}
			else if (joueur == 2 && i != -1 && k != -1)
			{

				drawable.drawImage(J2Transparent, positionPremierBatiment.getx() + taille_largeur*i+inter_batiment_largeur*i, positionPremierBatiment.gety() + taille_hauteur*k + inter_batiment_hauteur * k, taille_largeur,taille_hauteur,null);
			}

			if (c!= null && c.getConstruction() != null)
			{
				construction = c.getConstruction();
				i = construction.getx();
				k = construction.gety();
				if (j.getNbEtage(construction) == 1)
				{
					drawable.drawImage(rdcBord, positionPremierBatiment.getx() + taille_largeur*i+inter_batiment_largeur*i, positionPremierBatiment.gety() + taille_hauteur*k + inter_batiment_hauteur * k, taille_largeur,taille_hauteur,null);
				}
				else if (j.getNbEtage(construction) == 2)
				{
					drawable.drawImage(etage1Bord, positionPremierBatiment.getx() + taille_largeur*i+inter_batiment_largeur*i, positionPremierBatiment.gety() + taille_hauteur*k + inter_batiment_hauteur * k, taille_largeur,taille_hauteur,null);
				}
				else if (j.getNbEtage(construction) == 3)
				{
					drawable.drawImage(etage2Bord, positionPremierBatiment.getx() + taille_largeur*i+inter_batiment_largeur*i, positionPremierBatiment.gety() + taille_hauteur*k + inter_batiment_hauteur * k, taille_largeur,taille_hauteur,null);
				
				}
				else if (j.getNbEtage(construction) == 4)
				{
					drawable.drawImage(coupoleBord, positionPremierBatiment.getx() + taille_largeur*i+inter_batiment_largeur*i, positionPremierBatiment.gety() + taille_hauteur*k + inter_batiment_hauteur * k, taille_largeur,taille_hauteur,null);
					
				}
			}
				
			
			
			if (c != null && arriver != null)
			{
				i = arriver.getx();
				k = arriver.gety();
				drawable.drawImage(joueurBord, positionPremierBatiment.getx() + taille_largeur*i+inter_batiment_largeur*i, positionPremierBatiment.gety() + taille_hauteur*k + inter_batiment_hauteur * k, taille_largeur,taille_hauteur,null);
			}
			else if (debut != null)
			{
				i = debut.getx();
				k = debut.gety();
				drawable.drawImage(joueurBord, positionPremierBatiment.getx() + taille_largeur*i+inter_batiment_largeur*i, positionPremierBatiment.gety() + taille_hauteur*k + inter_batiment_hauteur * k, taille_largeur,taille_hauteur,null);
			}
		if (anim != null && !anim.estFini() && j.getAction(j.getJoueurEnJeu()) == modele.Action.A_CONSTRUIRE)
		{
			Point  p= anim.getPointActuel();
			i = p.getx() ;
			k = p.gety();
			if (j.getJoueurEnJeu() == 1)
			{
				//System.out.println("i : " + i + " k : "+ k);
				drawable.drawImage(J1,i,k, taille_largeur,taille_hauteur,null);
			}
			else
			{
				drawable.drawImage(J2,i,k, taille_largeur,taille_hauteur,null);
			}
		}
		if (animSelect != null && j.getAction(j.getJoueurEnJeu()) == modele.Action.EN_COURS_DE_DEPLACEMENT)
		{
			Point posi = animSelect.getPosi();
			if (animSelect.getEtat() == 0)
			{
			}
			else
			{
				drawable.drawImage(animation, posi.getx(), posi.gety(), taille_largeur,taille_hauteur,null);
			}
		}
	}
}
