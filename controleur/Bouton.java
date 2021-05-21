package controleur;

public enum Bouton {
	PAUSE, RETABLIR, RETOUR, RETOUR_JEU, RECOMMENCER, AVEC_IA, SANS_IA, RETOUR_MENU;

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
			case AVEC_IA :
				return "AVEC_IA";
			case SANS_IA :
				return "SANS_IA";
			case RETOUR_MENU :
				return "RETOUR_MENU";
		}
		return "";
	}
}