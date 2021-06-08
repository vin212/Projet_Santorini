package animation;

import interfaceUser.*;
import javax.swing.*;
//import java.awt.*;

import global.*;
//import structure.*;

public class AnimationListener{
	Timer t;
	public Animation anim;
	PlateauInterface_2 aire;
	EcouteurTime e;
	Configuration prop;

	public AnimationListener (PlateauInterface_2 aire, Configuration prop,structure. Point posiInit, structure.Point posiFinal)
	{
		this.anim = new Animation(posiInit,posiFinal,aire,prop);
		this.e = new EcouteurTime(anim,aire);
		this.t = new Timer(0,this.e);
		anim.defTime(this.t);
		
		
		this.t.start();
		this.aire = aire;
		aire.repaint();
	}

	public AnimationListener (PlateauInterface_2 aire, Configuration prop, structure.Point posi)
	{
		this.anim = new Animation(aire,prop, posi);
		this.e = new EcouteurTime(anim,aire);
		this.t = new Timer(0,this.e);
		anim.defTime(this.t);
		
		
		this.t.start();
		this.aire = aire;
		aire.repaint();
	}

	public structure.Point getPointInit ()
	{
		return anim.getPointInit();
	}

	public structure.Point getPointFinal ()
	{
		return anim.getPointFinal();
	}

	public structure.Point getPointActuel ()
	{
		return anim.getPointActuel();
	}

	public boolean estFini()
	{
		return anim.estFini();
	}

	public int getEtat ()
	{
		return anim.getEtat();
	}

	public structure.Point getPosi ()
	{
		return anim.getPosi();
	}

}