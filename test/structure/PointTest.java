package structure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PointTest {

    @Test
    public void testGetXCoordinate() {
        Point pointTest = new Point(1, 2);
        Assertions.assertEquals(1, pointTest.getx());
    }

    @Test
    public void testGetNegativeXCoordinate() {
        Point pointTest = new Point(-1, -2);
        Assertions.assertEquals(1, pointTest.getx());
    }

    @Test
    public void testGetYCoordinate() {
        Point pointTest = new Point(1, 2);
        Assertions.assertEquals(2, pointTest.gety());
    }

    @Test
    public void testGetNegativeYCoordinate() {
        Point pointTest = new Point(-1, -2);
        Assertions.assertEquals(-2, pointTest.gety());
    }

    @Test
    public void testSameCoordinatevalues() {
        Point comparePoint = new Point(1, 2);
        Assertions.assertEquals(0, new Point(1, 2).CompareTo(comparePoint));
    }

    @Test
    public void testModifValeur() {
        Point comparePoint = new Point(1, 2);

        comparePoint.modifValeur(3,4);
        Assertions.assertEquals(0, new Point(3, 4).CompareTo(comparePoint));
    }

    @Test
    public void testGreaterYCoordinateOfCurrentPoint() {
        Point comparePoint = new Point(1, 1);
        Assertions.assertEquals(1, new Point(1, 3).CompareTo(comparePoint));

    }

    @Test
    public void testGreaterYCoordinateOfInputPoint() {
        Point comparePoint = new Point(1, 3);
        Assertions.assertEquals(-1, new Point(1, 1).CompareTo(comparePoint));

    }

    @Test
    public void testLesserXCoordinateOfInputPoint() {
        Point comparePoint = new Point(1, 1);
        Assertions.assertEquals(1, new Point(2, 1).CompareTo(comparePoint));

    }

    @Test
    public void testGreaterXCoordinateOfInputPoint() {
        Point comparePoint = new Point(3, 2);
        Assertions.assertEquals(-1, new Point(1, 2).CompareTo(comparePoint));

    }

    @Test
    public void testToString() {
        String expected = ("( " + 1 + ", " + 2 + ")");
        String actual = new Point(1, 2).toString();
        Assertions.assertEquals(expected, actual);
    }
}
