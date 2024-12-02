package edu.tcu.cs.tankwargame.entities;

import edu.tcu.cs.tankwargame.utils.Constants;
import edu.tcu.cs.tankwargame.utils.Resources;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

import java.awt.*;

public class Missile extends ImageView {
    private Point2D position;
    private Direction direction;
    private double speed;
    private boolean isActive;

    public Missile(Point2D position, Direction direction) {
        this.position = position;
        this.direction = direction;
        if (direction == Direction.DOWN) {
            setRotate(180);
        }
        this.speed = Constants.MISSILE_SPEED;
        this.isActive = true;
        setImage(Resources.getMissileImage());
        setFitWidth(32);
        setFitHeight(32);
        setTranslateX(position.getX());
        setTranslateY(position.getY());
    }

    public void move() {
        if (!isActive) return;

        switch (direction) {
            case UP:
                this.position = new Point2D(getTranslateX(), getTranslateY() - speed);
                setTranslateY(getTranslateY() - speed);
                break;
            case DOWN:
                this.position = new Point2D(getTranslateX(), getTranslateY() + speed);
                setTranslateY(getTranslateY() + speed);
                break;
            case LEFT:
                this.position = new Point2D(getTranslateX() - speed, getTranslateY());
                setTranslateX(getTranslateX() - speed);
                break;
            case RIGHT:
                this.position = new Point2D(getTranslateX() + speed, getTranslateY());
                setTranslateX(getTranslateX() + speed);
                break;
        }
    }

    public void deactivate() {
        isActive = false;
    }

    // Getters and setters
    public boolean isActive() {
        return isActive;
    }

    public Point2D getPosition() {
        return position;
    }

    public int getDamage() {
        return 50;
    }
}
