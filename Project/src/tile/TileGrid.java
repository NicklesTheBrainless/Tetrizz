package tile;

import base.panel.GamePanel;
import utils.GameObject;

import java.awt.*;

import static base.setting.Settings.*;

public class TileGrid implements GameObject {

    GamePanel gp;

    private final byte[] tileBuffer = new byte[GRID_WIDTH * GRID_HEIGHT];

    public TileGrid(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void update(double delta) {
        // not used
    }

    @Override
    public void draw(Graphics2D g2) {

        for (int y = 0; y < GRID_HEIGHT; y++) {
            for (int x = 0; x < GRID_WIDTH; x++) {

                byte tileID = getTile(x, y);
                if (tileID != 0) {
                    Color tileColor = TileID.getTileColor(tileID);
                    g2.setColor(tileColor);
                    g2.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }

                g2.setColor(Color.LIGHT_GRAY);
                g2.drawRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
    }



    public void evaluateClearRows() {

        for (int y = LOSE_TILE_Y; y < GRID_HEIGHT;  ) {

            boolean fullRow = checkFullRow(y);
            if (fullRow) {
                fallRowsAbove(y);
                clearRow(y);
            } else {
                y++;
            }
        }
    }

    void fallRowsAbove(int yFromRow) {

        for (int y = yFromRow; y >= LOSE_TILE_Y; y--) {
            for (int x = 0; x < GRID_WIDTH; x++) {

                byte tileID = getTile(x, y);
                setTile(x, y, (byte) 0);
                setTile(x, y + 1, tileID);
            }
        }
    }

    void clearRow(int rowY) {
        for (int x = 0; x < GRID_WIDTH; x++)
            setTile(x, rowY, (byte) 0);
    }

    boolean checkFullRow(int rowY) {

        for (int x = 0; x < GRID_WIDTH; x++) {
            boolean empty = (getTile(x, rowY) == 0);
            if (empty)
                return false;
        }

        return true;
    }



    public void evaluateLost() {
        boolean lost = checkLost();
        if (lost)
            gp.lost = true;
    }

    boolean checkLost() {

        for (int checkX = 0; checkX < GRID_WIDTH; checkX++) {

            boolean lost = (getTile(checkX, LOSE_TILE_Y) != 0);
            if (lost)
                return true;
        }

        return false;
    }



    public byte getTile(int x, int y) {
        return tileBuffer[x + (y * GRID_WIDTH)];
    }

    public void setTile(int x, int y, byte id) {
        tileBuffer[x + (y * GRID_WIDTH)] = id;
    }

}
