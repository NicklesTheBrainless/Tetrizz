package base.panel;

import base.listeners.KeyHandler;
import block.BlockController;
import tile.TileGrid;

import java.awt.*;

import static base.setting.Settings.*;

public class GamePanel extends BasePanel {

    // input listeners
    public KeyHandler keyH = new KeyHandler();

    public TileGrid grid = new TileGrid(this);
    public BlockController blockControl = new BlockController(this);

    public boolean lost = false;

    int timeStep = 0;
    public int fallSpeedup = 0;

    public GamePanel() {

        super(SETTING_MAX_FPS);

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(BACKGROUND_COLOR);

        this.setDoubleBuffered(true);
        this.setFocusable(true);

        this.addKeyListener(keyH);
    }



    @Override
    protected void update(double delta) {

        if (lost)
            return;

        if ((STANDARD_STEPS_PER_FALL - fallSpeedup) > MINIMUM_STEPS_PER_FALL)
            timeStep++;

        if (timeStep >= STEPS_PER_SPEEDUP) {
            fallSpeedup++;
            timeStep = 0;
        }


        blockControl.update(delta);

        // update input listeners
        keyH.update();
    }

    @Override
    protected void draw(Graphics2D g2) {

        grid.draw(g2);
        blockControl.draw(g2);

        if (lost) {
            g2.setColor(new Color(20, 20, 20));
            g2.fillRoundRect((int) (2.8 * TILE_SIZE), 4, (int) (4.6 * TILE_SIZE), (int) (1.2 * TILE_SIZE), 8, 8);
            g2.setColor(Color.WHITE);
            g2.setFont(new Font(Font.DIALOG, Font.BOLD, TILE_SIZE));
            g2.drawString("You lost!", 3 * TILE_SIZE, TILE_SIZE);
        }


        g2.setStroke(new BasicStroke(4));
        g2.setColor(Color.RED);
        g2.drawLine(0, LOSE_TILE_Y * TILE_SIZE, SCREEN_WIDTH, LOSE_TILE_Y * TILE_SIZE);
        g2.setStroke(new BasicStroke(1));
    }

}