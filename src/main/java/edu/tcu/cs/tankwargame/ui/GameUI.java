package edu.tcu.cs.tankwargame.ui;

import edu.tcu.cs.tankwargame.ai.EnemyAI;
import edu.tcu.cs.tankwargame.entities.*;
import edu.tcu.cs.tankwargame.factories.*;
import edu.tcu.cs.tankwargame.utils.Constants;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;

public class GameUI extends Pane {
    private PlayerTank playerTank;
    private final List<Tank> tanks = new ArrayList<>();
    private final List<Missile> missiles = new ArrayList<>();
    private final List<MedPack> medPacks = new ArrayList<>();
    private final Map<EnemyTank, EnemyAI> enemyAIMap = new HashMap<>();
    private AnimationTimer gameLoop;
    private Timeline medPackSpawnTimer;
    public boolean running;

    private boolean gameWon = false;

    private Stage primaryStage;

    public GameUI(Stage primaryStage) {
        this.primaryStage = primaryStage;
        setupUI();
        setupGameLoop();
        startMedPackSpawning();
    }

    private void setupUI() {
        Random random = new Random();
        setStyle("-fx-background-color: #000;");
        running = true;

        double playerX = Constants.GAME_WIDTH / 2;
        double playerY = Constants.GAME_HEIGHT - 64;

        playerTank = (PlayerTank) TankFactory.createTank("Player", new Point2D(playerX, playerY));
        tanks.add(playerTank);
        getChildren().add(playerTank);

        Wall[] walls1 = WallFactory.createRowOfWalls(Constants.GAME_WIDTH / 2 - 80, 200);
        getChildren().addAll(walls1);
        Wall[] walls2 = WallFactory.createRowOfWalls(Constants.GAME_WIDTH / 2 - 80, Constants.GAME_HEIGHT - 200);
        getChildren().addAll(walls2);

        Wall[] walls3 = WallFactory.createColumnOfWalls( 200, Constants.GAME_HEIGHT / 2 - 80);
        getChildren().addAll(walls3);
        Wall[] walls4 = WallFactory.createColumnOfWalls( Constants.GAME_WIDTH - 200, Constants.GAME_HEIGHT / 2 - 80);
        getChildren().addAll(walls4);


        initializeEnemies();

        setOnKeyPressed(event -> handleKeyPress(event.getCode()));
        setOnKeyReleased(event -> handleKeyRelease(event.getCode()));

        requestFocus();
    }

    // Method to add random horizontal and vertical wall rows
    private void addRandomWallRowsAndColumns() {
        Random random = new Random();

        // Add 3 horizontal wall rows at random positions
        for (int i = 0; i < 3; i++) {
            double x = random.nextDouble() * (Constants.GAME_WIDTH - Wall.WALL_WIDTH * 5);  // Random starting x for the row
            double y = random.nextDouble() * (Constants.GAME_HEIGHT - Wall.WALL_HEIGHT); // Random starting y for the row

            Wall[] walls = WallFactory.createRowOfWalls(x, y);
            getChildren().addAll(walls); // 5 walls in this row
        }

        // Add 3 vertical wall columns at random positions
        for (int i = 0; i < 3; i++) {
            double x = random.nextDouble() * (Constants.GAME_WIDTH - Wall.WALL_WIDTH);  // Random starting x for the column
            double y = random.nextDouble() * (Constants.GAME_HEIGHT - Wall.WALL_HEIGHT * 5); // Random starting y for the column

            WallFactory.createColumnOfWalls(x, y);  // 5 walls in this column
        }
    }

