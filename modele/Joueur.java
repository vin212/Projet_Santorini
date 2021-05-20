package	modele;

import structure.*;


public class Joueur {
	int nbPersoPlacer;

	Point perso1;
	Point perso2;

	Action actionEnCours;

	public Joueur(){
		nbPersoPlacer = 0;
		actionEnCours =  Action.AFK;
		this.perso1 = null;
		this.perso2 = null;
	}

	public int placerPerso (Point p){
		int retour;
		if (nbPersoPlacer == 0){
			this.perso1 =p;
			nbPersoPlacer++;
			retour = 0;
		} else if (nbPersoPlacer == 1) {
			this.perso2 =p;
			nbPersoPlacer++;
			retour = 0;
		} else {
			retour =  -1;
		}
		return retour;
	}

	public int enleverPerso (Point p) 
	{
		int retour = 0;
		if (this.perso2 != null && p.CompareTo(this.perso2) == 0 )
		{
			nbPersoPlacer--;
			this.perso2 = null;
			retour = 0;
		}
		else if (this.perso1 != null && p.CompareTo(this.perso1) == 0)
		{
			nbPersoPlacer--;
			this.perso1 = null;
			retour = 0;
		}
		else
		{
			retour = -1;
		}
		return retour;
	}

	public int placerPerso1 (Point p){
		this.perso1 = p;
		return 0;
	}

	public int placerPerso2(Point p){
		this.perso2 = p;
		return 0;
	}

	public int deplacerPerso (Point posi_init, Point posi_final){
		int retour;
		int perso = checkPerso(posi_init);

		if (posi_init.CompareTo(posi_final) != 0){
			if (perso == 1){
				this.perso1 = posi_final;
				retour = 0;
			}
			else if (perso == 2)
			{
				this.perso2 = posi_final;
				retour = 0;
			} else {
				retour = -1;
			}
		} else {
			retour = -1;
		}
		return retour;
	} 

	public int checkPerso (Point posi_init){
		if (this.perso1.CompareTo (posi_init) == 0){
			return 1;
		}
		else if (this.perso2.CompareTo (posi_init) == 0)
		{
			return 2;
		} else {
			return -1;
		}
	}

	public Point [] getPosiPions (){
		Point [] retour;
		if (perso1 != null && perso2 != null){
			retour = new Point [2];
			retour[0]  = perso1;
			retour[1] = perso2;
		} else {
			retour = null;
		}
		return retour;
	}

	public Action getAction (){
		if (actionEnCours != null){
			return actionEnCours;
		} else {
			return null;
		}
	}

	public String toString(){
		return "Etat : " + actionEnCours + "Pion 1 : " + perso1 + "\n Pion 2 : " + perso2;
	}

	public void setAction (Action a)
	{
		actionEnCours = a;
	}
}