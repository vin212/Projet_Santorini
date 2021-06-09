package controleur;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

import global.*;
import interfaceUser.*;

public class ChangerToucheClavier extends KeyAdapter{

	String nomConfigue;
	Configuration prop;
	JFrame frame;
	int nb;
	Fenetres f;


	public ChangerToucheClavier(Configuration prop, String nomConfigue, JFrame frame, int nb, Fenetres f)
	{
		this.prop = prop;
		this.nomConfigue = nomConfigue;
		this.frame = frame;
		this.nb = nb;
		this.f = f;
	}	

	 public void keyPressed(KeyEvent event){
	 	int source = event.getKeyCode();
	 	prop.changerValeur (nomConfigue,String.valueOf(source), nb);
	 	frame.removeKeyListener(this);
	 	f.gestionFenetre ();

	 }

}