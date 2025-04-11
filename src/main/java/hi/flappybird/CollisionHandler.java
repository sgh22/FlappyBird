package hi.flappybird;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class CollisionHandler {

    public boolean collisionDetection(ArrayList<ImageView> obstacles, Node bird) {
        for (ImageView obstacle : obstacles) {
            if (bird.getBoundsInParent().intersects(obstacle.getBoundsInParent())) {
                return true;
            }
        }
        return false;
    }
}

