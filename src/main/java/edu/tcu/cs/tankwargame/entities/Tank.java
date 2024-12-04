package edu.tcu.cs.tankwargame.entities;

import edu.tcu.cs.tankwargame.utils.Constants;
import edu.tcu.cs.tankwargame.utils.Resources;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Tank extends StackPane {
    private Point2D position;
    private int health;
    protected double speed;
    private Image image;
    private Rectangle healthBar;

    public Tank(Point2D position, int health, double speed, String imagePath) {
        this.position = position;
        this.health = health;
        this.speed = speed;
        this.image = Resources.loadImage(imagePath);
        this.healthBar = new Rectangle(50, 5, Color.GREEN);

        // Set the position of the health bar above the tank
        this.healthBar.setTranslateY(24);
        ImageView imageView = new ImageView(this.image);
        imageView.setFitWidth(64);   // Set the width of the image
        imageView.setFitHeight(64);  // Set the height of the image
        getChildren().addAll(imageView, this.healthBar);
        setTranslateX(this.position.getX());
        setTranslateY(this.position.getY());
    }

    public abstract void updateMovement();

    public void updateHealthBar() {
        double healthPercentage = (double) health / Constants.MAX_HEALTH;
        healthBar.setWidth(50 * healthPercentage);
        healthBar.setFill(healthPercentage <= 0.2 ? Color.RED : Color.GREEN);
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        updateHealthBar();
    }

    public void fireMissile() {
        System.out.println("Firing missile in direction of rotation: " + getRotate());
        // Missile firing logic should be implemented here
    }

    public void ensureWithinBounds() {
        double x = getTranslateX();
        double y = getTranslateY();

        if (x < 0) setTranslateX(0);
        if (x > Constants.GAME_WIDTH - 64) setTranslateX(Constants.GAME_WIDTH - 64);
        if (y < 0) setTranslateY(0);
        if (y > Constants.GAME_HEIGHT - 64) setTranslateY(Constants.GAME_HEIGHT - 64);
    }

    public Point2D getPosition() {
        return new Point2D(getTranslateX(), getTranslateY());
    }

    public double getSpeed() {
        return this.speed;
    }

    public void setPosition(Point2D position) {
        this.position = position;
        setTranslateX(position.getX());
        setTranslateY(position.getY());
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
        updateHealthBar();
    }
}
