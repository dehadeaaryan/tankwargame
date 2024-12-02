package edu.tcu.cs.tankwargame.ui;

import edu.tcu.cs.tankwargame.entities.*;
import edu.tcu.cs.tankwargame.factories.ExplosionFactory;
import edu.tcu.cs.tankwargame.factories.MissileFactory;
import edu.tcu.cs.tankwargame.factories.TankFactory;
import edu.tcu.cs.tankwargame.factories.WallFactory;
import edu.tcu.cs.tankwargame.utils.Constants;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameUI extends Pane {
    private PlayerTank playerTank;
    private final List<Tank> tanks = new ArrayList<>();
    private final List<Missile> missiles = new ArrayList<>();
    private final List<Wall> walls = new ArrayList<>();
    private final List<MedPack> medPacks = new ArrayList<>();
    private AnimationTimer gameLoop;

    // Track the last missile fire time
    private long lastMissileTime = 0;
    // Track whether the spacebar is being held down
    private boolean spacebarPressed = false;

    // Flag to track game status
    private boolean gameWon = false;

    // Add a primaryStage variable
    private Stage primaryStage;

    public GameUI(Stage primaryStage) {
        this.primaryStage = primaryStage;  // Store the Stage locally
        setupUI();
        setupGameLoop();
    }

    private void setupUI() {
        // Set the background color of the game UI to black
        setStyle("-fx-background-color: #000;");

        // Initialize player tank in the center of the screen
        double centerX = Constants.GAME_WIDTH / 2;
        double centerY = Constants.GAME_HEIGHT - 64;

        playerTank = (PlayerTank) TankFactory.createTank("Player", new Point2D(centerX, centerY));
        tanks.add(playerTank);
        getChildren().add(playerTank);

        // Initialize enemy tanks
        initializeEnemies();

        // Add event listeners for player controls
        setOnKeyPressed(event -> handleKeyPress(event.getCode()));
        setOnKeyReleased(event -> handleKeyRelease(event.getCode()));

        // Request focus for keyboard input
        requestFocus();
    }

    private void setupGameLoop() {
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (gameWon) {
                    // If the game is won, stop the game loop
                    return;
                }

                // Continuously update the tank's movement
                playerTank.updateMovement();
                update();

                // Check if the spacebar is still pressed, and fire missile if allowed
                if (spacebarPressed && System.currentTimeMillis() - lastMissileTime >= Constants.MISSILE_COOLDOWN) {
                    fireMissile();
                    lastMissileTime = System.currentTimeMillis();
                }

                // Update enemy tanks' movements and fire missiles randomly
                updateEnemies();
            }
        };

        gameLoop.start();
    }

    private void initializeEnemies() {
        // Initialize a fixed number of enemy tanks
        for (int i = 0; i < Constants.INITIAL_ENEMY_TANKS; i++) {
            double x = Math.random() * Constants.GAME_WIDTH;
            double y = Math.random() * (Constants.GAME_HEIGHT - 400); // Don't overlap with player tank
            Tank enemyTank = TankFactory.createTank("Enemy", new Point2D(x, y));
            tanks.add(enemyTank);
            getChildren().add(enemyTank);
        }
    }

    private void replaceWithExplosion(Tank tank) {
        // Create explosion at the tank's position
        Point2D tankPosition = tank.getPosition();
        Explosion explosion = ExplosionFactory.createExplosion(tankPosition);
        getChildren().add(explosion);

        // Remove explosion after 1 second (1000 ms)
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            getChildren().remove(explosion);
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }


    private void updateEnemies() {
        Iterator<Tank> tankIterator = tanks.iterator();
        while (tankIterator.hasNext()) {
            Tank tank = tankIterator.next();
            if (tank != playerTank) {
                // Simple enemy AI: move randomly
                tank.updateMovement();

                if (tank.getHealth() <= 0) {
                    // Replace the tank with an explosion
                    replaceWithExplosion(tank);
                    tankIterator.remove();
                    getChildren().remove(tank);
                    continue;  // Skip the rest of the loop for this tank
                }

                // Fire missiles randomly (simplified behavior)
                if (Math.random() < 0.01) { // 1% chance per frame to fire a missile
                    fireEnemyMissile(tank);
                }
            }
        }

        // Check if all enemies are defeated
        if (tanks.size() == 1 && tanks.contains(playerTank)) { // Only the player tank is left
            gameWon = true;
            showWinnerScreen();
        }
    }

    private void fireMissile() {
        Missile missile = MissileFactory.createMissile(playerTank.getPosition(), playerTank.getDirection());
        missiles.add(missile);
        getChildren().add(missile);
    }

    private void fireEnemyMissile(Tank enemyTank) {
        Missile missile = MissileFactory.createMissile(enemyTank.getPosition(), enemyTank.getDirection());
        missiles.add(missile);
        getChildren().add(missile);
    }

    private void handleKeyPress(KeyCode code) {
        if (code == KeyCode.SPACE) {
            // Set spacebarPressed to true, missile will be fired in game loop
            spacebarPressed = true;
        } else {
            // Handle movement keys
            playerTank.move(code);
        }
    }

    private void handleKeyRelease(KeyCode code) {
        if (code == KeyCode.SPACE) {
            // Set spacebarPressed to false when spacebar is released
            spacebarPressed = false;
        } else {
            // Stop movement for other keys
            playerTank.stop(code);
        }
    }

    public void update() {
        // Update all missiles
        updateMissiles();

        // Update all tanks
        for (Tank tank : tanks) {
            tank.ensureWithinBounds();
        }

        // Check for medpack collisions
        checkMedPackCollisions();
    }

    private void updateMissiles() {
        Iterator<Missile> missileIterator = missiles.iterator();
        while (missileIterator.hasNext()) {
            Missile missile = missileIterator.next();
            missile.move();

            // Remove missiles out of bounds
            if (isOutOfBounds(missile)) {
                missileIterator.remove();
                getChildren().remove(missile);
                continue;
            }

            // Handle missile collisions with tanks
            handleMissileCollisions(missileIterator, missile);
        }
    }

    private void handleMissileCollisions(Iterator<Missile> missileIterator, Missile missile) {
        for (Tank tank : tanks) {
            if (tank != playerTank && tank.getBoundsInParent().intersects(missile.getBoundsInParent())) {
                // Missile hits an enemy tank
                tank.takeDamage(missile.getDamage());
                missileIterator.remove();
                getChildren().remove(missile);
                break;
            }

            // Check if missile hits player tank
            if (tank == playerTank && tank.getBoundsInParent().intersects(missile.getBoundsInParent())) {
                playerTank.takeDamage(missile.getDamage());
                missileIterator.remove();
                getChildren().remove(missile);
                break;
            }
        }
    }


    private void checkMedPackCollisions() {
        Iterator<MedPack> medPackIterator = medPacks.iterator();
        while (medPackIterator.hasNext()) {
            MedPack medPack = medPackIterator.next();
            if (playerTank.getBoundsInParent().intersects(medPack.getBoundsInParent())) {
                playerTank.setHealth(
                        Math.min(playerTank.getHealth() + medPack.getHealAmount(), Constants.MAX_HEALTH));
                medPackIterator.remove();
                getChildren().remove(medPack);
            }
        }
    }

    private boolean isOutOfBounds(Missile missile) {
        double x = missile.getPosition().getX();
        double y = missile.getPosition().getY();
        return x < 0 || x > Constants.GAME_WIDTH || y < 0 || y > Constants.GAME_HEIGHT;
    }

    private void showWinnerScreen() {
        // Stop the game loop
        gameLoop.stop();

        // Display the Winner screen
        WinnerScreen winnerScreen = new WinnerScreen();
        getChildren().clear();
        getChildren().add(winnerScreen);

        // Setup the Scene for the winner screen
        winnerScreen.setOnMouseClicked(e -> {
            // When clicked on the winner screen, switch to the title screen or restart
            TitleScreen titleScreen = new TitleScreen(primaryStage);  // Pass the primaryStage to TitleScreen
            Scene titleScene = new Scene(titleScreen, 800, 600);
            primaryStage.setScene(titleScene);
            primaryStage.show();
        });
    }
}
