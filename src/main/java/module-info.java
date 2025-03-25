module hi.flappybird {
    requires javafx.controls;
    requires javafx.fxml;


    opens hi.flappybird to javafx.fxml;
    exports hi.flappybird;
}