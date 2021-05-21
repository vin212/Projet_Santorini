package interfaceUser;

public enum NomFenetres {
	PAGE_ACCUEIL, MENU_PAUSE, JEU, AUTRE;

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
		}
		return "";
	}
}