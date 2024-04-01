package ca.mcmaster.se2aa4.mazerunner.Solver;

import ca.mcmaster.se2aa4.mazerunner.Maze;
import ca.mcmaster.se2aa4.mazerunner.Path;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BfsSolverTest {
    @Test
    public void testBfs() throws Exception {
        MazeSolver solver = new BfsSolver();
        Maze maze = new Maze("./examples/small.maz.txt");
        Path path = solver.solve(maze);
        String bfsPath = path.getFactorizedForm();
        String shortestPath = "F L F R 2F L 6F R 4F R 2F L 2F R 2F L F";


        assertEquals(bfsPath, shortestPath);
    }

}