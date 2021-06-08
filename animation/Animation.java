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

	TypeAnimation type;

	Point posi;
	int etat;

	public Animation(Point posiInit,Point posiFinal,PlateauInterface_2 aire, Configuration prop)
	{
		this.posiInit = posiInit;
		this.posiFinal = posiFinal;
		this.posiActuel = posiInit;

		this.vitesse = Integer.parseInt(prop.recupValeur("vitesse_animation"));

		this.aire = aire;

		calculDirection ();
		fin = false;

		this.type = TypeAnimation.AVANCER;


	}

	public Animation(PlateauInterface_2 aire, Configuration prop, Point posi)
	{
		this.type = TypeAnimation.CLIGNOTER;
		this.etat = 0;
		this.posi = posi;
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

	public TypeAnimation getType ()
	{
		return type;
	}

	public int getEtat ()
	{
		return this.etat;
	}

	public Point getPosi ()
	{
		return this.posi;
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
	}



	void Avancer()
	{
        int x; 
        int y;
		int ecart_x = aire.getSize().width/50;
		int ecart_y = aire.getSize().height/50;

		if ( Math.abs(this.posiActuel.getx() -this.posiFinal.getx()) > Math.abs(this.direction.getx())+ecart_x || Math.abs(this.posiActuel.gety() -this.posiFinal.gety()) > Math.abs(this.direction.gety())+ecart_y)
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
			fin = true;
		}

	}

	public void changerEtat()
	{
		etat = (etat + 1) % 2;
	}

}