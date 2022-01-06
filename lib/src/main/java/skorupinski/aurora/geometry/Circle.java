package skorupinski.aurora.geometry;

import skorupinski.aurora.math.Vector2;

public class Circle extends Shape {

    public float radius;

    private int vertexNumber;

    private static final int VERTEX_NUMBER_RATIO = 2;

    public Circle(Vector2 position, float radius) {
        super(position);
        this.radius = radius;

        vertexNumber = (int) radius * VERTEX_NUMBER_RATIO;
    }

    public Circle(float x, float y, float radius) {
        super(x, y);
        this.radius = radius;

        vertexNumber = (int) radius * VERTEX_NUMBER_RATIO;
    } 

    @Override
    public boolean collidesWith(Vector2 point) {
        return false;
    }

    @Override
    public Vector2 getMid() {
        return position;
    }

    @Override
    public Vector2[] getVertices() {
        Vector2[] vertices = new Vector2[vertexNumber];

        for(int i = 0; i < vertexNumber; i++) {
            double angle = Math.toRadians(((double) i / vertexNumber) * 360); 

            vertices[i] = new Vector2(
                position.x + (float) (Math.cos(angle) * radius), 
                position.y + (float) (Math.sin(angle) * radius)
            );
        }

        return vertices;
    }
    
}
