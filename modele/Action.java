package modele;

public enum Action {
PREMIER_PLACEMENT, DEUXIEME_PLACEMENT, A_DEPLACER, EN_COURS_DE_DEPLACEMENT, A_CONSTRUIRE, AFK;

	public String toString ()
	{
		switch (this)
		{
			case AFK :
				return "AFK";
			case A_CONSTRUIRE :
				return "Choisir case de construction";
			case EN_COURS_DE_DEPLACEMENT :
				return "Choisir la destination";
			case A_DEPLACER:
				return "Choisir le pion";
			case DEUXIEME_PLACEMENT :
				return "Placer 2nd pion";
			case PREMIER_PLACEMENT :
				return "Placer 1er pion";
		}
		return "";
	}
}