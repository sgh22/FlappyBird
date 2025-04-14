package hi.flappybird.vidmot;

import hi.flappybird.vinnsla.*;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.layout.VBox;
import hi.flappybird.vinnsla.BlueBird;
import hi.flappybird.vinnsla.DollyBird;
import hi.flappybird.vinnsla.PinkBird;



/**
 * Controller fyrir leikjaskjáinn í Flappy Bird.
 * Heldur utan um fuglinn, stig, hindranir og hvernig leikurinn keyrir.
 */
public class GameSceneController implements Initializable {

    AnimationTimer gameLoop;

    @FXML
    private AnchorPane plane;

    @FXML
    private Label gameOverLabel;

    @FXML
    private Button restartButton;

    @FXML
    private Button backToMenuButton;

    @FXML
    private TextField score;

    @FXML
    private VBox gameOverUI;


    private Bird bird;
    private BirdMovement birdComponent;
    private ObstaclesHandler obstaclesHandler;

    private final ArrayList<ImageView> obstacles = new ArrayList<>();
    private int gameTime = 0;
    private int scoreCounter = 0;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image bgImage = new Image(getClass().getResourceAsStream("/images/background1.png"));
        ImageView bgView = new ImageView(bgImage);

        bgView.setPreserveRatio(false);
        bgView.setSmooth(true);

        bgView.fitWidthProperty().bind(plane.widthProperty());
        bgView.fitHeightProperty().bind(plane.heightProperty());

        plane.getChildren().add(0, bgView);

        int jumpHeight = 75;

        String selected = SelectedBird.getSelectedBird();
        if ("blue".equals(selected)) {
            bird = new BlueBird();
        } else if ("dolly".equals(selected)) {
            bird = new DollyBird();
        } else {
            bird = new PinkBird();
        }
        plane.getChildren().add(bird.getView());
        birdComponent = new BirdMovement(bird.getView(), jumpHeight);


        double planeHeight = 600;
        double planeWidth = 400;
        obstaclesHandler = new ObstaclesHandler(plane, planeHeight, planeWidth);

        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();
            }
        };

        load();

        gameLoop.start();
        plane.setFocusTraversable(true);
        Platform.runLater(() -> plane.requestFocus());

        gameOverLabel.toFront();
        score.toFront();
        restartButton.toFront();
        backToMenuButton.toFront();

        String selectedTheme = SelectedTheme.getTheme();

        switch (selectedTheme) {
            case "pink":
                plane.setStyle("-fx-background-image: url('/images/pink.png'); -fx-background-size: cover;");
                break;
            case "blue":
                plane.setStyle("-fx-background-image: url('/images/blue.png'); -fx-background-size: cover;");
                break;
            case "halloween":
                plane.setStyle("-fx-background-image: url('/images/halloween.png'); -fx-background-size: cover;");
                break;
            default:
                plane.setStyle("-fx-background-color: lightblue;");
                break;
        }

    }

    private void update() {
        gameTime++;
        birdComponent.applyGravity();

        if (pointChecker(obstacles, bird.getView())) {
            scoreCounter++;
            score.setText(String.valueOf(scoreCounter));
        }

        obstaclesHandler.moveObstacles(obstacles);
        if (gameTime % 200 == 0) {
            obstacles.addAll(obstaclesHandler.createObstacles());

            gameOverLabel.toFront();
            score.toFront();
            restartButton.toFront();
            backToMenuButton.toFront();
        }


        if (birdComponent.isBirdDead(obstacles, plane)) {
            gameOver();
        }
    }

    @FXML
    private void pressed(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            birdComponent.fly();
        }
    }

    private void load() {
        obstacles.addAll(obstaclesHandler.createObstacles());
    }

    private void resetGame() {

        plane.getChildren().removeAll(obstacles);
        obstacles.clear();

        if (bird != null) {
            plane.getChildren().remove(bird.getView());
        }

        String selected = SelectedBird.getSelectedBird();
        if ("blue".equals(selected)) {
            bird = new BlueBird();
        } else if ("dolly".equals(selected)) {
            bird = new DollyBird();
        } else {
            bird = new PinkBird();
        }
        plane.getChildren().add(bird.getView());
        birdComponent = new BirdMovement(bird.getView(), 75);


        gameTime = 0;
        scoreCounter = 0;
        score.setText(String.valueOf(scoreCounter));

        load();
        Platform.runLater(() -> plane.requestFocus());

    }
    private void gameOver() {
        gameLoop.stop();
        gameOverLabel.setVisible(true);
        restartButton.setVisible(true);
        backToMenuButton.setVisible(true);

        Platform.runLater(() -> {
            score.toFront();
            gameOverUI.toFront();
        });

        if (scoreCounter > HighScore.getHighScore()) {
            HighScore.setHighScore(scoreCounter);
        }
    }



    @FXML
    private void restartGame() {
        resetGame();
        gameOverLabel.setVisible(false);
        restartButton.setVisible(false);
        backToMenuButton.setVisible(false);
        gameLoop.start();
        Platform.runLater(() -> plane.requestFocus());

        Platform.runLater(() -> {
            score.toFront();
            gameOverUI.toFront();
        });



    }

    @FXML
    private void backToMenu() {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("main-menu.fxml"));
            javafx.scene.Parent root = loader.load();
            javafx.stage.Stage stage = (javafx.stage.Stage) plane.getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean pointChecker(ArrayList<ImageView> obstacles, Node bird) {
        int birdX = (int) bird.getBoundsInParent().getMinX();

        for (ImageView obstacle : obstacles) {

            if (!Boolean.TRUE.equals(obstacle.getProperties().get("scoreZone"))) continue;

            int pipeRightEdge = (int) (obstacle.getX() + obstacle.getFitWidth());

            if (birdX > pipeRightEdge && !obstacle.getProperties().containsKey("scored")) {
                obstacle.getProperties().put("scored", true);
                return true;
            }
        }
        return false;
    }
}






