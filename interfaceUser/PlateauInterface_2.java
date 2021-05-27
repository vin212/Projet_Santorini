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

	//Image joueurBord;

	ActionUser actionUser;

	public PlateauInterface_2(Jeu j) {
		this.j = j;
		actionUser = new ActionUser (j);

		String dossierTexture = "ressource/texture/";
		String dossierBatiment = dossierTexture + "Batiment/";
		String dossierCase = dossierTexture + "Case/";
		String dossierPersonnage = dossierTexture + "Joueur/";


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
		//counter = 1;
	}

	public void FaireActionUser (int x, int y)
	{
		int width = getSize().width;
		int height = getSize().height;

		Point positionPremierBatiment = new Point(width/12,height/12);

		int inter_batiment_largeur = width/50; 
		int inter_batiment_hauteur = height/50;

		int taille_hauteur = height/6-height/65;
		int taille_largeur = width/6 - width/65;

		int x_calcul = ((x-positionPremierBatiment.getx()) / (inter_batiment_largeur + taille_largeur));
		int y_calcul =((y-positionPremierBatiment.gety())/ (inter_batiment_hauteur + taille_hauteur));

		System.out.println( x_calcul+ "," + y_calcul);
		
		if (x >= positionPremierBatiment.getx() && y >= positionPremierBatiment.gety() && x_calcul >= 0 && y_calcul >= 0 && x_calcul < j.getLargeurPlateau() && y_calcul < j.getHauteurPlateau())
		{
			System.out.println("ok");
			actionUser.jouerAction(new Point(x_calcul,y_calcul));
			this.repaint();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D drawable = (Graphics2D) g;

		int width = getSize().width;
		int height = getSize().height;


		int inter_batiment_largeur = width/50; 
		int inter_batiment_hauteur = height/50;

		int taille_hauteur = height/6-height/65;
		int taille_largeur = width/6 - width/65;


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


				if (j.aPersonnage (new Point(i,k)))
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
		Point debut;
		Point arriver;
		Point construction;
		try
		{
			c = j.histoDernierCoup();
		}
		catch (IndexOutOfBoundsException Except)
		{
			c = null;
		}

		if ( c != null)
		{
			int i;
			int k;
			debut = c.getDepart();
			arriver = c.getArrive();
			construction = c.getConstruction();
			if (debut != null && construction != null)
			{
				i = debut.getx();
				k = debut.gety();
				if (c.getJoueur() == 1)
				{
					drawable.drawImage(J1Transparent, positionPremierBatiment.getx() + taille_largeur*i+inter_batiment_largeur*i, positionPremierBatiment.gety() + taille_hauteur*k + inter_batiment_hauteur * k, taille_largeur,taille_hauteur,null);
				}
				else if (c.getJoueur() == 2)
				{

					drawable.drawImage(J2Transparent, positionPremierBatiment.getx() + taille_largeur*i+inter_batiment_largeur*i, positionPremierBatiment.gety() + taille_hauteur*k + inter_batiment_hauteur * k, taille_largeur,taille_hauteur,null);
				}

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
			
			if (arriver != null)
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
		}

		//drawable.drawImage(P1_J1, positionPremierBatiment.getx() + taille_largeur*0 + inter_batiment_largeur*0, positionPremierBatiment.gety() +taille_hauteur*0 - hauteur*0 - inter_batiment_hauteur*0, taille_largeur,taille_hauteur,null);



	}
}
