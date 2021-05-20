package modele;

import structure.*;

public class Plateau{

	int hauteur;
	int largeur;
	public Case [][] cases;

	public Plateau (int hauteur, int largeur) {

		if (hauteur > 0 && largeur > 0){
			this.hauteur = hauteur;
			this.largeur = largeur;
			this.cases = new Case[largeur][hauteur];

			for (int i = 0; i < largeur; i++){
				for (int j = 0; j < hauteur; j++){
					this.cases[i][j] = new Case();
				}
			}
		}
	}

	public int getHauteur (){
		return hauteur;
	}

	public int getLargeur (){
		return largeur;
	}

	public int Construire(Point posi){
		int retour;
		if (this.cases != null && posi.getx() < largeur && posi.gety() < hauteur && posi.getx() >= 0 && posi.gety() >= 0){
			this.cases[posi.getx()][posi.gety()].ajoutEtage ();
			retour = 0;
		} else {
			retour = -1;
		}
		return retour;
	}

	public int detruireEtage (Point posi){
		int retour;
		if (this.cases != null && posi.getx() < largeur && posi.gety() < hauteur && posi.getx() >= 0 && posi.gety() >= 0){
			this.cases[posi.getx()][posi.gety()].detruireEtage ();
			retour = 0;
		} else {
			retour = -1;
		}
		return retour;
	}


	public boolean Constructible (Point posi){
		boolean retour;
		if (this.cases != null && posi.getx() < largeur && posi.gety() < hauteur && posi.getx() >= 0 && posi.gety() >= 0){
			retour = this.cases[posi.getx()][posi.gety()].Constructible();
		} else {
			retour = false;
		}
		return retour;
	}

