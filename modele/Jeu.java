package modele;

import structure.*;
import java.lang.Math;
import java.math.BigInteger;

public class Jeu{
	Plateau p;
	int t;
	Joueur []joueurs;
	int joueurEnJeu;
	public Historique historique;
	public BigInteger hashCode;
	//TODO hash code à partir d'une base 5 (5^33 au max)


	public Jeu (){
		this.p = new Plateau (5,5);

		historique = new Historique();
		//System.out.println("Init plateau : " + this.p);
		this.t = 0;
		this.joueurs = new Joueur [2];
		this.joueurEnJeu = 1;
		this.hashCode = BigInteger.valueOf((long) 0);
		if (joueurs != null)
		{
			for (int i = 0; i < 2 ; i++)
			{
				this.joueurs[i] = new Joueur();
			}
		}
	}

	public int getHauteurPlateau(){
		return p.getHauteur();
	}

	public int getLargeurPlateau(){
		return p.getLargeur();
	}

	public void AfficherPlateau(){
		//System.out.println("Afficher plateau : " + this.p);
		this.p.afficher_CMD();
	}

	public int Construire(Point posi){
		int x = posi.getx();
		int y = posi.gety();
		hashCode = hashCode.add( BigInteger.valueOf( (long) Math.pow(5,x+5*y)) );
		//retourHashCode(hashCode);
		return p.Construire(posi);
	}

	public int detruireEtage(Point posi){
		int x = posi.getx();
		int y = posi.gety();
		hashCode = hashCode.subtract( BigInteger.valueOf( (long) Math.pow(5,x+5*y)) );
		//retourHashCode(hashCode);
		return p.detruireEtage(posi);
	}

	public boolean Constructible (Point posi){
		return p.Constructible (posi);
	}

	public boolean peutPoserUnPerso (Point posi){
		return p.peutPoserUnPerso (posi);
	}

	public int getNbEtage (Point posi){
		return p.getNbEtage(posi);
	}

	public int getTour(){
		return t;
	}

	public void addTour(){
		this.t++;
		calculJoueurEnJeu();
	}

	public void histoAjouterCoup(Coup c){
		historique.ajouteCoup(c);
	}

	public Coup histoAnnulerCoup() throws IndexOutOfBoundsException{
		//System.out.println("historique : "+historique);
		return historique.annuler();
	}

	public Coup histoRetablir() throws IndexOutOfBoundsException{
		//System.out.println("historique : "+historique);
		return historique.retablir();
	}

	public int histoPosition(){
		return historique.positionnement();
	}

	public boolean peutPoserUnPerso(Point posi_init,Point posi_final){
		return this.p.peutPoserUnPerso(posi_init,posi_final);
	}


	public int enleverPerso (Point posi)
	{
		int retour =0;
		retour = this.p.eleverPerso(posi);
		joueurs[0].enleverPerso(posi);
		joueurs[1].enleverPerso(posi);

		return retour;
	}

	public int subTour (){
		int retour;
		if (t > 0){
			this.t--;
			calculJoueurEnJeu();
			retour = 0;
		} else {
			retour = -1;
		}
		return retour;
	}

	public boolean aPersonnage(Point posi){
		return p.aPersonnage(posi);
	}

	public int poserPersonnage (Point posi_final, int nbPerso){
		int retour;
		if (nbPerso >= 1 && nbPerso <= 2){
			retour = this.joueurs[nbPerso-1].placerPerso (posi_final);
			retour = p.poserPersonnage(posi_final,nbPerso) + retour ;
		} else {	
			retour = -1;
		}
		if (retour < 0){
			retour = -1;
		}
		return retour;
	}

	public int deplacerPersonnage (Point posi_init, Point posi_final){
		int retour;
		int x0, y0, x1, y1;
		BigInteger decalage;
		if (joueurs != null){	
			retour = this.joueurs[0].deplacerPerso(posi_init,posi_final);
			retour = this.joueurs[1].deplacerPerso(posi_init,posi_final) + retour;
			retour = p.deplacerPersonnage(posi_init,posi_final) + retour;

			Point posi [] = getPosiPions(joueurEnJeu);
			if (this.joueurEnJeu == 1)
				decalage = BigInteger.valueOf((long) 5).pow(25);
			else
				decalage = BigInteger.valueOf((long) 5).pow(29);

			if (posi[0].CompareTo(posi[1]) == -1){
				x0 = posi[0].getx();
				y0 = posi[0].gety();
				x1 = posi[1].getx();
				y1 = posi[1].gety();
			} else {
				x1 = posi[0].getx();
				y1 = posi[0].gety();
				x0 = posi[1].getx();
				y0 = posi[1].gety();
			}

			BigInteger intermediaire = hashCode.divide(decalage);
			BigInteger val [] = intermediaire.divideAndRemainder(BigInteger.valueOf((long) Math.pow(5, 4)));
			hashCode = hashCode.subtract(val[1].multiply(decalage));

			hashCode = hashCode.add(decalage.multiply(BigInteger.valueOf((long) x0 + y0*5 + x1 * 25 + y1 * 125)));
		} else {
			retour = -1;
		}
		if (retour < 0){
			retour = -1;
		}
		return retour;
	}

