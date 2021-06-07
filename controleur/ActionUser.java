package controleur;

import modele.*;
import structure.*;

import global.*;

public class ActionUser {

	Jeu j;
	Point posi_init;
	public Coup coupJouer;
	Configuration prop;


	public ActionUser (Jeu j, Configuration prop)
	{
		this.j = j;
		this.posi_init = new Point(-1,-1);
		this.coupJouer = new Coup();
		this.prop = prop;
	}

	public void initActionUser(Jeu j, Configuration prop)
	{
		this.j = j;
		this.posi_init = new Point(-1,-1);
		this.coupJouer = new Coup();
		this.prop = prop;
	}

	public Point recupPosiPerso ()
	{
		return posi_init;
	}

	public void jouerAction (Point posi_final)
	{
		Action a = j.getAction (j.getJoueurEnJeu());
		System.out.println(a);

		if (!j.estGagnant()){
			switch (a)
			{
				case A_DEPLACER :
					selectionnerPerso(posi_final);
				break;
				case EN_COURS_DE_DEPLACEMENT :
                    avancerPerso (posi_final,true,false);
				break;
				case A_CONSTRUIRE :
					construireIci (posi_final,true);
					
				break;
				case PREMIER_PLACEMENT :
					placerPerso (posi_final,a,true);
				break;
				case DEUXIEME_PLACEMENT :
					placerPerso (posi_final,a,true);
				break;
				case AFK :
					//System.out.println("afk");
				break;
				default:
					prop.envoyerLogger("action invalide",TypeLogger.WARNING);;
			}
		}
	}

	public void selectionnerPerso(Point posi_final){
		int perso = j.quiEstIci (posi_final);
		if (perso == j.getJoueurEnJeu()) {
			this.posi_init = posi_final;
			j.setAction (j.getJoueurEnJeu(),Action.EN_COURS_DE_DEPLACEMENT);
			prop.envoyerLogger("Joueur " + j.getJoueurEnJeu() + " a selectionner " + posi_init ,TypeLogger.SEVERE);
		}
	}

    public void avancerPerso (Point posi_final, boolean histoActiv, boolean cheat){
		int perso;
		perso = j.quiEstIci (posi_final);
		if (perso != j.getJoueurEnJeu()){
			if (Math.abs(posi_final.getx() - this.posi_init.getx()) + Math.abs(posi_final.gety() - this.posi_init.gety()) == 1 ) {
                if ((j.peutPoserUnPerso (posi_final) && j.getNbEtage (posi_final) - j.getNbEtage (posi_init) <= 1) ||( cheat == true ))
				{
					deplacer(posi_final,histoActiv);
				}
				else
				{
					prop.envoyerLogger("Coup INVALIDE deplacer!!! " + posi_final + posi_init,TypeLogger.WARNING);
				}
				
			}
			else if ((Math.abs(posi_final.getx() - this.posi_init.getx()) == 1 && Math.abs(posi_final.gety() - this.posi_init.gety()) == 1)  )
			{
				if ((j.peutPoserUnPerso (posi_final) && j.getNbEtage (posi_final) - j.getNbEtage (posi_init) <= 1 )|| (cheat == true))
				{
					deplacer(posi_final,histoActiv);
				}
				else
				{
					prop.envoyerLogger("Coup INVALIDE deplacer!!! " + posi_final + posi_init,TypeLogger.WARNING);
				}
			}
			else if (perso == j.getJoueurEnJeu())
			{
				this.posi_init = posi_final;
			}
			else
			{
				prop.envoyerLogger("Coup INVALIDE autre!!! " + posi_final + posi_init,TypeLogger.WARNING);
			}
		}
        else if (perso == j.getJoueurEnJeu())
        {
			this.posi_init = posi_final;
        }
	}

	public void construireIci (Point posi_final, boolean histoActiv)
	{
		if (Math.abs(posi_final.getx() - this.posi_init.getx()) + Math.abs(posi_final.gety() - this.posi_init.gety()) == 1 )
		{
			if (j.Constructible (posi_final))
			{
				construire(posi_final,histoActiv);
			}
			else
			{
				prop.envoyerLogger("Coup INVALIDE !!! " + posi_final + posi_init,TypeLogger.WARNING);
			}
		}
		else if (Math.abs(posi_final.getx() - this.posi_init.getx()) == 1 && Math.abs(posi_final.gety() - this.posi_init.gety()) == 1 )
		{
			construire(posi_final,histoActiv);
		}
		else
		{
			prop.envoyerLogger("Coup INVALIDE !!! " + posi_final + posi_init,TypeLogger.WARNING);
		}
	}

	public void placerPerso (Point posi_final,Action a, boolean histoActiv)
	{
		if (j.peutPoserUnPerso (posi_final) && j.getNbEtage (posi_final) - j.getNbEtage (posi_init) <= 1  )
		{
			j.poserPersonnage (posi_final, j.getJoueurEnJeu ());
			if (a == Action.PREMIER_PLACEMENT)
			{
				j.setAction (j.getJoueurEnJeu(),Action.DEUXIEME_PLACEMENT);
				if (histoActiv)
				{
					coupJouer = new Coup(posi_final,j.getJoueurEnJeu());
					j.histoAjouterCoup(coupJouer);
					coupJouer = new Coup();
				}
			}
			else
			{
				j.setAction (j.getJoueurEnJeu(),Action.AFK);
				if (histoActiv)
				{
					coupJouer = new Coup(posi_final,j.getJoueurEnJeu());
					j.histoAjouterCoup(coupJouer);
					coupJouer = new Coup();
				}
			}
			this.posi_init = posi_final;				
		}	
	}

