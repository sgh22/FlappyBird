module hi.flappybird {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.prefs;



    opens hi.flappybird to javafx.fxml;
    exports hi.flappybird.vinnsla;
    opens hi.flappybird.vinnsla to javafx.fxml;
    exports hi.flappybird.vidmot;
    opens hi.flappybird.vidmot to javafx.fxml;
}