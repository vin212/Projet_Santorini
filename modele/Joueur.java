package	modele;

import structure.*;

public class Joueur {
	
	int nbPersoPlacer;

	Point perso1;
	Point perso2;

	public Joueur()
	{
		nbPersoPlacer = 0;
	}

	public void placerPerso (Point p)
	{
		if (nbPersoPlacer == 0)
		{
			this.perso1 =p;
			nbPersoPlacer++;
		}
		else if (nbPersoPlacer == 1)
		{
			this.perso2 =p;
			nbPersoPlacer++;
		}
	}

	public void placerPerso1 (Point p)
	{
		this.perso1 = p;
	}

	public void placerPerso2(Point p)
	{
		this.perso2 = p;
	}

	public void deplacerPerso (Point posi_init, Point posi_final)
	{
		int perso = checkPerso(posi_init);
		if (perso == 1)
		{
			this.perso1 = posi_final;
		}
		else if (perso == 2)
		{
			this.perso2 = posi_init;
		}

	} 

	public int checkPerso (Point posi_init)
	{
		if (this.perso1.CompareTo (posi_init) == 0)
		{
			return 1;
		}
		else if (this.perso1.CompareTo (posi_init) == 1)
		{
			return 2;
		}	
		else 
		{
			return -1;
		}

	}

	public Point [] getPosiPions ()
	{
		Point [] retour = new Point [2];
		retour[0]  = perso1;
		retour[1] = perso2;
		return retour;
	}
}