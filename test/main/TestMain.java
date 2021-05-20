package test.main;

import modele.TestPlateau;
import structure.PointTest;
import test.modele.TestCase;

public class TestMain {
    private static final PointTest pointTest = new PointTest();
    private static final TestCase testCase = new TestCase();
    private static final TestPlateau testPlateau = new TestPlateau();

    public static void main(String[] argc) {
        pointTest.testSameCoordinatevalues();
        pointTest.testModifValeur();
        pointTest.testToString();
        pointTest.testSameCoordinatevalues();
        pointTest.testGreaterXCoordinateOfInputPoint();
        pointTest.testGreaterYCoordinateOfCurrentPoint();
        pointTest.testGreaterYCoordinateOfInputPoint();
        pointTest.testLesserXCoordinateOfInputPoint();

        testCase.setup();
        testCase.testModificationEtage();
        testCase.testModificationPerso();

        // TODO
        //testPlateau.setup();
        //testPlateau.testInit();
        //testPlateau.testConstruire();
        //testPlateau.testDetruireEtage();
        //testPlateau.testConstructible();
        //testPlateau.testDeplacerPersonnage();
        //testPlateau.testPeutPoserUnPerson();
        //testPlateau.testApersonnage();
        //testPlateau.testAfficherPlateau_CMD();
    }
}