	public int quiEstIci (Point posi){
		return p.quiEstIci (posi);
	}

	public Point [] getPosiPions (int nbPerso){
			return this.joueurs[nbPerso -1].getPosiPions();
	}

	public Action getAction (int nbPerso){
		return this.joueurs[nbPerso-1].getAction();
	}

	public int getJoueurEnJeu (){
		return joueurEnJeu;
	}


	public void setAction (int nbPerso, Action a){
		this.joueurs[nbPerso-1].setAction(a);
	}

	public boolean estGagnant(){
		boolean retour = false;
		Point [] posiPions;
		int totVoisin= 0;
		Verificateur v = new VerificateurPion(this);
		for (int i = 0; i < 2; i++)
		{
			posiPions = this.joueurs[i].getPosiPions();
			if (posiPions != null){
				for (int j = 0; j < posiPions.length; j++){
					retour = retour || p.getNbEtage (posiPions[j]) == 3;
				}
			}
		}

		posiPions = this.joueurs[joueurEnJeu-1].getPosiPions();
		if (posiPions != null && getAction(joueurEnJeu) == Action.A_DEPLACER)
		{
			for (int j = 0; j < posiPions.length; j++)
			{
				totVoisin = getNbVoisin(posiPions[j],v) + totVoisin;
			}

		}
		else
		{
			totVoisin = 1;
		}

		retour = retour || (totVoisin == 0);


		return retour;
	}

	public int quiGagnant()
	{
		int retour = 0
		;
		Point [] posiPions;
		int totVoisin= 0;
		Verificateur v = new VerificateurPion(this);
		for (int i = 0; i < 2; i++)
		{
			posiPions = this.joueurs[i].getPosiPions();
			if (posiPions != null)
			{
				for (int j = 0; j < posiPions.length; j++)
				{	
					if (retour == 0 && p.getNbEtage (posiPions[j]) == 3)
					{
						retour = i+1;
					}
				}
			}
		}
		posiPions = this.joueurs[joueurEnJeu-1].getPosiPions();
		if (posiPions != null && getAction(joueurEnJeu) == Action.A_DEPLACER)
		{
			for (int j = 0; j < posiPions.length; j++)
			{
				totVoisin = getNbVoisin(posiPions[j],v) + totVoisin;
			}

		}
		else
		{
			totVoisin = 1;
		}

		if (retour == 0 && totVoisin == 0)
		{
			retour = joueurEnJeu%2+1;
		}

		return retour;
	}

	public int calculJoueurEnJeu (){
		int retour = 0;
		if (t >= 0){
			joueurEnJeu = t % 2;
			joueurEnJeu++;
			retour = 0;
		} else {
			retour = -1;
		}
		return retour;
	}

	public int getNbVoisin(Point posi, Verificateur v)
	{
		return p.getNbVoisin(posi,v);
	}

	public String toString(){
		return ("Au joueur " + joueurEnJeu + " de jouer sur le plateau :\n" + p);
	}

	public BigInteger gethashCode(){
		return hashCode;
	}
	
    public void retourHashCode(BigInteger hashbis){
		BigInteger val[] = hashbis.divideAndRemainder(BigInteger.valueOf((long) 1));
		for (int i = 0; i<5;i++){
			for (int j = 0; j<5;j++){
				val = val[0].divideAndRemainder(BigInteger.valueOf((long) 5));
				System.out.print("   " + val[1]);
			}
			System.out.println("");
		}
		
		for (int j = 1; j<9;j++){
			val = val[0].divideAndRemainder(BigInteger.valueOf((long) 5));
			System.out.print("   " + val[1]);
			if (j%4 == 0){
				System.out.println("");
			}
		}
}

}