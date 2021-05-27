package controleur;

public enum Bouton {
	PAUSE, RETABLIR, RETOUR, RETOUR_JEU,
	RECOMMENCER, AVEC_IA, SANS_IA, RETOUR_MENU, 
	SAUVEGARDER, VALIDER_SAUVEGARDE, ANNULER_SAUVEGARDE,
	CHARGER,NOUVELLE_PARTIE,LANCER_PARTIE;

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
			case RETOUR_MENU :
				return "RETOUR_MENU";
			case SAUVEGARDER :
				return "SAUVEGARDER";
			case VALIDER_SAUVEGARDE :
				return "VALIDER_SAUVEGARDE";
			case ANNULER_SAUVEGARDE :
				return "ANNULER_SAUVEGARDE";
			case CHARGER:
				return "CHARGER";
			case NOUVELLE_PARTIE :
				return "NOUVELLE_PARTIE";
			case LANCER_PARTIE :
				return "LANCER_PARTIE";
		}
		return "";
	}
}