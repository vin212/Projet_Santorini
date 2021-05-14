package interfaceUser;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class Fenetre implements Runnable {

	JFrame frame;
	Fenetres f;

	public Fenetre ()
	{
		//this.g = g;
	}

	public void run()
	{

		f = new Fenetres();
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