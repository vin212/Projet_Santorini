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

	public int Construire(Point posi)
	{
		int retour;
		if (this.cases != null && posi.getx() < largeur && posi.gety() < hauteur && posi.getx() >= 0 && posi.gety() >= 0)
		{
			this.cases[posi.getx()][posi.gety()].ajoutEtage ();
			retour = 0;
		}
		else
		{
			retour = -1;
		}

		return -1;
	}

	public int detruireEtage (Point posi)
	{
		int retour;
		if (this.cases != null && posi.getx() < largeur && posi.gety() < hauteur && posi.getx() >= 0 && posi.gety() >= 0)
		{
			this.cases[posi.getx()][posi.gety()].detruireEtage ();
			retour = 0;
		}
		else
		{
			retour = -1;
		}
		return retour;
	}


	public boolean Constructible (Point posi)
	{
		boolean retour;
		if (this.cases != null && posi.getx() < largeur && posi.gety() < hauteur && posi.getx() >= 0 && posi.gety() >= 0)
		{
			retour = this.cases[posi.getx()][posi.gety()].Constructible();
		}
		else
		{
			retour = false;
		}
		return retour;
		
	}

	public boolean peutPoserUnPerso (Point posi)
	{
		boolean retour;
		if (this.cases != null && posi.getx() < largeur && posi.gety() < hauteur && posi.getx() >= 0 && posi.gety() >= 0)
		{
			retour = this.cases[posi.getx()][posi.gety()].peutPoserUnPerso();
		}
		else
		{
			retour = false;
		}
		return retour;
	}

	public int getNbEtage (Point posi)
	{
		int retour;
		if (this.cases != null && posi.getx() < largeur && posi.gety() < hauteur && posi.getx() >= 0 && posi.gety() >= 0)
		{
			retour = this.cases[posi.getx()][posi.gety()].getNbEtage();
		}
		else
		{
			retour = -1;
		}
		return retour;
	}

	public int deplacerPersonnage (Point posi_init, Point posi_final)
	{
		int retour;
		if (this.cases != null && posi_init.getx() < largeur && posi_init.gety() < hauteur && posi_init.getx() >= 0 && posi_init.gety() >= 0 && posi_final.getx() < largeur && posi_final.gety() < hauteur && posi_final.getx() >= 0 && posi_final.gety() >= 0)
		{
			int nbPerso = this.cases[posi_init.getx()][posi_init.gety()].getNbPerso();
			this.cases[posi_init.getx()][posi_init.gety()].enleverPerso();
			this.cases[posi_final.getx()][posi_final.gety()].mettrePerso (nbPerso);

			retour = 0;
		}
		else
		{
			retour = -1;
		}
		return retour;

	}

	public int poserPersonnage (Point posi_final, int nbPerso)
	{
		int retour;
		if (this.cases != null && posi_final.getx() >= 0 && posi_final.gety() >= 0 && posi_final.getx()  < largeur && posi_final.gety() < hauteur && nbPerso <= 2 && nbPerso >= 1  )
		{
			this.cases[posi_final.getx()][posi_final.gety()].mettrePerso (nbPerso);
			retour = 0;
		}
		else
		{
			retour = -1;
		}

		return retour;
	}

	public int quiEstIci (Point posi)
	{
		int retour;
		if (cases != null && posi.getx() >= 0 && posi.gety() >= 0 && posi.getx()  < largeur && posi.gety() < hauteur)
		{
			retour = this.cases[posi.getx()][posi.gety()].getNbPerso();
		}
		else
		{
			retour = -1;
		}

		return retour;
	}

	public boolean aPersonnage(Point posi)
	{
		Boolean retour;
		if (cases != null && posi.getx() >= 0 && posi.gety() >= 0 && posi.getx()  < largeur && posi.gety() < hauteur)
		{
			retour = this.cases[posi.getx()][posi.gety()].aPersonnage();
		}
		else
		{
			retour = false;
		}
		return retour;

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