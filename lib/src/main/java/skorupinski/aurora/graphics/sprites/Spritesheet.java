package skorupinski.aurora.graphics.sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

import skorupinski.aurora.loading.ResourceManager;

public class Spritesheet {

    private BufferedImage raw;
    private Sprite[][] sprites;
    
    public Spritesheet(Path path, int spriteWidth, int spriteHeight) {
        try {
            raw = ResourceManager.load(BufferedImage.class, path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int columns = raw.getWidth() / spriteWidth;
        int rows = raw.getHeight() / spriteHeight;

        sprites = new Sprite[rows][columns];

        for(int i = 0; i < columns; i++) {
            for(int j = 0; j < rows; j++) {
                int x = i * spriteWidth;
                int y = j * spriteHeight;

                BufferedImage image = raw.getSubimage(x, y, spriteWidth, spriteHeight);
                sprites[j][i] = new Sprite(image);
            }
        }
    }

    public Sprite[][] getSprites() {
        return sprites;
    }

    public Sprite[] getSpritesAt(int row) {
        return sprites[row];
    }

    public Sprite getSpriteAt(int row, int column) {
        return sprites[row][column];
    }

}
