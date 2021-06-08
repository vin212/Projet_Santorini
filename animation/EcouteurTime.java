package animation;

import java.awt.event.*;
import javax.swing.*;

import interfaceUser.*;

public class EcouteurTime extends AbstractAction{

	Animation anim;
	PlateauInterface_2 aire;

	public EcouteurTime (Animation  anim,PlateauInterface_2 aire)
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
		if (anim  == null)
		{		}
		else if (anim.getType() == TypeAnimation.AVANCER)
		{
			anim.Avancer();
			aire.repaint();
		}
		else if (anim.getType() == TypeAnimation.CLIGNOTER)
		{
			anim.changerEtat();
			aire.repaint();
		}
	}
}