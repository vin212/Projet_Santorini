package modele;

public class Case {

	Batiment b;

	public Case () {
		b = new Batiment();
	}

	public int getHauteurBatiment()
	{	
		return b.getNbEtage();
	}

	public void ConstruireEtage (int tour)
	{
		if (b.Constructible ())
		{
			b.ajoutEtage (tour);
		}
		else
		{
			System.out.println("Tu ne peux pas construire, batiement trop haut");
		}
	}

	public void detruireEtage ()
	{
		if (b.getNbEtage () > 0)
		{
			b.getNbEtage();
		}
		else
		{
			System.out.println("impossible de detrurie");
		}
	}

	public void reConstruirEtage ()
	{
		b.reConstruirEtage ();
	}

	public boolean Constructible ()
	{
		return b.Constructible();
	}

	public int getDernierTour ()
	{
		return b.getDernierTour();
	}

}