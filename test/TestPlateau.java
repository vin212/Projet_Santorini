package test;

import java.io.*;
import modele.*;
import structure.*;


public class TestPlateau {

	public static void testInitialisation() {

		System.out.println("--Initialisation--");

		try {
			Plateau plateauNorm = new Plateau(5, 6);
			System.out.println("Test de la hauteur (5=true) : " + (5==plateauNorm.getHauteur()));
			System.out.println("Test de la largeur (6=true) : " + (6==plateauNorm.getLargeur()));
		}
		catch (Throwable e) {
			System.out.println("Erreur : Initialisation plateau");
			System.out.println(e);
		}

	}



}