    private void setupGameLoop() {
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (gameWon || !running) {
                    return;
                }

                playerTank.updateMovement();
                update();
                updateEnemies();
            }
        };
        gameLoop.start();
    }

    // Method to start spawning MedPacks at random intervals
    private void startMedPackSpawning() {
        this.medPackSpawnTimer = new Timeline(new KeyFrame(Duration.seconds(Math.random() * 5 + 5), e -> spawnMedPack()));
        this.medPackSpawnTimer.setCycleCount(Timeline.INDEFINITE);
        this.medPackSpawnTimer.play();
    }

    // Method to spawn a MedPack at a valid random position
    private void spawnMedPack() {
        if (!running) return;
        double x, y;

        // Ensure that MedPacks are at least 10 units away from the boundaries
        do {
            x = Math.random() * (Constants.GAME_WIDTH - 20) + 10; // X between 10 and GAME_WIDTH - 10
            y = Math.random() * (Constants.GAME_HEIGHT - 20) + 10; // Y between 10 and GAME_HEIGHT - 10
        } while (!isValidMedPackPosition(x, y));

        MedPack medPack = MedPackFactory.createMedPack(new Point2D(x, y));
        medPacks.add(medPack);
        getChildren().add(medPack);
    }

    // Check if the generated MedPack position is at least 10 units away from all boundaries
    private boolean isValidMedPackPosition(double x, double y) {
        return x >= 10 && x <= Constants.GAME_WIDTH - 10 && y >= 10 && y <= Constants.GAME_HEIGHT - 10;
    }

    private void initializeEnemies() {
        for (int i = 0; i < Constants.INITIAL_ENEMY_TANKS; i++) {
            double x = Math.random() * Constants.GAME_WIDTH;
            double y = Math.random() * (Constants.GAME_HEIGHT - 400);
            EnemyTank enemyTank = (EnemyTank) TankFactory.createTank("Enemy", new Point2D(x, y));
            tanks.add(enemyTank);
            getChildren().add(enemyTank);

            // Initialize EnemyAI for each enemy tank
            EnemyAI enemyAI = new EnemyAI(enemyTank, playerTank);
            enemyAIMap.put(enemyTank, enemyAI);
        }
    }

    private void replaceWithExplosion(Tank tank) {
        if (!running) return;
        Point2D tankPosition = tank.getPosition();
        Explosion explosion = ExplosionFactory.createExplosion(tankPosition);
        getChildren().add(explosion);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> getChildren().remove(explosion)));
        timeline.setCycleCount(1);
        timeline.play();
    }

    private void updateEnemies() {
        if (!running) return;
        Iterator<Tank> tankIterator = tanks.iterator();
        while (tankIterator.hasNext()) {
            Tank tank = tankIterator.next();
            if (tank instanceof EnemyTank enemyTank) {
                EnemyAI enemyAI = enemyAIMap.get(enemyTank);
                if (enemyAI != null) {
                    enemyAI.move();
                    enemyAI.attack();
                }

                if (tank.getHealth() <= 0) {
                    replaceWithExplosion(tank);
                    enemyAIMap.remove(enemyTank);
                    tankIterator.remove();
                    getChildren().remove(tank);
                }
            }
        }

        if (tanks.size() == 1 && tanks.contains(playerTank)) {
            gameWon = true;
            showWinnerScreen();
        }
    }

    private void handleKeyPress(KeyCode code) {
        if (code == KeyCode.SPACE) {
            playerTank.fireMissile();
        } else {
            playerTank.move(code);
        }
    }

    private void handleKeyRelease(KeyCode code) {
        playerTank.stop(code);
    }

    public void update() {
        updateMissiles();
        for (Tank tank : tanks) {
            tank.ensureWithinBounds();
        }
        checkMedPackCollisions();
        if (playerTank.getHealth() <= 0) {
            running = false;
            showGameOverScreen();
        }
    }

    private void showGameOverScreen() {
        gameLoop.stop();
        this.medPackSpawnTimer.stop();

        GameOverScreen gameOverScreen = new GameOverScreen();
        getChildren().clear();
        getChildren().add(gameOverScreen);

        gameOverScreen.setOnMouseClicked(e -> {
            TitleScreen titleScreen = new TitleScreen(primaryStage);
            Scene titleScene = new Scene(titleScreen, 800, 600);
            primaryStage.setScene(titleScene);
            primaryStage.show();
        });
    }

    public boolean isValidMove(Tank tank, double newX, double newY, boolean isPlayerTank) {
        if (!running) return false;
        // Temporarily move the tank to the new position
        double originalX = tank.getTranslateX();
        double originalY = tank.getTranslateY();
        tank.setTranslateX(newX);
        tank.setTranslateY(newY);

        boolean valid = true;

        Bounds shrunkBounds = tank.getBoundsInParent();
        Bounds adjustedBounds = new BoundingBox(
                shrunkBounds.getMinX() + 10,
                shrunkBounds.getMinY() + 10,
                shrunkBounds.getWidth() - 20,
                shrunkBounds.getHeight() - 20
        );

        // Check collision with other tanks
        if (!isPlayerTank) {
            for (Tank otherTank : tanks) {
                if (otherTank == playerTank) {
                    // Check intersection with the playerTank's bounds
                    if (adjustedBounds.intersects(otherTank.getBoundsInParent())) {
                        valid = false;
                        break;
                    }
                }
            }
        }


        // Check collision with walls
        for (javafx.scene.Node child : getChildren()) {
            if (child instanceof Wall) {
                // Check intersection with the wall's bounds
                if (adjustedBounds.intersects(child.getBoundsInParent())) {
                    valid = false;
                    break;
                }
            }
        }

        // Reset the tank to its original position
        tank.setTranslateX(originalX);
        tank.setTranslateY(originalY);

        return valid;
    }


    public void createMissile(Point2D position, double angle, String owner) {
        if (!running) return;
        Missile missile = MissileFactory.createMissile(position, angle, owner);
        missiles.add(missile);
        getChildren().add(missile);
    }

    private void updateMissiles() {
        if (!running) return;
        Iterator<Missile> missileIterator = missiles.iterator();
        while (missileIterator.hasNext()) {
            Missile missile = missileIterator.next();
            missile.move();

            if (isOutOfBounds(missile)) {
                missileIterator.remove();
                getChildren().remove(missile);
                continue;
            }

            // Handle missile collisions with tanks and walls
            handleMissileCollisions(missileIterator, missile);
        }
    }


    private void handleMissileCollisions(Iterator<Missile> missileIterator, Missile missile) {
        if (!running) return;

        // Shrink bounds for the missile
        Bounds missileBounds = missile.getBoundsInParent();
        Bounds adjustedMissileBounds = new BoundingBox(
                missileBounds.getMinX() + 5,
                missileBounds.getMinY() + 5,
                missileBounds.getWidth() - 10,
                missileBounds.getHeight() - 10
        );

        for (Tank tank : tanks) {
            // Shrink bounds for the tank
            Bounds tankBounds = tank.getBoundsInParent();
            Bounds adjustedTankBounds = new BoundingBox(
                    tankBounds.getMinX() + 10,
                    tankBounds.getMinY() + 10,
                    tankBounds.getWidth() - 20,
                    tankBounds.getHeight() - 20
            );

            if (tank != playerTank && missile.isPlayerOwner() && adjustedTankBounds.intersects(adjustedMissileBounds)) {
                tank.takeDamage(missile.getDamage());
                missileIterator.remove();
                getChildren().remove(missile);
                break;
            } else if (tank == playerTank && !missile.isPlayerOwner() && adjustedTankBounds.intersects(adjustedMissileBounds)) {
                tank.takeDamage(missile.getDamage());
                missileIterator.remove();
                getChildren().remove(missile);
                break;
            }
        }

        for (javafx.scene.Node child : getChildren()) {
            if (child instanceof Wall) {
                // Shrink bounds for the wall
                Bounds wallBounds = child.getBoundsInParent();
                Bounds adjustedWallBounds = new BoundingBox(
                        wallBounds.getMinX() + 5,
                        wallBounds.getMinY() + 5,
                        wallBounds.getWidth() - 10,
                        wallBounds.getHeight() - 10
                );

                if (adjustedWallBounds.intersects(adjustedMissileBounds)) {
                    missileIterator.remove();
                    getChildren().remove(missile);
                    break;
                }
            }
        }
    }


    private void checkMedPackCollisions() {
        if (!running) return;
        Iterator<MedPack> medPackIterator = medPacks.iterator();
        while (medPackIterator.hasNext()) {
            MedPack medPack = medPackIterator.next();
            if (playerTank.getBoundsInParent().intersects(medPack.getBoundsInParent())) {
                playerTank.setHealth(Math.min(playerTank.getHealth() + medPack.getHealAmount(), Constants.MAX_HEALTH));
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
        gameLoop.stop();
        this.medPackSpawnTimer.stop();

        WinnerScreen winnerScreen = new WinnerScreen();
        getChildren().clear();
        getChildren().add(winnerScreen);

        winnerScreen.setOnMouseClicked(e -> {
            TitleScreen titleScreen = new TitleScreen(primaryStage);
            Scene titleScene = new Scene(titleScreen, 800, 600);
            primaryStage.setScene(titleScene);
            primaryStage.show();
        });
    }
}
