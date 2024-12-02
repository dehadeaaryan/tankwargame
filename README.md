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

## **Project Structure**

src/
├── main/
│   ├── java/
│   │   ├── me.aaryandehade/
│   │   │   ├── App.java          # Main entry point of the application
│   │   │   ├── entities/         # Core game objects
│   │   │   │   ├── Tank.java
│   │   │   │   ├── Missile.java
│   │   │   │   ├── Wall.java
│   │   │   │   ├── MedPack.java
│   │   │   │   ├── Explosion.java
│   │   │   │   ├── Direction.java
│   │   │   ├── utils/            # Utility classes
│   │   │   │   ├── Constants.java
│   │   │   │   ├── Resources.java
│   │   │   ├── factories/        # Factory classes
│   │   │   │   ├── TankFactory.java
│   │   │   │   ├── MissileFactory.java
│   │   │   │   ├── WallFactory.java
│   │   │   ├── ai/               # AI for enemy tanks
│   │   │   │   ├── EnemyAI.java
│   ├── resources/
│   │   ├── assets/               # Game assets (images, sounds)
│   │   │   ├── tank.png
│   │   │   ├── enemy_tank.png
│   │   │   ├── missile.png
│   │   │   ├── explosion.png
│   │   │   ├── medpack.png
│   │   ├── application.css       # Stylesheet for GUI
│   │   ├── game_layout.fxml      # Optional FXML for advanced layouts
└── test/                         # Test directory


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
