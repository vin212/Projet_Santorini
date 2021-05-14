package modele;

public class Etage{
	int tourConstruction;
	boolean construit;

	public Etage ()
	{
		this.tourConstruction = 0;
		this.construit = false;
	}

	int getTourConstruction ()
	{
		return tourConstruction;
	}

	boolean getConstruit ()
	{
		return construit;
	}

	void detruireEtage ()
	{
		this.tourConstruction = -this.tourConstruction;
		this.construit = false; 
	}

	void reConstruire ()
	{
		this.tourConstruction = -this.tourConstruction;
		this.construit = true;
	}

	void construire (int tour)
	{
		this.tourConstruction = tour;
		this.construit = true;
	}


}