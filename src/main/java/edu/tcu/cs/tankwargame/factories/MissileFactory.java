package edu.tcu.cs.tankwargame.factories;

import edu.tcu.cs.tankwargame.entities.Missile;
import edu.tcu.cs.tankwargame.entities.Direction;
import javafx.geometry.Point2D;

public class MissileFactory {
    public static Missile createMissile(Point2D position, Direction direction) {
        Point2D missilePosition;
        if (direction == Direction.UP) {
            missilePosition = new Point2D(position.getX() + 16, position.getY() - 32);
        } else {
            missilePosition = new Point2D(position.getX() + 16, position.getY() + 64);
        }
        return new Missile(missilePosition, direction);
    }
}
