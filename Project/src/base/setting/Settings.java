package base.setting;

import java.awt.*;

public abstract class Settings {

    // WINDOW
    public static final String TITLE = "Tetrizz";

    // FPS
    public static final int SETTING_MAX_FPS = 12;

    // TILE GRID
    public static final int GRID_WIDTH = 10;
    public static final int GRID_HEIGHT = 20;
    public static final int TILE_SIZE = 32;

    // BLOCK
    public static final int MAX_BLOCK_WIDTH = 5;
    public static final int MAX_BLOCK_HEIGHT = 5;
    public static final int BLOCK_X_CENTER = 2;
    public static final int BLOCK_Y_CENTER = 2;

    // SPEEDUP
    public static final int STANDARD_STEPS_PER_FALL = 12;
    public static final int MINIMUM_STEPS_PER_FALL = 4;
    public static final int STEPS_PER_SPEEDUP = 25 * SETTING_MAX_FPS;

    // SCREEN
    public static final int SCREEN_WIDTH  = GRID_WIDTH * TILE_SIZE;
    public static final int SCREEN_HEIGHT = GRID_HEIGHT * TILE_SIZE;
    public static final Color BACKGROUND_COLOR = new Color(30, 32, 42);

    // LOSE
    public static final int LOSE_TILE_Y = 4;

}
