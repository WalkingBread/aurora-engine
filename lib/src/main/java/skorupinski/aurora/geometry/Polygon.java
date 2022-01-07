package skorupinski.aurora.geometry;

import skorupinski.aurora.math.Vector2;

public abstract class Polygon extends Shape {

    protected Polygon(Vector2 position) {
        super(position);
    }

    protected Polygon(float x, float y) {
        super(x, y);
    }

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
