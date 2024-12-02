package edu.tcu.cs.tankwargame.factories;

import edu.tcu.cs.tankwargame.entities.Explosion;
import javafx.geometry.Point2D;

public class ExplosionFactory {
    public static Explosion createExplosion(Point2D position) {
        return new Explosion(position.getX(), position.getY());
    }
}
