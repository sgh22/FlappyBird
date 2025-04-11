package hi.flappybird;

import java.util.prefs.Preferences;

public class HighScore {
    private static final String HIGHSCORE_KEY = "highscore";
    private static final Preferences prefs = Preferences.userNodeForPackage(HighScore.class);

    public static int getHighScore() {
        return prefs.getInt(HIGHSCORE_KEY, 0);
    }

    public static void setHighScore(int score) {
        prefs.putInt(HIGHSCORE_KEY, score);
    }
}

