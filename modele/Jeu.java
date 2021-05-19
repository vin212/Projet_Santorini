package modele;

import structure.*;

public class Jeu{
	Plateau p;
	int t;
	Joueur []joueurs;
	int joueurEnJeu;


	public Jeu ()
	{
		this.p = new Plateau (5,5);

		System.out.println("Init plateau : " + this.p);
		this.t = 0;
		this.joueurs = new Joueur [2];
		this.joueurEnJeu = 1;
		if (joueurs != null)
		{
			for (int i = 0; i < 2 ; i++)
			{
				this.joueurs[i] = new Joueur();
			}
		}
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

	public int Construire(Point posi)
	{
		return p.Construire(posi);
	}

	public int detruireEtage (Point posi)
	{
		return p.detruireEtage(posi);
	}

	public boolean Constructible (Point posi)
	{
		return p.Constructible (posi);
	}

	public boolean peutPoserUnPerso (Point posi)
	{
		return p.peutPoserUnPerso (posi);
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

	public int subTour ()
	{
		int retour;
		if (t > 0)
		{
			this.t--;
			retour = 0;
		}
		else
		{
			retour = -1;
		}
		return retour;
	}

	public boolean aPersonnage (Point posi)
	{
		return p.aPersonnage(posi);
	}

	public int poserPersonnage (Point posi_final, int nbPerso)
	{
		int retour;
		if (nbPerso >= 1 && nbPerso <= 2)
		{
			retour = this.joueurs[nbPerso-1].placerPerso (posi_final);
			retour = p.poserPersonnage(posi_final,nbPerso) + retour ;
		}
		else
		{	
			retour = -1;
		}

		if (retour < 0)
		{
			retour = -1;
		}

		return retour;
	}

	public int deplacerPersonnage (Point posi_init, Point posi_final)
	{
		int retour;
		if (joueurs != null)
		{
			retour = this.joueurs[0].deplacerPerso(posi_init,posi_final);
			retour = this.joueurs[1].deplacerPerso(posi_init,posi_final) + retour;
			retour = p.deplacerPersonnage(posi_init,posi_final) + retour;
		}
		else
		{
			retour = -1;
		}
		if (retour < 0)
		{
			retour = -1;
		}

		return retour;
	}

	public int quiEstIci (Point posi)
	{
		return p.quiEstIci (posi);
	}

	public Point [] getPosiPions (int nbPerso)
	{
			return this.joueurs[nbPerso -1].getPosiPions();
	}

	public Action getAction (int nbPerso)
	{
		return this.joueurs[nbPerso-1].getAction();
	}

	public int getJoueurEnJeu ()
	{
		return joueurEnJeu;
	}

	public void setAction (int nbPerso, Action a)
	{
		this.joueurs[nbPerso-1].setAction(a);
	}

	public int calculJoueurEnJeu ()
	{
		int retour = 0;
		if (t >= 0)
		{
			joueurEnJeu = t % 2 + 1;
			retour = 0;
		}
		else
		{
			retour = -1;
		}

		return retour;

	}


}