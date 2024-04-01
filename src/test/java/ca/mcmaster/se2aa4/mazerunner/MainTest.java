package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static ca.mcmaster.se2aa4.mazerunner.Main.speedUp;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    public void testSpeedUp(){
        Path path = new Path();
        path.addStep('L');
        path.addStep('R');
        path.addStep('F');

        Path pathh = new Path();
        pathh.addStep('F');
        pathh.addStep('L');
        pathh.addStep('R');

        Float speedup = speedUp(path, pathh);

        // Assert the speedup value is approximately equal to 1.00
        assertEquals(1.00f, speedup);
    }
}