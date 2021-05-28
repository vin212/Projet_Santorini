package global;

import java.io.*;
import java.util.Properties;

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
		return prop.getProperty(nom);
	}

	public void changerValeur (String nom, String nouvelleValeur)
	{
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

}