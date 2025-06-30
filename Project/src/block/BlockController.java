package block;

import base.listeners.KeyHandler;
import base.panel.GamePanel;
import tile.TileGrid;
import tile.TileID;
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
        
        if (keyH.wJustPressed)
            currentBlock.rotateRight();
        
        
    }

    @Override
    public void draw(Graphics2D g2) {
        for (int partY = 0; partY < MAX_BLOCK_HEIGHT; partY++) {
            for (int partX = 0; partX < MAX_BLOCK_WIDTH; partX++) {

                int screenX = (currentBlock.x + partX) * TILE_SIZE;
                int screenY = (currentBlock.y + partY) * TILE_SIZE;

                byte partID = currentBlock.getPart(partX, partY);
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



    void spawnBlock() {

        BlockType[] allTypes = BlockType.values();
        BlockType randomType = allTypes[Utils.random.nextInt(allTypes.length)];
        currentBlock = new Block((GRID_WIDTH - MAX_BLOCK_WIDTH) / 2, MAX_BLOCK_HEIGHT / 2 - 1, randomType);
    }



    void partlyFallBlock() {

        if (fallStep >= STEPS_PER_FALL)
            fallBlock();
    }

    void fallBlock() {

        boolean hitsGround = checkHitsGround();

        if (hitsGround)
            onGroundHit();
        else
            currentBlock.y++;

        fallStep = 0;
    }



    void onGroundHit() {

        placeDownBlock();

        boolean lost = checkLost();
        if (lost)
            gp.lost = true;

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



    boolean checkHitsGround() {

        for (int partY = 0; partY < MAX_BLOCK_HEIGHT; partY++) {
            for (int partX = 0; partX < MAX_BLOCK_WIDTH; partX++) {

                if (currentBlock.getPart(partX, partY) == 0)
                    continue;

                int checkX = currentBlock.x + partX;
                int checkY = currentBlock.y + partY + 1;

                if (checkY >= GRID_HEIGHT)
                    return true;

                byte tileID = grid.getTile(checkX, checkY);
                boolean hitGround = (tileID != 0);
                if (hitGround)
                    return true;
            }
        }

        return false;
    }

    boolean checkLost() {

        for (int checkX = 0; checkX < GRID_WIDTH; checkX++) {

            boolean lost = (grid.getTile(checkX, 0) != 0);
            if (lost)
                return true;
        }

        return false;
    }

}
