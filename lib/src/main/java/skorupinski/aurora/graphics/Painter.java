package skorupinski.aurora.graphics;

import java.awt.Graphics2D;

import skorupinski.aurora.geometry.shapes.Shape;
import skorupinski.aurora.graphics.sprites.Sprite;
import skorupinski.aurora.math.Vector2;
import skorupinski.aurora.math.Vector2i;

import java.awt.Color;
import java.awt.geom.Path2D;
import java.awt.BasicStroke;

public class Painter {
    private Graphics2D graphics;

    public Painter(Graphics2D graphics) {
        this.graphics = graphics;
    }

    public void color(Color color) {
        graphics.setColor(color);
    }

    public void lineWidth(int width) {
        graphics.setStroke(new BasicStroke(width));
    }

    private Path2D getPathFor(Shape shape) {
        Vector2[] vertices = shape.getVertices();

        Vector2 first = vertices[0];

        Path2D path = new Path2D.Float();
        path.moveTo(first.x, first.y);

        for(int i = 1; i < vertices.length; i++) {
            Vector2 v = vertices[i];
            path.lineTo(v.x, v.y);

        }
        path.lineTo(first.x, first.y);
        path.closePath();

        return path;
    }

    public void draw(Shape shape) {
        Path2D path = getPathFor(shape);
        graphics.draw(path);
    }

    public void fill(Shape shape) {
        Path2D path = getPathFor(shape);
        graphics.fill(path);
    }

    public void line(Vector2i p1, Vector2i p2) {
        graphics.drawLine(p1.x, p1.y, p2.x, p2.y);
    }

    public void line(int x1, int y1, int x2, int y2) {
        graphics.drawLine(x1, y1, x2, y2);
    }

    public void sprite(Sprite sprite, Vector2i position) {
        position = position.toVector2().add(position.toVector2()).toVector2i();
        graphics.drawImage(sprite.raw(), position.x, position.y, null);
    }

    public void sprite(Sprite sprite, Vector2i position, Vector2i size) {
        position = position.toVector2().add(position.toVector2()).toVector2i();
        graphics.drawImage(sprite.resize(size).raw(), position.x, position.y, null);
    }

    public void sprite(Sprite sprite, Vector2i position, int height) {
        position = position.toVector2().add(position.toVector2()).toVector2i();
        graphics.drawImage(sprite.resizeWithProp(height).raw(), position.x, position.y, null);
    }
}
