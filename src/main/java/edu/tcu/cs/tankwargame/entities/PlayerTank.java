package edu.tcu.cs.tankwargame.entities;

import edu.tcu.cs.tankwargame.utils.Constants;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;

public class PlayerTank extends Tank {
    private boolean movingUp = false;
    private boolean movingDown = false;
    private boolean movingLeft = false;
    private boolean movingRight = false;

    public PlayerTank(Point2D position) {
        super(position, Direction.UP, Constants.MAX_HEALTH, Constants.PLAYER_TANK_SPEED, Constants.PLAYER_TANK_IMAGE);
    }

    @Override
    public void move(KeyCode code) {
        // Set movement direction flags based on the key pressed
        switch (code) {
            case W -> movingUp = true;
            case S -> movingDown = true;
            case A -> movingLeft = true;
            case D -> movingRight = true;
        }
    }

    public void stop(KeyCode code) {
        // Set movement direction flags to false when the key is released
        switch (code) {
            case W -> movingUp = false;
            case S -> movingDown = false;
            case A -> movingLeft = false;
            case D -> movingRight = false;
        }
    }

    // Update the tank's position based on the current direction flags
    @Override
    public void updateMovement() {
        if (movingUp) moveUp();
        if (movingDown) moveDown();
        if (movingLeft) moveLeft();
        if (movingRight) moveRight();
    }

    @Override
    public void moveUp() {
        setPosition(getPosition().add(0, -speed)); // Move up by speed
    }

    @Override
    public void moveDown() {
        setPosition(getPosition().add(0, speed)); // Move down by speed
    }

    @Override
    public void moveLeft() {
        setPosition(getPosition().add(-speed, 0)); // Move left by speed
    }

    @Override
    public void moveRight() {
        setPosition(getPosition().add(speed, 0)); // Move right by speed
    }

    @Override
    public void fireMissile() {
        // Implement missile firing for player
        System.out.println("Player firing missile!");
    }
}
