package controleur;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import interfaceUser.*;
import modele.*;

public class EcouteurDeClavier  extends KeyAdapter{
   
    Integer [] toucheAppuier;
    Fenetres f;
    Jeu j;

    PlateauInterface_2 aire2;
    ActionUser action;

   	public EcouteurDeClavier (Integer [] toucheAppuier, Fenetres f, Jeu j, PlateauInterface_2 aire)
   	{
        this.toucheAppuier = toucheAppuier;
        this.f =f;
        this.j = j;

        this.aire2 = aire;
        this.action  = new ActionUser (j) ;
        /*this.toucheAppuier[0] = (Integer)(1);
        this.toucheAppuier[1] = 1;*/
   	}
   	
    //@Override
    public void keyPressed(KeyEvent event){
    	System.out.println("ici clqiue");
        int source = event.getKeyCode();
        //Test testActu = Test.this;
        if(source==17)
        {
            System.out.println("ctrl");
            if (toucheAppuier[0] == -1)
            {
                toucheAppuier[0] = 17;
            }
        }
        else if(source==86)
        {
            System.out.println("v");
            if (toucheAppuier[1] == -1)
            {
                toucheAppuier[1] = 86;
            }
        }
        else if (source == 90)
        {
            System.out.println("z");
            if (toucheAppuier [1] == -1)
            {
                toucheAppuier[1] = 90;
            }   
        }
        else if(source==27)
        {
            System.out.println("echp");
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
            System.out.println("y");
            if(toucheAppuier[1] == -1)
            {
                toucheAppuier[1] = 89;
            }
        }
        else if(source==KeyEvent.VK_LEFT)
            System.out.println("Gauche");
        else
        {
            toucheAppuier[0] = -1;
            toucheAppuier[1] = -1;
        }

        System.out.println(toucheAppuier[0] + "," + toucheAppuier[1] );
    
        if (toucheAppuier[0] == 17  && toucheAppuier[1] == 86)
        {
            System.out.println("les deux sont appuier");
        }
        else if (toucheAppuier[0] == 17 && toucheAppuier[1] == 90)
        {
            try
            {
                if (f.ia1 != null && f.ia2 != null && (f.ia1.estActive() || f.ia2.estActive()) )
                    {
                        System.out.println("je passe ici");
                        f.g.iaJoue = true;
                        action.annulerCoup();
                        f.ia1.desactiverIA();
                        f.ia2.desactiverIA();
                        System.out.println(f.g.iaJoue);
                    }
                    else if (f.ia1 != null && f.ia1.estActive() && f.ia2 == null)
                    {
                        System.out.println("je passe ici");
                        f.g.iaJoue = true;
                        action.annulerCoup();
                        f.ia1.desactiverIA();
                        System.out.println(f.g.iaJoue);
                    }
                     else if (f.ia2 != null && f.ia2.estActive() && f.ia1 == null)
                    {
                        System.out.println("je passe ici");
                        f.g.iaJoue = true;
                        action.annulerCoup();
                        f.ia2.desactiverIA();
                        System.out.println(f.g.iaJoue);
                    }
                    else
                    {
                        action.annulerCoup();
                    }
                    
                    
            }
            catch (IndexOutOfBoundsException except)
            {
                System.err.println("Impossible d'annuler");
            }
            aire2.repaint();
        }
        else if (toucheAppuier[0] == 17 && toucheAppuier[1] == 89)
        {
            try
            {
                action.retablirCoup();
            }
            catch (IndexOutOfBoundsException except)
            {
                System.err.println("Impossible de rétablir");
            }
            aire2.repaint();
        }
        
    }
           
    //@Override   
    public void keyReleased(KeyEvent event){
    	System.out.println("ici relacher");
        int source = event.getKeyCode();
        if (source == 17 && toucheAppuier[0] == 17)
        {
            System.out.println("touche : ctr relacher");
            toucheAppuier[0] = -1;
        }
        else if (source == 86 && toucheAppuier [1] == 86)
        {
            System.out.println("touche : v relacher");
            toucheAppuier[1] = -1;
        }
        else if (source == 89 && toucheAppuier[1] == 89)
        {
            System.out.println("touche : y relacher");
            toucheAppuier[1] = -1;
        }
        else if (source == 90 && toucheAppuier[1] == 90)
        {
            System.out.println("touche : z relacher");
            toucheAppuier[1] = -1;
        }
    }

    //@Override
    public void keyTyped(KeyEvent event){
    	System.out.println("ici");
    }
}


