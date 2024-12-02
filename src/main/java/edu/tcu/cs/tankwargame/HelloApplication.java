package edu.tcu.cs.tankwargame;

import edu.tcu.cs.tankwargame.ui.GameUI;
import edu.tcu.cs.tankwargame.ui.TitleScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        // Create the title screen UI
        TitleScreen titleScreen = new TitleScreen(primaryStage);

        // Create a scene and set the size for the title screen
        Scene titleScene = new Scene(titleScreen, 800, 600);

        // Set title and icon for the stage
        primaryStage.setTitle("Tank War Game");
        primaryStage.getIcons().add(new Image("file:src/main/resources/edu/tcu/cs/tankwargame/icons/tank_icon.png"));
        primaryStage.setScene(titleScene);
        primaryStage.show();

        // Request focus for the title screen to capture key events if necessary
        titleScreen.requestFocus();
    }
}