	public boolean peutPoserUnPerso (Point posi){
		boolean retour;
		if (this.cases != null && posi.getx() < largeur && posi.gety() < hauteur && posi.getx() >= 0 && posi.gety() >= 0){
			retour = this.cases[posi.getx()][posi.gety()].peutPoserUnPerso();
		} else {
			retour = false;
		}
		return retour;
	}
	public boolean peutPoserUnPerso (Point posi_init,Point posi_final)
	{
		if (posi_init.getx() >= 0 && posi_init.gety() >= 0 && posi_final.getx() >= 0 &&  posi_final.gety() >= 0)
		{
			if (posi_init.getx() < largeur && posi_init.gety() < hauteur && posi_final.getx() < largeur && posi_final.gety() < hauteur)
			{
				if (Math.abs(posi_final.getx() - posi_init.getx()) + Math.abs(posi_final.gety() - posi_init.gety()) == 1 )
				{
					if (peutPoserUnPerso (posi_final) && getNbEtage (posi_final) - getNbEtage (posi_init) <= 1  )
					{
						return true;
					}
				}
				else if (Math.abs(posi_final.getx() - posi_init.getx()) == 1 && Math.abs(posi_final.gety() - posi_init.gety()) == 1 )
				{
					if (peutPoserUnPerso (posi_final) && getNbEtage (posi_final) - getNbEtage (posi_init) <= 1  )
					{
						return true;
					}
				}
				else
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
		return false;
	}

	public int getNbEtage (Point posi)
	{
		int retour;
		if (this.cases != null && posi.getx() < largeur && posi.gety() < hauteur && posi.getx() >= 0 && posi.gety() >= 0){
			retour = this.cases[posi.getx()][posi.gety()].getNbEtage();
		} else {
			retour = -1;
		}
		return retour;
	}

	public int deplacerPersonnage (Point posi_init, Point posi_final){
		int retour;
		if (this.cases != null && posi_init.getx() < largeur && posi_init.gety() < hauteur && posi_init.getx() >= 0 && posi_init.gety() >= 0 && posi_final.getx() < largeur && posi_final.gety() < hauteur && posi_final.getx() >= 0 && posi_final.gety() >= 0){
			int nbPerso = this.cases[posi_init.getx()][posi_init.gety()].getNbPerso();
			this.cases[posi_init.getx()][posi_init.gety()].enleverPerso();
			this.cases[posi_final.getx()][posi_final.gety()].mettrePerso (nbPerso);

			retour = 0;
		} else {
			retour = -1;
		}
		return retour;
	}

	public int poserPersonnage (Point posi_final, int nbPerso){
		int retour;
		if (this.cases != null && posi_final.getx() >= 0 && posi_final.gety() >= 0 && posi_final.getx()  < largeur && posi_final.gety() < hauteur && nbPerso <= 2 && nbPerso >= 1  ){
			this.cases[posi_final.getx()][posi_final.gety()].mettrePerso (nbPerso);
			retour = 0;
		} else {
			retour = -1;
		}
		return retour;
	}

	public int eleverPerso (Point posi)
	{
		int retour = 0;
		if (this.cases != null && posi.getx() >= 0 && posi.getx() < largeur && posi.gety() >= 0 && posi.gety() < hauteur)
		{
			if (this.cases[posi.getx()][posi.gety()].aPersonnage())
			{
				retour = this.cases[posi.getx()][posi.gety()].enleverPerso();
			}
			else
			{
				retour = -1;
			}
		}
		else
		{
			retour =-1;
		}
		return retour;
	}

	public int quiEstIci (Point posi){
		int retour;
		if (cases != null && posi.getx() >= 0 && posi.gety() >= 0 && posi.getx()  < largeur && posi.gety() < hauteur){
			retour = this.cases[posi.getx()][posi.gety()].getNbPerso();
		} else {
			retour = -1;
		}
		return retour;
	}

	public boolean aPersonnage(Point posi){
		Boolean retour;
		if (cases != null && posi.getx() >= 0 && posi.gety() >= 0 && posi.getx()  < largeur && posi.gety() < hauteur){
			retour = this.cases[posi.getx()][posi.gety()].aPersonnage();
		} else {
			retour = false;
		}
		return retour;
	}

	public int getNbVoisin(Point posi, Verificateur v){
        int x = posi.getx(), y = posi.gety();
        int xmax = largeur;
        int ymax = hauteur;
        int voisins = 0;
        if (0 < x){
            if (v.verifie(posi, new Point(x-1, y)))
                voisins++;
        }
        if (0 < y){
            if (v.verifie(posi, new Point(x, y-1)))
                voisins++;
        }
        if (x < xmax){
            if (v.verifie(posi, new Point(x+1, y)))
                voisins++;
        }
        if (y < ymax){
            if (v.verifie(posi, new Point(x, y+1)))
                voisins++;
        }
        if (0 < x && 0 < y){
            if (v.verifie(posi, new Point(x-1, y-1)))
                voisins++;
        }
        if (x < xmax && y < ymax){
            if (v.verifie(posi, new Point(x+1, y+1)))
                voisins++;
        }
        if (x < xmax && 0 < y){
            if (v.verifie(posi, new Point(x+1, y-1)))
                voisins++;
        }
        if (0 < x && y < ymax){
            if (v.verifie(posi, new Point(x-1, y+1)))
                voisins++;
        }
        return voisins;
    }


	public void afficher_CMD (){
		for (int i = 0; i < hauteur; i++){
			for (int j = 0; j < largeur; j++){
				System.out.print(this.cases[j][i].getNbEtage ());
			}
			System.out.println();
		}
		System.out.println();
	}

	public String toString(){
		String msg = "plateau de taille " + hauteur + "x" + largeur + " : \n";
		for(int j = 0; j < hauteur; j++){
			for(int i = 0; i < largeur; i++){
				msg += "|" + cases[i][j];
			}
			msg += "|\n";
			for(int i = 0; i < largeur; i++){
				msg += "|----";
			}
			msg += "|\n";
		}
		return msg;
	}



}