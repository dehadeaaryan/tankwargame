package edu.tcu.cs.tankwargame.entities;

import edu.tcu.cs.tankwargame.utils.Resources;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

public class MedPack extends ImageView {
    public MedPack(Point2D position) {
        setImage(Resources.getMedPackImage());
        setFitWidth(32);
        setFitHeight(32);
        setTranslateX(position.getX());
        setTranslateY(position.getY());
    }

    public int getHealAmount() {
        return 50;
    }
}
