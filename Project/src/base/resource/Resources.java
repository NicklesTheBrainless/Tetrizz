package base.resource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Resources {

    public static final BufferedImage ICON = getImage("/icon.png");

    private static BufferedImage getImage(String filePath) {
        try {
            return ImageIO.read(Objects.requireNonNull(Resources.class.getResourceAsStream(filePath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
