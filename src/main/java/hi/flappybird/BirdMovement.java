package hi.flappybird;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class BirdMovement {

    private Rectangle bird;
    private int jumpHeight;
    CollisionHandler collisionHandler = new CollisionHandler();

    /**
     * Constructor sem tengir fuglinn og setur hversu hátt hann hoppar.
     *
     * @param bird      Rétthyrningurinn sem táknar fuglinn
     * @param jumpHeight Hæðin sem fuglinn hoppar upp þegar ýtt er á space
     */

    public BirdMovement(Rectangle bird, int jumpHeight) {
        this.bird = bird;
        this.jumpHeight = jumpHeight;
    }

    /**
     * Fuglinn hoppar upp (notað þegar ýtt er á space).
     * Ef hann er nálægt toppnum á skjánum, þá hoppar hann ekki út fyrir skjáinn.
     */

    public void fly() {
        double movement = -jumpHeight;
        double currentY = bird.getLayoutY();

        if (currentY <= jumpHeight) {
            movement = -currentY;
        }

        moveBirdY(movement);
    }

    /**
     * Færir fuglinn lóðrétt (notað bæði fyrir þyngdarafl og hoppa).
     *
     * @param positionChange Hversu mikið fuglinn á að færast upp eða niður
     */

    public void moveBirdY(double positionChange) {
        bird.setLayoutY(bird.getLayoutY() + positionChange);
    }
    /**
     * Athugar hvort fuglinn sé dauður.
     * Hann deyr ef hann rekst á hindrun eða dettur niður fyrir skjáinn.
     *
     * @param obstacles Listi af öllum hindrunum í leiknum
     * @param plane     Heildar leiksvæðið (AnchorPane)
     * @return true ef fuglinn rekst á hindrun eða dettur af skjánum, annars false
     * @see CollisionHandler#collisionDetection(ArrayList, Rectangle)
     */

    public boolean isBirdDead(ArrayList<Rectangle> obstacles, AnchorPane plane){
        double birdY = bird.getLayoutY() + bird.getY();

        if(collisionHandler.collisionDetection(obstacles, bird)){
            return  true;
        }

        return birdY >= plane.getHeight();
    }
}
