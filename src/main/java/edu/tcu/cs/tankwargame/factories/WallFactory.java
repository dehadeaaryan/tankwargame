package edu.tcu.cs.tankwargame.factories;

import edu.tcu.cs.tankwargame.entities.Wall;
import javafx.geometry.Point2D;
import edu.tcu.cs.tankwargame.utils.Constants;

public class WallFactory {

    // Creates a single wall at a specific location
    public static Wall createSingleWall(double x, double y) {
        return new Wall(new Point2D(x, y));
    }

    // Creates the boundary walls around the game window
    public static Wall[] createBoundary() {
        // Define the number of walls on each side of the boundary
        int topWallCount = Constants.GAME_WIDTH / Wall.WALL_WIDTH;
        int leftWallCount = Constants.GAME_HEIGHT / Wall.WALL_HEIGHT;

        // Create an array to hold all the wall objects (4 sides of the boundary)
        Wall[] walls = new Wall[topWallCount + topWallCount + leftWallCount + leftWallCount];

        // Top boundary
        for (int i = 0; i < topWallCount; i++) {
            walls[i] = createSingleWall(i * Wall.WALL_WIDTH, 0); // Place walls on the top edge
        }

        // Bottom boundary
        for (int i = 0; i < topWallCount; i++) {
            walls[topWallCount + i] = createSingleWall(i * Wall.WALL_WIDTH, Constants.GAME_HEIGHT - Wall.WALL_HEIGHT); // Place walls on the bottom edge
        }

        // Left boundary
        for (int i = 0; i < leftWallCount; i++) {
            walls[topWallCount * 2 + i] = createSingleWall(0, i * Wall.WALL_HEIGHT); // Place walls on the left edge
        }

        // Right boundary
        for (int i = 0; i < leftWallCount; i++) {
            walls[topWallCount * 2 + leftWallCount + i] = createSingleWall(Constants.GAME_WIDTH - Wall.WALL_WIDTH, i * Wall.WALL_HEIGHT); // Place walls on the right edge
        }

        return walls;
    }
}
