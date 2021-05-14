package modele;

import structure.*;

public class Plateau{

	int hauteur;
	int largeur;
	Batiment [][] cases;

	public Plateau (int hauteur, int largeur) {
		this.hauteur = hauteur;
		this.largeur = largeur;
		this.cases = new Batiment[largeur][hauteur];

		for (int i = 0; i < largeur; i++)
		{
			for (int j = 0; j < hauteur; j++)
			{
				this.cases[i][j] = new Batiment();
			}
		}
	}

	public int getHauteur ()
	{
		return hauteur;
	}

	public int getLargeur ()
	{
		return largeur;
	}

	public void Construire(Point posi, int tour)
	{
		this.cases[posi.getx()][posi.gety()].ajoutEtage (tour);
	}

	public void detruireEtage (Point posi)
	{
		this.cases[posi.getx()][posi.gety()].detruireEtage ();
	}

	public void reConstruirEtage (Point posi)
	{
		this.cases[posi.getx()][posi.gety()].reConstruirEtage();
	}

	public boolean Constructible (Point posi)
	{
		return this.cases[posi.getx()][posi.gety()].Constructible ();
	}

	public int getDernierTour (Point posi)
	{
		return this.cases[posi.getx()][posi.gety()].getDernierTour();
	}

	public void afficher_CMD ()
	{
		for (int i = 0; i < hauteur; i++)
		{
			for (int j = 0; j < largeur; j++)
			{
				System.out.print(this.cases[j][i].getNbEtage ());
			}
			System.out.println();

		}
		System.out.println();
	}



}