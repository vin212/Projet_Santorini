package modele;

public class Case {

	int nbEtage;
	int nbPerso;

	public Case ()
	{
		this.nbEtage = 0;
		this.nbPerso = 0;
	}

	public  void ajoutEtage (int tour)
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

	public boolean peuPoserUnPerso ()
	{
		return nbEtage <= 3;
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