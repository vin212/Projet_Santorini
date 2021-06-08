package controleur;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import interfaceUser.*;
import modele.*;
import global.*;

public class EcouteurDeClavier  extends KeyAdapter{
   
    Integer [] toucheAppuier;
    Fenetres f;
    Jeu j;

    PlateauInterface_2 aire2;
    ActionUser action;

    Configuration prop;

    Integer [] toucheRetour;
    Integer [] toucheRetablir;
   	Integer [] toucheSave;

    public EcouteurDeClavier (Integer [] toucheAppuier, Fenetres f, Jeu j, PlateauInterface_2 aire, Configuration prop, ActionUser action)
   	{
        this.toucheAppuier = toucheAppuier;
        this.f =f;
        this.j = j;

        this.aire2 = aire;
        this.action  = action ;

        toucheRetour = new Integer[2];
        toucheRetablir = new Integer[2];
        toucheSave = new Integer[2];

        String [] buff = prop.recupValeur("raccourci_retour").split(" ");
        toucheRetour[0] = Integer.parseInt(buff[0]);
        toucheRetour[1] = Integer.parseInt(buff[1]);

        buff = prop.recupValeur("raccourci_retablir").split(" ");
        toucheRetablir[0] = Integer.parseInt(buff[0]);
        toucheRetablir[1] = Integer.parseInt(buff[1]);

        buff = prop.recupValeur("raccourci_save").split(" ");
        toucheSave[0] = Integer.parseInt(buff[0]);
        toucheSave[1] = Integer.parseInt(buff[1]);

        this.prop = prop;

   	}
   	
    //@Override
    public void keyPressed(KeyEvent event){
        int source = event.getKeyCode();
        if(source==toucheRetablir[0] && toucheAppuier[0] == -1)
        {
            toucheAppuier[0] = toucheRetablir[0];
        }
        else if(source==toucheRetablir[1] && toucheAppuier[1] == -1)
        {
            toucheAppuier[1] = toucheRetablir[1];
        }
        else if (source==toucheRetour[1] && toucheAppuier[1] == -1)
        {
            toucheAppuier[1] = toucheRetour[1];
        }
         else if (source==toucheRetour[0] && toucheAppuier[0] == -1)
        {
            toucheAppuier[0] = toucheRetour[0];
        }
        else if (source == toucheSave[0] && toucheAppuier[0] == -1)
        {
            toucheAppuier[0] = source;
        }
        else if (source == toucheSave[1] && toucheAppuier[1] == -1)
        {
            toucheAppuier[1] = source;
        }
        else if(source == Integer.parseInt(prop.recupValeur("raccourci_pause")))
        {
            if (f.getNomFenetres() == NomFenetres.MENU_PAUSE)
            {
                f.ChangerFenetres (NomFenetres.JEU);
                f.gestionFenetre ();
            }
            else
            {
                f.ChangerFenetres (NomFenetres.MENU_PAUSE);
                f.gestionFenetre ();
            }
        }
        else if (source == 89)
        {
            if(toucheAppuier[1] == -1)
            {
                toucheAppuier[1] = 89;
            }
        }
        else if(source==KeyEvent.VK_LEFT)
        {
        }
        else
        {
            toucheAppuier[0] = -1;
            toucheAppuier[1] = -1;
        }

        if (toucheAppuier[0] == toucheRetour[0] && toucheAppuier[1] == toucheRetour[1])
        {
            try
            {
                if (f.ia1 != null && f.ia2 != null && (f.ia1.estActive() || f.ia2.estActive()) )
                    {
                        f.g.iaJoue = true;
                        action.annulerCoup();
                        f.ia1.desactiverIA();
                        f.ia2.desactiverIA();
                    }
                    else if (f.ia1 != null && f.ia1.estActive() && f.ia2 == null)
                    {
                        f.g.iaJoue = true;
                        action.annulerCoup();
                        f.ia1.desactiverIA();
                    }
                     else if (f.ia2 != null && f.ia2.estActive() && f.ia1 == null)
                    {
                        f.g.iaJoue = true;
                        action.annulerCoup();
                        f.ia2.desactiverIA();
                    }
                    else
                    {
                        action.annulerCoup();
                    }
                    
                    
            }
            catch (IndexOutOfBoundsException except)
            {
                prop.envoyerLogger("Impossible d'annuler",TypeLogger.WARNING);
            }
            aire2.repaint();
        }
        else if (toucheAppuier[0] == toucheRetablir[0] && toucheAppuier[1] == toucheRetablir[1])
        {
            try
            {
                action.retablirCoup();
            }
            catch (IndexOutOfBoundsException except)
            {
               prop.envoyerLogger("Impossible de retablir",TypeLogger.WARNING);
            }
            aire2.repaint();
        }
        else if (toucheAppuier[0] == toucheSave[0] && toucheAppuier[1] == toucheSave[1])
        {
            f.ChangerFenetres(NomFenetres.POPUP_SAUVEGARDE);
			f.gestionFenetre ();
			f.frame.repaint();
        }
        
    }
           
    //@Override   
    public void keyReleased(KeyEvent event){
        int source = event.getKeyCode();

        if (source == toucheAppuier[0])
        {
            toucheAppuier[0] = -1;
        }
        else if (source == toucheAppuier[1])
        {   
            toucheAppuier[1] = -1;
        }
        /*if (source == toucheRetour[0] && toucheAppuier[0] == toucheRetour[0] )
        {
            toucheAppuier[0] = -1;
        }
        else if (source == toucheRetour[1]  && toucheAppuier[1] == toucheRetour[1] )
        {
            toucheAppuier[1] = -1;
        }
        else if (source == toucheRetablir[1] && toucheAppuier[1] == toucheRetablir[1])
        {
            toucheAppuier[1] = -1;
        }
        else if (source == toucheRetablir[0] && toucheAppuier[0] == toucheRetablir[0])
        {
            toucheAppuier[0] = -1;
        }
        else if (source == toucheSave[0] && toucheAppuier[0] == toucheSave[0])
        {
            toucheAppuier[0] = -1;
        }
        else if (source == 86 && toucheAppuier[1] == 86)
        {
            toucheAppuier[1] = -1;
        }*/
    }

    //@Override
    public void keyTyped(KeyEvent event){ }
}


