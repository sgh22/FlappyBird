package hi.flappybird.vinnsla;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;


public abstract class Bird {

    private ImageView birdView;
    private List<Image> birdFrames = new ArrayList<>();
    private int currentFrame = 0;
    private double locationX = 70;
    private double locationY = 200;
    private int BIRD_WIDTH = 50;
    private int BIRD_HEIGHT = 45;
    private Timeline animation;

    public Bird(String[] imagePaths) {
        loadFrames(imagePaths);
        birdView = new ImageView(birdFrames.get(0));
        birdView.setFitWidth(BIRD_WIDTH);
        birdView.setFitHeight(BIRD_HEIGHT);
        birdView.setX(locationX);
        birdView.setY(locationY);
        startAnimation();
    }

    private void loadFrames(String[] paths) {
        for (String path : paths) {
            birdFrames.add(new Image(getClass().getResourceAsStream(path)));
        }
    }

    private void startAnimation() {
        animation = new Timeline(new KeyFrame(Duration.seconds(0.15), e -> {
            currentFrame = (currentFrame + 1) % birdFrames.size();
            birdView.setImage(birdFrames.get(currentFrame));
        }));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }

    public ImageView getView() {
        return birdView;
    }

}
