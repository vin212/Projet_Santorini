package modele;

import structure.*;

public class Jeu{
	Plateau p;
	int t;

	public Jeu ()
	{
		this.p = new Plateau (5,5);

		System.out.println("Init plateau : " + this.p);
		this.t = 0;
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

	public boolean Constructible (Point posi)
	{
		return p.Constructible (posi);
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

	public boolean aPersonnage (Point posi)
	{
		return p.aPersonnage(posi);
	}

	public void poserPersonnage (Point posi_final, int nbPerso)
	{
		p.poserPersonnage(posi_final,nbPerso);
	}

	public void deplacerPersonnage (Point posi_init, Point posi_final)
	{
		p.deplacerPersonnage(posi_init,posi_final);
	}

	public int quiEstIci (Point posi)
	{
		return p.quiEstIci (posi);
	}

}