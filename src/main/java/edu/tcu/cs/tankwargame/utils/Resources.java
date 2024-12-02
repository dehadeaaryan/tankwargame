package edu.tcu.cs.tankwargame.utils;

import javafx.scene.image.Image;
import java.util.HashMap;
import java.util.Map;

public class Resources {
    // A map to store loaded images for easy access
    private static final Map<String, Image> imageCache = new HashMap<>();

    public static Image loadImage(String path) {
        if (!imageCache.containsKey(path)) {
            Image image = new Image(Resources.class.getResourceAsStream(path));
            imageCache.put(path, image);
        }
        return imageCache.get(path);
    }

    // Example of loading specific game assets
    public static Image getPlayerTankImage() {
        return loadImage(Constants.PLAYER_TANK_IMAGE);
    }

    public static Image getEnemyTankImage() {
        return loadImage(Constants.ENEMY_TANK_IMAGE);
    }

    public static Image getMissileImage() {
        return loadImage(Constants.MISSILE_IMAGE);
    }

    public static Image getExplosionImage() {
        return loadImage(Constants.EXPLOSION_IMAGE);
    }

    public static Image getMedPackImage() {
        return loadImage(Constants.MEDPACK_IMAGE);
    }

    public static Image getWallImage() {
        return loadImage(Constants.WALL_IMAGE);
    }
}
