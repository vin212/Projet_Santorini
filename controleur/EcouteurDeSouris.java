package controleur;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

import modele.*;
import controleurIA.*;
import save.*;

import interfaceUser.*;
import global.*;

public class EcouteurDeSouris extends MouseAdapter {

	PlateauInterface_2 plateau;
	GestionUser g;
    IA ia1;
    IA ia2;

    NomFenetres name;
    String nomFichier;
    String nomProp;

    Fenetres f;

    Jeu j;

    Configuration prop;

    int nb;

    JFrame frame;

    ChangerToucheClavier clv;

    public EcouteurDeSouris(PlateauInterface_2 plateau, GestionUser g, IA ia1, IA ia2)
	{
		this.plateau = plateau;
		this.g = g;
        this.ia1 = ia1;
        this.ia2 = ia2;
        this.name = NomFenetres.JEU;
	}

	public EcouteurDeSouris(String nomFichier, Jeu j, Fenetres f)
	{
		this.name = NomFenetres.CHARGER;
		this.nomFichier = nomFichier;
		this.j = j;
		this.f = f;
		
	}

	public EcouteurDeSouris (Configuration prop, String nomProp, int nb, JFrame frame, Fenetres f)
	{
		this.name = NomFenetres.OPTION;
		this.prop = prop;
		this.nomProp = nomProp;
		this.nb = nb;
		this.frame = frame;
		this.f = f;
	}


	@Override
	public void mousePressed(MouseEvent e) 
	{
		if (name == NomFenetres.JEU && ia2 != null && !ia2.estActive() && g.iaJoue && ia1 != null && !ia1.estActive())
        {
            ia1.activeIA();
            ia2.activeIA();
        }
        else if (name == NomFenetres.JEU && ia1 != null && !ia1.estActive() && g.iaJoue )
        {
            ia1.activeIA();
        }
        else if (name == NomFenetres.JEU && ia2 != null && !ia2.estActive() && g.iaJoue )
        {
            ia2.activeIA();
        }

		else if (name == NomFenetres.JEU && !g.iaJoue)
		{
			plateau.FaireActionUser (e.getX(),e.getY());
		}
		else if (name == NomFenetres.CHARGER)
		{
			//j.Construire(new Point(0,0));
			Save save = new Save(f.j);
			f.j = save.chargerSauvegarde(nomFichier);
			if (f.j != null)
			{
				f.ChangerFenetres(NomFenetres.JEU);
				f.gestionFenetre ();
			}
		}
		else if (name == NomFenetres.OPTION)
		{
			prop.envoyerLogger("La propriété : " + nomProp + " a été changer",TypeLogger.WARNING);
			if (clv != null)
			{
				frame.removeKeyListener(clv);
			}
			frame.addKeyListener(new ChangerToucheClavier(prop,nomProp,frame,nb,f));

		}
	}
}