package test;

import java.io.*;
import modele.*;
import structure.*;

public class TestPoint {

	public static void testPointNorm() {

		System.out.println("--Point normal--");
		try {
			Point pointNorm = new Point(2, 3);
			System.out.println("Test de la coordonnée x (2=true) : " + (2==pointNorm.getx()));
			System.out.println("Test de la coordonnée y (3=true) : " + (3==pointNorm.gety()));
		}
		catch (Throwable e) {
			System.out.println("Erreur : pointNorm");
			System.out.println(e);
		}
	}

	public static void testModifPoint() {

		System.out.println("--Modification du point--");
		try {
			Point pointModif = new Point(2, 3);
			pointModif.modifValeur(4, 5);
			System.out.println("Test de la modification du x (4=true) : " + (4==pointModif.getx()));
			System.out.println("Test de la modification du y (5=true) : " + (5==pointModif.gety()));
		}
		catch (Throwable e) {
			System.out.println("Erreur : modification des points");
			System.out.println(e);
		}

	}

	public static void testPointHautesCoordonnees() {

		System.out.println("--Point à hautes coordonnées--");
		try {
			Point pointHaut = new Point(5000000, 6000000);
			System.out.println("Test d'un point de haute coordonnée x (5000000=true) : " + (5000000==pointHaut.getx()));
			System.out.println("Test d'un point de haute coordonnée y (6000000=true) : " + (6000000==pointHaut.gety()));
		}
		catch (Throwable e) {
			System.out.println("Erreur : pointHaut");
			System.out.println(e);
		}

	}

	public static void testPointNegatif() {

		System.out.println("--Point négatif--");
		try {
			Point pointNeg = new Point(-1, -2);
			System.out.println("Test d'un point à coordonnée x négative (-1=true) : " + (-1==pointNeg.getx()));
			System.out.println("Test d'un point à coordonnée y négative (-2=true)" + (-2==pointNeg.gety()));
		}
		catch (Throwable e) {
			System.out.println("Erreur : pointNeg");
			System.out.println(e);
		}

	}

	public static void testComparaisonPoints() {

		System.out.println("--Comparer les points--");
		try {
			Point pointNorm = new Point(2, 3);
			Point pointNeg = new Point(-1, -1);
			System.out.println("Test de comparaison : point négatif et point négatif (identiques=true) : " + (pointNeg.compareTo(pointNeg)==0));
			System.out.println("Test de comparaison : point négatif et point normal (différents=true) : " + (pointNeg.compareTo(pointNorm)==-1));
			System.out.println("Test de comparaison : point négatif et point normal (différents=true) : " + (pointNorm.compareTo(pointNeg)==1));

		}
		catch (Throwable e) {
			System.out.println("Erreur : compareTo");
			System.out.println(e);
		}

	}

	public static void testPointToString() {

		System.out.println("--Point en string--");
		try {
			Point pointNorm = new Point(2, 3);
			Point pointHaut = new Point(5000000, 6000000);
			Point pointNeg = new Point(-1, -2);
			System.out.println("Test d'écriture du point normal : " + (pointNorm.toString()));
			System.out.println("Test d'écriture du point à hautes coordonnées : " + (pointHaut.toString()));
			System.out.println("Test d'écriture du point négatif : " + (pointNeg.toString()));
		}
		catch (Throwable e) {
			System.out.println("Erreur : toString");
			System.out.println(e);
		}
	}

}