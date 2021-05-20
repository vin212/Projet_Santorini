package controleur;

public enum Bouton {
	PAUSE, RETABLIR, RETOUR;

	public String toString ()
	{
		switch (this)
		{
			case PAUSE :
				return "PAUSE";
			case RETABLIR :
				return "RETABLIR";
			case RETOUR :
				return "RETOUR";
		}
		return "";
	}
}