	public void annulerCoup(){
		
		Action action = j.getAction(j.getJoueurEnJeu()) ;
		if (action != Action.AFK && action != Action.A_CONSTRUIRE )
		{
			annuler();
		}
		else if (j.estGagnant() && action == Action.A_CONSTRUIRE )
		{
			coupJouer.setConstruction (new Point(-1,-1));
			j.histoAjouterCoup(coupJouer);
			coupJouer = new Coup();
			annuler();
		}

	}

	public void annuler()
	{
		int pos;
		Coup c;
		try{
				c = j.histoAnnulerCoup();
			} catch(IndexOutOfBoundsException e){
				prop.envoyerLogger("Plus de coup a annuler",TypeLogger.WARNING);
				return;
			}
			int nbJ2 = c.getJoueur() %2 + 1;
		
			if(c.estDeplacement()){

				this.posi_init = c.getArrive();
				j.setAction(c.getJoueur(), Action.EN_COURS_DE_DEPLACEMENT);
				avancerPerso(c.getDepart(),false,true);
				j.detruireEtage(c.getConstruction());
				
				j.setAction(c.getJoueur(), Action.A_DEPLACER);
				j.setAction(nbJ2, Action.AFK);
                                j.subTour();
			}else{
				pos = j.histoPosition();
				if(pos % 2 == 0){
					j.enleverPerso(c.getDepart());
					j.setAction(c.getJoueur(), Action.PREMIER_PLACEMENT);
					j.setAction(nbJ2, Action.AFK);
					//j.subTour();
				}
				else{
					j.enleverPerso(c.getDepart());
					j.setAction(c.getJoueur(), Action.DEUXIEME_PLACEMENT);
					j.setAction(nbJ2, Action.AFK);
					j.subTour();
				
				}
			}
	}

	public void retablirCoup(){
		Coup c;
		int numJoueur;
		if (j.getAction(j.getJoueurEnJeu()) == Action.A_DEPLACER || j.getAction(j.getJoueurEnJeu()) == Action.DEUXIEME_PLACEMENT  )
		{
			try
			{
				c = j.histoRetablir();
			}
			catch(IndexOutOfBoundsException e)
			{
				prop.envoyerLogger("Plus de coup à rétablir.",TypeLogger.WARNING);

				return;
			}
			if (c.estDeplacement())
			{
				selectionnerPerso(c.getDepart());
				avancerPerso(c.getArrive(),false,false);
				construireIci(c.getConstruction(),false);
				if (j.getTour() > 1)
				{
					j.addTour();
					numJoueur = j.getJoueurEnJeu();
					j.setAction(numJoueur,Action.A_DEPLACER);
					j.setAction(numJoueur %2 + 1, Action.AFK);
				}
				else if (j.getAction(j.getJoueurEnJeu()) == Action.PREMIER_PLACEMENT)
				{
					numJoueur = j.getJoueurEnJeu();
					j.setAction(numJoueur,Action.DEUXIEME_PLACEMENT);
				}
				else if (j.getAction(j.getJoueurEnJeu()) == Action.DEUXIEME_PLACEMENT)
				{
					numJoueur = j.getJoueurEnJeu();
					j.setAction(numJoueur,Action.AFK);
					j.setAction(numJoueur%2 + 1, Action.PREMIER_PLACEMENT);
					j.addTour();
				}

			} else{
				placerPerso(c.getDepart(), j.getAction(j.getJoueurEnJeu()),false);
			}
		}
	}

	public void deplacer (Point posi_final, boolean histoActiv)
	{
		if (histoActiv)
		{
			coupJouer.setJoueur(j.getJoueurEnJeu());
			coupJouer.setDeplacement(posi_init,posi_final);
		}

		j.deplacerPersonnage (posi_init,posi_final);
		j.setAction (j.getJoueurEnJeu(),Action.A_CONSTRUIRE);
		this.posi_init = posi_final;
		prop.envoyerLogger("Joueur " + j.getJoueurEnJeu() + " a avancer sur la case " + posi_final ,TypeLogger.SEVERE);
	}

	public void construire (Point posi_final, boolean histoActiv)
	{
		if (j.Constructible (posi_final))
		{
			j.setAction (j.getJoueurEnJeu(),Action.AFK);
			j.Construire (posi_final);
			prop.envoyerLogger("le joueur : " + j.getJoueurEnJeu() + " a construit en " + posi_final,TypeLogger.SEVERE);
			if (histoActiv)
			{
				coupJouer.setConstruction (posi_final);
				j.histoAjouterCoup(coupJouer);
				prop.envoyerLogger(coupJouer.toString(),TypeLogger.INFO);
				coupJouer = new Coup();

			}
		}
		else
		{
			prop.envoyerLogger("Coup INVALIDE !!! " + posi_final + posi_init,TypeLogger.WARNING);
		}
	}


}