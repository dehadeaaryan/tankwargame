package edu.tcu.cs.tankwargame.entities;

import edu.tcu.cs.tankwargame.utils.Constants;
import edu.tcu.cs.tankwargame.utils.Resources;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

public class Missile extends ImageView {
    private Point2D position;
    private double angle;  // Track the angle the missile is moving at
    private double speed;
    private boolean isActive;
    private String owner;

    public Missile(Point2D position, double angle, String owner) {
        this.position = position;
        this.angle = angle;
        this.speed = Constants.MISSILE_SPEED;  // Define the missile's speed
        this.isActive = true;
        this.owner = owner;

        // Set the missile image
        setImage(Resources.getMissileImage());
        setFitWidth(32);
        setFitHeight(32);

        // Set initial position of missile
        setTranslateX(position.getX() + 16);
        setTranslateY(position.getY() + 16);

        // Set missile's rotation (same as the tank's facing direction)
        setRotate(angle + 90);
    }

    // This method updates the missile's position
    public void move() {
        if (!isActive) return;

        // Update the missile's position using trigonometry based on its angle
        double deltaX = Math.cos(Math.toRadians(angle)) * speed;  // Calculate X movement
        double deltaY = Math.sin(Math.toRadians(angle)) * speed;  // Calculate Y movement

        // Update missile's position
        setTranslateX(getTranslateX() + deltaX);
        setTranslateY(getTranslateY() + deltaY);
    }

    // Deactivate the missile when it goes out of bounds or hits something
    public void deactivate() {
        isActive = false;
    }

    // Getter for the missile's position
    public Point2D getPosition() {
        return new Point2D(getTranslateX(), getTranslateY());
    }

    // Getter for the missile's damage value
    public int getDamage() {
        return 50;  // For example, set damage to 50
    }

    // Getter for the missile's active state
    public boolean isActive() {
        return isActive;
    }

    public String getOwner() {
    	return owner;
    }

    public boolean isPlayerOwner() {
    	return owner.equals("Player");
    }
}
