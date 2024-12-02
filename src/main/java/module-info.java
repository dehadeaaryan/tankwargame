module edu.tcu.cs.tankwargame {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens edu.tcu.cs.tankwargame to javafx.fxml;
    exports edu.tcu.cs.tankwargame;
    exports edu.tcu.cs.tankwargame.ui;
    opens edu.tcu.cs.tankwargame.ui to javafx.fxml;
}