package edu.tcu.cs.tankwargame.entities;

import edu.tcu.cs.tankwargame.utils.Constants;
import edu.tcu.cs.tankwargame.utils.Resources;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.KeyCode;  // Import KeyCode for key input

public abstract class Tank extends StackPane {
    private Point2D position;
    private int health;
    protected double speed;
    private Image image;
    private Direction direction;
    private Rectangle healthBar;

    public Tank(Point2D position, Direction direction, int health, double speed, String imagePath) {
        this.position = position;
        this.health = health;
        this.speed = speed;
        this.image = Resources.loadImage(imagePath);
        this.direction = direction;
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
    public abstract void moveUp();
    public abstract void moveDown();
    public abstract void moveLeft();
    public abstract void moveRight();

    // Update health bar to reflect current health
    public void updateHealthBar() {
        double healthPercentage = (double) health / Constants.MAX_HEALTH;
        healthBar.setWidth(50 * healthPercentage);
        if (healthPercentage <= 0.2) {
            healthBar.setFill(Color.RED);
        }
    }

    // When tank takes damage, update health and health bar
    public void takeDamage(int damage) {
        this.health -= damage;
        updateHealthBar();
    }

    // Fire missile (placeholder for missile firing)
    public void fireMissile() {
        // Placeholder for firing a missile
    }

    // Move the tank using a KeyCode input
    public void move(KeyCode key) {
        System.out.println("moving");
        switch (key) {
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
            default:
                break;  // No movement for other keys
        }
    }

    // Ensure the tank stays within the game bounds
    public void ensureWithinBounds() {
        double x = getPosition().getX();
        double y = getPosition().getY();

        // Ensure the tank doesn't go out of bounds
        if (x < 0) {
            setPosition(new Point2D(0, y));
        } else if (x > Constants.GAME_WIDTH - 64) {
            setPosition(new Point2D(Constants.GAME_WIDTH - 64, y));
        }

        if (y < 0) {
            setPosition(new Point2D(x, 0));
        } else if (y > Constants.GAME_HEIGHT - 64) {
            setPosition(new Point2D(x, Constants.GAME_HEIGHT - 64));
        }
    }

    // Getters and setters for position, health, and direction
    public Point2D getPosition() {
        return position;
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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
