package hi.flappybird;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

public class CollisionHandler {
    /**
     * Athugar hvort fuglinn (rétthyrningurinn) rekist á einhverja af hindrununum.
     *
     * @param obstacles Listi af öllum hindrunum sem fuglinn þarf að forðast
     * @param bird      Rétthyrningurinn sem táknar fuglinn í leiknum
     * @return true ef fuglinn rekst á hindrun, annars false
     */
    public boolean collisionDetection(ArrayList<Rectangle> obstacles , Rectangle bird){
        for (Rectangle rectangle: obstacles) {
            if(rectangle.getBoundsInParent().intersects(bird.getBoundsInParent())){
                return true;
            }
        }
        return  false;
    }
}
