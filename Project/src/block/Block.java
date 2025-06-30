package block;

import tile.TileID;
import utils.GameObject;

import java.awt.*;

import static base.setting.Settings.*;

public class Block implements GameObject {

    public int x;
    public int y;

    private byte[] partBuffer;

    public Block(int x, int y, byte[] partBuffer) {
        this.x = x;
        this.y = y;
        this.partBuffer = partBuffer;
    }

    public Block(int x, int y, BlockType type) {
        this.x = x;
        this.y = y;
        this.partBuffer = type.partBuffer.clone();
    }

    public Block copy() {
        return new Block(this.x, this.y, this.partBuffer.clone());
    }



    @Override
    public void update(double delta) {
        // not used
    }

    @Override
    public void draw(Graphics2D g2) {

        for (int partY = 0; partY < MAX_BLOCK_HEIGHT; partY++) {
            for (int partX = 0; partX < MAX_BLOCK_WIDTH; partX++) {

                int screenX = (x + partX) * TILE_SIZE;
                int screenY = (y + partY) * TILE_SIZE;

                byte partID = getPart(partX, partY);
                if (partID != 0) {
                    Color partColor = TileID.getTileColor(partID);
                    g2.setColor(partColor);
                    g2.fillRect(screenX, screenY, TILE_SIZE, TILE_SIZE);
                }

                g2.setColor(Color.LIGHT_GRAY);
                g2.drawRect(screenX, screenY, TILE_SIZE, TILE_SIZE);
            }
        }
    }



    public void rotate() {

        // to rotate something by 90° to the right do:
        // (x, y) -> (y, -x)

        byte[] rotatedPartBuffer = new byte[MAX_BLOCK_WIDTH * MAX_BLOCK_HEIGHT];

        for (int y = 0; y < MAX_BLOCK_HEIGHT; y++) {
            for (int x = 0; x < MAX_BLOCK_WIDTH; x++) {

                byte partID = getPart(partBuffer, x, y);

                int centeredX = x - BLOCK_X_CENTER;
                int centeredY = y - BLOCK_Y_CENTER;

                setPart(rotatedPartBuffer, centeredY + BLOCK_X_CENTER, -centeredX + BLOCK_Y_CENTER, partID);
            }
        }

        this.partBuffer = rotatedPartBuffer;
    }



    public byte getPart(int x, int y) {
        return partBuffer[x + (y * MAX_BLOCK_WIDTH)];
    }

    public void setPart(int x, int y, byte id) {
        partBuffer[x + (y * MAX_BLOCK_WIDTH)] = id;
    }



    static byte getPart(byte[] partBuffer, int x, int y) {
        return partBuffer[x + (y * MAX_BLOCK_WIDTH)];
    }

    static void setPart(byte[] partBuffer, int x, int y, byte id) {
        partBuffer[x + (y * MAX_BLOCK_WIDTH)] = id;
    }

}
