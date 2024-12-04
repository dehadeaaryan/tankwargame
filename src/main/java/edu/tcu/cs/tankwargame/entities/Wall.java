package edu.tcu.cs.tankwargame.entities;

import edu.tcu.cs.tankwargame.utils.Resources;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Wall extends ImageView {

    public static final int WALL_WIDTH = 32;
    public static final int WALL_HEIGHT = 32;

    private Point2D position;

    public Wall(Point2D position) {
        this.position = position;

        setImage(Resources.getWallImage());
        setFitWidth(32);
        setFitHeight(32);

        setTranslateX(position.getX());
        setTranslateY(position.getY());
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
        setTranslateX(position.getX());
        setTranslateY(position.getY());
    }
}
