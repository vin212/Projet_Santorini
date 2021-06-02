package global;

import java.io.*;
import java.util.Properties;
import java.util.Iterator;
import java.util.ArrayList;

public class Configuration
{
	Properties prop;
	String defaut;
	String user;

	public Configuration ()
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

		InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(nom);
		System.out.println(in);

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

	public void changerValeur (String nom, String nouvelleValeur)
	{
		if(prop.getProperty("fix-"+nom) != null)
		{
			nom = "fix-"+nom;
		}
		else if(prop.getProperty("modifiable-"+nom) != null)
		{
			nom = "modifiable-"+nom;
		}

		try {
			FileOutputStream out = new FileOutputStream (user);
			prop.setProperty(nom,nouvelleValeur);
			prop.store(out,null);
		}
		catch (Exception expt)
		{
			System.out.println("erreur");
		}
	}

	public ArrayList<String> recupClesModifiable ()
	{
		ArrayList <String> clefs = new ArrayList <String> (0);
		Iterator<Object> it = prop.keySet().iterator();
		while (it.hasNext()) 
		{
			String key = (String) it.next();
			String [] keyDecoup = key.split("-");
			if (keyDecoup[0].equals("modifiable"))
			{
				clefs.add(keyDecoup[1]);
				//System.out.println("OK");
			}
			//System.out.println("***" + keyDecoup[0] + "***");
		} 

		return clefs;
	}

}