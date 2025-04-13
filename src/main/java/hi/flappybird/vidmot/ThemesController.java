package hi.flappybird.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import hi.flappybird.vinnsla.SelectedTheme;

import java.io.IOException;

public class ThemesController {

    @FXML
    private void choosePink(ActionEvent event) throws IOException {
        SelectedTheme.setTheme("pink");
        goBackToMainMenu(event);
    }

    @FXML
    private void chooseBlue(ActionEvent event) throws IOException {
        SelectedTheme.setTheme("blue");
        goBackToMainMenu(event);
    }

    @FXML
    private void chooseHalloween(ActionEvent event) throws IOException {
        SelectedTheme.setTheme("halloween");
        goBackToMainMenu(event);
    }

    private void goBackToMainMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/hi/flappybird/main-menu.fxml"));
        Scene scene = new Scene(loader.load(), 400, 600);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show(); // Don't forget to show the stage!
    }
}
