package hi.flappybird;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ObstaclesHandler {
    private AnchorPane plane;
    private double planeHeight;
    private double planeWidth;
    Random random = new Random();
    /**
     * @param plane AnchorPane sem hindranir birtast á
     * @param planeHeight reikna  rými milli pípa
     * @param planeWidth notað til aað staðsetja hindranir utan skjás
     */
    public ObstaclesHandler(AnchorPane plane, double planeHeight, double planeWidth) {
        this.plane = plane;
        this.planeHeight = planeHeight;
        this.planeWidth = planeWidth;
    }
    /**
     * Býr til tvær hindranir (efri og neðri pípu) með bili á milli.
     * Efri pípan fær "scoreZone" til að telja stig.
     *
     * @return tvær pípur
     */
    public ArrayList<Rectangle> createObstacles(){

        int width = 25;
        double xPos = planeWidth;
        double space = 200;
        double recTopHeight = random.nextInt((int)(planeHeight - space - 100)) + 50;
        double recBottomHeight = planeHeight - space - recTopHeight;


        Rectangle rectangleTop = new Rectangle(xPos,0,width,recTopHeight);
        Rectangle rectangleBottom = new Rectangle(xPos, recTopHeight + space, width, recBottomHeight);

        rectangleTop.getProperties().put("scoreZone", true);


        plane.getChildren().addAll(rectangleTop,rectangleBottom);
        return new ArrayList<>(Arrays.asList(rectangleTop,rectangleBottom));
    }
    /**
     * Færir allar hindranir til vinstri í hverjum ramma.
     * Hindranir sem eru komnar alveg út fyrir skjáinn eru fjarlægðar.
     *
     * @param obstacles allar hindranir
     */
    public void moveObstacles(ArrayList<Rectangle> obstacles){

        ArrayList<Rectangle> outOfScreen = new ArrayList<>();

        for (Rectangle rectangle: obstacles) {
            moveRectangle(rectangle, - 0.75);

            if(rectangle.getX() <= -rectangle.getWidth()){
                outOfScreen.add(rectangle);
            }
        }
        obstacles.removeAll(outOfScreen);
        plane.getChildren().removeAll(outOfScreen);
    }
    /**
     * Færir eina hindrun til vinstri
     *
     * @param rectangle Hindrunin sem á að færa
     * @param amount hversu 'langt' hún færist
     */
    private void moveRectangle(Rectangle rectangle, double amount){
        rectangle.setX(rectangle.getX() + amount);
    }
}


