package interfaceUser;

public enum NomFenetres {
	PAGE_ACCUEIL, MENU_PAUSE, JEU, AUTRE, POPUP_SAUVEGARDE, CHARGER, NOUVELLE_PARTIE, OPTION;

	public String toString()
	{
		switch (this)
		{
			case PAGE_ACCUEIL :
				return "PAGE_ACCUEIL";
			case MENU_PAUSE : 
				return "MENU_PAUSE";
			case JEU :
				return "JEU";
			case POPUP_SAUVEGARDE :
				return "POPUP_SAUVEGARDE";
			case CHARGER :
				return "CHARGER";
			case NOUVELLE_PARTIE :
				return "NOUVELLE_PARTIE";
			case OPTION :
				return "OPTION";
			case AUTRE:
				break;
			default:
				break;
		}
		return "";
	}
}