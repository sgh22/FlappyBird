package hi.flappybird.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import hi.flappybird.vinnsla.SelectedBird;

import java.io.IOException;

public class BirdSelectionController {

    @FXML
    private void choosePinkBird(ActionEvent event) throws IOException {
        SelectedBird.setSelectedBird("pink");
        goBackToMainMenu(event);
    }

    @FXML
    private void chooseBlueBird(ActionEvent event) throws IOException {
        SelectedBird.setSelectedBird("blue");
        goBackToMainMenu(event);
    }

    @FXML
    private void chooseDollyBird(ActionEvent event) throws IOException {
        SelectedBird.setSelectedBird("dolly");
        goBackToMainMenu(event);
    }

    private void goBackToMainMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/hi/flappybird/main-menu.fxml"));
        Scene scene = new Scene(loader.load(), 400, 600);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
