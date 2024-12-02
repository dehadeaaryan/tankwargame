module edu.tcu.cs.tankwargame {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.tcu.cs.tankwargame to javafx.fxml;
    exports edu.tcu.cs.tankwargame;
}