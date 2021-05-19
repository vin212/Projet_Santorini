package interfaceUser;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;
import java.io.*;

import java.util.Scanner;

import modele.*;
import structure.*;
import controleur.*;

public class PlateauInterface_2 extends JComponent {

	Jeu j;
	Image rdc;
	Image plateau;
	Image etage1;
	Image etage2;
	Image coupole;
	Image J2;
	Image J1;

	ActionUser actionUser;

	public PlateauInterface_2(Jeu j) {
		this.j = j;
		actionUser = new ActionUser (j);

		try {
			InputStream in = new FileInputStream("ressource/texture/rdc_2D.png");
			rdc = ImageIO.read(in);

			in = new FileInputStream("ressource/texture/Plateau_2D.jpg");
		 	plateau = ImageIO.read(in);

		 	in = new FileInputStream("ressource/texture/Etage_1_2D.png");
		 	etage1 = ImageIO.read(in);

		 	in = new FileInputStream("ressource/texture/Etage_2_2D.png");
		 	etage2 = ImageIO.read(in);

		 	in = new FileInputStream("ressource/texture/couple_2D.png");
		 	coupole = ImageIO.read(in);

		 	in = new FileInputStream("ressource/texture/J1_2D.png");
		 	J1 = ImageIO.read(in);

		 	in = new FileInputStream("ressource/texture/J2_2D.png");
		 	J2 = ImageIO.read(in);

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

		if (x >= positionPremierBatiment.getx() && y >= positionPremierBatiment.gety())
		{
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

		//drawable.drawImage(P1_J1, positionPremierBatiment.getx() + taille_largeur*0 + inter_batiment_largeur*0, positionPremierBatiment.gety() +taille_hauteur*0 - hauteur*0 - inter_batiment_hauteur*0, taille_largeur,taille_hauteur,null);



	}
}
