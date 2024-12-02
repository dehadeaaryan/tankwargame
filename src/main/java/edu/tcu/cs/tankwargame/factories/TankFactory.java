package edu.tcu.cs.tankwargame.factories;

import edu.tcu.cs.tankwargame.entities.PlayerTank;
import edu.tcu.cs.tankwargame.entities.EnemyTank;
import edu.tcu.cs.tankwargame.entities.Tank;
import javafx.geometry.Point2D;

public class TankFactory {
    public static Tank createTank(String type, Point2D position) {
        return switch (type) {
            case "Player" -> new PlayerTank(position);
            case "Enemy" -> new EnemyTank(position);
            default -> throw new IllegalArgumentException("Unknown tank type: " + type);
        };
    }
}
