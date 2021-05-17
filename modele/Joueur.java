package modele;

import structure.*;

public class Joueur {
	Personnages  [] personnage;

	public Joueur ()
	{
		this.personnage = new Personnages [2];
		for (int i = 0; i < 2; i++)
		{
			personnage[i] = new Personnages();
		}

	} 

	public int hauteurPersonnage (int nb)
	{
		return personnage[nb].getHauteur();
	}

	public Point posiPerso (int nb)
	{
		return personnage[nb].getPosi();
	}

	public boolean aPersonnage (Point posi)
	{
		boolean retour = false;
		for (int i = 0; i < 2; i++)
		{
			retour = retour || personnage[i].APersonnage(posi);
		}
		return retour;
	}

	public void deplacerPersonnage (int x, int y,int nb)
	{
		personnage[nb].deplacerPersonnage(x,y);
	}

	public Integer [] quiEstIci (Point posi)
	{
		Integer [] nb = new Integer [2];
		int i = 0;
		while (i < 2 && !personnage[i].APersonnage(posi))
		{
			i++;
		}

		if (i < 2 && personnage[i].APersonnage(posi))
		{
			nb[0] = (Integer)(i);
		}
		else
		{
			nb[0] = (Integer)(-1);
		}

		return nb;
	}

	public void setHauteurPersonnage (int hauteur, int nb)
	{
		personnage[nb].setHauteur(hauteur);
	}


}