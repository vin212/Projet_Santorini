package controleur;


import java.awt.event.*;
import javax.swing.*;

import global.*;
import modele.*;

public class EcouteurCheckBox extends AbstractAction {

	String nomProp;
	Configuration prop;
	Jeu j;

	public EcouteurCheckBox(String nomProp, Configuration prop, Jeu j)
	{
		this.nomProp = nomProp;
		this.prop = prop;
		this.j = j;
	}
    public void actionPerformed(ActionEvent e) {
    	JCheckBox cb = (JCheckBox) e.getSource();
    	if (cb.isSelected())
    	{
    		System.out.println("aide : " + true);
    		prop.changerValeur(nomProp,"true",0);
    	}
    	else
    	{
    		System.out.println("aide : " + false);
    		prop.changerValeur(nomProp,"false",0);
    	}
    	j.reactuProp();
        System.out.println(nomProp);
    }
}