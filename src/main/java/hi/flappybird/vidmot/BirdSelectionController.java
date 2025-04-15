package hi.flappybird.vidmot;

import hi.flappybird.vinnsla.SelectedBird;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Node;

import java.io.IOException;


public class BirdSelectionController {

    @FXML
    private ImageView pinkBirdImage;

    @FXML
    private ImageView blueBirdImage;

    @FXML
    private ImageView dollyBirdImage;

    @FXML
    private ImageView previewImage;

    private String selectedBird;

    @FXML
    public void initialize() {
        pinkBirdImage.setImage(new Image(getClass().getResourceAsStream("/images/pinkbird1.png")));
        blueBirdImage.setImage(new Image(getClass().getResourceAsStream("/images/bluebird1.png")));
        dollyBirdImage.setImage(new Image(getClass().getResourceAsStream("/images/DollyBird1.png")));
    }


    private void clearSelectionStyles() {
        pinkBirdImage.getStyleClass().remove("selected-bird");
        blueBirdImage.getStyleClass().remove("selected-bird");
        dollyBirdImage.getStyleClass().remove("selected-bird");
    }


    @FXML
    private void previewPinkBird() {
        clearSelectionStyles();
        pinkBirdImage.getStyleClass().add("selected-bird");
        updatePreview("/images/pinkbird1.png");
        selectedBird = "pink";
    }


    @FXML
    private void previewBlueBird() {
        clearSelectionStyles();
        blueBirdImage.getStyleClass().add("selected-bird");
        updatePreview("/images/bluebird1.png");
        selectedBird = "blue";
    }

    @FXML
    private void previewDollyBird() {
        clearSelectionStyles();
        dollyBirdImage.getStyleClass().add("selected-bird");
        updatePreview("/images/DollyBird1.png");
        selectedBird = "dolly";
    }



    private void updatePreview(String imagePath) {
        Image birdImage = new Image(getClass().getResourceAsStream(imagePath));
        previewImage.setImage(birdImage);
    }
    @FXML
    private void confirmSelection(ActionEvent event) throws IOException {
        if (selectedBird != null) {
            SelectedBird.setSelectedBird(selectedBird);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/hi/flappybird/main-menu.fxml"));
            Scene scene = new Scene(loader.load(), 400, 600);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

}

