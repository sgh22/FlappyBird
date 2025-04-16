package hi.flappybird.vidmot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FlappyBirdApplication extends Application {
    /**
     * @param stage Glugginn sem leikurinn keyrir í
     * @throws IOException ef tekst ekki að hlaða main-menu.fxml
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FlappyBirdApplication.class.getResource("/hi/flappybird/main-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 600);
        stage.setScene(scene);
        stage.setResizable(false); //þannig að pane haldi stærð, breytist ekki
        stage.show();
    }
    /**
     * Main fallið sem keyrir forritið. Ræsir JavaFX með launch().
     */
    public static void main(String[] args) {

        launch();
    }
}