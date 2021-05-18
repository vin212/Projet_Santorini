package modele;

import structure.*;

public class Plateau{

	int hauteur;
	int largeur;
	Case [][] cases;

	public Plateau (int hauteur, int largeur) {

		if (hauteur > 0 && largeur > 0)
		{
			this.hauteur = hauteur;
			this.largeur = largeur;
			this.cases = new Case[largeur][hauteur];


			for (int i = 0; i < largeur; i++)
			{
				for (int j = 0; j < hauteur; j++)
				{
					this.cases[i][j] = new Case();
				}
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

	public void Construire(Point posi)
	{
		this.cases[posi.getx()][posi.gety()].ajoutEtage ();
	}

	public void detruireEtage (Point posi)
	{
		this.cases[posi.getx()][posi.gety()].detruireEtage ();
	}


	public boolean Constructible (Point posi)
	{
		return this.cases[posi.getx()][posi.gety()].Constructible ();
	}

	public boolean peutPoserUnPerso (Point posi)
	{
		return this.cases[posi.getx()][posi.gety()].peutPoserUnPerso	();
	}

	public int getNbEtage (Point posi)
	{
		return this.cases[posi.getx()][posi.gety()].getNbEtage();
	}

	public void deplacerPersonnage (Point posi_init, Point posi_final)
	{
		int nbPerso = this.cases[posi_init.getx()][posi_init.gety()].getNbPerso();
		this.cases[posi_init.getx()][posi_init.gety()].enleverPerso();
		this.cases[posi_final.getx()][posi_final.gety()].mettrePerso (nbPerso);

	}

	public void poserPersonnage (Point posi_final, int nbPerso)
	{
		this.cases[posi_final.getx()][posi_final.gety()].mettrePerso (nbPerso);
	}

	public int quiEstIci (Point posi)
	{
		return this.cases[posi.getx()][posi.gety()].getNbPerso();
	}

	public boolean aPersonnage(Point posi)
	{
		return this.cases[posi.getx()][posi.gety()].aPersonnage();
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