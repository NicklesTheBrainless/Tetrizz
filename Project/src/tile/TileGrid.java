package tile;

import utils.GameObject;

import java.awt.*;

import static base.setting.Settings.*;

public class TileGrid implements GameObject {

    private byte[] tileBuffer = new byte[GRID_WIDTH * GRID_HEIGHT];

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



    public byte getTile(int x, int y) {
        return tileBuffer[x + (y * GRID_WIDTH)];
    }

    public void setTile(int x, int y, byte id) {
        tileBuffer[x + (y * GRID_WIDTH)] = id;
    }

}
