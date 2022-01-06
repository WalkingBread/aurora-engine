package skorupinski.aurora.geometry;

import skorupinski.aurora.math.Vector2;

public class Rectangle extends Shape {

    public Vector2 size;

    public Rectangle(Vector2 position, Vector2 size) {
        super(position);
        this.size = size;
    }

    public Rectangle(float x, float y, float w, float h) {
        super(x, y);
        size = new Vector2(w, h);
    }

    public boolean collidesWith(Rectangle rect) {
        Vector2 mid1 = getMid();
        Vector2 mid2 = rect.getMid();

        if(Math.abs(mid1.x - mid2.x) <= size.x / 2 + rect.size.x / 2) {
            if(Math.abs(mid1.y - mid2.y) <= size.y / 2 + rect.size.y / 2) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean collidesWith(Vector2 point) {
        if(point.x >= position.x && point.x <= position.x + size.x) {
            if(point.y >= position.y && point.y <= position.y + size.y) {
                return true;
            }
        }
        return false;
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
