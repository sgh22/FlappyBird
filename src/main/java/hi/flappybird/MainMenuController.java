package hi.flappybird;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    /**
     * Skiptir yfir á game-scene.fxml þegar ýtt er á PLAY hnappinn.
     *
     * @param event Atburður þegar notandi ýtir á hnapp
     * @throws IOException ef það tekst ekki að hlaða FXML skrána
     */

    public void switchToGame(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/hi/flappybird/game-scene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
   }