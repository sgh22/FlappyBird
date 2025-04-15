package hi.flappybird.vinnsla;

public class SelectedBird {
    private static String selectedBird = "pink";

    public static void setSelectedBird(String bird) {

        selectedBird = bird;
    }

    public static String getSelectedBird() {
        return selectedBird;
    }
}
