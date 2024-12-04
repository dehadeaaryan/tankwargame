package edu.tcu.cs.tankwargame.entities;

import edu.tcu.cs.tankwargame.ai.EnemyAI;
import edu.tcu.cs.tankwargame.ui.GameUI;
import edu.tcu.cs.tankwargame.utils.Constants;
import javafx.geometry.Point2D;

public class EnemyTank extends Tank {
    private EnemyAI enemyAI;

    public EnemyTank(Point2D position) {
        super(position, Constants.MAX_HEALTH, Constants.ENEMY_TANK_SPEED, Constants.ENEMY_TANK_IMAGE);
    }

    public void initializeAI(PlayerTank playerTank) {
        this.enemyAI = new EnemyAI(this, playerTank);
    }

    @Override
    public void updateMovement() {
        if (enemyAI != null) {
            // Delegate movement to the AI
            enemyAI.move();
            enemyAI.attack(); // Include attack logic in the movement update cycle
        } else {
            // Basic fallback movement if AI is not initialized
            moveForward();
//            if (Math.random() < 0.1) rotateRandomly();
        }
    }

    private void moveForward() {
        double angleRadians = Math.toRadians(getRotate());
        double deltaX = Math.cos(angleRadians) * speed;
        double deltaY = Math.sin(angleRadians) * speed;
        setTranslateX(getTranslateX() + deltaX);
        setTranslateY(getTranslateY() - deltaY);
    }

    private void rotateRandomly() {
        double randomRotation = Math.random() * 30 - 15; // Rotate between -15 and +15 degrees
        setRotate(getRotate() + randomRotation);
    }

    @Override
    public void fireMissile() {
        double angleDegrees = getRotate() - 90;
        double angleRadians = Math.toRadians(angleDegrees);

        double missileX = getPosition().getX() + 48 * Math.cos(angleRadians);
        double missileY = getPosition().getY() + 48 * Math.sin(angleRadians);

        // Create the missile at the calculated position
        Point2D missilePosition = new Point2D(missileX, missileY);

        // Get the parent GameUI and create the missile
        GameUI gameUI = (GameUI) getParent(); // Assuming GameUI is the parent of PlayerTank
        gameUI.createMissile(missilePosition, angleDegrees, "Enemy");
    }
}
