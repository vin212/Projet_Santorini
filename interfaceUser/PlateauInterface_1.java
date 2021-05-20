package interfaceUser;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;
import java.io.*;

import modele.*;
import structure.*;

public class PlateauInterface_1 extends JComponent {

	Jeu j;
	Image rdc;
	Image plateau;
	Image etage1;
	Image etage2;
	Image coupole;
	Image P1_J1;
	Image P1_J2;
	
	public PlateauInterface_1(Jeu j) {
		this.j = j;

		try {
			InputStream in = new FileInputStream("ressource/texture/test_bis.JPG");
			rdc = ImageIO.read(in);

			in = new FileInputStream("ressource/texture/Plateau.JPG");
		 	plateau = ImageIO.read(in);

		 	in = new FileInputStream("ressource/texture/Etage_1.JPG");
		 	etage1 = ImageIO.read(in);

		 	in = new FileInputStream("ressource/texture/etage_2.png");
		 	etage2 = ImageIO.read(in);

		 	in = new FileInputStream("ressource/texture/coupole.png");
		 	coupole = ImageIO.read(in);

		 	in = new FileInputStream("ressource/texture/P1_J1.png");
		 	P1_J1 = ImageIO.read(in);

		 	in = new FileInputStream("ressource/texture/P1_J2.png");
		 	P1_J2 = ImageIO.read(in);

		} catch (Exception e) {
			System.out.print("erreur \n");
			System.exit(1);
		}
		//counter = 1;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D drawable = (Graphics2D) g;

		int width = getSize().width;
		int height = getSize().height;


		int inter_batiment_largeur = 6*width/400;
		int inter_batiment_hauteur = 6*height/200;

		int taille_hauteur = height/7;
		int taille_largeur = width/6 + width/35  - inter_batiment_largeur;

		int hauteur = height/18;



		Point positionPremierBatiment = new Point(inter_batiment_largeur,height/3-height/40);
		Point taillePlateau  = new Point (width,height/3*2 - inter_batiment_hauteur);
		//int taille_hauteur = 


		drawable.clearRect(0, 0, width, height	);
		drawable.setColor(new Color(216,239,253));
		drawable.fillRect(0, 0, width, height);
		drawable.drawImage(plateau,0,height/3,taillePlateau.getx(),taillePlateau.gety(),null);


		for (int i = 0; i < j.getLargeurPlateau(); i++)
		{
			for (int k = 0; k < j.getHauteurPlateau (); k++)
			{
				if (j.getNbEtage(new Point(i,k)) >= 1)
				{
					drawable.drawImage(rdc, positionPremierBatiment.getx() + taille_largeur*i + inter_batiment_largeur*i, positionPremierBatiment.gety() + taille_hauteur*k - inter_batiment_hauteur*k, taille_largeur,taille_hauteur,null);
				}
				if (j.getNbEtage(new Point(i,k)) >= 2)
				{
					drawable.drawImage(etage1, positionPremierBatiment.getx() + taille_largeur*i + inter_batiment_largeur*i, positionPremierBatiment.gety() +taille_hauteur*k - hauteur - inter_batiment_hauteur*k, taille_largeur,taille_hauteur,null);
				}
				if (j.getNbEtage(new Point(i,k)) >= 3)
				{
					drawable.drawImage(etage2, positionPremierBatiment.getx() + taille_largeur*i + inter_batiment_largeur*i, positionPremierBatiment.gety() +taille_hauteur*k - hauteur*2 - inter_batiment_hauteur*k, taille_largeur,taille_hauteur,null);
				}
				if (j.getNbEtage(new Point(i,k)) >= 4)
				{
					drawable.drawImage(coupole, positionPremierBatiment.getx() + taille_largeur*i + inter_batiment_largeur*i, positionPremierBatiment.gety() +taille_hauteur*k - hauteur*3 - inter_batiment_hauteur*k, taille_largeur,taille_hauteur,null);
				}

				if (j.aPersonnage (new Point(i,k)))
				{
					int perso = j.quiEstIci (new Point(i,k));
					int persoHauteur = j.getNbEtage (new Point (i,k));
					if (perso == 1)
					{	
						drawable.drawImage(P1_J1, positionPremierBatiment.getx() + taille_largeur*i + inter_batiment_largeur*i, positionPremierBatiment.gety() +taille_hauteur*k - hauteur*persoHauteur - inter_batiment_hauteur*k, taille_largeur,taille_hauteur,null);
					}
					else if (perso == 2)
					{
						drawable.drawImage(P1_J2, positionPremierBatiment.getx() + taille_largeur*i + inter_batiment_largeur*i, positionPremierBatiment.gety() +taille_hauteur*k - hauteur*persoHauteur - inter_batiment_hauteur*k, taille_largeur,taille_hauteur,null);
					}
				}
			}
		}

		//drawable.drawImage(P1_J1, positionPremierBatiment.getx() + taille_largeur*0 + inter_batiment_largeur*0, positionPremierBatiment.gety() +taille_hauteur*0 - hauteur*0 - inter_batiment_hauteur*0, taille_largeur,taille_hauteur,null);



	}
}