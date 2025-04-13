package hi.flappybird.vinnsla;

public class SelectedTheme {
    private static String selectedTheme = "blue"; // default theme

    public static void setTheme(String theme) {
        selectedTheme = theme;
    }

    public static String getTheme() {
        return selectedTheme;
    }
}
