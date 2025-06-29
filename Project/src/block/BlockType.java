package block;

import static base.setting.Settings.MAX_BLOCK_HEIGHT;
import static base.setting.Settings.MAX_BLOCK_WIDTH;

public enum BlockType {

    LIGHT_BLUE_LONG (0,0,0,0,0,
                            0,0,0,0,0,
                            0,1,1,1,1,
                            0,0,0,0,0,
                            0,0,0,0,0),

    DARK_BLUE_LEFT_L(0,0,0,0,0,
                            0,2,0,0,0,
                            0,2,2,2,0,
                            0,0,0,0,0,
                            0,0,0,0,0),

    ORANGE_RIGHT_L  (0,0,0,0,0,
                            0,0,0,3,0,
                            0,3,3,3,0,
                            0,0,0,0,0,
                            0,0,0,0,0),

    YELLOW_SQUARE   (0,0,0,0,0,
                            0,0,4,4,0,
                            0,0,4,4,0,
                            0,0,0,0,0,
                            0,0,0,0,0),

    GREEN_LEFT_Z    (0,0,0,0,0,
                            0,0,5,5,0,
                            0,5,5,0,0,
                            0,0,0,0,0,
                            0,0,0,0,0),

    PURPLE_T        (0,0,0,0,0,
                            0,0,6,0,0,
                            0,6,6,6,0,
                            0,0,0,0,0,
                            0,0,0,0,0),

    RED_RIGHT_Z     (0,0,0,0,0,
                            0,7,7,0,0,
                            0,0,7,7,0,
                            0,0,0,0,0,
                            0,0,0,0,0);

    public final byte[] partBuffer;

    BlockType(int... parts) {

        if (parts.length != (MAX_BLOCK_WIDTH * MAX_BLOCK_HEIGHT))
            throw new RuntimeException("Can NOT initialize BlockType, partBuffer does NOT have the correct length!");

        this.partBuffer = new byte[MAX_BLOCK_WIDTH * MAX_BLOCK_HEIGHT];

        for (int i = 0; i < (MAX_BLOCK_WIDTH * MAX_BLOCK_HEIGHT); i++)
            this.partBuffer[i] = (byte) parts[i];
    }
}
