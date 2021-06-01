package animation;

import interfaceUser.*;
import structure.*;
import global.*;

import java.lang.Math;
import javax.swing.*;

public class Animation {

	public Point posiInit;
	Point posiActuel;
	Point posiFinal;

	Point direction;

	PlateauInterface_2 aire;
	int vitesse;
	Timer t;

	boolean fin;

	Configuration prop;

	public Animation(Point posiInit,Point posiFinal,PlateauInterface_2 aire, Configuration prop)
	{
		this.posiInit = posiInit;
		this.posiFinal = posiFinal;
		this.posiActuel = posiInit;

		this.vitesse = Integer.parseInt(prop.recupValeur("vitesse_animation"));

		this.aire = aire;

		calculDirection ();
		fin = false;


	}

	public void defTime(Timer t)
	{
		this.t = t;
	}

	public Point getPointInit ()
	{
		return this.posiInit;
	}

	public Point getPointFinal ()
	{
		return this.posiFinal;
	}

	public Point getPointActuel ()
	{
		return this.posiActuel;
	}

	public boolean estFini ()
	{
		return fin;
	}

	public void calculDirection ()
	{
		double angle;
		angle = Math.atan((double)(posiFinal.getx() - posiInit.getx())/(double)(posiFinal.gety() - posiInit.gety()));
		

		int y = (int)(Math.cos(angle) * vitesse);
		int x = (int)(Math.sin(angle) * vitesse);

		if (posiFinal.getx() < posiInit.getx() && x > 0)
		{
			x = -x;
		}
		if (posiFinal.gety()  < posiInit.gety()  && y > 0)
		{
			y = -y;
		}
		if (posiFinal.gety() > posiInit.gety() && y < 0)
		{
			y = -y;
		}
		if (posiFinal.getx() > posiInit.getx() && x < 0)
		{
			x = -x;
		}

		this.direction = new Point(x,y);
        //System.out.println("avancer x :" + this.direction.getx() + "avancer y  " + this.direction.gety());
	}



	void Avancer()
	{
        int x; 
        int y;
		//System.out.println(this.posiActuel.getx()-this.posiFinal.getx());
		//System.out.println("direction " + this.direction);
		if ( Math.abs(this.posiActuel.getx() -this.posiFinal.getx()) > Math.abs(this.direction.getx())+10 || Math.abs(this.posiActuel.gety() -this.posiFinal.gety()) > Math.abs(this.direction.gety())+10)
		{
			x  = this.posiActuel.getx() + this.direction.getx();
			y = this.posiActuel.gety() + this.direction.gety();
			this.posiActuel.modifValeur (x,y);
			fin = false;
		}
		else
		{
			x = this.posiFinal.getx();
			y = this.posiFinal.gety();
			this.posiActuel.modifValeur (x,y);
			this.t.stop();
			//System.out.println("fin avancer \n");
			fin = true;
		}

	}

}