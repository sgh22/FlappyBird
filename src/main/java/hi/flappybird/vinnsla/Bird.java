package hi.flappybird.vinnsla;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;

public class Bird {

    private ImageView birdView;
    private ArrayList<Image> birdFrames = new ArrayList<>();
    private int currentFrame = 0;
    private double locationX = 70;
    private double locationY = 200;
    private int BIRD_WIDTH = 50;
    private int BIRD_HEIGHT = 45;
    private Timeline animation;

    public Bird() {
        loadFrames();
        birdView = new ImageView(birdFrames.get(0));
        birdView.setFitWidth(BIRD_WIDTH);
        birdView.setFitHeight(BIRD_HEIGHT);
        birdView.setX(locationX);
        birdView.setY(locationY);

        startAnimation();
    }

    private void loadFrames() {
        birdFrames.add(new Image(getClass().getResourceAsStream("/images/pinkbird1.png")));
        birdFrames.add(new Image(getClass().getResourceAsStream("/images/pinkbird2.png")));
        birdFrames.add(new Image(getClass().getResourceAsStream("/images/pinkbird3.png")));
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

    public void setY(double y) {
        locationY = y;
        birdView.setY(y);
    }

    public double getY() {
        return birdView.getY();
    }

    public double getX() {
        return birdView.getX();
    }

    public ImageView getImageView() {
        return birdView;
    }

}
