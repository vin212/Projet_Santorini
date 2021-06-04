package controleur;

public enum Bouton {
	PAUSE, RETABLIR, RETOUR, RETOUR_JEU,
	RECOMMENCER, RETOUR_MENU, 
	SAUVEGARDER, VALIDER_SAUVEGARDE, ANNULER_SAUVEGARDE,
	CHARGER,NOUVELLE_PARTIE,LANCER_PARTIE, OPTION, QUITTER, RETOUR_OPTION, RETABLIR_DEFAUT;

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
				return "Retour au jeu";
			case RECOMMENCER :
				return "Recommencer";
			case RETOUR_MENU :
				return "Retour au menu";
			case SAUVEGARDER :
				return "Sauvegarder";
			case VALIDER_SAUVEGARDE :
				return "VALIDER_SAUVEGARDE";
			case ANNULER_SAUVEGARDE :
				return "ANNULER_SAUVEGARDE";
			case CHARGER:
				return "Charger une partie";
			case NOUVELLE_PARTIE :
				return "Nouvelle partie";
			case LANCER_PARTIE :
				return "LANCER_PARTIE";
			case QUITTER :
				return "Quitter";
			case OPTION : 
				return "Option";
			case RETOUR_OPTION :
				return "RETOUR_OPTION";
			case RETABLIR_DEFAUT :
				return "RETABLIR_DEFAUT";
		}
		return "";
	}
}