package block;

import base.panel.GamePanel;
import utils.GameObject;
import utils.Utils;

import java.awt.*;

import static base.setting.Settings.*;

public class BlockController implements GameObject {

    GamePanel gp;

    Block currentBlock;

    public BlockController(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void update(double delta) {

    }

    @Override
    public void draw(Graphics2D g2) {

    }



    void fallBlock() {

        currentBlock.x += 1;

        for (int partY = 0; partY < MAX_BLOCK_HEIGHT; partY++) {
            for (int partX = 0; partX < MAX_BLOCK_WIDTH; partX++) {

                if (currentBlock.getPart(partX, partY) == 0)
                    continue;

                // TODO: do shit here yk
            }
        }
    }

    void spawnBlock() {

        BlockType[] allTypes = BlockType.values();
        BlockType randomType = allTypes[Utils.random.nextInt(allTypes.length)];
        currentBlock = new Block((GRID_WIDTH + MAX_BLOCK_WIDTH) / 2, MAX_BLOCK_HEIGHT / 2, randomType);
    }
}
