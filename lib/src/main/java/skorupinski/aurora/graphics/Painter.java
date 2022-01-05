package skorupinski.aurora.graphics;

import java.awt.Graphics2D;

import skorupinski.aurora.graphics.sprites.Sprite;
import skorupinski.aurora.math.Vector2i;

import java.awt.Color;

public class Painter {
    private Graphics2D graphics;

    public Painter(Graphics2D graphics) {
        this.graphics = graphics;
    }

    public void color(Color color) {
        graphics.setColor(color);
    }

    public void background() {
        graphics.setBackground(graphics.getColor());
    }

    public void line(Vector2i p1, Vector2i p2) {
        graphics.drawLine(p1.x, p1.y, p2.x, p2.y);
    }

    public void line(int x1, int y1, int x2, int y2) {
        graphics.drawLine(x1, y1, x2, y2);
    }

    public void fillRect(Vector2i position, Vector2i size) {
        graphics.fillRect(position.x, position.y, size.x, size.y);
    }

    public void fillRect(int x, int y, int w, int h) {
        graphics.fillRect(x, y, w, h);
    }

    public void sprite(Sprite sprite, Vector2i position) {
        graphics.drawImage(sprite.raw(), position.x, position.y, null);
    }

    public void sprite(Sprite sprite, Vector2i position, Vector2i size) {
        graphics.drawImage(sprite.resize(size).raw(), position.x, position.y, null);
    }

    public void sprite(Sprite sprite, Vector2i position, int height) {
        graphics.drawImage(sprite.resizeWithProp(height).raw(), position.x, position.y, null);
    }
}
