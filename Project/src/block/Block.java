package block;

import static base.setting.Settings.*;

public class Block {

    public int x;
    public int y;

    private byte[] partBuffer;

    public Block(int x, int y, BlockType type) {
        this.x = x;
        this.y = y;
        this.partBuffer = type.partBuffer;
    }



    public void rotateRight() {

        // to rotate something by 90° to the right do:
        // (x, y) -> (y, -x)

        byte[] rotatedPartBuffer = new byte[MAX_BLOCK_WIDTH * MAX_BLOCK_HEIGHT];

        for (int y = 0; y < MAX_BLOCK_HEIGHT; y++) {
            for (int x = 0; x < MAX_BLOCK_WIDTH; x++) {

                byte partID = getPart(partBuffer, x, y);
                setPart(rotatedPartBuffer, x, -y, partID);
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



    public static byte getPart(byte[] partBuffer, int x, int y) {
        return partBuffer[x + (y * MAX_BLOCK_WIDTH)];
    }

    public static void setPart(byte[] partBuffer, int x, int y, byte id) {
        partBuffer[x + (y * MAX_BLOCK_WIDTH)] = id;
    }

}
