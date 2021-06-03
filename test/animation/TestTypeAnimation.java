package test.animation;

import animation.TypeAnimation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestTypeAnimation {

    @Test
    public void testTypeAnimation() {
        Assertions.assertEquals("AVANCER", TypeAnimation.AVANCER.toString());
        Assertions.assertEquals("CLIGNOTER", TypeAnimation.CLIGNOTER.toString());
    }
}
