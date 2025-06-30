package tile;

import java.awt.*;

public enum TileID {

    EMPTY       (null),
    LIGHT_BLUE  (new Color(90, 170, 220)),
    DARK_BLUE   (new Color(50, 80, 150)),
    ORANGE      (new Color(230, 150, 80)),
    YELLOW      (new Color(220, 200, 80)),
    GREEN       (new Color(100, 200, 100)),
    PURPLE      (new Color(150, 100, 200)),
    RED         (new Color(220, 80, 90));

    final Color color;

    TileID(Color color) {
        this.color = color;
    }

    public static Color getTileColor(byte id) {
        return values()[id].color;
    }

}
