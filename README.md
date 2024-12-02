# Tank War Game - Documentation

## **Project Overview**

The **Tank War Game** is a 2D action game inspired by the classic *Battle City*. In this game, the player controls a tank to destroy enemy tanks while avoiding their attacks. Using JavaFX, the game showcases core Object-Oriented Programming (OOP) principles, such as encapsulation, inheritance, and polymorphism, and implements several design patterns to ensure scalability and maintainability.

---

## **Features**

### **Core Gameplay**
1. **Player-Controlled Tank**:
    - Moves in four directions (up, down, left, right) using keyboard input.
    - Fires missiles to destroy enemy tanks.
    - Loses health when hit by enemy missiles.

2. **Enemy Tanks**:
    - Controlled by a basic AI.
    - Attack the player’s tank and move around the map.

3. **Game Mechanics**:
    - Tanks can’t pass through walls or screen edges.
    - Missiles move in the direction they were fired and damage tanks.

4. **MedPacks**:
    - Restore full health to tanks when collected.

5. **Explosions**:
    - Triggered when tanks are destroyed, providing visual feedback.

6. **Winning and Losing Conditions**:
    - Win: Destroy all enemy tanks.
    - Lose: The player’s tank is destroyed.

---

## **Design Principles**

1. **Object-Oriented Design (OOD)**:
    - Core game objects include `Tank`, `Missile`, `Wall`, `MedPack`, and `Explosion`.
    - Use of encapsulation, inheritance, and polymorphism for modular and reusable code.

2. **Design Patterns**:
    - **Factory Pattern**: To create game objects (e.g., tanks, missiles, walls).
    - **Strategy Pattern**: For flexible movement and behavior (e.g., player-controlled vs. AI tanks).
    - **Observer Pattern**: To notify components (e.g., GUI updates when tanks are destroyed).
    - **Singleton Pattern**: To manage shared resources (e.g., game state or map).

---

## **Graphical User Interface (GUI)**

1. **Game Area**:
    - Displays the game environment where tanks move and interact.
    - Includes walls, medpacks, and enemy tanks.

2. **HUD (Heads-Up Display)**:
    - Health bar for the player’s tank.
    - Score counter to show remaining enemies and player’s lives.

---

## **How to Run the Game**

1. **Prerequisites**:
    - Java JDK 17 or higher (ensure JavaFX is compatible with your version).
    - Maven for project management.

2. **Steps**:
    - Clone the repository to your local machine.
    - Navigate to the project directory and run the following Maven command:
      ```
      mvn javafx:run
      ```
    - Alternatively, you can import the project into IntelliJ IDEA, build the project, and run the main application class.

3. **Controls**:
    - Use arrow keys to move the player tank.
    - Press `Space` to fire missiles.

---

## Project Structure

The project is organized into the following structure:

- `src/`
   - `main/`
      - `java/`
         - `edu/tcu/cs/tankwargame/`
            - `App.java`
               - Main entry point of the application that launches the game.
            - `entities/`
               - Contains core game objects:
                  - `PlayerTank.java`
                     - Represents the player-controlled tank.
                  - `EnemyTank.java`
                     - Represents the enemy-controlled tanks.
                  - `Missile.java`
                     - Represents a missile fired by the tanks.
                  - `Wall.java`
                     - Represents indestructible walls in the game.
                  - `MedPack.java`
                     - Represents health packs that restore tank health.
                  - `Explosion.java`
                     - Represents the explosion effect when a tank is destroyed.
                  - `Direction.java`
                     - Enum or class for tank movement directions (up, down, left, right).
            - `utils/`
               - Contains utility classes:
                  - `Constants.java`
                     - Holds constant values used throughout the game.
                  - `Resources.java`
                     - Handles loading resources such as images and sounds.
            - `factories/`
               - Contains factory classes for creating game objects:
                  - `TankFactory.java`
                     - Creates different types of tanks (player-controlled, enemy).
                  - `MissileFactory.java`
                     - Creates missiles for the player and enemy tanks.
                  - `WallFactory.java`
                     - Creates walls for the game map.
            - `ai/`
               - Contains AI logic for enemy tank behavior:
                  - `EnemyAI.java`
                     - Implements basic AI for enemy tanks (e.g., random movement, firing at the player).
      - `resources/`
         - `edu/tcu/cs/tankwargame/`
            - `assets/`
               - Contains game assets like images and sounds:
                  - `player_tank.png`
                     - Image of the player tank.
                  - `enemy_tank.png`
                     - Image of the enemy tank.
                  - `missile.png`
                     - Image of a missile.
                  - `explosion.png`
                     - Image for the explosion effect.
                  - `medpack.png`
                     - Image for health pack.
                  - `wall.png`
                     - Image for a single block of wall.
            - `application.css`
               - Stylesheet for customizing the appearance of the game (optional).
            - `game_layout.fxml`
               - Optional FXML file for more advanced game layout (used for JavaFX GUI).

---

## **Advanced Features**

### **Bonus Additions**:
- **Destructible Walls**:
    - Implement walls that can be destroyed by missiles.
- **Advanced AI**:
    - Smarter enemy tanks that coordinate attacks and chase the player.
- **Power-Ups**:
    - Add items to boost speed or grant temporary invincibility.

---

## **Future Enhancements**

- Multiplayer support with networked gameplay.
- Different tank models with unique abilities.
- Level editor for creating custom maps.

---

## **Credits**

Game developed by **Aaryan Dehade**. Inspired by the classic *Battle City*.
