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
				return "Choisir case de construction";
			//break;
			case EN_COURS_DE_DEPLACEMENT :
				return "Choisir la destination";
			//break;
			case A_DEPLACER:
				return "Choisir le pion";
			//break;
			case DEUXIEME_PLACEMENT :
				return "Placer 2nd pion";
			//break;
			case PREMIER_PLACEMENT :
				return "Placer 1er pion";
			//break;
		}
		return "";
	}
}