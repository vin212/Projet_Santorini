import java.io.*;
import modele.*;
import structure.*;
import test.*;

public class mainTest {

	public static void main(String[] argc) {
		System.out.println("Test des points");
		TestPoint.testPointNorm();
		TestPoint.testModifPoint();
		TestPoint.testPointHautesCoordonnees();
		TestPoint.testPointNegatif();
		TestPoint.testComparaisonPoints();
		TestPoint.testPointToString();
		/*

		System.out.println("-------------------------------------");
		System.out.println("Test des coups");
		TestCoup.testCoup();

		*/
		System.out.println("-------------------------------------");
		System.out.println("Test des cases");
		TestCase.testInitialisation();
		TestCase.testModificationEtage();
		TestCase.testModificationPerso();

		System.out.println("-------------------------------------");
		System.out.println("Test du plateau");
		TestPlateau.testInitialisation();
		
	}

}