package hi.flappybird.vinnsla;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ObstaclesHandler {

    private AnchorPane plane;
    private double planeHeight;
    private double planeWidth;

    private final Random random = new Random();
    private final Image obstacleImage;

    public ObstaclesHandler(AnchorPane plane, double planeHeight, double planeWidth) {
        this.plane = plane;
        this.planeHeight = planeHeight;
        this.planeWidth = planeWidth;

        String selectedTheme = SelectedTheme.getTheme();
        String pipePath;

        if ("blue".equals(selectedTheme) || "halloween".equals(selectedTheme)) {
            pipePath = "/images/pipe2.png";
        } else {
            pipePath = "/images/pipe1.png";
        }

        obstacleImage = new Image(getClass().getResource(pipePath).toExternalForm());
    }

    public ArrayList<ImageView> createObstacles() {
        int width = 25;
        double xPos = planeWidth;
        double space = 200;
        double topHeight = random.nextInt((int)(planeHeight - space - 100)) + 50;
        double bottomHeight = planeHeight - space - topHeight;

        ImageView topPipe = new ImageView(obstacleImage);
        topPipe.setFitWidth(width);
        topPipe.setFitHeight(topHeight);
        topPipe.setX(xPos);
        topPipe.setY(0);

        ImageView bottomPipe = new ImageView(obstacleImage);
        bottomPipe.setFitWidth(width);
        bottomPipe.setFitHeight(bottomHeight);
        bottomPipe.setX(xPos);
        bottomPipe.setY(topHeight + space);
        bottomPipe.setScaleY(-1);

        topPipe.getProperties().put("scoreZone", true);

        plane.getChildren().addAll(topPipe, bottomPipe);
        return new ArrayList<>(Arrays.asList(topPipe, bottomPipe));

    }

    public void moveObstacles(ArrayList<? extends ImageView> obstacles) {
        ArrayList<ImageView> outOfScreen = new ArrayList<>();

        for (ImageView obstacle : obstacles) {
            moveObstacle(obstacle, -0.75);

            if (obstacle.getX() <= -obstacle.getFitWidth()) {
                outOfScreen.add(obstacle);
            }
        }

        obstacles.removeAll(outOfScreen);
        plane.getChildren().removeAll(outOfScreen);
    }

    private void moveObstacle(ImageView obstacle, double amount) {
        obstacle.setX(obstacle.getX() + amount);

    }
}



