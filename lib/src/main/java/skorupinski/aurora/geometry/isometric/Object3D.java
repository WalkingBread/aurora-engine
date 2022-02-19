package skorupinski.aurora.geometry.isometric;

import skorupinski.aurora.geometry.positions.Isometric;
import skorupinski.aurora.geometry.shapes.Rectangle;
import skorupinski.aurora.geometry.shapes.Shape;
import skorupinski.aurora.graphics.Painter;
import skorupinski.aurora.math.Vector2;
import skorupinski.aurora.math.Vector3;
import skorupinski.aurora.rendering.Camera;
import skorupinski.aurora.rendering.Renderable;

import java.awt.Color;

public abstract class Object3D extends Renderable<Isometric> {

    private Vector3 rotation;

    private Isometric rotationMid;

    private Isometric mid;

    private boolean drawBoundingBox;

    private Color boundingBoxColor;

    private Color outlineColor;

    public Object3D(Isometric position, Isometric mid) {
        super(position);

        this.mid = mid;

        rotation = new Vector3();
        rotationMid = mid;

        drawBoundingBox = false;

        boundingBoxColor = Color.BLUE;
        outlineColor = Color.BLACK;
    }

    protected abstract void drawObject(Painter painter, Vector2 position, Camera camera);

    @Override
    protected void draw(Painter painter, Vector2 position, Camera camera) {
        painter.color(outlineColor);

        drawObject(painter, position, camera);
        if(drawBoundingBox) {
            
            painter.color(boundingBoxColor);
            Rectangle rect = getRectangle();
            Vector2 p = camera.getDisplayPosition(rect.position);
            rect.position = p;
    
            painter.draw(rect);
        }
    }

    public void drawBoundingBox(boolean draw) {
        drawBoundingBox = draw;
    }

    public void setBoundingBoxColor(Color color) {
        boundingBoxColor = color;
    }

    public void setOutlineColor(Color color) {
        outlineColor = color;
    }

    public void setRotationMid(Isometric rotationMid) {
        this.rotationMid = rotationMid;

        if(rotationMid == null) {
            this.rotationMid = mid;
        } 
    }

    public void rotate(Vector3 angles) {
        rotation = rotation.add(angles);
    }

    public abstract Isometric[] getInitialVertexPositions();

    public Isometric[] getVertices() {
        Isometric[] vertices = getInitialVertexPositions();

        for(Isometric v : vertices) {
            v.rotate(rotationMid, rotation);
        }

        return vertices;
    }

    public Vector3[] getVerticesVectors() {
        Isometric[] ve = getVertices();
        Vector3[] vertices = new Vector3[ve.length];

        for(int i = 0; i < ve.length; i++) {
            vertices[i] = ve[i].vector();
        }

        return vertices;
    }

    public Vector2[] getVerticesDisplayed(Camera camera) {
        Isometric[] vertices = getVertices();
        Vector2[] vertices2D = new Vector2[vertices.length];

        for(int i = 0; i < vertices.length; i++) {
            vertices2D[i] = vertices[i].toDisplayPosition(camera).vector();
        }

        return vertices2D;
    }

    public Shape getDisplayedShape(Camera camera) {
        Vector2[] vertices = getVerticesDisplayed(camera);
        return new Shape(vertices[0]) {

            @Override
            public Vector2 getMid() {
                return mid.toDisplayPosition(camera).vector();
            }

            @Override
            public Vector2[] getVertices() {
                return vertices;
            }
            
        };
    }
}
