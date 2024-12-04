package edu.tcu.cs.tankwargame.utils;

public class Constants {
    // Window size
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;

    // Tank settings
    public static final int PLAYER_TANK_SPEED = 3;
    public static final double PLAYER_TANK_ROTATION_SPEED = 5.0;
    public static final double ENEMY_TANK_ROTATION_SPEED = 1;
    public static final int ENEMY_TANK_SPEED = 1;
    public static final int MISSILE_SPEED = 5;

    // Health points
    public static final int MAX_HEALTH = 500;
    public static final int DAMAGE_PER_MISSILE = 25;

    // Game settings
    public static final int INITIAL_ENEMY_TANKS = 3;
    public static final int MEDPACK_HEAL_AMOUNT = 50;

    // Other constants
    public static final String PLAYER_TANK_IMAGE = "/edu/tcu/cs/tankwargame/assets/player_tank.png";
    public static final String ENEMY_TANK_IMAGE = "/edu/tcu/cs/tankwargame/assets/enemy_tank.png";
    public static final String MISSILE_IMAGE = "/edu/tcu/cs/tankwargame/assets/missile.png";
    public static final String EXPLOSION_IMAGE = "/edu/tcu/cs/tankwargame/assets/explosion.png";
    public static final String MEDPACK_IMAGE = "/edu/tcu/cs/tankwargame/assets/medpack.png";
    public static final String WALL_IMAGE = "/edu/tcu/cs/tankwargame/assets/wall.png";

    // Missile firing cooldown time (in milliseconds)
    public static final long MISSILE_COOLDOWN = 500; // 500 ms = 0.5 seconds
}
