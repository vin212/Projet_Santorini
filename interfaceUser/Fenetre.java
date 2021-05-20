package interfaceUser;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

import modele.*;

public class Fenetre implements Runnable {

	JFrame frame;
	Fenetres f;
	Jeu j;

	public Fenetre (Jeu j)
	{
		this.j = j;
	}

	public void run()
	{

		f = new Fenetres(j);
		f.gestionFenetre ();

		try 
		{
			TimeUnit.SECONDS.sleep(1);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}

		/*System.out.println("fin timer");

		f.ChangerFenetres (1);
		f.gestionFenetre ();*/


	}

}