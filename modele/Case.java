package modele;

public class Case implements Cloneable {

	int nbEtage;
	int nbPerso;

	public Case (){
		this.nbEtage = 0;
		this.nbPerso = 0;
	}

	public  int ajoutEtage (){
		int retour;
		if (nbEtage + 1 <= 4){
			nbEtage ++;
			retour = 0;
		}
		else{
			retour = -1;
		}
		return retour;
	}

	public int detruireEtage (){
		int retour;
		if (nbEtage == 0){
			retour = -1;
		} else {
			nbEtage--;
			retour = 0;
		}
		return retour;
	}



	public boolean Constructible ()
	{
		return (nbEtage <= 3 && nbPerso ==0);
	}

	public int getNbEtage (){
		return nbEtage;
	}

	public boolean peutPoserUnPerso (){
		return nbEtage <= 3 && nbPerso == 0;
	}

	public boolean aPersonnage(){
		return nbPerso > 0;
	}

	public int enleverPerso (){
		int retour;
		if (nbPerso > 0){
			nbPerso = 0;
			retour = 0;
		} else {
			retour = -1;
		}

		return retour;
	}

	public void mettrePerso (int newNbPerso){
		nbPerso = newNbPerso;
	}

	public int getNbPerso (){
		return nbPerso;
	}

	@Override
	public Case clone(){
		try{
			Case resultat = (Case) super.clone();
			resultat.nbEtage = nbEtage;
			resultat.nbPerso = nbPerso;
			return resultat;
		} catch (CloneNotSupportedException e){
			System.err.println("Bug interne, case non clonable");
		}
		return null;
	}

	public String toString(){
		if (aPersonnage())
			return ("" + nbEtage + ":" + nbPerso + " ");
		else
			return "" + nbEtage + "   ";
	}

}