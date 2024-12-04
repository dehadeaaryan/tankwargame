package edu.tcu.cs.tankwargame.entities;

import edu.tcu.cs.tankwargame.ui.GameUI;
import edu.tcu.cs.tankwargame.utils.Constants;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PlayerTank extends Tank {
    private boolean movingForward = false;
    private boolean movingBackward = false;
    private boolean rotatingLeft = false;
    private boolean rotatingRight = false;
    private long lastMissileFire = 0;

    public PlayerTank(Point2D position) {
        super(position, Constants.MAX_HEALTH, Constants.PLAYER_TANK_SPEED, Constants.PLAYER_TANK_IMAGE);
    }

    public void move(KeyCode code) {
        switch (code) {
            case W -> movingForward = true;
            case S -> movingBackward = true;
            case A -> rotatingLeft = true;
            case D -> rotatingRight = true;
        }
    }

    public void stop(KeyCode code) {
        switch (code) {
            case W -> movingForward = false;
            case S -> movingBackward = false;
            case A -> rotatingLeft = false;
            case D -> rotatingRight = false;
        }
    }

    @Override
    public void updateMovement() {
        // Rotation logic
        if (rotatingLeft) setRotate(getRotate() - Constants.PLAYER_TANK_ROTATION_SPEED);
        if (rotatingRight) setRotate(getRotate() + Constants.PLAYER_TANK_ROTATION_SPEED);

        // Movement logic
        if (movingForward) moveForward();
        if (movingBackward) moveBackward();
    }

    private void moveForward() {
        // Move forward based on the current rotation of the tank
        double angleRadians = Math.toRadians(getRotate() - 90);  // Convert angle to radians
        double deltaX = Math.cos(angleRadians) * getSpeed();  // X movement based on angle
        double deltaY = Math.sin(angleRadians) * getSpeed();  // Y movement based on angle

        // Apply the movement to the tank's position
        setTranslateX(getTranslateX() + deltaX);
        setTranslateY(getTranslateY() + deltaY);
    }

    private void moveBackward() {
        // Move backward based on the current rotation of the tank (opposite direction of forward)
        double angleRadians = Math.toRadians(getRotate() - 90);  // Convert angle to radians
        double deltaX = Math.cos(angleRadians) * getSpeed();  // X movement based on angle
        double deltaY = Math.sin(angleRadians) * getSpeed();  // Y movement based on angle

        // Apply the movement to the tank's position (opposite direction)
        setTranslateX(getTranslateX() - deltaX);
        setTranslateY(getTranslateY() - deltaY);
    }

    @Override
    public void fireMissile() {
        if (getParent() == null) return;
        if (System.currentTimeMillis() - lastMissileFire < Constants.MISSILE_COOLDOWN) return;
        // Get the current rotation angle in degrees
        double angleDegrees = getRotate() - 90;
        double angleRadians = Math.toRadians(angleDegrees);

        double missileX = getPosition().getX() + 48 * Math.cos(angleRadians);
        double missileY = getPosition().getY() + 48 * Math.sin(angleRadians);

        // Create the missile at the calculated position
        Point2D missilePosition = new Point2D(missileX, missileY);

        // Get the parent GameUI and create the missile
        GameUI gameUI = (GameUI) getParent(); // Assuming GameUI is the parent of PlayerTank
        gameUI.createMissile(missilePosition, angleDegrees, "Player"); // Pass the rotation angle for missile direction
        lastMissileFire = System.currentTimeMillis();
    }
}
