package edu.tcu.cs.tankwargame.ai;

import edu.tcu.cs.tankwargame.entities.EnemyTank;
import edu.tcu.cs.tankwargame.entities.PlayerTank;
import edu.tcu.cs.tankwargame.ui.GameUI;
import edu.tcu.cs.tankwargame.utils.Constants;
import javafx.geometry.Point2D;

import java.util.Random;

public class EnemyAI {
    private final EnemyTank enemyTank;
    private final PlayerTank playerTank;
    private final Random random;

    // Timer to manage the periodic attack (last attack time)
    private long lastAttackTime;

    public EnemyAI(EnemyTank enemyTank, PlayerTank playerTank) {
        this.enemyTank = enemyTank;
        this.playerTank = playerTank;
        this.random = new Random();
        this.lastAttackTime = System.currentTimeMillis();  // Initialize with the current time
    }


    public void move() {
        if (((GameUI) enemyTank.getParent()) == null) return;
        Point2D playerPos = playerTank.getPosition();
        Point2D enemyPos = enemyTank.getPosition();

        // Calculate the angle to the player
        double angleToPlayer = calculateAngleToPlayer(enemyPos, playerPos);

        // Rotate the tank smoothly toward the player
        rotateTowardsPlayer(angleToPlayer);

        // Move forward in the direction the tank is facing
        // The tank's rotation is offset by -90 degrees, so we need to adjust for this
        double angleRadians = Math.toRadians(enemyTank.getRotate() + 90);
        double deltaX = Math.cos(angleRadians) * enemyTank.getSpeed();
        double deltaY = Math.sin(angleRadians) * enemyTank.getSpeed();
        double newX = enemyTank.getTranslateX() - deltaX;
        double newY = enemyTank.getTranslateY() - deltaY;

        // Validate and apply movement
        if (((GameUI) enemyTank.getParent()).isValidMove(enemyTank, newX, newY)) {
            enemyTank.setTranslateX(newX);
            enemyTank.setTranslateY(newY);
        }

        // Ensure the tank stays within bounds
        enemyTank.ensureWithinBounds();
    }



    private void rotateTowardsPlayer(double angleToPlayer) {
        double currentAngle = enemyTank.getRotate();

        // Adjust for turret's 90-degree clockwise offset
        double adjustedAngleToPlayer = angleToPlayer + 90;

        // Normalize the angle difference
        double angleDifference = normalizeAngle(adjustedAngleToPlayer - currentAngle);

        // Rotate incrementally toward the target angle
        if (Math.abs(angleDifference) > Constants.ENEMY_TANK_ROTATION_SPEED) {
            double rotationStep = Math.signum(angleDifference) * Constants.ENEMY_TANK_ROTATION_SPEED;
            enemyTank.setRotate(currentAngle + rotationStep);
        } else {
            // Snap to the target angle if close enough
            enemyTank.setRotate(adjustedAngleToPlayer);
        }
    }




    public void attack() {
        // Get the current time
        long currentTime = System.currentTimeMillis();

        // Check if at least 1 second has passed since the last attack
        if (currentTime - lastAttackTime >= 2000) {  // 1000 ms = 1 second
            // If enough time has passed, fire the missile
            enemyTank.fireMissile();
            lastAttackTime = currentTime;  // Update the last attack time
        }
    }

    private double calculateAngleToPlayer(Point2D enemyPos, Point2D playerPos) {
        double deltaX = playerPos.getX() - enemyPos.getX();
        double deltaY = playerPos.getY() - enemyPos.getY(); // Correct for standard screen coordinates
        return Math.toDegrees(Math.atan2(deltaY, deltaX)); // No need for further adjustment
    }



    private double normalizeAngle(double angle) {
        angle = angle % 360;
        if (angle > 180) angle -= 360;
        if (angle < -180) angle += 360;
        return angle;
    }


}
