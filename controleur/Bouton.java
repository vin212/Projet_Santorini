package controleur;

public enum Bouton {
	PAUSE, RETABLIR, RETOUR, RETOUR_JEU, RECOMMENCER;

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
			case RETOUR_JEU:
				return "RETOUR_JEU";
			case RECOMMENCER :
				return "RECOMMENCER";
		}
		return "";
	}
}