package test;

import java.io.*;
import modele.*;
import structure.*;


public class TestPlateau {

	public static void testInitialisation() {

		System.out.println("--Initialisation--");

		try {
			Plateau plateauNorm = new Plateau(5, 6);
			System.out.println(5==plateauNorm.getHauteur());
			System.out.println(6==plateauNorm.getLargeur());
			for (int i = 0; i < plateauNorm.getLargeur(); i++) {
				for (int j = 0; j < plateauNorm.getHauteur(); j++) {
					if (plateauNorm.cases[i][j]==null) {
						System.out.println("Erreur : Case non initialisée : ("+ i + ", "+ j + ")");
					}
				}
			}
		}
		catch (Throwable e) {
			System.out.println("Erreur : Initialisation plateau");
			System.out.println(e);
		}

	}



}