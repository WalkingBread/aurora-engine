package skorupinski.aurora.geometry.isometric;

import skorupinski.aurora.geometry.Rectangle;
import skorupinski.aurora.geometry.positions.Isometric;
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

    public Object3D(Isometric position, Isometric mid) {
        super(position);

        this.mid = mid;

        rotation = new Vector3();
        rotationMid = mid;

        drawBoundingBox = false;
    }

    protected abstract void drawObject(Painter painter, Vector2 position, Camera camera);

    @Override
    protected void draw(Painter painter, Vector2 position, Camera camera) {

        drawObject(painter, position, camera);
        if(drawBoundingBox) {
            
            painter.color(Color.BLUE);
            Rectangle rect = getRectangle();
            Vector2 p = camera.getDisplayPosition(rect.position);
            rect.position = p;
    
            painter.draw(rect);
        }
    }

    public void drawBoundingBox(boolean draw) {
        drawBoundingBox = draw;
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


}
