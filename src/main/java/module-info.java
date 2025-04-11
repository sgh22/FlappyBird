module hi.flappybird {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.prefs;



    opens hi.flappybird to javafx.fxml;
    exports hi.flappybird;
}