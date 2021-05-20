package modele;

public enum Action {
PREMIER_PLACEMENT, DEUXIEME_PLACEMENT, A_DEPLACER, EN_COURS_DE_DEPLACEMENT, A_CONSTRUIRE, AFK;

	public String toString ()
	{
		switch (this)
		{
			case AFK :
				return "AFK";
			//break;
			case A_CONSTRUIRE :
				return "A_CONSTRUIRE";
			//break;
			case EN_COURS_DE_DEPLACEMENT :
				return "EN_COURS_DE_DEPLACEMENT";
			//break;
			case A_DEPLACER:
				return "A_DEPLACER";
			//break;
			case DEUXIEME_PLACEMENT :
				return "DEUXIEME_PLACEMENT";
			//break;
			case PREMIER_PLACEMENT :
				return "PREMIER_PLACEMENT";
			//break;
		}
		return "";
	}
}