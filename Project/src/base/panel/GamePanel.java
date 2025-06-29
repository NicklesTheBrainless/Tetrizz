package base.panel;

import base.listeners.KeyHandler;
import block.BlockController;
import tile.TileGrid;

import java.awt.*;

import static base.setting.Settings.*;

public class GamePanel extends BasePanel {

    // input listeners
    public KeyHandler keyH = new KeyHandler();

    public TileGrid grid = new TileGrid();
    public BlockController blockControl = new BlockController(this);

    public boolean lost = false;

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

        blockControl.update(delta);

        // update input listeners
        keyH.update();
    }

    @Override
    protected void draw(Graphics2D g2) {

        grid.draw(g2);
        blockControl.draw(g2);

        if (lost)
            g2.drawString("You lost!", 0, 0);

        g2.setColor(Color.RED);
        g2.drawLine(0, LOSE_TILE_Y * TILE_SIZE, SCREEN_WIDTH, LOSE_TILE_Y * TILE_SIZE);
    }

}