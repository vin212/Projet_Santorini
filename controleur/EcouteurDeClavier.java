package controleur;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class EcouteurDeClavier  extends KeyAdapter{
   
   	public EcouteurDeClavier ()
   	{

   	}
   	
    //@Override
    public void keyPressed(KeyEvent event){
    	System.out.println("ici clqiue");
        int source = event.getKeyCode();
        //Test testActu = Test.this;
        if(source==KeyEvent.VK_UP)
            System.out.println("Haut");
        else if(source==KeyEvent.VK_DOWN)
            System.out.println("Bas");
        else if(source==KeyEvent.VK_RIGHT)
            System.out.println("Droite");
        else if(source==KeyEvent.VK_LEFT)
            System.out.println("Gauche");
    }
           
    //@Override   
    public void keyReleased(KeyEvent event){
    	System.out.println("ici relacher");
    }

    //@Override
    public void keyTyped(KeyEvent event){
    	System.out.println("ici");
    }
}


