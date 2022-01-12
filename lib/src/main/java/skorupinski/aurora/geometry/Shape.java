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

    protected Vector2[] getAxes() {
        Vector2[] vertices = getVertices();
        Vector2[] axes = new Vector2[vertices.length];

        for(int i = 0; i < axes.length; i++) {
            Vector2 v = new Vector2(
                vertices[i].x - vertices[i + 1 == vertices.length ? 0 : i + 1].x,
                vertices[i].y - vertices[i + 1 == vertices.length ? 0 : i + 1].y
            );
    
            axes[i] = v.normal().normalize();
        }
        return axes;
    }
}
