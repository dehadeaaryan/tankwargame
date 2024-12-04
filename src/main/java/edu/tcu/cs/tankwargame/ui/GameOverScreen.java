package edu.tcu.cs.tankwargame.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameOverScreen extends StackPane {

    public GameOverScreen() {
        // Create the "Game Over" message
        Text gameOverText = new Text("Game Over");
        gameOverText.setFont(new Font(40));
        gameOverText.setStyle("-fx-fill: white;");

        // Create a button to go back to the title screen
        Button restartButton = new Button("Back to Title");
        restartButton.setFont(new Font(20));
        restartButton.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-padding: 10 20; -fx-border-radius: 0;");

        // Action for the "Restart" button: go back to the title screen
        restartButton.setOnAction(e -> restartGame());

        // Position the text and button in the center
        StackPane.setAlignment(gameOverText, javafx.geometry.Pos.CENTER);
        StackPane.setAlignment(restartButton, javafx.geometry.Pos.BOTTOM_CENTER);
        getChildren().addAll(gameOverText, restartButton);

        // Set background color for the game over screen
        setStyle("-fx-background-color: #000;");

        // Ensure the pane fills the entire screen
        setPrefSize(800, 600); // Or use Constants.GAME_WIDTH/HEIGHT dynamically
    }

    private void restartGame() {
        Stage primaryStage = (Stage) getScene().getWindow();
        TitleScreen titleScreen = new TitleScreen(primaryStage); // Go to title screen
        Scene titleScene = new Scene(titleScreen, 800, 600);
        primaryStage.setScene(titleScene);
        primaryStage.show();
    }
}
