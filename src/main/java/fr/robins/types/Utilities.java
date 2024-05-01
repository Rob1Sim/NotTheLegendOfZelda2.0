package fr.robins.types;

import javafx.stage.Screen;
import javafx.stage.Stage;

public class Utilities {
    public static double WINDOW_WIDTH = 1920;
    public static double WINDOW_HEIGHT = 1080;
    public final static int TILE_SIZE = 96;

    public static void setFullScreen(Stage stage, boolean fullscreen) {
        if (fullscreen) {
            WINDOW_WIDTH = Screen.getPrimary().getVisualBounds().getWidth();
            WINDOW_HEIGHT = Screen.getPrimary().getVisualBounds().getHeight();
        }else {
            WINDOW_WIDTH = 1920;
            WINDOW_HEIGHT = 1080;
        }

        stage.setFullScreen(fullscreen);
    }

}
