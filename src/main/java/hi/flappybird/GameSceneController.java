package hi.flappybird;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.TextField;
import javafx.application.Platform;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

/**
 * Controller fyrir leikjaskjáinn í Flappy Bird.
 * Heldur utan um fuglinn, stig, hindranir og hvernig leikurinn keyrir.
 */
public class GameSceneController implements Initializable {

    AnimationTimer gameLoop;

    @FXML
    private AnchorPane plane;

    @FXML
    private Rectangle bird;

    @FXML
    private Label gameOverLabel;

    @FXML
    private Button restartButton;

    @FXML
    private Button backToMenuButton;


    @FXML
    private TextField score;

    private double accelerationTime = 0;
    private int gameTime = 0;
    private int scoreCounter = 0;

    private BirdMovement birdComponent;
    private ObstaclesHandler obstaclesHandler;

    ArrayList<Rectangle> obstacles = new ArrayList<>();
    /**
     * Byrjar leikinn: Setur upp fuglinn, leikflöt, og hindranir.
     * Keyrir leik-loopið
     *
     * @param url ekki notað
     * @param resourceBundle ekki notað
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        int jumpHeight = 75;
        birdComponent = new BirdMovement(bird, jumpHeight);
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

    }
    /**
     * Bregst við þegar ýtt er á takka. Fuglinn hoppar þegar ýtt er á spacebar.
     *
     * @param event takki sem var ýtt á
     */

    @FXML
    void pressed(KeyEvent event) {
        if(event.getCode() == KeyCode.SPACE){
            birdComponent.fly();
            accelerationTime = 0;
        }
    }

    private void update() {
        gameTime++;
        accelerationTime++;
        double yDelta = 0.02;
        birdComponent.moveBirdY(yDelta * accelerationTime);

        if(pointChecker(obstacles, bird)){
            scoreCounter++;
            score.setText(String.valueOf(scoreCounter));
        }

        obstaclesHandler.moveObstacles(obstacles);
        if(gameTime % 500 == 0){
            obstacles.addAll(obstaclesHandler.createObstacles());
        }

        if(birdComponent.isBirdDead(obstacles, plane)){
            gameOver();
        }




    }
    /**
     * Setur fyrstu hindranir inn í leikinn þegar hann byrjar.
     */
    private void load(){
        obstacles.addAll(obstaclesHandler.createObstacles());
    }

    private void resetGame(){
        bird.setY(0);
        plane.getChildren().removeAll(obstacles);
        obstacles.clear();
        gameTime = 0;
        accelerationTime = 0;
        scoreCounter = 0;
        score.setText(String.valueOf(scoreCounter));

        load();

    }
    /**
     * Endurræsir leikinn:
     * - Setur fuglinn í upphafsstöðu
     * - Hreinsar hindranir
     * - Nollar stigin og tímamælingu
     * - Bætir nýjum hindrunum inn
     */
    private void gameOver() {
        gameLoop.stop(); //pása leikinn áa meðan GameOver kemur
        gameOverLabel.setVisible(true);
        restartButton.setVisible(true);
        backToMenuButton.setVisible(true);
    }
    /**
     * Hefur leikinn aftur:
     * - Kallar á resetGame()
     * - Felur Game Over viðmótið
     * - Byrjar aftur leik-loopið
     */
    @FXML
    private void restartGame() {
        resetGame();
        gameOverLabel.setVisible(false);
        restartButton.setVisible(false);
        backToMenuButton.setVisible(false);
        gameLoop.start();

        Platform.runLater(() -> plane.requestFocus());

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
    /**
     * Athugar hvort fuglinn hafi farið framhjá hindrun sem telst sem stig.
     * Notar "scoreZone" til að telja aðeins efri hindranir þá hækkar "score" bara um 1 ekki 2
     *
     * @param obstacles Listi af hindrunum sem eru á skjánum
     * @param bird Fuglalögunin sem notandinn stýrir
     * @return true ef fuglinn fer framhjá skoraðri hindrun, annars false
     */

    private boolean pointChecker(ArrayList<Rectangle> obstacles, Rectangle bird) {
        int birdX = (int) (bird.getLayoutX() + bird.getX());

        for (Rectangle obstacle : obstacles) {

            if (!Boolean.TRUE.equals(obstacle.getProperties().get("scoreZone"))) continue;

            int pipeRightEdge = (int) (obstacle.getLayoutX() + obstacle.getX() + obstacle.getWidth());


            if (birdX > pipeRightEdge &&
                    !obstacle.getProperties().containsKey("scored")) {

                obstacle.getProperties().put("scored", true);
                return true;
            }
        }

        return false;
    }
}


