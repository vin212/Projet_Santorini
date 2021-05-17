package modele;

import structure.*;

public class Personnages {
	Point posi;
	int hauteur;

	public Personnages ()
	{
		posi = new Point(-1,-1);
	 	hauteur = 0;
	}

	public int getHauteur ()
	{
		return hauteur;
	}

	public Point getPosi ()
	{
		return posi;
	}

	public boolean APersonnage (Point posi)
	{
		return (this.posi.getx() == posi.getx() && this.posi.gety() == posi.gety());
	}

	public void deplacerPersonnage (int x, int y)
	{
		this.posi.modifValeur(x,y);
	}

	public void setHauteur (int hauteur)
	{
		this.hauteur = hauteur;
	}
}