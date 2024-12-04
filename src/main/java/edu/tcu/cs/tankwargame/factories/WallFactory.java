package edu.tcu.cs.tankwargame.factories;

import edu.tcu.cs.tankwargame.entities.Wall;
import javafx.geometry.Point2D;

public class WallFactory {

    // Creates a single wall at a specific location
    public static Wall createSingleWall(double x, double y) {
        return new Wall(new Point2D(x, y));
    }

    // Creates a row of 5 walls starting at a given position (horizontally)
    public static Wall[] createRowOfWalls(double startX, double startY) {
        Wall[] walls = new Wall[5];

        // Create a row of 5 walls horizontally starting from the given (startX, startY)
        for (int i = 0; i < 5; i++) {
            walls[i] = createSingleWall(startX + (i * Wall.WALL_WIDTH), startY);
        }

        return walls;
    }

    // Creates a column of 5 walls starting at a given position (vertically)
    public static Wall[] createColumnOfWalls(double startX, double startY) {
        Wall[] walls = new Wall[5];

        // Create a column of 5 walls vertically starting from the given (startX, startY)
        for (int i = 0; i < 5; i++) {
            walls[i] = createSingleWall(startX, startY + (i * Wall.WALL_HEIGHT));
        }

        return walls;
    }
}
