package modele;

import structure.*;

public class Jeu{
	Plateau p;
	int t;
	Joueurs j;

	public Jeu ()
	{
		this.p = new Plateau (5,5);

		System.out.println("Init plateau : " + this.p);
		this.t = 0;
		j = new Joueurs (2);
		//this.j = new Joueurs ();
	}

	public int getHauteurPlateau ()
	{
		return p.getHauteur();
	}

	public int getLargeurPlateau ()
	{
		return p.getLargeur();
	}

	public void AfficherPlateau ()
	{
		System.out.println("Afficher plateau : " + this.p);
		this.p.afficher_CMD();
	}

	public void Construire(Point posi)
	{
		p.Construire(posi,t);
	}

	public void detruireEtage (Point posi)
	{
		p.detruireEtage(posi);
	}

	public void reConstruirEtage (Point posi)
	{
		p.reConstruirEtage(posi);
	}

	public boolean Constructible (Point posi)
	{
		return p.Constructible (posi);
	}

	public int getDernierTour (Point posi)
	{
		return p.getDernierTour(posi);
	}

	public int getNbEtage (Point posi)
	{
		return p.getNbEtage(posi);
	}

	public int getTour ()
	{
		return t;
	}

	public void addTour ()
	{
		this.t++;
	}

	public void subTour ()
	{
		this.t--;
	}

	public int hauteurPersonnage (int nb, int perso)
	{
		return j.hauteurPersonnage (nb,perso);
	}

	public Point posiPerso (int nb, int perso)
	{
		return j.posiPerso (nb,perso);
	}

	public boolean aPersonnage (Point posi)
	{
		return j.aPersonnage(posi);
	}

	public void deplacerPersonnage (int x, int y,int nb, int perso)
	{
		j.deplacerPersonnage(x,y,nb,perso);
	}

	public Integer [] quiEstIci (Point posi)
	{
		return j.quiEstIci (posi);
	}

	public void setHauteurPersonnage(int hauteur, int nb, int perso)
	{
		j.setHauteurPersonnage(hauteur,nb,perso);
	}

}