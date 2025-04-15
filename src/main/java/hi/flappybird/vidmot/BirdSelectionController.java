package hi.flappybird.vidmot;

import hi.flappybird.vinnsla.SelectedBird;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Node;

import java.io.IOException;

public class BirdSelectionController {

    @FXML
    private ImageView previewImage;

    @FXML
    private void choosePinkBird(ActionEvent event) throws IOException {
        SelectedBird.setSelectedBird("pink");
        updatePreview("/images/pinkbird1.png");
        goBackToMainMenu(event);
    }

    @FXML
    private void chooseBlueBird(ActionEvent event) throws IOException {
        SelectedBird.setSelectedBird("blue");
        updatePreview("/images/bluebird1.png");
        goBackToMainMenu(event);
    }

    @FXML
    private void chooseDollyBird(ActionEvent event) throws IOException {
        SelectedBird.setSelectedBird("dolly");
        updatePreview("/images/DollyBird1.png");
        goBackToMainMenu(event);
    }

    private void updatePreview(String imagePath) {
        Image birdImage = new Image(getClass().getResourceAsStream(imagePath));
        previewImage.setImage(birdImage);
    }

    private void goBackToMainMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/hi/flappybird/main-menu.fxml"));
        Scene scene = new Scene(loader.load(), 400, 600);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}

