package global;

import java.io.*;
import java.util.Properties;
import java.util.Iterator;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Configuration
{
	Properties prop;
	Logger log;

	String defaut;
	String user;

	public Configuration ()
	{
		creePropriete ();
		creeLogger ();
	}

	private void creePropriete ()
	{
		defaut = "ressource/defaut.cfg";
		user = "ressource/user.cfg";
		chargerProprietes(user);
		if (prop == null)
		{
			chargerProprietes(defaut);
		}
	}


	private void chargerProprietes(String nom) {

		FileInputStream in = null;
		try
		{
			in = new FileInputStream(nom);
		}
		catch (Exception exept)
		{

		}
		if (in != null)
		{

			this.prop = new Properties();
			try {
				prop.load(in);
			} catch (IOException e) {
				System.err.println("Impossible de charger " + nom);
				System.err.println(e.toString());
				System.exit(1);
			}
		}
	}

	public String recupValeur (String nom)
	{
		String retour;
		retour=prop.getProperty("fix-"+nom);
		if (retour==null)
		{	
			retour = prop.getProperty("modifiable-"+nom);
		}

		return retour;
	}

	public void changerValeur (String nom, String nouvelleValeur, int nb)
	{
		String element = prop.getProperty("fix-"+nom);
		String valeur;
		if(element != null)
		{
			nom = "fix-"+nom;
		}
		else
		{
			element = prop.getProperty("modifiable-"+nom);
			if (element != null)
			{
				nom = "modifiable-"+nom;
			}
		}

		if (element != null && element.split(" ").length >= 2)
		{
			if (nb == 0)
			{
				valeur = nouvelleValeur +" "+ element.split(" ")[1];
			}
			else
			{
				valeur = element.split(" ")[0] + " "+ nouvelleValeur;
			}
		}
		else
		{
			valeur = nouvelleValeur;
		}



		try {
			FileOutputStream out = new FileOutputStream (user);
			prop.setProperty(nom,valeur);
			prop.store(out,null);
		}
		catch (Exception expt)
		{
			System.out.println("erreur");
		}
	}

	public ArrayList recupClesModifiable ()
	{
		ArrayList <String> clefs = new ArrayList <String> (0);
		Iterator it = prop.keySet().iterator();
		while (it.hasNext()) 
		{
			String key = (String) it.next();
			String [] keyDecoup = key.split("-");
			if (keyDecoup[0].equals("modifiable"))
			{
				clefs.add(keyDecoup[1]);
			}
		} 
		return clefs;
	}

	public void retablirDefaut ()
	{
		InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(defaut);
		
		if (in != null)
		{
			this.prop = new Properties();
			try {
				prop.load(in);

			} catch (IOException e) {
				System.err.println("Impossible de charger " + defaut);
				System.err.println(e.toString());
				System.exit(1);
			}

			try{
				FileOutputStream out = new FileOutputStream(user);
				prop.store(out,null);
			}
			catch (Exception e) {
				System.err.println("Impossible de charger " + user);
				System.err.println(e.toString());
				System.exit(1);
			}
		}

	}

	public void creeLogger ()
	{
		if (this.log == null) 
		{
			System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s : %5$s%n");
			log = Logger.getLogger("Santorini.Logger");
			log.setLevel(Level.parse("ALL"));
		}
	}

	public void envoyerLogger (String valeur, TypeLogger type)
	{
		switch (type)
		{
			case INFO :
				log.info(valeur);
			break;
			case WARNING :
				log.warning(valeur);
			break;
			case SEVERE :
				log.severe(valeur);
			break; 
		}
	}

}