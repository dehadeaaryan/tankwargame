package edu.tcu.cs.tankwargame.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WinnerScreen extends StackPane {

    public WinnerScreen() {
        // Create the "You Win!" message
        Text winText = new Text("You Win!");
        winText.setFont(new Font(40));
        winText.setStyle("-fx-fill: white;");

        // Create a button to go back to the title screen or restart the game
        Button restartButton = new Button("Back to Title");
        restartButton.setFont(new Font(20));
        restartButton.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-padding: 10 20; -fx-border-radius: 0;");

        // Action for the "Restart" button: go back to the title screen (or you can restart the game here)
        restartButton.setOnAction(e -> restartGame());

        // Position the text and button in the center
        StackPane.setAlignment(winText, javafx.geometry.Pos.CENTER);
        StackPane.setAlignment(restartButton, javafx.geometry.Pos.BOTTOM_CENTER);
        getChildren().addAll(winText, restartButton);

        // Set background color for the winner screen
        setStyle("-fx-background-color: #000;");

        // Ensure the pane fills the entire screen
        setPrefSize(800, 600); // Or use Constants.GAME_WIDTH/HEIGHT if you want to set this dynamically
    }

    private void restartGame() {
        // Here you can choose to go back to the title screen or restart the game
        // This example assumes you have a main stage to work with.
        Stage primaryStage = (Stage) getScene().getWindow();
        TitleScreen titleScreen = new TitleScreen(primaryStage); // Go to title screen (you can modify it to restart the game)
        Scene titleScene = new Scene(titleScreen, 800, 600);
        primaryStage.setScene(titleScene);
        primaryStage.show();
    }
}