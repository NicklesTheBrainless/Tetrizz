package block;

import base.panel.GamePanel;
import tile.TileGrid;
import tile.TileID;
import utils.GameObject;
import utils.Utils;

import java.awt.*;

import static base.setting.Settings.*;

public class BlockController implements GameObject {

    GamePanel gp;
    TileGrid grid;

    Block currentBlock;

    public BlockController(GamePanel gp) {
        this.gp = gp;
        this.grid = gp.grid;

        spawnBlock();
    }

    @Override
    public void update(double delta) {

        fallBlock();
        System.out.println("fall heheh");
        System.out.println(currentBlock.x + "   " + currentBlock.y);
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



    void fallBlock() {

        boolean hitGround = false;

        for (int partY = 0; partY < MAX_BLOCK_HEIGHT; partY++) {
            for (int partX = 0; partX < MAX_BLOCK_WIDTH; partX++) {

                if (currentBlock.getPart(partX, partY) == 0)
                    continue;

                int checkX = currentBlock.x + partX;
                int checkY = currentBlock.y + partY + 1;

                if (checkY >= GRID_HEIGHT) {
                    hitGround = true;
                    break;
                }

                byte tileID = grid.getTile(checkX, checkY);
                hitGround = (tileID != 0);
                if (hitGround)
                    break;
            }
        }

        if (hitGround)
            onGroundHit();
        else
            currentBlock.y++;
    }

    void spawnBlock() {

        BlockType[] allTypes = BlockType.values();
        BlockType randomType = allTypes[Utils.random.nextInt(allTypes.length)];
        currentBlock = new Block((GRID_WIDTH - MAX_BLOCK_WIDTH) / 2, MAX_BLOCK_HEIGHT / 2 - 1, randomType);
    }



    void onGroundHit() {

        for (int partY = 0; partY < MAX_BLOCK_HEIGHT; partY++) {
            for (int partX = 0; partX < MAX_BLOCK_WIDTH; partX++) {

                if (currentBlock.getPart(partX, partY) == 0)
                    continue;

                byte partID = currentBlock.getPart(partX, partY);
                grid.setTile(currentBlock.x + partX, currentBlock.y + partY, partID);
            }
        }

        checkLost();
        spawnBlock();
    }

    void checkLost() {

        boolean lost = false;
        for (int checkX = 0; checkX < GRID_WIDTH; checkX++) {

            lost = (grid.getTile(checkX, 0) != 0);
            if (lost)
                break;
        }

        if (lost)
            gp.lost = true;
    }

}
