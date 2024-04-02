package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpeedUpTest {
    @Test
    public void testSpeedUp(){
        Path path = new Path();
        path.addStep('F');
        path.addStep('R');
        path.addStep('L');


        Path pathh = new Path();
        pathh.addStep('F');
        pathh.addStep('L');
        pathh.addStep('R');

        SpeedUp var = new SpeedUp();
        Float ratio = var.speedUpRatio(path, pathh);

        assertEquals(1.00F, ratio);
    }

}