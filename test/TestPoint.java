package test;

import java.io.*;
import modele.*;
import structure.*;

public class TestPoint {

	public static void testPointNorm() {

		System.out.println("--Point normal--");
		try {
			Point pointNorm = new Point(2, 3);
			System.out.println(2==pointNorm.getx());
			System.out.println(3==pointNorm.gety());
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
			System.out.println(4==pointModif.getx());
			System.out.println(5==pointModif.gety());
		}
		catch (Throwable e) {
			System.out.println("Erreur : modification des points");
			System.out.println(e);
		}

	}

	public static void testPointHautesCoordonnees() {

		System.out.println("--Point à hautes coordonnées--");
		try {
			Point pointHaut = new Point(5000000, 5000000);
			System.out.println(5000000==pointHaut.getx());
			System.out.println(5000000==pointHaut.gety());
		}
		catch (Throwable e) {
			System.out.println("Erreur : pointHaut");
			System.out.println(e);
		}

	}

	public static void testPointNegatif() {

		System.out.println("--Point négatif--");
		try {
			Point pointNeg = new Point(-1, -1);
			System.out.println(-1==pointNeg.getx());
			System.out.println(-1==pointNeg.gety());
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
			System.out.println(pointNeg.CompareTo(pointNeg)==0);
			System.out.println(pointNeg.CompareTo(pointNorm)==-1);
			System.out.println(pointNorm.CompareTo(pointNeg)==1);

		}
		catch (Throwable e) {
			System.out.println("Erreur : CompareTo");
			System.out.println(e);
		}

	}

	public static void testPointToString() {

		System.out.println("--Point en string--");
		try {
			Point pointNorm = new Point(2, 3);
			Point pointHaut = new Point(5000000, 5000000);
			Point pointNeg = new Point(-1, -1);
			System.out.println(pointNorm.toString());
			System.out.println(pointHaut.toString());
			System.out.println(pointNeg.toString());
		}
		catch (Throwable e) {
			System.out.println("Erreur : toString");
			System.out.println(e);
		}
	}

}