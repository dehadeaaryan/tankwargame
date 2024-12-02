package edu.tcu.cs.tankwargame.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TitleScreen extends StackPane {

    public TitleScreen(Stage primaryStage) {
        // Load the background image
        Image titleImage = new Image("file:src/main/resources/edu/tcu/cs/tankwargame/assets/title_screen.png");

        // Create an ImageView for the background
        ImageView backgroundImageView = new ImageView(titleImage);

        // Resize the image to fit the width of the screen, cropping top and bottom equally
        double imageWidth = 800;
        double imageHeight = titleImage.getHeight() * (imageWidth / titleImage.getWidth());
        backgroundImageView.setFitWidth(imageWidth);
        backgroundImageView.setFitHeight(imageHeight);
        backgroundImageView.setPreserveRatio(true);

        // Center the image vertically by adjusting the translateY to crop the top and bottom equally
        double cropHeight = (imageHeight - 600) / 2;
        backgroundImageView.setTranslateY(-cropHeight);

        // Ensure the image is centered horizontally by setting translateX to 0
        backgroundImageView.setTranslateX(0);

        // Add the ImageView to the layout
        getChildren().add(backgroundImageView);

        // Set the scene to have no extra nodes (only the image)
        setStyle("-fx-background-color: transparent;");

        // Load the "Play" button image
        Image playButtonImage = new Image("file:src/main/resources/edu/tcu/cs/tankwargame/assets/play_button.png");

        // Calculate the crop areas to show only the central 1000x800 part of the image
        double cropX = (playButtonImage.getWidth() - 1000) / 2;  // Crop 522px from each side (total of 1048px)
        double cropY = (playButtonImage.getHeight() - 800) / 2;  // Crop 624px from top and bottom (total of 1248px)

        // Create a WritableImage to manually crop the original image
        WritableImage croppedImage = new WritableImage(playButtonImage.getPixelReader(),
                (int) cropX, (int) cropY, 1000, 800);

        // Resize the cropped image to 512x512
        Image resizedImage = new Image("file:src/main/resources/edu/tcu/cs/tankwargame/assets/play_button.png", 400, 400, true, true);

        // Create an ImageView for the button and set it to the resized image
        ImageView playButtonImageView = new ImageView(resizedImage);

        // Create the button with no text, only the image
        Button playButton = new Button();
        playButton.setGraphic(playButtonImageView); // Set the resized image as the button graphic

        // Ensure the button's image fits perfectly
        playButton.setStyle("-fx-background-color: transparent; -fx-padding: -100; -fx-border-radius: 0;");

        // Add a shadow effect to the button
        DropShadow shadow = new DropShadow();
        shadow.setOffsetX(3);
        shadow.setOffsetY(3);
        shadow.setRadius(5);
        playButton.setEffect(shadow);

        // Align the play button to the center of the screen, but slightly lower
        StackPane.setAlignment(playButton, Pos.BOTTOM_CENTER);
        playButton.setTranslateY(-24);  // Move the button 50px lower

        // Add the button to the layout
        getChildren().add(playButton);

        // Set the action for the "Play" button
        playButton.setOnAction(e -> {
            // Once the button is clicked, switch to the GameUI
            GameUI gameUI = new GameUI(primaryStage);
            Scene gameScene = new Scene(gameUI, 800, 600);
            primaryStage.setScene(gameScene);
            primaryStage.show();
            gameUI.requestFocus();  // Ensure the game UI is ready to capture keyboard input
        });
    }
}
