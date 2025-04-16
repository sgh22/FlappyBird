package hi.flappybird.vidmot;

import hi.flappybird.vinnsla.SelectedTheme;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ThemesController {

    @FXML
    private void setPink(ActionEvent event) throws IOException {
        SelectedTheme.setTheme("pink");
        goToMainMenu(event);
    }

    @FXML
    private void setBlue(ActionEvent event) throws IOException {
        SelectedTheme.setTheme("blue");
        goToMainMenu(event);
    }

    @FXML
    private void setHalloween(ActionEvent event) throws IOException {
        SelectedTheme.setTheme("halloween");
        goToMainMenu(event);
    }

    private void goToMainMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/hi/flappybird/main-menu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
