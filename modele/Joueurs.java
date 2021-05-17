package modele;

import structure.*;


public class Joueurs {
	Joueur [] joueur;
	int nbJoueur;

	public Joueurs (int nbJoueur)
	{
		joueur = new Joueur [nbJoueur];
		this.nbJoueur = nbJoueur;
		for (int i = 0; i < nbJoueur; i++)
		{
			joueur[i] = new Joueur ();
		}
	} 

	public int hauteurPersonnage (int nb, int perso)
	{
		return joueur[perso].hauteurPersonnage (nb);
	}

	public Point posiPerso (int nb, int perso)
	{
		return joueur[perso].posiPerso(nb);
	}

	public boolean aPersonnage (Point posi)
	{
		boolean retour = false;
		for (int i = 0; i < nbJoueur; i++)
		{
			retour = retour || joueur[i].aPersonnage(posi);
		}
		return retour;
	}

	public void deplacerPersonnage (int x, int y,int nb, int perso)
	{
		joueur[perso].deplacerPersonnage(x,y,nb);
	}

	public Integer [] quiEstIci (Point posi)
	{
		int i = 0;
		Integer [] retour;
		while (i < nbJoueur && !joueur[i].aPersonnage(posi))
		{
			i++;
		}

		if (i < nbJoueur && joueur[i].aPersonnage(posi))
		{	
			retour = joueur[i].quiEstIci (posi);
			retour[1] = (Integer) (i);
		}
		else
		{
			retour = new Integer [2];
			retour[0] =(Integer)(-1);
			retour[1] = (Integer)(-1);
		}

		return retour;

		
	}

	public void setHauteurPersonnage (int hauteur, int nb, int perso)
	{
		joueur[perso].setHauteurPersonnage(hauteur,nb);
	}
}