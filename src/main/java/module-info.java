module hi.flappybird {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens hi.flappybird to javafx.fxml;
    exports hi.flappybird;
}