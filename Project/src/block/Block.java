package block;

import static base.setting.Settings.*;

public class Block {

    private byte[] partBuffer;

    public Block(byte... partBuffer) {

        if (partBuffer.length != (MAX_BLOCK_WIDTH * MAX_BLOCK_HEIGHT))
            throw new RuntimeException("Part-Buffer is NOT correct length while initializing Block Object");

        this.partBuffer = partBuffer;
    }



    public void rotateRight() {

        // to rotate something 90° to the right do:
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



    public byte getPart(byte[] partBuffer, int x, int y) {
        return partBuffer[x + (y * MAX_BLOCK_WIDTH)];
    }

    public void setPart(byte[] partBuffer, int x, int y, byte id) {
        partBuffer[x + (y * MAX_BLOCK_WIDTH)] = id;
    }

}
