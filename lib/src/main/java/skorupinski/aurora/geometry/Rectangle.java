package skorupinski.aurora.geometry;

import skorupinski.aurora.math.Vector2;

public class Rectangle extends Polygon {

    public Vector2 size;

    public Rectangle(Vector2 position, Vector2 size) {
        super(position);
        this.size = size;
    }

    public Rectangle(float x, float y, float w, float h) {
        super(x, y);
        size = new Vector2(w, h);
    }

    @Override
    public Vector2 getMid() {
        return new Vector2(
            position.x + size.x / 2,
            position.y + size.y / 2
        );      
    }

    @Override
    public Vector2[] getVertices() {
        return new Vector2[] {
            position,
            position.add(new Vector2(size.x, 0)),
            position.add(size),
            position.add(new Vector2(0, size.y))
        };
    }
}
