package modele;

public class Batiment {

	Etage [] etages;
	int nbEtage;

	public Batiment ()
	{
		this.etages = new Etage[3];
		this.nbEtage = 0;

		for (int i = 0; i< 3; i++)
		{
			this.etages[i] = new Etage();
		}
	}

	public  void ajoutEtage (int tour)
	{
			etages[nbEtage].construire(tour);
			nbEtage ++;
	}

	public void detruireEtage ()
	{
		etages[nbEtage-1].detruireEtage ();
		nbEtage--;
	}

	public void reConstruirEtage ()
	{
		if (nbEtage < 0)
		{
			etages[nbEtage].reConstruire ();
			nbEtage++;
		}
		else
		{
			System.out.println("Tu ne peux pas reConstruire, il n'a jamais ete construir");
		}
	}

	public boolean Constructible ()
	{
		return (nbEtage <= 3);
		
	}

	public int getDernierTour ()
	{
		return etages[nbEtage].getTourConstruction ();
	}

	public int getNbEtage ()
	{
		return nbEtage;
	}
}