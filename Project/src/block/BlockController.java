package block;

import base.listeners.KeyHandler;
import base.panel.GamePanel;
import tile.TileGrid;
import utils.GameObject;
import utils.Utils;

import java.awt.*;

import static base.setting.Settings.*;

public class BlockController implements GameObject {

    GamePanel gp;
    KeyHandler keyH;
    TileGrid grid;

    Block currentBlock;
    int fallStep = 0;

    public BlockController(GamePanel gp) {
        this.gp = gp;
        this.keyH = gp.keyH;
        this.grid = gp.grid;

        spawnBlock();
    }



    @Override
    public void update(double delta) {

        partlyFallBlock();

        // rotate the block to the right
        if (keyH.wJustPressed)
            rotateBlock();

        // make the block fall faster
        if (keyH.sJustPressed)
            fallBlock();

        // move the block right or left
        if (keyH.aJustPressed)
            moveX(-1);
        if (keyH.dJustPressed)
            moveX(1);
    }

    @Override
    public void draw(Graphics2D g2) {
        currentBlock.draw(g2);
    }



    void spawnBlock() {

        BlockType[] allTypes = BlockType.values();
        BlockType randomType = allTypes[Utils.random.nextInt(allTypes.length)];
        currentBlock = new Block((GRID_WIDTH - MAX_BLOCK_WIDTH) / 2, 0, randomType);
    }



    void partlyFallBlock() {

        fallStep++;

        if (fallStep >= STANDARD_STEPS_PER_FALL)
            fallBlock();
    }

    void fallBlock() {

        Block fellBlockState = currentBlock.copy();
        fellBlockState.y++;

        boolean collides = checkCollision(fellBlockState);

        if (collides)
            onCollision();
        else
            currentBlock.y++;

        fallStep = 0;
    }

    void rotateBlock() {

        Block rotatedBlockState = currentBlock.copy();
        rotatedBlockState.rotate();

        boolean collides = checkCollision(rotatedBlockState);

        if (!collides)
            currentBlock.rotate();
    }

    void moveX(int moveX) {

        Block movedBlockState = currentBlock.copy();
        movedBlockState.x += moveX;

        boolean collision = checkCollision(movedBlockState);

        if (!collision)
            currentBlock.x += moveX;
    }



    void onCollision() {
        placeDownBlock();
        grid.evaluateLost();
        grid.evaluateClearRows();
        spawnBlock();
    }

    void placeDownBlock() {

        for (int partY = 0; partY < MAX_BLOCK_HEIGHT; partY++) {
            for (int partX = 0; partX < MAX_BLOCK_WIDTH; partX++) {

                if (currentBlock.getPart(partX, partY) == 0)
                    continue;

                byte partID = currentBlock.getPart(partX, partY);
                grid.setTile(currentBlock.x + partX, currentBlock.y + partY, partID);
            }
        }
    }



    boolean checkCollision(Block checkBlockState) {

        for (int partY = 0; partY < MAX_BLOCK_HEIGHT; partY++) {
            for (int partX = 0; partX < MAX_BLOCK_WIDTH; partX++) {

                if (checkBlockState.getPart(partX, partY) == 0)
                    continue;

                int checkX = checkBlockState.x + partX;
                int checkY = checkBlockState.y + partY;

                boolean xOutBounds = (checkX < 0) || (checkX >= GRID_WIDTH);
                boolean yOutBounds = (checkY < 0) || (checkY >= GRID_HEIGHT);
                if (xOutBounds || yOutBounds)
                    return true;

                byte tileID = grid.getTile(checkX, checkY);
                boolean hitGround = (tileID != 0);
                if (hitGround)
                    return true;
            }
        }

        return false;
    }

}
