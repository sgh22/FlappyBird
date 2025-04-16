package hi.flappybird.vinnsla;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;


public class BirdMovement {

    private ImageView bird;
    private int jumpHeight;
    private CollisionHandler collisionHandler = new CollisionHandler();

    private double velocity = 0;
    private double gravity = 0.3;
    private double lift = -6;

    /**
     * @param bird       ImageView sem táknar fuglinn
     * @param jumpHeight Hæðin sem fuglinn hoppar upp þegar ýtt er á space
     */
    public BirdMovement(ImageView bird, int jumpHeight) {
        this.bird = bird;
        this.jumpHeight = jumpHeight;
    }

    public void fly() {
        velocity = lift;
    }

    public void applyGravity() {
        velocity += gravity;
        if (velocity > 10) velocity = 10;
        moveBirdY(velocity);
    }

    /**
     * @param positionChange Hversu mikið fuglinn á að færast upp eða niður
     */
    public void moveBirdY(double positionChange) {
        bird.setLayoutY(bird.getLayoutY() + positionChange);
    }

    /**
     * @param obstacles Listi af öllum hindrunum í leiknum
     * @param plane     Heildar leiksvæðið (AnchorPane)
     * @return true ef fuglinn rekst á hindrun eða dettur af skjánum, annars false
     */
    public boolean isBirdDead(ArrayList<ImageView> obstacles, AnchorPane plane) {
        double birdY = bird.getLayoutY() + bird.getY();

        if (collisionHandler.collisionDetection(obstacles, bird)) {
            return true;
        }

        return birdY >= plane.getHeight();
    }
}


