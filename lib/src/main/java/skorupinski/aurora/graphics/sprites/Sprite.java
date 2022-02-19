package skorupinski.aurora.graphics.sprites;

import skorupinski.aurora.loading.ResourceManager;
import skorupinski.aurora.math.Vector2;
import skorupinski.aurora.math.Vector2i;

import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Graphics;
import java.io.IOException;
import java.nio.file.Path;

public class Sprite {
    private BufferedImage image;

    private Vector2i offset;

    public Sprite(Path path) {
        try {
            image = ResourceManager.load(BufferedImage.class, path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Sprite(BufferedImage image) {
        this.image = image;
    }

    public Sprite resize(Vector2i size) {
        Image temp = image.getScaledInstance(size.x, size.y, Image.SCALE_DEFAULT);
        BufferedImage scaled = new BufferedImage(size.x, size.y, BufferedImage.TYPE_INT_ARGB);

        Graphics g = scaled.createGraphics();
        g.drawImage(temp, 0, 0, null);
        g.dispose();

        return new Sprite(scaled);
    }

    public Sprite resizeWithProp(int height) {
        float prop = (float) height / image.getHeight();
        int width = (int) (image.getWidth() * prop);

        return resize(new Vector2i(width, height));
    }

    public Vector2i getSize() {
        return new Vector2i(
            image.getWidth(),
            image.getHeight()
        );
    }

    public void setOffset(Vector2i offset) {
        this.offset = offset;
    }

    public Vector2i getOffset() {
        return offset;
    }

    public BufferedImage raw() {
        return image;
    }
}
