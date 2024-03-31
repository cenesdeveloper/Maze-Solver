package ca.mcmaster.se2aa4.mazerunner;

import java.util.*;

public class BfsSolver implements MazeSolver{
    private boolean[][] marked;
    private Position[][] edgeTo;
    private Position source;
    private StringBuilder pathString;
    private Maze maze;
    private Position current;

    @Override
    public Path solve(Maze maze) {
        this.maze = maze;
        this.source = maze.getStart();
        this.current = maze.getEnd();
        this.pathString = new StringBuilder();
        marked = new boolean[maze.getSizeY()][maze.getSizeX()];
        edgeTo = new Position[maze.getSizeY()][maze.getSizeX()];
        bfs();
        return findPath();
    }
    private void bfs(){
        Queue<Position> queue = new LinkedList<>();
        queue.add(source);
        marked[source.y()][source.x()] = true;

        while(!queue.isEmpty()){
            Position v = queue.poll();

            for (Position current: getMazeNeighbors(v)){
                if (!marked[current.y()][current.x()]){
                    edgeTo[current.y()][current.x()] = v;
                    marked[current.y()][current.x()] = true;
                    queue.add(current);
                }
            }
        }
    }
    private Path findPath(){
        Path path = new Path();
        Direction dir = Direction.LEFT;
        while (!current.equals(source)){
            Position parent = edgeTo[current.y()][current.x()];
            if (dir == Direction.RIGHT){
                if (current.x() < parent.x()){
                    pathString.append('F');
                }
                else if (current.x() > parent.x()){
                    pathString.append('R');
                    pathString.append('R');
                    pathString.append('F');
                    dir = dir.turnLeft().turnLeft();
                }
                else if (current.y() < parent.y()){
                    pathString.append('L');
                    pathString.append('F');
                    dir = dir.turnRight();
                }
                else if (current.y() > parent.y()){
                    pathString.append('R');
                    pathString.append('F');
                    dir = dir.turnLeft();
                }
            }
            if (dir == Direction.LEFT){
                if (current.x() < parent.x()){
                    pathString.append('L');
                    pathString.append('L');
                    pathString.append('F');
                    dir = dir.turnRight().turnRight();
                }
                else if (current.x() > parent.x()){
                    pathString.append('F');
                }
                else if (current.y() < parent.y()){
                    pathString.append('R');
                    dir = dir.turnLeft();
                }
                else if (current.y() > parent.y()){
                    pathString.append('L');
                    dir = dir.turnRight();
                }
            }
            if (dir == Direction.UP){
                if (current.x() < parent.x()){
                    pathString.append('L');
                    pathString.append('F');
                    dir = dir.turnRight();
                }
                else if (current.x() > parent.x()){
                    pathString.append('R');
                    pathString.append('F');
                    dir = dir.turnLeft();
                }
                else if (current.y() < parent.y()){
                    pathString.append('L');
                    pathString.append('L');
                    pathString.append('F');
                    dir = dir.turnRight().turnRight();
                }
                else if (current.y() > parent.y()){
                    pathString.append('F');
                }
            }
            if (dir == Direction.DOWN){
                if (current.x() < parent.x()){
                    pathString.append('R');
                    pathString.append('F');
                    dir = dir.turnLeft();
                }
                else if (current.x() > parent.x()){
                    pathString.append('L');
                    pathString.append('F');
                    dir = dir.turnRight();
                }
                else if (current.y() < parent.y()){
                    pathString.append('F');
                }
                else if (current.y() > parent.y()){
                    pathString.append('R');
                    pathString.append('R');
                    pathString.append('F');
                    dir = dir.turnLeft().turnLeft();
                }
            }
            current = parent;
        }
        for (int i = pathString.length() - 1; i >= 0; i--){
            path.addStep(pathString.charAt(i));
        }
        return path;
    }
    private List<Position> getMazeNeighbors(Position pos) {
        List<Position> neighbors = new ArrayList<>();

        Position left = pos.add(new Position(-1, 0));
        if (left.x() >= 0 && !maze.isWall(left)) neighbors.add(left);

        Position right = pos.add(new Position(1, 0));
        if (right.x() < maze.getSizeX() && !maze.isWall(right)) neighbors.add(right);

        Position up = pos.add(new Position(0, -1));
        if (up.y() >= 0 && !maze.isWall(up)) neighbors.add(up);

        Position down = pos.add(new Position(0, 1));
        if (down.y() < maze.getSizeY() && !maze.isWall(down)) neighbors.add(down);

        return neighbors;
    }
}
