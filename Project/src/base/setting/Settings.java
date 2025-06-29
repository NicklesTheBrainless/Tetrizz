package base.setting;

import java.awt.*;

public abstract class Settings {

    // WINDOW
    public static final String TITLE = "Tetrizz";

    // FPS
    public static final int SETTING_MAX_FPS = 6;

    // TILE GRID
    public static final int GRID_WIDTH = 10;
    public static final int GRID_HEIGHT = 16;
    public static final int TILE_SIZE = 32;

    // BLOCK
    public static final int MAX_BLOCK_WIDTH = 4;
    public static final int MAX_BLOCK_HEIGHT = 4;
    public static final int BLOCK_X_CENTER = 1;
    public static final int BLOCK_Y_CENTER = 1;

    // SCREEN
    public static final int SCREEN_WIDTH  = GRID_WIDTH * TILE_SIZE;
    public static final int SCREEN_HEIGHT = GRID_HEIGHT * TILE_SIZE;
    public static final Color BACKGROUND_COLOR = new Color(30, 32, 42);

}
