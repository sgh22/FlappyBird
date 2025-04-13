package hi.flappybird.vidmot;

import hi.flappybird.vinnsla.HighScore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.io.IOException;

public class MainMenuController {

    @FXML
    private Label highScoreLabel;

    @FXML
    private Button themesButton;


    @FXML
    public void initialize() {
        int highScore = HighScore.getHighScore();
        highScoreLabel.setText("High Score: " + highScore);
    }

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

    @FXML
    private void onThemesButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/hi/flappybird/themes.fxml"));
        Scene themeScene = new Scene(loader.load(), 400, 600);
        Stage stage = (Stage) ((Node) themesButton).getScene().getWindow();
        stage.setScene(themeScene);
    }

    @FXML
    private void onThemes(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/hi/flappybird/themes.fxml"));
        Scene scene = new Scene(loader.load(), 400, 600);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


}
