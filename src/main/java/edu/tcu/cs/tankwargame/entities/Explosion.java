package edu.tcu.cs.tankwargame.entities;

import edu.tcu.cs.tankwargame.utils.Resources;
import javafx.scene.image.ImageView;

public class Explosion extends ImageView {
    public Explosion(double x, double y) {
        setImage(Resources.getExplosionImage());
        setFitWidth(64);
        setFitHeight(64);
        setTranslateX(x);
        setTranslateY(y);
    }
}
