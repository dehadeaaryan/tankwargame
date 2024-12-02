package edu.tcu.cs.tankwargame.entities;

import edu.tcu.cs.tankwargame.utils.Constants;
import javafx.geometry.Point2D;

public class EnemyTank extends Tank {
    public EnemyTank(Point2D position) {
        super(position, Direction.DOWN, Constants.MAX_HEALTH, Constants.ENEMY_TANK_SPEED, Constants.ENEMY_TANK_IMAGE);
    }

    @Override
    public void moveUp() {
        setPosition(getPosition().add(0, -speed));
    }

    @Override
    public void moveDown() {
        setPosition(getPosition().add(0, speed));
    }

    @Override
    public void moveLeft() {
        setPosition(getPosition().add(-speed, 0));
    }

    @Override
    public void moveRight() {
        setPosition(getPosition().add(speed, 0));
    }

    @Override
    public void fireMissile() {
        // Implement missile firing for enemy
        System.out.println("Enemy firing missile!");
    }

    @Override
    public void updateMovement() {
        // For now, we'll just move the enemy tank randomly
        double randomDirection = Math.random();
        if (randomDirection < 0.25) {
            moveUp();
        } else if (randomDirection < 0.5) {
            moveDown();
        } else if (randomDirection < 0.75) {
            moveLeft();
        } else {
            moveRight();
        }
    }
}
