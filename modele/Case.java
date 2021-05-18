package modele;

public class Case {

	int nbEtage;
	int nbPerso;

	public Case ()
	{
		this.nbEtage = 0;
		this.nbPerso = 0;
	}

	public  void ajoutEtage ()
	{
		nbEtage ++;
	}

	public void detruireEtage ()
	{
		nbEtage--;
	}


	public boolean Constructible ()
	{
		return (nbEtage <= 3);
	}

	public int getNbEtage ()
	{
		return nbEtage;
	}

	public boolean peutPoserUnPerso ()
	{
		return nbEtage <= 3 && nbPerso == 0;
	}

	public boolean aPersonnage()
	{
		return nbPerso > 0;
	}

	public void enleverPerso ()
	{
		nbPerso = 0;
	}

	public void mettrePerso (int newNbPerso)
	{
		nbPerso = newNbPerso;
	}

	public int getNbPerso ()
	{
		return nbPerso;
	}


}