package edu.tcu.cs.tankwargame.factories;

import edu.tcu.cs.tankwargame.entities.Missile;
import javafx.geometry.Point2D;

public class MissileFactory {

    // Create missile with the direction based on the tank's rotation angle
    public static Missile createMissile(Point2D position, double angle, String owner) {
        // Calculate the missile's initial position based on the tank's position and its facing angle
        // Place missile slightly in front of the tank
//        double missileOffsetX = Math.cos(Math.toRadians(angle)) * 32;  // Offset in X direction
//        double missileOffsetY = Math.sin(Math.toRadians(angle)) * 32;  // Offset in Y direction

        // Set the missile's position
        Point2D missilePosition = new Point2D(position.getX(), position.getY());

        // Return a new missile with the calculated position and angle
        return new Missile(missilePosition, angle, owner);
    }
}
