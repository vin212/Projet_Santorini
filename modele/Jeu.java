package modele;

import structure.*;

public class Jeu{
	Plateau p;
	int t;
	//Joueurs j;

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

}