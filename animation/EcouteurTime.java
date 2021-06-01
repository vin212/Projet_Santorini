package animation;

import java.awt.event.*;
import javax.swing.*;

import interfaceUser.*;

public class EcouteurTime extends AbstractAction{

	Animation anim;
	PlateauInterface_2 aire;

	public EcouteurTime (Animation  anim,	PlateauInterface_2 aire)
	{
		if (anim == null)
		{
			throw new RuntimeException("a est null");
		}
		else
		{
			this.anim = anim;
			this.aire = aire;
		}
	}

	public void actionPerformed(ActionEvent e) {
		//System.out.println("je dois avancer \n");
		if (anim  == null)
		{
			throw new RuntimeException("a est null");
		}
		else
		{
			anim.Avancer();
			aire.repaint();
		}
	}
}