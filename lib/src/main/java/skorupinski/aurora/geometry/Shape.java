package skorupinski.aurora.geometry;

import skorupinski.aurora.math.Vector2;

public abstract class Shape {

    public Vector2 position;

    protected Shape(Vector2 position) {
        this.position = position;
    }

    protected Shape(float x, float y) {
        position = new Vector2(x, y);
    }

    public boolean collidesWith(Shape shape) {
        return CollisionDetector.collide(this, shape);
    }

    public boolean collidesWith(Vector2 point) {
        return CollisionDetector.collide(this, new Point(point));
    }

    public abstract Vector2 getMid();

    public abstract Vector2[] getVertices();

    protected abstract Vector2[] getAxes();
}
