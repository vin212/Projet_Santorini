package modele;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import structure.Point;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestPlateau {

    @Mock
    Plateau plateauMock;

    @Test
    public void testAfficherPlateau_CMD() {
        doCallRealMethod().when(plateauMock).afficher_CMD();
        plateauMock.afficher_CMD();
        verify(plateauMock, times(1)).afficher_CMD();
    }

    @Test
    public void testConstruire() {
        plateauMock = new Plateau(3, 4);

        Point point = new Point(1,0);

        plateauMock.Construire(point, 2);
        Assertions.assertEquals(1, plateauMock.getNbEtage(point));
    }

    @Test
    public void testDetruireEtage() {
        plateauMock = new Plateau(3, 4);

        Point point = new Point(1,0);

        plateauMock.Construire(point, 2);
        plateauMock.detruireEtage(point);


        Assertions.assertEquals(0, plateauMock.getNbEtage(point));
    }

    @Test
    public void testReConstruirEtage() {
        plateauMock = new Plateau(3, 4);

        Point point = new Point(1,0);

        plateauMock.Construire(point, 1);
        plateauMock.detruireEtage(point);
        plateauMock.reConstruirEtage(point);

        Assertions.assertEquals(0, plateauMock.getNbEtage(point));
    }

    @Test
    public void testConstructible() {
        Plateau plateau = new Plateau(3, 4);

        Point point = new Point(1,0);

        plateau.Construire(point, 2);
        plateau.Construire(point, 1);
        plateau.Construire(point, 2);
        plateau.Construire(point, 1);

        Assertions.assertFalse(plateau.Constructible(point));
    }
}
