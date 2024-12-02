package edu.tcu.cs.tankwargame.ai;

import edu.tcu.cs.tankwargame.entities.EnemyTank;
import edu.tcu.cs.tankwargame.entities.PlayerTank;
import edu.tcu.cs.tankwargame.utils.Constants;
import javafx.geometry.Point2D;
import java.util.Random;

public class EnemyAI {
    private final EnemyTank enemyTank;
    private final PlayerTank playerTank;
    private final Random random;

    public EnemyAI(EnemyTank enemyTank, PlayerTank playerTank) {
        this.enemyTank = enemyTank;
        this.playerTank = playerTank;
        this.random = new Random();
    }

    public void move() {
        // Randomly move the enemy tank, avoiding walls and player tank
        Point2D currentPos = enemyTank.getPosition();
        double moveChoice = random.nextDouble();

        if (moveChoice < 0.25) {
            enemyTank.moveUp();
        } else if (moveChoice < 0.5) {
            enemyTank.moveDown();
        } else if (moveChoice < 0.75) {
            enemyTank.moveLeft();
        } else {
            enemyTank.moveRight();
        }

        // Ensure the enemy tank stays within bounds and doesn't move through walls
        enemyTank.ensureWithinBounds();
    }

    public void attack() {
        // Simple AI: Attack when in range of the player tank
        Point2D playerPos = playerTank.getPosition();
        Point2D enemyPos = enemyTank.getPosition();

        double distanceToPlayer = playerPos.distance(enemyPos);

        if (distanceToPlayer < Constants.GAME_WIDTH / 2) {
            // If player is within range, fire at them
            enemyTank.fireMissile();
        }
    }
}
