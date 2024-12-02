package edu.tcu.cs.tankwargame.factories;

import edu.tcu.cs.tankwargame.entities.MedPack;
import javafx.geometry.Point2D;

public class MedPackFactory {
    public static MedPack createMedPack(Point2D position) {
        return new MedPack(position);
    }
}
