package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.Solver.BfsSolver;
import ca.mcmaster.se2aa4.mazerunner.Solver.MazeSolver;
import ca.mcmaster.se2aa4.mazerunner.Solver.RightHandSolver;
import ca.mcmaster.se2aa4.mazerunner.Solver.TremauxSolver;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

public static void main(String[] args){
        logger.info("** Starting Maze Runner");
        CommandLineParser parser = new DefaultParser();

        CommandLine cmd = null;
        try {
            cmd = parser.parse(getParserOptions(), args);
            String filePath = cmd.getOptionValue('i');

            long loadFile = System.currentTimeMillis();

            Maze maze = new Maze(filePath);

            long loadedFile = System.currentTimeMillis();
            long time = loadedFile - loadFile;

            if (cmd.getOptionValue("p") != null) {
                logger.info("Validating path");
                Path path = new Path(cmd.getOptionValue("p"));
                if (maze.validatePath(path)) {
                    System.out.println("correct path");
                } else {
                    System.out.println("incorrect path");
                }
            } else {
                if (cmd.getOptionValue("baseline") != null){
                    String method = cmd.getOptionValue("method", "bfs");
                    String baseline = cmd.getOptionValue("baseline","righthand");
                    System.out.printf("Time spent loading the maze from the file: %s",String.format("%.2f", (double) time) + " milliseconds\n");
                    long mazeStart = System.currentTimeMillis();
                    Path path = solveMaze(method, maze);
                    long mazeEnd = System.currentTimeMillis();
                    long mazeTime = mazeEnd - mazeStart;
                    System.out.printf("Time spent exploring the maze using method %s: %s",method, String.format("%.2f", (double) mazeTime) + " milliseconds\n");

                    long baselineStart = System.currentTimeMillis();
                    Path pathh = solveBaseline(baseline, maze);
                    long baselineEnd = System.currentTimeMillis();
                    long baselineTime = baselineEnd - baselineStart;
                    System.out.printf("Time spent exploring the maze using baseline method %s: %s",baseline, String.format("%.2f", (double) baselineTime) + " milliseconds\n");

                    SpeedUp speedUp = new SpeedUp();
                    Float speedup = speedUp.speedUp(pathh, path);
                    System.out.printf("SpeedUp = %.2f\n", speedup);
                }
                else{
                    String method = cmd.getOptionValue("method", "bfs");
                    Path path = solveMaze(method, maze);
                    System.out.println(path.getFactorizedForm());
                }
            }
        } catch (Exception e) {
            System.err.println("MazeSolver failed.  Reason: " + e.getMessage());
            logger.error("MazeSolver failed.  Reason: " + e.getMessage());
            logger.error("PATH NOT COMPUTED");
        }

        logger.info("End of MazeRunner");
    }

    /**
     * Solve provided maze with specified method.
     *
     * @param method Method to solve maze with
     * @param maze Maze to solve
     * @return Maze solution path
     * @throws Exception If provided method does not exist
     */
    private static Path solveMaze(String method, Maze maze) throws Exception {
        MazeSolver solver = null;
        switch (method) {
            case "bfs" -> {
                logger.debug("BFS algorithm chosen.");
                solver = new BfsSolver();
            }
            case "tremaux" -> {
                logger.debug("Tremaux algorithm chosen.");
                solver = new TremauxSolver();
            }
            case "righthand" -> {
                logger.debug("Right Hand algorithm chosen.");
                solver = new RightHandSolver();
            }
            default -> {
                throw new Exception("Maze solving method '" + method + "' not supported.");
            }
        }

        logger.info("Computing path");
        return solver.solve(maze);
    }
    private static Path solveBaseline(String baselineMethod, Maze maze) throws Exception{
        MazeSolver solver = null;
        switch (baselineMethod) {
            case "bfs" -> {
                logger.debug("BFS algorithm chosen.");
                solver = new BfsSolver();
            }
            case "tremaux" -> {
                logger.debug("Tremaux algorithm chosen.");
                solver = new TremauxSolver();
            }
            case "righthand" -> {
                logger.debug("Right Hand algorithm chosen.");
                solver = new RightHandSolver();
            }
            default -> {
                throw new Exception("Maze solving method '" + baselineMethod + "' not supported.");
            }
        }
        return solver.solve(maze);
    }
    /**
     * Get options for CLI parser.
     *
     * @return CLI parser options
     */
    private static Options getParserOptions() {
        Options options = new Options();

        Option fileOption = new Option("i", true, "File that contains maze");
        fileOption.setRequired(true);
        options.addOption(fileOption);

        options.addOption(new Option("p", true, "Path to be verified in maze"));
        options.addOption(new Option("method", true, "Specify which path computation algorithm will be used"));
        options.addOption(new Option("baseline", true, "Measure speedup"));

        return options;
    }
}
